package mate.academy.internetshop.service.impl;

import java.util.List;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Inject
    private static OrderDao orderDao;
    @Inject
    private static UserDao userDao;

    @Override
    public Order create(Order order) {
        return orderDao.create(order);
    }

    @Override
    public Order get(Long id) {
        return orderDao.get(id).get();
    }

    @Override
    public Order update(Order order) {
        return orderDao.update(order);
    }

    @Override
    public void delete(Long id) {
        orderDao.delete(id);
    }

    @Override
    public Order completeOrder(List<Item> items, Long userId) {
        Order order = new Order();
        order.setItems(items);
        User user = userDao.get(userId).get();
        order.setUserId(user.getId());
        order.setUser(user);
        orderDao.create(order);
        user.getOrders().add(order);
        userDao.update(user);
        return order;
    }

    @Override
    public Order completeOrder(Bucket bucket) {
        Order order = new Order(bucket.getUser().getId(), bucket.getItems());
        return orderDao.create(order);
    }
}
