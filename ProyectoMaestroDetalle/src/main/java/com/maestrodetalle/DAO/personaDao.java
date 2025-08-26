/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.maestrodetalle.DAO;

import com.maestrodetalle.DTO.personaDTO;
import com.maestrodetalle.Persona;
import com.maestrodetalle.adapter.DB;
import com.maestrodetalle.adapter.IDBAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Emmanuel Martinez Si
 */
public class personaDao {
    private Connection conexion;
    private IDBAdapter idbAdapter;
    
    public personaDao(){
        idbAdapter = DB.getDefaultDbAdapter();
    }

    public void insertPersona(Persona persona) throws  SQLException {
        conexion = idbAdapter.getConnection();
        String sql = "INSERT INTO persona(id, documentoidentidad, nombres, apellidos, fechanacimiento, email, direccion, numerocontacto, genero) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, persona.getId());
            statement.setString(2, persona.getDocumentoIdentidad());
            statement.setString(3, persona.getNombres());
            statement.setString(4, persona.getApellidos());
            statement.setString(5, persona.getFechaNacimiento());
            statement.setString(6, persona.getEmail());
            statement.setString(7, persona.getDireccion());
            statement.setString(8, persona.getNumeroContacto());
            statement.setString(9, persona.getGenero());
            statement.executeUpdate();
        }
    }
    public void eliminarPersona(int idComision) throws SQLException {
        conexion = idbAdapter.getConnection();
        String sql = "DELETE FROM persona WHERE id =?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, idComision);
            statement.executeUpdate();
        }
    }
    public personaDTO obtenerPersona(int idPersona) throws SQLException {
        conexion = idbAdapter.getConnection();
        personaDTO persona = null;
        String sql = "SELECT * FROM persona WHERE id = ?";
        try(PreparedStatement statement = conexion.prepareStatement(sql)){
            statement.setInt(1, idPersona);
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    persona = new personaDTO(resultSet.getInt("id"), resultSet.getString("documentoidentidad"),resultSet.getString("nombres"),resultSet.getString("apellidos"),resultSet.getString("fechanacimiento"),resultSet.getString("email"),resultSet.getString("direccion"),resultSet.getString("numerocontacto"),resultSet.getString("genero"));
                }
            }
        }
        return persona;
        
    }
}