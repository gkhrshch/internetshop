package mate.academy.internetshop.dao;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import mate.academy.internetshop.exceptions.AuthenticationException;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;

public interface UserDao {

    User create(User user);

    void addRoles(User user, Set<Role> roles);

    Optional<User> get(Long id);

    User update(User user);

    User delete(Long id);

    List<User> getAll();

    Optional<User> login(String login, String password) throws AuthenticationException;

    Optional<User> getByToken(String token);
}
