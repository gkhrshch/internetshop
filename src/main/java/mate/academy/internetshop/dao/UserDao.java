package mate.academy.internetshop.dao;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.exceptions.AuthenticationException;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.User;

@Dao
public interface UserDao {

    User create(User user);

    Optional<User> get(Long id);

    User update(User user);

    User delete(Long id);

    List<User> getAll();

    Optional<User> login(String login, String password) throws AuthenticationException;

    Optional<User> getByToken(String token);
}
