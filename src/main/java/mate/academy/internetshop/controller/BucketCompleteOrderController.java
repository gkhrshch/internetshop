package mate.academy.internetshop.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.UserService;

public class BucketCompleteOrderController extends HttpServlet {
    @Inject
    private static OrderService orderService;
    @Inject
    private static BucketService bucketService;
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession(true).getAttribute("userId");
        Long bucketId = bucketService.getBucketByUserId(userId).getId();
        orderService.completeOrder(bucketService.getAllItems(bucketService.get(bucketId)), userId);
        bucketService.clear(bucketId);
        List<Order> orders = userService.getOrders(userId);
        req.setAttribute("name", userService.get(userId).get().getName());
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/WEB-INF/views/orders.jsp").forward(req, resp);
    }

}
