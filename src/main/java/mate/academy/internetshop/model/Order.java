package mate.academy.internetshop.model;

import java.util.List;

public class Order {
    private Long id;
    private List<Item> items;
    private Long userId;

    public Order(Long userId, List<Item> items) {
        this.userId = userId;
        this.items = items;
    }

    public Order(Long id, List<Item> items, Long userId) {
        this.id = id;
        this.items = items;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
