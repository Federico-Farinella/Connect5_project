package com.example.connect5_project.utility;

import com.example.connect5_project.exceptions.DbConnectException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DbConfigurationManager {
    private static String configFilePath = "src/main/resources/config.properties";
    private static String dbUser = null;
    private static String dbPass = null;

    public static void DbConfigurationManagerInitialize() throws DbConnectException {
        if (dbUser == null) {
            try (FileInputStream propsInput = new FileInputStream(configFilePath)) {
                Properties properties = new Properties();
                properties.load(propsInput);
                dbUser = properties.getProperty("dbUser");
                dbPass = properties.getProperty("pass");
            } catch (FileNotFoundException e) {
                throw new DbConnectException("Config file not found");
            } catch (IOException e) {
                throw new DbConnectException("Error reading from config file");
            }
        }
    }

    public static String getDbUser() {
        return dbUser;
    }

    public void setDbUser (String dbUser){
            DbConfigurationManager.dbUser = dbUser;
        }

        public static String getDbPass () {
            return dbPass;
        }

        public void setDbPass (String dbPass){
            DbConfigurationManager.dbPass = dbPass;
        }
    }
