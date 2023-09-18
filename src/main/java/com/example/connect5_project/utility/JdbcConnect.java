package com.example.connect5_project.utility;

import com.example.connect5_project.exceptions.ConnectionDBException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcConnect {
    private String user;
    private String password;
    private String url;
    private static final String DB_URL = "jdbc:mysql://localhost/connect5_db"; // modifico da connect5db a connect5_db

    private static JdbcConnect jdbcConn;
    private final Connection connection;

    private JdbcConnect() throws ConnectionDBException {
        // Devo usare questa per centralizzare i dati di configurazione qui
        try {
            this.getDBCredentials();
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (IOException | SQLException e) {
            throw new ConnectionDBException("DB Connection Error");
        }

    }

    public static JdbcConnect getInstance() throws ConnectionDBException, SQLException {// Devo usare questa
        if (jdbcConn == null || jdbcConn.getConnection().isClosed()) {
            jdbcConn = new JdbcConnect();
        }
        return jdbcConn;
    }

    public Connection getConnection() {
        return this.connection;
    }

    private void getDBCredentials() throws IOException {
        try (FileInputStream propsInput = new FileInputStream("src/main/resources/config.properties")) {
            Properties prop = new Properties();
            prop.load(propsInput);
            this.url = prop.getProperty("dbUrl");
            this.user = prop.getProperty("dbUser");
            this.password = prop.getProperty("pass");
        } catch (IOException ex) {
            throw new IOException();
        }
    }
}
