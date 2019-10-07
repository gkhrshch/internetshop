package mate.academy.internetshop.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.UserService;

public class BucketRemoveItemController extends HttpServlet {
    @Inject
    private static BucketService bucketService;
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession(true).getAttribute("userId");
        Bucket bucket = bucketService.getBucketByUserId(userId);
        String itemId = req.getParameter("item_id");
        bucketService.removeItem(bucket.getId(), Long.parseLong(itemId));
        req.setAttribute("user", userService.get(userId).get().getName());
        List<Item> items = bucketService.getAllItems(bucket);
        req.setAttribute("item", items);
        req.getRequestDispatcher("/WEB-INF/views/bucketAccess.jsp").forward(req, resp);
    }
}
