package mate.academy.internetshop;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.InjectorOld;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.UserService;
import org.apache.log4j.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(InjectorOld.class);

    @Inject
    private static UserService userService;
    @Inject
    private static OrderService orderService;
    @Inject
    private static BucketService bucketService;
    @Inject
    private static ItemService itemService;

    static  {
        try {
            InjectorOld.injectDependencies();
        } catch (IllegalAccessException e) {
            logger.error(e);
        }
    }

    public static void main(String[] args) {

        Item item1 = new Item("ABitem", 1.11);
        Item item2 = new Item("BCitem", 2.22);
        Item item3 = new Item("BAitem", 3.33);
        itemService.create(item1);
        itemService.create(item2);
        itemService.create(item3);
        logger.debug("Item by id 0: " + itemService.get(0L).toString());
        logger.debug("getAllItems method with built-in alphabetical sort:\n"
                + itemService.getAllItems().toString() + "\n");

        User user1 = new User("username");
        userService.create(user1);
        logger.debug("User by id 0:" + userService.get(0L).toString());

        //Uncomment after refactoring user model with private Bucket
        //        bucketService.create(user1.getBucket());
        //        bucketService.addItem(user1.getBucket().getId(), item1.getId());
        //        bucketService.addItem(user1.getBucket().getId(), item2.getId());
        //        logger.debug(bucketService.getAllItems(user1.getBucket().getId()));
        //        bucketService.clear(user1.getBucket().getId());
        //        logger.debug(bucketService.getAllItems(user1.getBucket().getId()));
        //        bucketService.addItem(user1.getBucket().getId(), item1.getId());
        //        bucketService.addItem(user1.getBucket().getId(), item1.getId());
        //        logger.debug(bucketService.getAllItems(user1.getBucket().getId()));
        //        bucketService.removeItem(user1.getBucket().getId(), item1.getId());
        //        orderService.completeOrder(user1.getBucket());
        //        logger.debug(user1.getOrders());
    }
}
