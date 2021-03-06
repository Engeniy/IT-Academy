package ru.mail.krivonos.project_jd1.services.impl;

import ru.mail.krivonos.project_jd1.repository.ProfileRepository;
import ru.mail.krivonos.project_jd1.repository.connection.ConnectionService;
import ru.mail.krivonos.project_jd1.repository.connection.ConnectionServiceImpl;
import ru.mail.krivonos.project_jd1.repository.exceptions.ProfileRepositoryException;
import ru.mail.krivonos.project_jd1.repository.impl.ProfileRepositoryImpl;
import ru.mail.krivonos.project_jd1.repository.model.Profile;
import ru.mail.krivonos.project_jd1.services.ProfileService;
import ru.mail.krivonos.project_jd1.services.converter.impl.ProfileConverterImpl;
import ru.mail.krivonos.project_jd1.services.model.ProfileDTO;

import java.sql.Connection;
import java.sql.SQLException;

public class ProfileServiceImpl implements ProfileService {

    private static ProfileService instance;

    private ProfileRepository profileRepository = ProfileRepositoryImpl.getInstance();

    private ConnectionService connectionService = ConnectionServiceImpl.getInstance();

    private ProfileServiceImpl() {
    }

    public static ProfileService getInstance() {
        if (instance == null) {
            synchronized (ProfileServiceImpl.class) {
                if (instance == null) {
                    instance = new ProfileServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public void add(ProfileDTO profileDTO) {
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                Profile profile = ProfileConverterImpl.getInstance().fromDTO(profileDTO);
                profileRepository.add(connection, profile);
                connection.commit();
            } catch (SQLException | ProfileRepositoryException e) {
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
    public void update(ProfileDTO profileDTO) {
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                Profile profile = ProfileConverterImpl.getInstance().fromDTO(profileDTO);
                profileRepository.update(connection, profile);
                connection.commit();
            } catch (SQLException | ProfileRepositoryException e) {
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
    public ProfileDTO getByUserID(Long userID) {
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                Profile profile = profileRepository.findByUserID(connection, userID);
                if (profile != null) {
                    ProfileDTO profileDTO = ProfileConverterImpl.getInstance().toDTO(profile);
                    connection.commit();
                    System.out.println("-------- Profile Was Found --------");
                    return profileDTO;
                }
            } catch (SQLException | ProfileRepositoryException e) {
                System.out.println(e.getMessage());
                connection.rollback();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        System.out.println("-------- Profile Wasn't Found --------");
        return null;
    }
}
