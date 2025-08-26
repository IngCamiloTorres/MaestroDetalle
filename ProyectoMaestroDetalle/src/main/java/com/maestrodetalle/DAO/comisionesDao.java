/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.maestrodetalle.DAO;

import com.maestrodetalle.Comisiones;
import com.maestrodetalle.DTO.comisionesDTO;
import com.maestrodetalle.adapter.DB;
import com.maestrodetalle.adapter.IDBAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Emmanuel Martinez Si
 */
public class comisionesDao {
    private Connection conexion;
    private IDBAdapter idbAdapter;
    
    public comisionesDao(){
        idbAdapter = DB.getDefaultDbAdapter();
    }

    public void insertComision(Comisiones comisiones) throws  SQLException {
        conexion = idbAdapter.getConnection();
        String sql = "INSERT INTO comision(id,porcentaje, valorminimoventa, valormaximoventa) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, comisiones.getId());
            statement.setDouble(2, comisiones.getPorcentaje());
            statement.setDouble(3, comisiones.getValorMinimoVenta());
            statement.setDouble(4, comisiones.getValorMaximoVenta());
            statement.executeUpdate();
        }
    }
    public void eliminarComision(int idComision) throws SQLException {
        conexion = idbAdapter.getConnection();
        String sql = "DELETE FROM comision WHERE id =?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, idComision);
            statement.executeUpdate();
        }
    }
    public List<comisionesDTO> obtenerComision() throws SQLException {
    List<comisionesDTO> lista = new ArrayList<>();
    try (Connection conexion = idbAdapter.getConnection();
         Statement statement = conexion.createStatement();
         ResultSet resultSet = statement.executeQuery("SELECT * FROM comision")) {

        while (resultSet.next()) {
            lista.add(new comisionesDTO(resultSet.getInt("id"), resultSet.getFloat("porcentaje"), resultSet.getFloat("valorminimoventa"), resultSet.getFloat("valormaximoventa")));
        }
    }
    return lista;
}

    
}
