package mate.academy.internetshop.dao;

import java.sql.SQLException;
import java.util.List;
import mate.academy.internetshop.model.Item;

public interface ItemDao {

    Item create(Item item) throws SQLException;

    Item get(Long id);

    Item update(Item item);

    Item delete(Long id);

    List<Item> getAll() throws SQLException;
}
