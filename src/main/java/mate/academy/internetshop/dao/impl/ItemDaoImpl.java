package mate.academy.internetshop.dao.impl;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Item;

import java.util.Iterator;
import java.util.NoSuchElementException;

@Dao
public class ItemDaoImpl implements ItemDao {
    @Override
    public Item create(Item item) {
        Storage.items.add(item);
    }

    @Override
    public Item get(Long id) {
        return Storage.items
                .stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElseThrow(NoSuchElementException::new("Can't find item with id " + id));
    }

    @Override
    public Item update(Item item) {
        return null;
    }

    @Override
    public Item delete(Long id) {
        Item item = get(id);
        Storage.items.removeIf(item -> item.getId().equals(id));
        return item;
    }

    @Override
    public Item delete(Item item) {
        Storage.items.removeIf(item -> item.equals(item));
        return item;
    }
}
