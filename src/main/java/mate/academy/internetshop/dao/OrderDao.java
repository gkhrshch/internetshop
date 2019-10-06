package mate.academy.internetshop.dao;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Order;

@Dao
public interface OrderDao {

    Order create(Order order);

    Optional<Order> get(Long id);

    Order update(Order order);

    Order delete(Long id);

    List<Item> getItems(Long orderId);

    List<Order> getAllOrdersByUserId(Long id);
}
