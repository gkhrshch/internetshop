package mate.academy.internetshop.service.impl;

import java.sql.SQLException;
import java.util.List;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.ItemService;
import org.apache.log4j.Logger;

@Service
public class ItemServiceImpl implements ItemService {
    private static Logger logger = Logger.getLogger(ItemServiceImpl.class);

    @Inject
    private static ItemDao itemDao;

    @Override
    public Item create(Item item) {
        try {
            return itemDao.create(item);
        } catch(SQLException e) {
            logger.warn("Item creation failed", e);
        }
        return null;
    }

    @Override
    public Item get(Long id) {
        return itemDao.get(id);
    }

    @Override
    public List<Item> getAllItems(){
        try {
            return itemDao.getAll();
        } catch (SQLException e) {
            logger.warn("Get all items failed", e);
        }
        return null;
    }

    @Override
    public Item update(Item item) {
        return itemDao.update(item);
    }

    @Override
    public Item delete(Long id) {
        return itemDao.delete(id);
    }
}
