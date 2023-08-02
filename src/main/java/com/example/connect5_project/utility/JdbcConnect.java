package com.example.connect5_project.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnect {
    private static String user;
    private static String password;
    final private static String dbUrl = "jdbc:mysql://localhost/connect5_db"; // modifico da connect5db a connect5_db
    //final private static String dbUrl = "jdbc:mariadb://localhost:3306/connect5_db";
    final private static String driverClassName = "com.mysql.jdbc.Driver";
    //final private static String driverClassName = "org.mariadb.jdbc.Driver";

    //private static Connection rootConnection;
    //private static Connection userConnection;
    private static JdbcConnect jdbcConn = null;
    private final Connection connection;
    private int id;

    private JdbcConnect() throws ClassNotFoundException, SQLException {  // Aggiunto per perfezionare pattern Singleton, andrebbe cancellato del codice ripetuto piu giu
        this.user = "root";
        this.password = "";
        Class.forName(driverClassName);

        this.connection = DriverManager.getConnection(dbUrl, user, password);
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
        if (jdbcConn == null) {
            jdbcConn = new JdbcConnect(dbUser, password);
        }
        return jdbcConn;
    }

    public Connection getConnection() {
        return this.connection;
    }
    /*

    public static Connection getRootConnection() throws Exception {
        if (rootConnection == null || rootConnection.isClosed()) {
            user = "root";
            password = "";
            Class.forName(driverClassName);
            rootConnection = DriverManager.getConnection(db_Url, user, password);
        }
        return rootConnection;
    }

    public static Connection getUserConnection() throws Exception {
        if (userConnection == null || userConnection.isClosed()) {
            user = "root";
            password = "";
            System.out.println("Loading Class");
            System.out.println(driverClassName);
            Class.forName(driverClassName);
            System.out.println("Class loaded");
            userConnection = DriverManager.getConnection(db_Url, user, password);
        }
        return userConnection;
    }*/
}
