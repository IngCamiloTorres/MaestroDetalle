/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.maestrodetalle;

import com.maestrodetalle.GIU.Menu;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Emmanuel Martinez Si
 */
public class GUI implements Runnable {

    @Override
    public void run() {
        // Crear y mostrar la ventana GUI
        Menu pantalla = null;
        try {
            pantalla = new Menu();
        } catch (SQLException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        pantalla.setVisible(true);
        pantalla.setLocationRelativeTo(null);
    }
}
