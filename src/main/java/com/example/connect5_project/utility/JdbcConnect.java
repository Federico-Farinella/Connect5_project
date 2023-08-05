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
    final private static String dbUrl = "jdbc:mysql://localhost/connect5_db"; // modifico da connect5db a connect5_db
    //final private static String dbUrl = "jdbc:mariadb://localhost:3306/connect5_db";
    final private static String driverClassName = "com.mysql.jdbc.Driver";
    //final private static String driverClassName = "org.mariadb.jdbc.Driver";

    //private static Connection rootConnection;
    //private static Connection userConnection;
    private static JdbcConnect jdbcConn;
    private final Connection connection;
    private int id;

    private JdbcConnect() throws ConnectionDBException {  // Aggiunto per perfezionare pattern Singleton, andrebbe cancellato del codice ripetuto piu giu
        // Devo usare questa per centralizzare i dati di configurazione qui
        try {
            Class.forName(driverClassName);
            getDBCredentials();
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (IOException | SQLException | ClassNotFoundException e) {
            throw new ConnectionDBException("DB Connection Error");
        }

    }

    private JdbcConnect(String dbUser, String password) throws ClassNotFoundException, SQLException {
        Class.forName(driverClassName);
        this.connection = DriverManager.getConnection(dbUrl, dbUser, password);
    }

    /*public static JdbcConnect getUserConnection() throws ClassNotFoundException, SQLException { //Aggiunto per perfezionare pattern Singleton, andrebbe cancellato del codice ripetuto piu giu
        if (jdbcConn == null) {
            jdbcConn = new JdbcConnect();
        }
        return jdbcConn;
    }*/

    public static JdbcConnect getUserConnection(String dbUser, String password) throws ClassNotFoundException, SQLException { //Aggiunto per perfezionare pattern Singleton, andrebbe cancellato del codice ripetuto piu giu
        if (jdbcConn == null || jdbcConn.getConnection().isClosed()) {
            jdbcConn = new JdbcConnect(dbUser, password);
        }
        return jdbcConn;
    }

    public static JdbcConnect getInstance() throws ConnectionDBException {// Devo usare questa
        try {
            boolean isClosed = jdbcConn.getConnection().isClosed();
            if (jdbcConn == null || isClosed) {
                jdbcConn = new JdbcConnect();
            }
            return jdbcConn;
        } catch (SQLException e) {
            throw new ConnectionDBException("DB Connection Error");
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    private void getDBCredentials() throws IOException {
        try{
            FileInputStream propsInput = new FileInputStream("src/main/logic/resources/config.properties");
            Properties prop = new Properties();
            prop.load(propsInput);
            this.url=prop.getProperty("urlDB");
            this.user=prop.getProperty("dbUser");
            this.password=prop.getProperty("pass");
            propsInput.close();}
        catch(IOException ex) {
            throw new IOException();}
    }
}
