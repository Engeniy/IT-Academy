package ru.mail.krivonos.project_jd1.config;

public class DatabaseInitManagerImpl implements DatabaseInitManager {

    private static DatabaseInitManager instance;

    private ConfigurationManager configurationManager = ConfigurationManagerImpl.getInstance();

    private DatabaseInitManagerImpl() {
    }

    public static DatabaseInitManager getInstance() {
        if (instance == null) {
            synchronized (DatabaseInitManagerImpl.class) {
                if (instance == null) {
                    instance = new DatabaseInitManagerImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public String getInitialFilePath() {
        String initialFilePath = this.getClass().getResource(configurationManager
                .getProperty(PropertiesVariables.DATABASE_INITIAL_FILE)).getPath();
        return initialFilePath;
    }
}
