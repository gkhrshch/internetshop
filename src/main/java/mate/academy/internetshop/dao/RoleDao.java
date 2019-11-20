package mate.academy.internetshop.dao;

import java.util.Set;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;

@Dao
public interface RoleDao {

    Set<Role> getRoles(User user);
}
