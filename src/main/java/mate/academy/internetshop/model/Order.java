package mate.academy.internetshop.model;

import java.util.List;
import mate.academy.internetshop.IdGenerator;

public class Order {
    private final Long id;
    private List<Item> items;
    private final Long userId;

    public Order(Long userId, List<Item> items) {
        this.id = IdGenerator.getOrderId();
        this.userId = userId;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Order{"
                + "id=" + id
                + ", items=" + items
                + ", user id=" + userId
                + '}';
    }
}
