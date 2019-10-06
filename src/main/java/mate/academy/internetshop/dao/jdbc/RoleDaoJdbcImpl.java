package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import mate.academy.internetshop.dao.RoleDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import org.apache.log4j.Logger;

@Dao
public class RoleDaoJdbcImpl extends AbstractDao<Role> implements RoleDao {
    private static Logger logger = Logger.getLogger(RoleDaoJdbcImpl.class);
    private static String DB_NAME = "internetshop";

    public RoleDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Set<Role> getRoles(User user) {
        Set<Role> roles = new HashSet<>();
        String query = "SELECT internetshop.roles.role_name "
                + "FROM " + DB_NAME + ".roles "
                + "INNER JOIN " + DB_NAME + ".roles_users "
                + "ON " + DB_NAME + ".roles_users.role_id=" + DB_NAME + ".roles.role_id "
                + "WHERE " + DB_NAME + ".roles_users.user_id=?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, user.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String roleString = resultSet.getString("role_name");
                Role role = Role.of(roleString);
                roles.add(role);
            }
        } catch (SQLException e) {
            logger.error("Can't get roles", e);
        }
        return roles;
    }
}
