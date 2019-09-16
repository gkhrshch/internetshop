package mate.academy.internetshop.dao.impl;

import java.util.NoSuchElementException;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Order;

@Dao
public class OrderDaoImpl implements OrderDao {

    @Override
    public Order create(Order order) {
        Storage.orders.add(order);
        return order;
    }

    @Override
    public Order get(Long id) {
        return Storage.orders
                .stream()
                .filter(o -> o.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(
                "Can't find order with id " + id));
    }

    @Override
    public Order update(Order toUpdate) {
        Order order = get(toUpdate.getId());
        order.setItems(toUpdate.getItems());
        return order;
    }

    @Override
    public Order delete(Long id) {
        Order order = get(id);
        Storage.orders.removeIf(o -> o.getId().equals(id));
        return order;
    }
}
