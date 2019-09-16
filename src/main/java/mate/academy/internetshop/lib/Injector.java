package mate.academy.internetshop.lib;

import java.lang.reflect.Field;
import mate.academy.internetshop.Factory;
import mate.academy.internetshop.Main;
import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.dao.impl.BucketDaoImpl;
import mate.academy.internetshop.dao.impl.ItemDaoImpl;
import mate.academy.internetshop.dao.impl.OrderDaoImpl;
import mate.academy.internetshop.dao.impl.UserDaoImpl;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.UserService;
import mate.academy.internetshop.service.impl.BucketServiceImpl;
import mate.academy.internetshop.service.impl.ItemServiceImpl;
import mate.academy.internetshop.service.impl.OrderServiceImpl;
import mate.academy.internetshop.service.impl.UserServiceImpl;

public class Injector {

    public static void injectDependencies() throws IllegalAccessException {
        inject(UserServiceImpl.class.getDeclaredFields());
        inject(OrderServiceImpl.class.getDeclaredFields());
        inject(ItemServiceImpl.class.getDeclaredFields());
        inject(BucketServiceImpl.class.getDeclaredFields());
        inject(Main.class.getDeclaredFields());
    }

    private static void inject(Field[] fields) throws IllegalAccessException {
        for (Field field : fields) {
            if (field.getDeclaredAnnotation(Inject.class) != null) {
                if (field.getType().equals(UserDao.class)
                        && UserDaoImpl.class.getDeclaredAnnotation(Dao.class) != null) {
                    field.setAccessible(true);
                    field.set(null, Factory.getUserDao());
                }
                if (field.getType().equals(OrderDao.class)
                        && OrderDaoImpl.class.getDeclaredAnnotation(Dao.class) != null) {
                    field.setAccessible(true);
                    field.set(null, Factory.getOrderDao());
                }
                if (field.getType().equals(ItemDao.class)
                        && ItemDaoImpl.class.getDeclaredAnnotation(Dao.class) != null) {
                    field.setAccessible(true);
                    field.set(null, Factory.getItemDao());
                }
                if (field.getType().equals(BucketDao.class)
                        && BucketDaoImpl.class.getDeclaredAnnotation(Dao.class) != null) {
                    field.setAccessible(true);
                    field.set(null, Factory.getBucketDao());
                }
                if (field.getType().equals(UserService.class)
                        && UserServiceImpl.class.getDeclaredAnnotation(Service.class) != null) {
                    field.setAccessible(true);
                    field.set(null, Factory.getUserService());
                }
                if (field.getType().equals(OrderService.class)
                        && OrderServiceImpl.class.getDeclaredAnnotation(Service.class) != null) {
                    field.setAccessible(true);
                    field.set(null, Factory.getOrderService());
                }
                if (field.getType().equals(ItemService.class)
                        && ItemServiceImpl.class.getDeclaredAnnotation(Service.class) != null) {
                    field.setAccessible(true);
                    field.set(null, Factory.getItemService());
                }
                if (field.getType().equals(BucketService.class)
                        && BucketServiceImpl.class.getDeclaredAnnotation(Service.class) != null) {
                    field.setAccessible(true);
                    field.set(null, Factory.getBucketService());
                }
            }
        }
    }
}
