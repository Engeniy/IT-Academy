package ru.mail.krivonos.project_jd1.repository.impl;

import ru.mail.krivonos.project_jd1.repository.ProfileRepository;
import ru.mail.krivonos.project_jd1.repository.exceptions.ProfileRepositoryException;
import ru.mail.krivonos.project_jd1.repository.model.Profile;
import ru.mail.krivonos.project_jd1.repository.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileRepositoryImpl implements ProfileRepository {

    private static ProfileRepository instance;

    private ProfileRepositoryImpl() {
    }

    public static ProfileRepository getInstance() {
        if (instance == null) {
            instance = new ProfileRepositoryImpl();
        }
        return instance;
    }

    @Override
    public void add(Connection connection, Profile profile) throws ProfileRepositoryException {
        String sql = "INSERT INTO Profile (user_id, address, telephone) VALUES " +
                "((SELECT u.id FROM User u WHERE u.email = ?), ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, profile.getUser().getEmail());
            preparedStatement.setString(2, profile.getAddress());
            preparedStatement.setString(3, profile.getTelephone());
            int added = preparedStatement.executeUpdate();
            System.out.println("-------- " + added + " Profile Added --------");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new ProfileRepositoryException(e);
        }
    }

    @Override
    public void update(Connection connection, Profile profile) throws ProfileRepositoryException {
        String sql = "UPDATE Profile SET address = ?, telephone = ? WHERE user_id = " +
                "(SELECT u.id FROM User u WHERE email = ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, profile.getAddress());
            preparedStatement.setString(2, profile.getTelephone());
            preparedStatement.setString(3, profile.getUser().getEmail());
            int updated = preparedStatement.executeUpdate();
            System.out.println("-------- " + updated + " Profile Updated --------");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new ProfileRepositoryException(e);
        }
    }

    @Override
    public Profile findByUserID(Connection connection, Long userID) throws ProfileRepositoryException {
        String sql = "SELECT *, u.name, u.surname, u.email FROM Profile " +
                "JOIN User u ON u.id = user_id WHERE user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, userID);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                Profile profile = getProfile(resultSet);
                return profile;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new ProfileRepositoryException(e);
        }
    }

    private Profile getProfile(ResultSet resultSet) throws ProfileRepositoryException {
        Profile profile = new Profile();
        try {
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("user_id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setEmail(resultSet.getString("email"));
                profile.setUser(user);
                String address = resultSet.getString("address");
                profile.setAddress(address);
                String telephone = resultSet.getString("telephone");
                profile.setTelephone(telephone);
            }
            return profile;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new ProfileRepositoryException(e);
        }
    }
}
