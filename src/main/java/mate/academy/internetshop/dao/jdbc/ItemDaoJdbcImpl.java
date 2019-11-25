package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.model.Item;
import org.apache.log4j.Logger;

public class ItemDaoJdbcImpl extends AbstractDao<Item> implements ItemDao {
    private static Logger logger = Logger.getLogger(ItemDaoJdbcImpl.class);
    private static String DB_NAME = "internetshop";

    public ItemDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Item create(Item item) {
        String query = "INSERT INTO " + DB_NAME
                + ".items (`name`, `price`) VALUES ( ?, ?);";
        try (PreparedStatement statement
                    = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            String name = item.getName();
            Double price = item.getPrice();
            statement.setString(1, name);
            statement.setDouble(2, price);
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                item.setId(resultSet.getLong(1));
            }  else {
                throw new SQLException("Item add failed, no ID obtained.");
            }
        } catch (SQLException e) {
            logger.error("Can't create item", e);
        }
        return item;
    }

    @Override
    public Optional<Item> get(Long id) {
        String query = "SELECT * FROM " + DB_NAME + ".items WHERE item_id=?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                long itemId = resultSet.getLong("item_id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                Item item = new Item(itemId);
                item.setName(name);
                item.setPrice(price);
                return Optional.of(item);
            }
        } catch (SQLException e) {
            logger.error("Can't get item by id=" + id);
        }
        return Optional.empty();
    }

    @Override
    public Item update(Item item) {
        String query = "UPDATE " + DB_NAME
                + ".items SET name=? WHERE item_id=?;";
        try (PreparedStatement statement = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, item.getName());
            statement.setLong(1, item.getId());
            ResultSet resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            logger.error("Item update failed", e);
        }
        return get(item.getId()).get();
    }

    @Override
    public Item delete(Long id) {
        Item item = get(id).get();
        String query = "DELETE FROM " + DB_NAME
                + ".items WHERE item_id=?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Item delete failed", e);
        }
        return item;
    }

    @Override
    public List<Item> getAll() {
        List<Item> items = new ArrayList<>();
        Statement statement = null;
        String query = "SELECT * FROM " + DB_NAME + ".items;";
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                long itemId = resultSet.getLong("item_id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                Item item = new Item(itemId);
                item.setName(name);
                item.setPrice(price);
                items.add(item);
            }
            return items;
        } catch (SQLException e) {
            logger.error("Can't get items list", e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.error("Can't close statement", e);
                }
            }
        }
        return items;
    }
}
