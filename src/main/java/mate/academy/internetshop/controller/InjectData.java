package mate.academy.internetshop.controller;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Item;
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
    }
}
