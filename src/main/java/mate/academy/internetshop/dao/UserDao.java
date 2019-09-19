package mate.academy.internetshop.dao;

import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.User;

import java.util.List;

@Dao
public interface UserDao {

    User create(User user);

    User get(Long id);

    User update(User user);

    User delete(Long id);

    List<User> getAll();
}
