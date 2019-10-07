package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.RoleDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.exceptions.AuthenticationException;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import org.apache.log4j.Logger;

@Dao
public class UserDaoJdbcImpl extends AbstractDao<User> implements UserDao {
    private static Logger logger = Logger.getLogger(UserDaoJdbcImpl.class);
    private static String DB_NAME = "internetshop";

    @Inject
    private static BucketDao bucketDao;
    @Inject
    private static RoleDao roleDao;

    public UserDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public User create(User user) {
        String query = "INSERT INTO " + DB_NAME
                + ".users (name, surname, login, password, token) VALUES (?, ?, ?, ?, ?);";
        try (PreparedStatement statement
                     = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getToken());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Add user failed, no ID obtained.");
                }
                bucketDao.create(new Bucket(user.getId()));
                addRoles(user, user.getRoles());
            }
        } catch (SQLException e) {
            logger.error("Can't create user", e);
            return null;
        }
        return user;
    }



    @Override
    public void addRoles(User user, Set<Role> roles) {
        String getRoleIdQuery = "SELECT * FROM "
                + DB_NAME + ".roles WHERE role_name = ?;";
        String addRoleQuery = "INSERT INTO "
                + DB_NAME + ".roles_users (role_id, user_id) VALUES (?, ?);";
        try (PreparedStatement getRoleIdStmt = connection.prepareStatement(getRoleIdQuery);
                 PreparedStatement addRoleStmt
                         = connection.prepareStatement(addRoleQuery)) {
            for (Role role: roles) {
                getRoleIdStmt.setString(1, String.valueOf(role.getRoleName()));
                ResultSet resultSetCreate = getRoleIdStmt.executeQuery();
                Long roleId = null;
                if (resultSetCreate.next()) {
                    roleId = resultSetCreate.getLong("role_id");
                }
                addRoleStmt.setLong(1, roleId);
                addRoleStmt.setLong(2, user.getId());
                addRoleStmt.executeUpdate();
            }
        } catch (SQLException e) {
            logger.error("Can't add Role", e);
        }
    }

    @Override
    public Optional<User> get(Long id) {
        User user = null;
        String query = "SELECT * FROM `" + DB_NAME + "`.`users` WHERE user_id=" + id + ";";
        try (PreparedStatement statement
                     = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("user_id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setLogin(resultSet.getString("login"));
                user.setToken(resultSet.getString("token"));
            }
        } catch (SQLException e) {
            logger.error("Can't get user by ID", e);
        }
        return Optional.of(user);
    }

    @Override
    public User update(User user) {
        String query = "UPDATE " + DB_NAME + ".users "
                + "SET name = ?, surname = ?, login = ?, password = ? "
                + "WHERE (user_id = ?);";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPassword());
            statement.setLong(5, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't update user", e);
        }
        return user;
    }

    @Override
    public User delete(Long id) {
        User user = get(id).get();
        String query = "DELETE FROM " + DB_NAME
                + ".users WHERE user_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete user", e);
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        User user = null;
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM " + DB_NAME + ".users;";
        try (PreparedStatement statement
                     = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                user = getUserFromResultSet(resultSet);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            logger.error("Get all users failed", e);
        }
        return users;
    }

    @Override
    public Optional<User> login(String login, String password) throws AuthenticationException {
        User user = new User();
        String query = "SELECT * FROM " + DB_NAME + ".users WHERE login=? AND password=?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = getUserFromResultSet(resultSet);
            } else {
                throw new AuthenticationException("Incorrect login or password");
            }
        } catch (SQLException e) {
            logger.error("Can't get user by ID", e);
        }
        return Optional.of(user);
    }

    @Override
    public Optional<User> getByToken(String token) {
        User user = new User();
        String query = "SELECT * FROM " + DB_NAME + ".users WHERE token=?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, token);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Can't get user by token", e);
        }
        return Optional.of(user);
    }

    private  User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("user_id"));
        user.setName(resultSet.getString("name"));
        user.setSurname(resultSet.getString("surname"));
        user.setLogin(resultSet.getString("login"));
        user.setToken(resultSet.getString("token"));
        user.setRoles(roleDao.getRoles(get(user.getId()).get()));
        return user;
    }
}
