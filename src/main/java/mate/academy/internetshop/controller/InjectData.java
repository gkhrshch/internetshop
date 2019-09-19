package mate.academy.internetshop.controller;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.UserService;

public class InjectData {
    @Inject
    private static UserService userService;
    @Inject
    private static OrderService orderService;
    @Inject
    private static BucketService bucketService;
    @Inject
    private static ItemService itemService;

    public static void injectData() throws IllegalAccessException {
        Item item1 = new Item("hleb", 1.11);
        Item item2 = new Item("arbuz", 2.22);
        Item item3 = new Item("mivina", 3.33);
        itemService.create(item1);
        itemService.create(item2);
        itemService.create(item3);
        userService.create(new User("username"));
        userService.create(new User("username"));
        Bucket bucket1 = new Bucket();
        Bucket bucket2 = new Bucket();
        bucketService.create(bucket1);
        bucketService.create(bucket2);
        bucketService.addItem(bucket1.getId(), item1.getId());
        bucketService.addItem(bucket1.getId(), item2.getId());
        bucketService.addItem(bucket1.getId(), item3.getId());
        bucketService.addItem(bucket2.getId(), item1.getId());
    }
}
