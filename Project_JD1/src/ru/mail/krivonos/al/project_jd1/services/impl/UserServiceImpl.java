package ru.mail.krivonos.al.project_jd1.services.impl;

import ru.mail.krivonos.al.project_jd1.repository.UserRepository;
import ru.mail.krivonos.al.project_jd1.repository.connection.ConnectionService;
import ru.mail.krivonos.al.project_jd1.repository.connection.ConnectionServiceImpl;
import ru.mail.krivonos.al.project_jd1.repository.impl.UserRepositoryImpl;
import ru.mail.krivonos.al.project_jd1.repository.model.User;
import ru.mail.krivonos.al.project_jd1.services.UserService;
import ru.mail.krivonos.al.project_jd1.services.converter.UserConverterImpl;
import ru.mail.krivonos.al.project_jd1.services.model.UserDTO;

import java.sql.Connection;
import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    private static UserService instance;

    private UserRepository userRepository = UserRepositoryImpl.getInstance();

    private ConnectionService connectionService = ConnectionServiceImpl.getInstance();

    private UserServiceImpl() {
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public void add(UserDTO userDTO) {
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                User user = UserConverterImpl.getInstance().fromDTO(userDTO);
                userRepository.add(connection, user);
                connection.commit();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(UserDTO userDTO) {
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                User user = UserConverterImpl.getInstance().fromDTO(userDTO);
                userRepository.add(connection, user);
                connection.commit();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteByID(Long id) {
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                userRepository.deleteByID(connection, id);
                connection.commit();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                User user = userRepository.findUserByEmail(connection, email);
                UserDTO userDTO = UserConverterImpl.getInstance().toDTO(user);
                connection.commit();
                return userDTO;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return null;
    }
}
