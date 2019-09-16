package mate.academy.internetshop;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.UserService;

public class Main {

    @Inject
    private static UserService userService;
    @Inject
    private static OrderService orderService;
    @Inject
    private static BucketService bucketService;
    @Inject
    private static ItemService itemService;

    static {
        try {
            Injector.injectDependencies();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Item item1 = new Item("item1", 1.11);
        Item item2 = new Item("item2", 2.22);
        Item item3 = new Item("item3", 3.33);
        itemService.create(item1);
        itemService.create(item2);
        itemService.create(item3);
        System.out.println("Item by id 0: " + itemService.get(0L).toString());

        User user1 = new User("username");
        userService.create(user1);
        System.out.println("User by id 0:" + userService.get(0L).toString());

        bucketService.create(user1.getBucket());
        bucketService.addItem(user1.getBucket().getId(), item1.getId());
        bucketService.addItem(user1.getBucket().getId(), item2.getId());
        System.out.println(bucketService.getAllItems(user1.getBucket().getId()));
        bucketService.clear(user1.getBucket().getId());
        System.out.println(bucketService.getAllItems(user1.getBucket().getId()));
        bucketService.addItem(user1.getBucket().getId(), item1.getId());

        orderService.completeOrder(bucketService.getAllItems(user1.getBucket().getId()),
                user1.getId());
        System.out.println(user1.getOrders());
    }
}
