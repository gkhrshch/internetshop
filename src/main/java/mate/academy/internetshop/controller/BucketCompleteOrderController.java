package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.OrderService;

public class BucketCompleteOrderController extends HttpServlet {
    @Inject
    private static OrderService orderService;
    @Inject
    private static BucketService bucketService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long bucketId = Long.parseLong(req.getParameter("bucket_id"));
        Long userId = Long.parseLong(req.getParameter("user_id"));
        orderService.completeOrder(bucketService.getAllItems(bucketId), userId);
        resp.sendRedirect(req.getContextPath() + "/servlet/bucketAccess");
    }
}
