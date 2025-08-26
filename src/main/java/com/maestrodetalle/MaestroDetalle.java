/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.maestrodetalle;

import java.sql.SQLException;

/**
 *
 * @author Emmanuel Martinez Si
 */
public class MaestroDetalle {

    public static void main(String[] args) throws SQLException {
        
        // Crear clases para la consola y la GUI
        Consola consola = new Consola();
        GUI gui = new GUI();

        // Crear hilos para ejecutar la consola y la GUI
        Thread hiloConsola = new Thread(consola);
        Thread hiloGUI = new Thread(gui);
        
        // Iniciar la ejecución de los hilos
        hiloConsola.start();
        hiloGUI.start();
    }
}
