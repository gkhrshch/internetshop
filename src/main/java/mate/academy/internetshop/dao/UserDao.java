package mate.academy.internetshop.dao;

import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.User;

@Dao
public interface UserDao {

    User create(User user);

    User get(Long id);

    User update(User user);

    User delete(Long id);
}
