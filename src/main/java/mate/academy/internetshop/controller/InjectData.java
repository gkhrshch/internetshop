package mate.academy.internetshop.controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.UserService;
import mate.academy.internetshop.util.HashUtil;
import org.apache.log4j.Logger;

public class InjectData extends HttpServlet {
    @Inject
    private static UserService userService;
    @Inject
    private static OrderService orderService;
    @Inject
    private static BucketService bucketService;
    @Inject
    private static ItemService itemService;

    private static Logger logger = Logger.getLogger(InjectData.class);

    public static void injectData() throws IllegalAccessException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Item item1 = new Item("item1", 1.11);
        Item item2 = new Item("item2", 2.22);
        Item item3 = new Item("item3", 3.33);
        itemService.create(item1);
        itemService.create(item2);
        itemService.create(item3);

        User user = new User();
        user.setName("1");
        user.setSurname("1");
        user.addRole(Role.of("USER"));
        user.setLogin("1");
        user.setPassword("1");
        byte[] salt = HashUtil.getSalt();
        user.setSalt(salt);
        String userHashedPassword = HashUtil.hashPassword(user.getPassword(), salt);
        user.setPassword(userHashedPassword);
        user.setOrders(new ArrayList<>());
        Bucket newBucket = new Bucket();
        newBucket.setItems(new ArrayList<>());
        newBucket.setUser(user);
        Bucket bucket = bucketService.create(newBucket);
        user.setBucket(bucket);
        userService.create(user);

        User admin = new User();
        admin.setName("admin");
        admin.setSurname("admin");
        admin.addRole(Role.of("ADMIN"));
        admin.setLogin("admin");
        admin.setPassword("admin");
        salt = HashUtil.getSalt();
        admin.setSalt(salt);
        String adminHashedPassword = HashUtil.hashPassword(admin.getPassword(), salt);
        admin.setPassword(adminHashedPassword);
        userService.create(admin);
        resp.sendRedirect(req.getContextPath() + "/servlet/index");
    }
}
