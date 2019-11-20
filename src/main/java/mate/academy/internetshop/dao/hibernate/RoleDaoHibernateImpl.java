package mate.academy.internetshop.dao.hibernate;

import java.util.HashSet;
import java.util.Set;
import mate.academy.internetshop.dao.RoleDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.util.HibernateUtil;
import org.hibernate.Session;

@Dao
public class RoleDaoHibernateImpl implements RoleDao {
    @Override
    public Set<Role> getRoles(User user) {
        Set<Role> roles = new HashSet<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            roles = user.getRoles();
            return roles;
        }
    }
}
