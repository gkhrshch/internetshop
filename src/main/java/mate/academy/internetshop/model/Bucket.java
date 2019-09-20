package mate.academy.internetshop.model;

import java.util.ArrayList;
import java.util.List;
import mate.academy.internetshop.IdGenerator;

public class Bucket {
    private final Long id;
    private List<Item> items;
    private /*final*/ Long userId;
    //UNCOMMENT FINAL after creating healthy bucket service
    // with buckets ONLY bound to users in webapp

    public Bucket() {
        this.id = IdGenerator.getBucketId();
        this.items = new ArrayList<>();
    }

    public Bucket(Long userId) {
        this.id = IdGenerator.getBucketId();
        this.userId = userId;
        this.items = new ArrayList<>();
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
}
