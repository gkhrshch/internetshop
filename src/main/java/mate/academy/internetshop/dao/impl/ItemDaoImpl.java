package mate.academy.internetshop.dao.impl;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.model.Item;

import java.util.NoSuchElementException;

public class ItemDaoImpl implements ItemDao {
    @Override
    public Item create(Item item) {
        return null;
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
    public void delete(Long id) {

    }

    @Override
    public void deleteByItem(Item item) {

    }
}
