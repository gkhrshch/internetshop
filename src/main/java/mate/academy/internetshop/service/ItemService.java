package mate.academy.internetshop.service;

import java.util.List;
import mate.academy.internetshop.model.Item;

public interface ItemService {

    Item create(Item item);

    Item get(Long id);

    List<Item> getAllItems();

    Item update(Item item);

    Item delete(Long id);
}
