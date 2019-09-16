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
        Class<UserDaoImpl> userDaoImplClass = UserDaoImpl.class;
        Class<OrderDaoImpl> orderDaoImplClass = OrderDaoImpl.class;
        Class<ItemDaoImpl> itemDaoImplClass = ItemDaoImpl.class;
        Class<BucketDaoImpl> bucketDaoImplClass = BucketDaoImpl.class;
        Class<UserServiceImpl> userServiceImplClass = UserServiceImpl.class;
        Class<OrderServiceImpl> orderServiceImplClass = OrderServiceImpl.class;
        Class<ItemServiceImpl> itemServiceImplClass = ItemServiceImpl.class;
        Class<BucketServiceImpl> bucketServiceImplClass = BucketServiceImpl.class;
        for (Field field : fields) {
            if (field.getDeclaredAnnotation(Inject.class) != null
                    && field.getType().equals(UserDao.class)) {
                if (userDaoImplClass.getDeclaredAnnotation(Dao.class) != null) {
                    field.setAccessible(true);
                    field.set(null, Factory.getUserDao());
                }
            }
            if (field.getDeclaredAnnotation(Inject.class) != null
                    && field.getType().equals(OrderDao.class)) {
                if (orderDaoImplClass.getDeclaredAnnotation(Dao.class) != null) {
                    field.setAccessible(true);
                    field.set(null, Factory.getOrderDao());
                }
            }
            if (field.getDeclaredAnnotation(Inject.class) != null
                    && field.getType().equals(ItemDao.class)) {
                if (itemDaoImplClass.getDeclaredAnnotation(Dao.class) != null) {
                    field.setAccessible(true);
                    field.set(null, Factory.getItemDao());
                }
            }
            if (field.getDeclaredAnnotation(Inject.class) != null
                    && field.getType().equals(BucketDao.class)) {
                if (bucketDaoImplClass.getDeclaredAnnotation(Dao.class) != null) {
                    field.setAccessible(true);
                    field.set(null, Factory.getBucketDao());
                }
            }
            if (field.getDeclaredAnnotation(Inject.class) != null
                    && field.getType().equals(UserService.class)) {
                if (userServiceImplClass.getDeclaredAnnotation(Service.class) != null) {
                    field.setAccessible(true);
                    field.set(null, Factory.getUserService());
                }
            }
            if (field.getDeclaredAnnotation(Inject.class) != null
                    && field.getType().equals(OrderService.class)) {
                if (orderServiceImplClass.getDeclaredAnnotation(Service.class) != null) {
                    field.setAccessible(true);
                    field.set(null, Factory.getOrderService());
                }
            }
            if (field.getDeclaredAnnotation(Inject.class) != null
                    && field.getType().equals(ItemService.class)) {
                if (itemServiceImplClass.getDeclaredAnnotation(Service.class) != null) {
                    field.setAccessible(true);
                    field.set(null, Factory.getItemService());
                }
            }
            if (field.getDeclaredAnnotation(Inject.class) != null
                    && field.getType().equals(BucketService.class)) {
                if (bucketServiceImplClass.getDeclaredAnnotation(Service.class) != null) {
                    field.setAccessible(true);
                    field.set(null, Factory.getBucketService());
                }
            }
        }

    }
}
