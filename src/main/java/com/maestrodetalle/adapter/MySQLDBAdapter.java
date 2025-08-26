/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.maestrodetalle.adapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class MySQLDBAdapter implements IDBAdapter {
    private static final String DB_PROPERTIES = "C:\\Users\\emman\\OneDrive\\Escritorio\\maestroDetalle\\maestroDetalle\\src\\main\\java\\com\\maestrodetalle\\properties\\DBMySQL.properties";

    private static MySQLDBAdapter instance; // Instancia única

    private static final String DB_NAME_PROP = "dbname";
    private static final String DB_HOST_PROP = "host";
    private static final String DB_PASSWORD_PROP = "password";
    private static final String DB_PORT_PROP = "port";
    private static final String DB_USER_PROP = "user";

    static {
        try {
            new com.mysql.jdbc.Driver();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private MySQLDBAdapter() {
        // Constructor privado para evitar instanciación directa
        
    }

    public static synchronized MySQLDBAdapter getInstance() {
        if (instance == null) {
            instance = new MySQLDBAdapter();
        }
        return instance;
    }

    @Override
    public Connection getConnection() {
        try {
            String connectionString = createConnectionString();
            Connection connection = DriverManager.getConnection(connectionString);
           
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String createConnectionString() {
        Properties prop = PropertiesUtil.loadProperty(DB_PROPERTIES);
        String host = prop.getProperty(DB_HOST_PROP);
        String port = prop.getProperty(DB_PORT_PROP);
        String db = prop.getProperty(DB_NAME_PROP);
        String user = prop.getProperty(DB_USER_PROP);
        String password = prop.getProperty(DB_PASSWORD_PROP);

        String stringConection = "jdbc:mysql://" + host + ":" + port + "/" + db + "?user=" + user + "&password=" + password;
        
        return stringConection;
    }

    
}
