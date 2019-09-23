package mate.academy.internetshop.dao.impl;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Item;

@Dao
public class ItemDaoImpl implements ItemDao {

    @Override
    public Item create(Item item) {
        Storage.items.add(item);
        return  item;
    }

    @Override
    public Item get(Long id) {
        return Storage.items
                .stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(
                "Can't find item with id " + id));
    }

    @Override
    public Item update(Item toUpdate) {
        Item item = get(toUpdate.getId());
        item.setName(toUpdate.getName());
        return item;
    }

    @Override
    public Item delete(Long id) {
        Item item = get(id);
        Storage.items.remove(item);
        return item;
    }

    @Override
    public Item delete(Item item) {
        Storage.items.remove(item);
        return item;
    }

    @Override
    public List<Item> getAll() {
        List<Item> allItems = Storage.items.stream()
                .distinct()
                .collect(Collectors.toList());
        Collections.sort(allItems);
        return allItems;
    }
}
