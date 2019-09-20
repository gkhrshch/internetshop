package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.NoSuchElementException;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.User;

@Dao
public class UserDaoImpl implements UserDao {
    @Override
    public User create(User user) {
        Storage.users.add(user);
        return user;
    }

    @Override
    public User get(Long id) {
        return Storage.users
                .stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(
                "Can't find user with id " + id));
    }

    @Override
    public User update(User toUpdate) {
        User user = get(toUpdate.getId());
        //user.setBucket(toUpdate.getBucket());    uncomment after bucket bound to user is fixed in webapp
        user.setName(toUpdate.getName());
        user.setOrders(toUpdate.getOrders());
        return user;
    }

    @Override
    public User delete(Long id) {
        User user = get(id);
        Storage.users.remove(user);
        return user;
    }

    @Override
    public List<User> getAll() {
        return Storage.users;
    }
}
