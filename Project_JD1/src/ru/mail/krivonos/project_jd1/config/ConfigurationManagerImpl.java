package ru.mail.krivonos.project_jd1.config;

import java.util.ResourceBundle;

public class ConfigurationManagerImpl implements ConfigurationManager {

    private static ConfigurationManager instance;

    private static final String BUNDLE_NAME = "config";

    private static ResourceBundle bundle;

    private ConfigurationManagerImpl() {
    }

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            synchronized (ConfigurationManagerImpl.class) {
                if (instance == null) {
                    instance = new ConfigurationManagerImpl();
                    bundle = ResourceBundle.getBundle(BUNDLE_NAME);
                }
            }
        }
        return instance;
    }

    @Override
    public String getProperty(String key) {
        return bundle.getString(key);
    }
}
