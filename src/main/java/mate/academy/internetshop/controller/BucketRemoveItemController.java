package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.service.BucketService;

public class BucketRemoveItemController extends HttpServlet {
    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String itemId = req.getParameter("item_id");
        String bucketId = req.getParameter("bucket_id");
        bucketService.removeItem(Long.parseLong(bucketId), Long.parseLong(itemId));
        req.getRequestDispatcher("/WEB-INF/views/bucketAccess.jsp").forward(req, resp);
    }
}
