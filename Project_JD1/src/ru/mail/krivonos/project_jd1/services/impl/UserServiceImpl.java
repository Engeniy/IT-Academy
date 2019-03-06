package ru.mail.krivonos.project_jd1.services.impl;

import ru.mail.krivonos.project_jd1.repository.UserRepository;
import ru.mail.krivonos.project_jd1.repository.connection.ConnectionService;
import ru.mail.krivonos.project_jd1.repository.connection.ConnectionServiceImpl;
import ru.mail.krivonos.project_jd1.repository.exceptions.UserRepositoryException;
import ru.mail.krivonos.project_jd1.repository.impl.UserRepositoryImpl;
import ru.mail.krivonos.project_jd1.repository.model.PermissionsEnum;
import ru.mail.krivonos.project_jd1.repository.model.Role;
import ru.mail.krivonos.project_jd1.repository.model.RolesEnum;
import ru.mail.krivonos.project_jd1.repository.model.User;
import ru.mail.krivonos.project_jd1.services.UserService;
import ru.mail.krivonos.project_jd1.services.converter.user.AuthorizedUserConverterImpl;
import ru.mail.krivonos.project_jd1.services.converter.user.UserInfoConverterImpl;
import ru.mail.krivonos.project_jd1.services.converter.user.UserRegistrationConverterImpl;
import ru.mail.krivonos.project_jd1.services.exceptions.PasswordChangeException;
import ru.mail.krivonos.project_jd1.services.exceptions.RegistrationException;
import ru.mail.krivonos.project_jd1.services.model.user.AuthorizedUserDTO;
import ru.mail.krivonos.project_jd1.services.model.user.UserInfoDTO;
import ru.mail.krivonos.project_jd1.services.model.user.UserLoginDTO;
import ru.mail.krivonos.project_jd1.services.model.user.UserRegistrationDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private static final String DEFAUL_ROLE = "CUSTOMER_USER";

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
    public void add(UserRegistrationDTO userRegistrationDTO) throws RegistrationException {
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                User user = UserRegistrationConverterImpl.getInstance().fromDTO(userRegistrationDTO);
                User userByEmail = userRepository.findUserByEmail(connection, user.getEmail());
                if (userByEmail != null) {
                    throw new RegistrationException();
                }
                Role role = new Role();
                role.setName(DEFAUL_ROLE);
                List<PermissionsEnum> permissions = new ArrayList<>();
                permissions.add(PermissionsEnum.CUSTOMER_PERMISSION);
                role.setPermissions(permissions);
                user.setRole(role);
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
    public void updatePassword(String email, String oldPassword, String newPassword) throws PasswordChangeException {
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                User userByEmail = userRepository.findUserByEmail(connection, email);
                if (validatePassword(oldPassword, userByEmail.getPassword())) {
                    userRepository.updatePassword(connection, email, oldPassword, newPassword);
                } else {
                    throw new PasswordChangeException("Invalid old password!");
                }
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

    private boolean validatePassword(String passwordInput, String savedPassword) {
        return passwordInput.trim().equals(savedPassword);
    }
}
