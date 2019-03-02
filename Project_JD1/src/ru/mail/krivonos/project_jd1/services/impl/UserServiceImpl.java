package ru.mail.krivonos.project_jd1.services.impl;

import ru.mail.krivonos.project_jd1.repository.UserRepository;
import ru.mail.krivonos.project_jd1.repository.connection.ConnectionService;
import ru.mail.krivonos.project_jd1.repository.connection.ConnectionServiceImpl;
import ru.mail.krivonos.project_jd1.repository.exceptions.UserRepositoryException;
import ru.mail.krivonos.project_jd1.repository.impl.UserRepositoryImpl;
import ru.mail.krivonos.project_jd1.repository.model.User;
import ru.mail.krivonos.project_jd1.services.UserService;
import ru.mail.krivonos.project_jd1.services.converter.user.AuthorizedUserConverterImpl;
import ru.mail.krivonos.project_jd1.services.converter.user.UserInfoConverterImpl;
import ru.mail.krivonos.project_jd1.services.converter.user.UserRegistrationConverterImpl;
import ru.mail.krivonos.project_jd1.services.model.user.AuthorizedUserDTO;
import ru.mail.krivonos.project_jd1.services.model.user.UserInfoDTO;
import ru.mail.krivonos.project_jd1.services.model.user.UserLoginDTO;
import ru.mail.krivonos.project_jd1.services.model.user.UserRegistrationDTO;

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
    public void add(UserRegistrationDTO userRegistrationDTO) {
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                User user = UserRegistrationConverterImpl.getInstance().fromDTO(userRegistrationDTO);
                userRepository.add(connection, user);
                connection.commit();
            } catch (SQLException | UserRepositoryException e) {
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
    public void updateInfo(UserInfoDTO userDTO) {
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                User user = UserInfoConverterImpl.getInstance().fromDTO(userDTO);
                userRepository.updateInfo(connection, user);
                connection.commit();
            } catch (SQLException | UserRepositoryException e) {
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
    public void updateEmail(Long userID, String email) {
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                userRepository.updateEmail(connection, userID, email);
                connection.commit();
            } catch (SQLException | UserRepositoryException e) {
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
    public void updatePassword(Long userID, String password) {
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                userRepository.updatePassword(connection, userID, password);
                connection.commit();
            } catch (SQLException | UserRepositoryException e) {
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
            } catch (SQLException | UserRepositoryException e) {
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
    public AuthorizedUserDTO loginUser(UserLoginDTO userLoginDTO) {
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                User user = userRepository.findUserByEmail(connection, userLoginDTO.getEmail());
                if (user != null && validatePassword(userLoginDTO.getPassword(), user.getPassword())) {
                    AuthorizedUserDTO userDTO = AuthorizedUserConverterImpl.getInstance().toDTO(user);
                    connection.commit();
                    System.out.println("-------- User Was Found --------");
                    return userDTO;
                }
            } catch (SQLException | UserRepositoryException e) {
                System.out.println(e.getMessage());
                connection.rollback();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        System.out.println("-------- Invalid Login Or Password --------");
        return null;
    }

    @Override
    public UserInfoDTO getInfoByID(Long id) {
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                User user = userRepository.findUserByID(connection, id);
                if (user != null) {
                    UserInfoDTO userDTO = UserInfoConverterImpl.getInstance().toDTO(user);
                    connection.commit();
                    System.out.println("-------- User Info Was Found --------");
                    return userDTO;
                }
            } catch (SQLException | UserRepositoryException e) {
                System.out.println(e.getMessage());
                connection.rollback();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        System.out.println("-------- User Wasn't Found --------");
        return null;
    }

    private boolean validatePassword(String passwordInput, String savedPassword) {
        return passwordInput.equals(savedPassword);
    }
}
