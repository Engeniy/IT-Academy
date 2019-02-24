package ru.mail.krivonos.al.project_jd1.repository.impl;

import ru.mail.krivonos.al.project_jd1.repository.ProfileRepository;
import ru.mail.krivonos.al.project_jd1.repository.model.Profile;
import ru.mail.krivonos.al.project_jd1.repository.model.User;

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
    public void add(Connection connection, Profile profile) {
        String sql = "INSERT INTO Profile (user_id, address, telephone) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, profile.getUser().getId());
            preparedStatement.setString(2, profile.getAddress());
            preparedStatement.setString(3, profile.getTelephone());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Connection connection, Profile profile) {
        String sql = "UPDATE Profile SET address = ?, telephone = ? WHERE user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, profile.getAddress());
            preparedStatement.setString(2, profile.getTelephone());
            preparedStatement.setLong(3, profile.getUser().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Profile findByUserID(Connection connection, Long userID) {
        String sql = "SELECT * FROM Profile WHERE user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, userID);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                Profile profile = getProfile(resultSet);
                return profile;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private Profile getProfile(ResultSet resultSet) {
        Profile profile = new Profile();
        try {
            if (resultSet.next()) {
                User user = new User();
                Long userID = resultSet.getLong("user_id");
                user.setId(userID);
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
            throw new RuntimeException(e);
        }
    }
}
