/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.maestrodetalle.observer;

import com.maestrodetalle.adapter.DB;
import com.maestrodetalle.adapter.IDBAdapter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.postgresql.PGConnection;
import org.postgresql.PGNotification;

/**
 *
 * @author Emmanuel Martinez Si
 */
public class ControlCambiosBD implements Observable {

    private List<Observador> observadores;
    private Connection connection;
    private String tipoBaseDatos;
    private IDBAdapter dbAdapter;
    private static ControlCambiosBD controlCambios;

    public ControlCambiosBD() {
        this.observadores = new ArrayList<>();
        dbAdapter = DB.getDefaultDbAdapter();
    }

    public static ControlCambiosBD getInstance() {
        if (controlCambios == null) {
            controlCambios = new ControlCambiosBD();
        }
        return controlCambios;
    }

    public void getDatabaseType() {
        try {
            connection = dbAdapter.getConnection();
            DatabaseMetaData metaData = connection.getMetaData();
            String databaseProductName = metaData.getDatabaseProductName();
            if (databaseProductName.equals("MySQL")) {
                tipoBaseDatos = "MySQL";
            } else if (databaseProductName.equals("PostgreSQL")) {
                tipoBaseDatos = "PostgreSQL";
            } else {
                throw new IllegalArgumentException("Tipo de base de datos no válido: " + databaseProductName);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener el tipo de base de datos", e);
        }
    }

    public void configureChangeDetection() throws SQLException {
        this.getDatabaseType();
        System.out.println("Entro a configuracion");
        if (tipoBaseDatos.equals("MySQL")) {
            System.out.println("MySQL");
            new Thread(() -> {
                int initialRecordCount = 0;
                try {
                    initialRecordCount = getConteoInicial();
                } catch (SQLException ex) {
                    Logger.getLogger(ControlCambiosBD.class.getName()).log(Level.SEVERE, null, ex);
                }

                while (true) {
                    try (Connection pollConnection = dbAdapter.getConnection()) {
                        Statement statement = pollConnection.createStatement();
                        ResultSet resultSet = statement.executeQuery("SELECT * FROM notificacion");

                        int currentRecordCount = 0;
                        while (resultSet.next()) {
                            currentRecordCount++;
                        }
                        if (currentRecordCount != initialRecordCount) {
                            notificarObservadores();
                            initialRecordCount = currentRecordCount;
                        }

                        resultSet.close();
                        statement.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ControlCambiosBD.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    // Esperar 1 segundo antes del siguiente sondeo
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }).start();

        } else if (tipoBaseDatos.equals("PostgreSQL")) {
            System.out.println("PostgreSQL");
            try (Statement statement = connection.createStatement()) {
                statement.execute("LISTEN dbchange");
            }

            new Thread(() -> {
                try (Connection listenConnection = dbAdapter.getConnection()) {
                    listenConnection.setAutoCommit(true);
                    listenConnection.createStatement().execute("LISTEN dbchange");
                    System.out.println("PostgreSQL1");
                    while (!Thread.currentThread().isInterrupted()) {
                        PGNotification notifications[] = ((PGConnection) listenConnection).getNotifications();
                        if (notifications != null) {
                            for (PGNotification notification : notifications) {
                                String tableChanged = notification.getParameter();
                                System.out.println("PostgreSQL2");
                                notificarObservadores();
                            }
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            // Re-interrupt thread
                            Thread.currentThread().interrupt();
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ControlCambiosBD.class.getName()).log(Level.SEVERE, null, ex);
                }

            }).start();
        } else {
            throw new IllegalArgumentException("Tipo de base de datos no válido: " + tipoBaseDatos);
        }
    }

    private int getConteoInicial() throws SQLException {
        try (Connection pollConnection = dbAdapter.getConnection(); Statement statement = pollConnection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM notificacion");

            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                return 0;
            }
        }
    }

    @Override
    public void notificarObservadores() {

        for (Observador observador : observadores) {
            observador.actualizar();

        }

    }

    @Override
    public void registrarObservador(Observador observador) {
        observadores.add(observador);

    }

    @Override
    public void removerObservador(Observador observador) {
        observadores.remove(observador);
    }
}
