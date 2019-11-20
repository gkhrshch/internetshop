package mate.academy.internetshop.dao;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Item;

@Dao
public interface ItemDao {

    Item create(Item item);

    Optional<Item> get(Long id);

    Item update(Item item);

    Item delete(Long id);

    List<Item> getAll();
}
