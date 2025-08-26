/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.maestrodetalle.facade;

import Fabricas.FactoryDTO;
import com.maestrodetalle.Comisiones;
import com.maestrodetalle.DAO.empleadoDao;
import com.maestrodetalle.DAO.personaDao;
import com.maestrodetalle.DTO.empleadoDTO;
import com.maestrodetalle.DTO.personaDTO;
import com.maestrodetalle.Empleado;
import com.maestrodetalle.Persona;
import java.sql.SQLException;

/**
 *
 * @author Emmanuel Martinez Si
 */
public class Fachada {
    private Empleado empleado;
    private Persona persona;
    private Comisiones comisiones;
    private empleadoDTO empleados;
    int idPersona;
    int iDPersona;
    int idComision;
    
    public void ingresarDatos(String documentoIdentidad, String Nombre, String Apellidos, String fechaNacimientos, String email, String direccionResidencia, String numeroContacto, String cargo, String genero, String fechaInicio, String fechaFinal, float salario, int id, byte[] bytes, byte[] videoBytes) throws SQLException{
        iDPersona = Integer.parseInt(documentoIdentidad) - 1000;
        persona = new Persona(iDPersona,documentoIdentidad,Nombre,Apellidos,fechaNacimientos,email,direccionResidencia,numeroContacto,genero);
        persona.guardarPersona(persona);
        idComision = id + 10000;
        comisiones = new Comisiones(idComision);
        comisiones.guardarComision(comisiones);
        empleado = new Empleado(id,persona,comisiones,cargo,salario,fechaInicio,fechaFinal);
        empleado.guardarEmpleado(empleado, bytes, videoBytes);
    }
    
    public personaDTO mostrarDatos(int id) throws SQLException{
        empleadoDTO empleados = FactoryDTO.crearEmpleadoDTO();
        personaDTO personadto = FactoryDTO.crearPersonaDTO();
        
        empleadoDao empleadodao = new empleadoDao();
        personaDao personadao = new personaDao();
        
        empleados = empleadodao.obtenerEmpleado(id);
        personadto = personadao.obtenerPersona(empleados.getIdPersona());
                    
        return personadto;
    }
}
