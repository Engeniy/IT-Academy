package ru.mail.krivonos.project_jd1.repository.impl;

import ru.mail.krivonos.project_jd1.repository.UserRepository;
import ru.mail.krivonos.project_jd1.repository.exceptions.UserRepositoryException;
import ru.mail.krivonos.project_jd1.repository.model.PermissionsEnum;
import ru.mail.krivonos.project_jd1.repository.model.Role;
import ru.mail.krivonos.project_jd1.repository.model.RolesEnum;
import ru.mail.krivonos.project_jd1.repository.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private static UserRepository instance;

    private UserRepositoryImpl() {
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepositoryImpl();
        }
        return instance;
    }

    @Override
    public void add(Connection connection, User user) throws UserRepositoryException {
        String sql = "INSERT INTO User (email, surname, name, password, role_id) VALUES (?, ?, ?, ?, (SELECT r.id FROM Role r WHERE r.name = ?))";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getRole().getName());
            int added = preparedStatement.executeUpdate();
            System.out.println("-------- " + added + " User Added --------");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new UserRepositoryException(e);
        }
    }

    @Override
    public void updateInfo(Connection connection, User user) throws UserRepositoryException {
        String sql = "UPDATE User SET surname = ?, name = ? WHERE email = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getSurname());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getEmail());
            int updated = preparedStatement.executeUpdate();
            System.out.println("-------- " + updated + " User Info Updated --------");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new UserRepositoryException(e);
        }
    }

    @Override
    public void updateEmail(Connection connection, Long id, String email) throws UserRepositoryException {
        String sql = "UPDATE User SET email = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            preparedStatement.setLong(2, id);
            int updated = preparedStatement.executeUpdate();
            System.out.println("-------- " + updated + " User Email Updated --------");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new UserRepositoryException(e);
        }
    }

    @Override
    public void updatePassword(Connection connection, String email, String oldPassword, String newPassword) throws UserRepositoryException {
        String sql = "UPDATE User SET password = ? WHERE email = ? AND password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, oldPassword);
            int updated = preparedStatement.executeUpdate();
            System.out.println("-------- " + updated + " User Password Updated --------");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new UserRepositoryException(e);
        }
    }

    @Override
    public void deleteByID(Connection connection, Long id) throws UserRepositoryException {
        String sql = "DELETE * FROM User WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            int deleted = preparedStatement.executeUpdate();
            System.out.println("-------- " + deleted + " User Deleted --------");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new UserRepositoryException(e);
        }
    }

    @Override
    public User findUserByEmail(Connection connection, String email) throws UserRepositoryException {
        String sql = "SELECT u.id, u.password, u.role_id, r.name as role_name, p.name as permission_name FROM User u JOIN Role r " +
                "ON u.role_id = r.id JOIN Role_Permission rp " +
                "ON r.id = rp.role_id JOIN Permission p ON rp.permission_id = p.id WHERE u.email = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                User user = getUserForLogin(resultSet);
                return user;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new UserRepositoryException(e);
        }
    }

    @Override
    public User findUserByID(Connection connection, Long id) throws UserRepositoryException {
        String sql = "SELECT surname, name, email FROM User WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            User user;
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                user = getUserForInfo(resultSet);
            }
            return user;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new UserRepositoryException(e);
        }
    }

    private User getUserForInfo(ResultSet resultSet) throws UserRepositoryException {
        User user = new User();
        try {
            if (resultSet.next()) {
                user.setSurname(resultSet.getString("surname"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
            }
            return user;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new UserRepositoryException(e);
        }
    }

    private User getUserForLogin(ResultSet resultSet) throws UserRepositoryException {
        try {
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setPassword(resultSet.getString("password"));
                Role role = new Role();
                role.setId(resultSet.getLong("role_id"));
                role.setName(resultSet.getString("role_name"));
                PermissionsEnum permission = PermissionsEnum.valueOf(resultSet.getString("permission_name"));
                List<PermissionsEnum> permissionsList = new ArrayList<>();
                permissionsList.add(permission);
                role.setPermissions(permissionsList);
                user.setRole(role);
                return user;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new UserRepositoryException(e);
        }
        return null;
    }
}
