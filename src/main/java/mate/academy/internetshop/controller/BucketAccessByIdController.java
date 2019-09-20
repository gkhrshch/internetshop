package mate.academy.internetshop.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.BucketService;

public class BucketAccessByIdController extends HttpServlet {
    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/bucketAccess.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String bucketId = req.getParameter("id");
        List<Item> items = bucketService.getAllItems(Long.parseLong(bucketId));
        req.setAttribute("bucketId", bucketId);
        req.setAttribute("item", items);
        req.getRequestDispatcher("/WEB-INF/views/bucketAccess.jsp").forward(req, resp);
    }
}
