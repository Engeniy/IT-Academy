package ru.mail.krivonos.al.project_jd1.repository.impl;

import ru.mail.krivonos.al.project_jd1.repository.UserRepository;
import ru.mail.krivonos.al.project_jd1.repository.model.Permissions;
import ru.mail.krivonos.al.project_jd1.repository.model.Role;
import ru.mail.krivonos.al.project_jd1.repository.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public void add(Connection connection, User user) {
        String sql = "INSERT INTO User (email, surname, name, password, role_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setLong(5, user.getRole().getId());
            preparedStatement.executeUpdate();
            System.out.println("-------- User Successfully Added --------");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Connection connection, User user) {
        String sql = "UPDATE User SET surname = ?, name = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getSurname());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setLong(3, user.getId());
            preparedStatement.executeUpdate();
            System.out.println("-------- User Successfully Updated --------");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteByID(Connection connection, Long id) {
        String sql = "DELETE * FROM User WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("-------- User Successfully Deleted --------");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findUserByEmail(Connection connection, String email) {
        String sql = "SELECT u.*, r.name, p.name as permission_name FROM User u JOIN Role r ON u.role_id = r.id INNER JOIN Role_Permission rp " +
                "ON (r.id, rp.role_id) INNER JOIN Permission p ON (rp.permission_id, p.id) WHERE u.email = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            User user;
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                user = getUser(resultSet);
            }
            return user;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private User getUser(ResultSet resultSet) {
        User user = new User();
        try {
            if (resultSet.next()) {
                String surname = resultSet.getString("surname");
                user.setSurname(surname);
                String name = resultSet.getString("name");
                user.setName(name);
                Long id = resultSet.getLong("id");
                user.setId(id);
                String email = resultSet.getString("email");
                user.setEmail(email);
                String password = resultSet.getString("password");
                user.setPassword(password);
                Role role = new Role();
                Long roleID = resultSet.getLong("role_id");
                role.setId(roleID);
                Permissions permission = Permissions.valueOf(resultSet.getString("permission_name"));
                role.getPermissions().add(permission);
                user.setRole(role);
            }
            return user;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
