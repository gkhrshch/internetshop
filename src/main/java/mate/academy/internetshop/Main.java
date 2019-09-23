package mate.academy.internetshop;

import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.UserService;
import org.apache.log4j.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Injector.class);

    @Inject
    private static ItemService itemService;
    @Inject
    private static UserService userService;
    @Inject
    private static OrderService orderService;
    @Inject
    private static BucketService bucketService;

    static {
        try {
            Injector.injectDependency();
        } catch (IllegalAccessException e) {
            logger.error(e);
        }
    }

    public static void main(String[] args) {
        Item item1 = new Item("arbuz", 1.11);
        Item item2 = new Item("hleb", 2.22);
        User user1 = new User("username");
        Storage.items.add(item1);
        logger.debug(itemService.create(item1));
    }
}
