package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;
import org.apache.log4j.Logger;

public class OrderDaoJdbcImpl extends AbstractDao<Order> implements OrderDao {
    private static Logger logger = Logger.getLogger(OrderDaoJdbcImpl.class);
    private static String DB_NAME = "internetshop";

    public OrderDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Inject
    private static UserDao userDao;

    @Override
    public Order create(Order order) {
        String addOrderQuery = "INSERT INTO " + DB_NAME
                + ".orders (user_id) VALUES (?);";
        String addItemsQuery = "INSERT INTO " + DB_NAME
                + ".orders_items (order_id, item_id) VALUES (?, ?);";
        try (PreparedStatement addOrderStmt
                     = connection.prepareStatement(addOrderQuery, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement addItemsStmt
                         = connection.prepareStatement(addItemsQuery)) {
            addOrderStmt.setLong(1, order.getUser().getId());
            addOrderStmt.executeUpdate();
            ResultSet keys = addOrderStmt.getGeneratedKeys();
            if (keys.next()) {
                order.setId(keys.getLong(1));
            }
            addItemsStmt.setLong(1, order.getId());
            for (Item item : order.getItems()) {
                addItemsStmt.setLong(2, item.getId());
                addItemsStmt.executeUpdate();
            }
        } catch (SQLException e) {
            logger.error("Can't add new item", e);
        }
        return order;
    }

    @Override
    public Optional<Order> get(Long id) {
        String query = "SELECT * FROM " + DB_NAME
                + ".orders WHERE order_id = ?;";
        Order order = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long orderId = resultSet.getLong("order_id");
                Long userId = resultSet.getLong("user_id");
                User user = userDao.get(userId).orElseThrow();
                List<Item> items = getItems(orderId);
                order = new Order(user.getId(), items);
            }
        } catch (SQLException e) {
            logger.error("Get order by id failed", e);
        }
        return Optional.of(order);
    }

    @Override
    public Order update(Order order) {
        String addOrderQuery = "UPDATE " + DB_NAME + ".orders set user_id WHERE order_id =?;";
        String addItemsQuery = "UPDATE orders_items SET item_id = ? WHERE order_id=?;";
        try (PreparedStatement addOrderStmt
                     = connection.prepareStatement(addOrderQuery, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement addItemsStmt
                         = connection.prepareStatement(addItemsQuery)) {
            addOrderStmt.setLong(1, order.getUserId());
            addOrderStmt.executeUpdate();
            ResultSet generatedKeys = addOrderStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                order.setId(generatedKeys.getLong(1));
            }
            for (Item item : order.getItems()) {
                addItemsStmt.setLong(1, item.getId());
                addItemsStmt.executeUpdate();
            }
        } catch (SQLException e) {
            logger.error("Can't add new item", e);
        }
        return order;
    }

    @Override
    public void delete(Long id) {
        String deleteOrdersItemsQuery = "DELETE FROM "
                + DB_NAME + ".orders_items WHERE order_id = ?;";
        String deleteOrdersQuery = "DELETE FROM "
                + DB_NAME + ".orders WHERE order_id = ?;";
        try (PreparedStatement ordersItemsStmt
                     = connection.prepareStatement(deleteOrdersItemsQuery);
                 PreparedStatement ordersStmt
                         = connection.prepareStatement(deleteOrdersQuery)) {
            ordersItemsStmt.setLong(1, id);
            ordersStmt.setLong(1, id);
            ordersItemsStmt.executeUpdate();
            ordersStmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete order with id = " + id, e);
        }
    }

    @Override
    public List<Item> getItems(Long id) {
        List<Item> items = new ArrayList<>();
        String query = "SELECT " + DB_NAME
                + ".items.item_id, name, price "
                + "FROM items, orders_items "
                + "WHERE orders_items.item_id=items.item_id "
                + "AND orders_items.order_id=?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long itemId = resultSet.getLong("items.item_id");
                String name = resultSet.getString("name");
                Double price = resultSet.getDouble("price");
                Item item = new Item(name, price);
                item.setId(itemId);
                items.add(item);
            }
        } catch (SQLException e) {
            logger.error("Get items from order error", e);
            return null;
        }
        return items;
    }

    @Override
    public List<Order> getAllOrdersByUserId(Long id) {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT " + DB_NAME + ".orders.order_id FROM orders WHERE user_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long orderId = resultSet.getLong("order_id");
                Order order = get(orderId).get();
                order.setId(orderId);
                orders.add(order);
            }
        } catch (SQLException e) {
            logger.error("Can't get orders for user with id = " + id, e);
        }
        return orders;
    }
}
