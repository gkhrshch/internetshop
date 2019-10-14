package mate.academy.internetshop.controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;
import mate.academy.internetshop.service.UserService;
import mate.academy.internetshop.util.HashUtil;

public class RegistrationController extends HttpServlet {
    @Inject
    private static UserService userService;
    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        bucketService.create(new Bucket());
        req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User newUser = new User();
        newUser.setName(req.getParameter("user_name"));
        newUser.setLogin(req.getParameter("login"));
        newUser.setPassword(req.getParameter("psw"));
        String password = req.getParameter("psw");
        byte[] salt = HashUtil.getSalt();
        newUser.setSalt(salt);
        String hashedPassword = HashUtil.hashPassword(password, salt);
        newUser.setPassword(hashedPassword);
        newUser.setSurname(req.getParameter("user_surname"));
        newUser.addRole(Role.of("USER")); // TODO: Role role = roleService.getRoleByName("USER").get(); newUser.addRole(role)
        Bucket newBucket = new Bucket();
        newBucket.setItems(new ArrayList<>());


        User user = userService.create(newUser);
        newBucket.setUser(user);
        Bucket bucket = bucketService.create(newBucket);
        user.setBucket(bucket);

        HttpSession session = req.getSession(true);
        session.setAttribute("userId", user.getId());

        Cookie cookie = new Cookie("MATE", user.getToken());
        resp.addCookie(cookie);
        resp.sendRedirect(req.getContextPath() + "/servlet/index");
    }
}
