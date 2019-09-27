package mate.academy.internetshop.service;

import java.sql.SQLException;
import java.util.List;
import mate.academy.internetshop.model.Item;

public interface ItemService {

    Item create(Item item) throws SQLException;

    Item get(Long id);

    List<Item> getAllItems() throws SQLException;

    Item update(Item item);

    Item delete(Long id);
}
