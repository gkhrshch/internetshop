package mate.academy.internetshop.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

    @Inject
    private static ItemDao itemDao;

    @Override
    public Item create(Item item) {
        return itemDao.create(item);
    }

    @Override
    public Item get(Long id) {
        return itemDao.get(id);
    }

    @Override
    public List<Item> getAllItems() {
        List<Item> allItems = Storage.items.stream()
                                    .distinct()
                                    .collect(Collectors.toList());
        Collections.sort(allItems);
        return allItems;
    }

    @Override
    public Item update(Item item) {
        return itemDao.update(item);
    }

    @Override
    public Item delete(Long id) {
        return itemDao.delete(id);
    }

    @Override
    public Item delete(Item item) {
        return itemDao.delete(item);
    }
}
