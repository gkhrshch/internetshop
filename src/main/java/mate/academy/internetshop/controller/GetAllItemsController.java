package mate.academy.internetshop.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.ItemService;
import org.apache.log4j.Logger;

public class GetAllItemsController extends HttpServlet {
    private static Logger logger = Logger.getLogger(GetAllItemsController.class);

    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Item> items = null;
        items = itemService.getAllItems();

        List<Item> allItems = items.stream()
                .distinct()
                .collect(Collectors.toList());
        Collections.sort(allItems);
        req.setAttribute("item", allItems);
        req.getRequestDispatcher("/WEB-INF/views/allItems.jsp").forward(req, resp);
    }
}
