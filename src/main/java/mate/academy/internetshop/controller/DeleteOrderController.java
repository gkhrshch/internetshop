package mate.academy.internetshop.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.UserService;

public class DeleteOrderController extends HttpServlet {
    @Inject
    private static OrderService orderService;
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long orderId = Long.parseLong(req.getParameter("order_id"));
        Long userId = (Long) req.getSession(true).getAttribute("userId");
        Order toDelete = orderService.get(orderId);
        List<Order> userOrders = userService.getOrders(userId);
        userOrders.remove(toDelete);
        orderService.delete(orderId);
        resp.sendRedirect(req.getContextPath() + "/servlet/orderAccess");
    }
}
