package com.maestrodetalle.adapter;

import java.lang.reflect.Method;
import java.util.Properties;

public class DB {
    
    private static final String DB_FACTORY_PROPERTY_URL = "C:\\Users\\emman\\OneDrive\\Escritorio\\maestroDetalle\\maestroDetalle\\src\\main\\java\\com\\maestrodetalle\\properties\\DB.properties";
    private static final String DEFAULT_DB_CLASS_PROP = "defaultDBClass";
    
    private static DB dbAdapter;
    private static IDBAdapter dbAdapter1;
    // Privado para evitar instanciación directa
    private DB() {}

    public static synchronized DB getDatabaseAdapter() {
        if (dbAdapter == null) {
            dbAdapter = new DB();
        }
        return dbAdapter;
    }
    
    public static synchronized IDBAdapter getDefaultDbAdapter() {
        if (dbAdapter1 == null) {
            dbAdapter1 = initializeDefaultDBAdapter();
        }
        
        return dbAdapter1;
    }
    
    public static IDBAdapter initializeDefaultDBAdapter() {
        try {
            Properties prop = PropertiesUtil.loadProperty(DB_FACTORY_PROPERTY_URL);
            String defaultBDClass = prop.getProperty(DEFAULT_DB_CLASS_PROP);

            if (defaultBDClass == null) {
                throw new IllegalArgumentException("La propiedad defaultDBClass no está configurada en el archivo de propiedades.");
            }
            
            Class<?> dbClass = Class.forName(defaultBDClass);
            
            Method getInstanceMethod = dbClass.getMethod("getInstance");
            
            return (IDBAdapter) getInstanceMethod.invoke(null);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}