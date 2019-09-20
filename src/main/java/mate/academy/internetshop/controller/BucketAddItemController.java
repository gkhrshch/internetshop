package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.service.BucketService;

public class BucketAddItemController extends HttpServlet {
    @Inject
    private static BucketService bucketService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        bucketService.addItem(Long.parseLong(req.getParameter("bucket_id")),
                Long.parseLong(req.getParameter("item_id")));
        resp.sendRedirect(req.getContextPath() + "/servlet/getAllItems");
    }
}
