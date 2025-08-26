/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.maestrodetalle.DAO;

import com.maestrodetalle.Empleado;
import com.maestrodetalle.DTO.empleadoDTO;
import com.maestrodetalle.adapter.DB;
import com.maestrodetalle.adapter.IDBAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Emmanuel Martinez Si
 */
public class empleadoDao {
    private Connection conexion;
    private IDBAdapter idbAdapter;
    
    public empleadoDao(){
        idbAdapter = DB.getDefaultDbAdapter();
    }

    public void insertEmpleado(Empleado empleado, byte[] imagen, byte[] videoBytes) throws  SQLException {
        conexion = idbAdapter.getConnection();
        String sql = "INSERT INTO empleado(id, persona_id, comision_id, cargo, salario, fechaingreso, fechasalida, imagen, video) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, empleado.getId());
            statement.setInt(2, empleado.getIdPersona());
            statement.setInt(3, empleado.getIdComisiones());
            statement.setString(4, empleado.getCargo());
            statement.setFloat(5, empleado.getSalario());
            statement.setString(6, empleado.getFechaIngreso());
            statement.setString(7, empleado.getFechaSalida());
            statement.setBytes(8, imagen);
            statement.setBytes(9, videoBytes);
            statement.executeUpdate();
        }
    }
    public void eliminarEmpleado(int idComision) throws SQLException {
        conexion = idbAdapter.getConnection();
        String sql = "DELETE FROM empleado WHERE id =?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, idComision);
            statement.executeUpdate();
        }
    }
    public empleadoDTO obtenerEmpleado(int idEmpleado) throws SQLException {
        conexion = idbAdapter.getConnection();
        empleadoDTO empleado = null;
        String sql = "SELECT * FROM empleado WHERE id = ?";
        try(PreparedStatement statement = conexion.prepareStatement(sql)){
            statement.setInt(1, idEmpleado);
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    empleado = new empleadoDTO(resultSet.getInt("id"), resultSet.getInt("persona_id"),resultSet.getInt("comision_id"),resultSet.getString("cargo"),resultSet.getFloat("salario"),resultSet.getString("fechaingreso"),resultSet.getString("fechasalida"));
                }
            }
        }
        
        return empleado;
       
    }
    
    public byte[] obtenerImagenEmpleado(int idEmpleado) throws SQLException {
        byte[] imagen = null;
        conexion = idbAdapter.getConnection();
        String sql = "SELECT imagen FROM empleado WHERE id = ?";
        try(PreparedStatement statement = conexion.prepareStatement(sql)){
            statement.setInt(1, idEmpleado);
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                   imagen = resultSet.getBytes("imagen");
                }
            }
        }
        return imagen;
    }
    
    public byte[] obtenerVideoEmpleado(int idEmpleado) throws SQLException {
        byte[] video = null;
        conexion = idbAdapter.getConnection();
        String sql = "SELECT video FROM empleado WHERE id = ?";
        try(PreparedStatement statement = conexion.prepareStatement(sql)){
            statement.setInt(1, idEmpleado);
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                   video = resultSet.getBytes("video");
                }
            }
        }
        return video;
    }
    
    
   public List<empleadoDTO> obtenerEmpleados(String cargoElegido, float salarioMenor) throws SQLException {
    List<empleadoDTO> lista = new ArrayList<>();
    try (Connection conexion = idbAdapter.getConnection()) {
        String sql = "SELECT * FROM empleado WHERE cargo = ? AND salario > ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, cargoElegido);
            statement.setFloat(2, salarioMenor);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {  
                    lista.add(new empleadoDTO(resultSet.getInt("id"), resultSet.getInt("persona_id"), resultSet.getInt("comision_id"), resultSet.getString("cargo"), resultSet.getFloat("salario"), resultSet.getString("fechaingreso"), resultSet.getString("fechasalida")));  
                }
            }
        }
    }
    return lista;    
}

    
    public List<empleadoDTO> obtenerTodosEmpleados() throws SQLException {
        List<empleadoDTO> lista = new ArrayList<>();
        try (Connection conexion = idbAdapter.getConnection()){
        String sql = "SELECT * FROM empleado";
        PreparedStatement statement = conexion.prepareStatement(sql);            
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {  
                    lista.add(new empleadoDTO(resultSet.getInt("id"), resultSet.getInt("persona_id"),resultSet.getInt("comision_id"),resultSet.getString("cargo"),resultSet.getFloat("salario"),resultSet.getString("fechaingreso"),resultSet.getString("fechasalida")));  
                }
            }
        }
        return lista;    
    }
}
