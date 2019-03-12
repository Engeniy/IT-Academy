package ru.mail.krivonos.project_jd1.services.impl;

import ru.mail.krivonos.project_jd1.config.DatabaseInitManager;
import ru.mail.krivonos.project_jd1.config.DatabaseInitManagerImpl;
import ru.mail.krivonos.project_jd1.repository.DatabaseInitRepository;
import ru.mail.krivonos.project_jd1.repository.connection.ConnectionService;
import ru.mail.krivonos.project_jd1.repository.connection.ConnectionServiceImpl;
import ru.mail.krivonos.project_jd1.repository.impl.DatabaseInitRepositoryImpl;
import ru.mail.krivonos.project_jd1.services.DatabaseInitService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseInitServiceImpl implements DatabaseInitService {

    private static DatabaseInitService instance;

    private DatabaseInitRepository databaseInitRepository = DatabaseInitRepositoryImpl.getInstance();

    private DatabaseInitManager databaseInitManager = DatabaseInitManagerImpl.getInstance();

    private static final String SCRIPT_SEPARATOR = ";";

    private DatabaseInitServiceImpl() {
    }

    public static DatabaseInitService getInstance() {
        if (instance == null) {
            instance = new DatabaseInitServiceImpl();
        }
        return instance;
    }

    @Override
    public void initDatabase() {
        System.out.println("-------- Initialising Database --------");
        File scriptFile = new File(databaseInitManager.getInitialFilePath());
        ConnectionService connectionService = ConnectionServiceImpl.getInstance();
        try (Connection connection = connectionService.getConnection()) {
            try (BufferedReader br = new BufferedReader(new FileReader(scriptFile))) {
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.endsWith(SCRIPT_SEPARATOR)) {
                        stringBuilder.append(line);
                        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                        String script = stringBuilder.toString();
                        databaseInitRepository.runScript(connection, script);
                        stringBuilder = new StringBuilder();
                    } else {
                        stringBuilder.append(line);
                    }
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            System.out.println("-------- Database Successfully Initialised --------");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}


