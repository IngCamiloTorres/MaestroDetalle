/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.maestrodetalle;

import com.maestrodetalle.DAO.empleadoDao;
import java.sql.SQLException;

/**
 *
 * @author Emmanuel Martinez Si
 */
public class Empleado {
    int Id;
    Persona idPersona;
    Comisiones idComisiones;
    String cargo;
    float salario;
    String fechaIngreso;
    String fechaSalida;
    empleadoDao empleadosDao;

    public Empleado(int Id, Persona idPersona, Comisiones idComisiones, String cargo, float salario, String fechaIngreso, String fechaSalida) {
        this.Id = Id;
        this.idPersona = idPersona;
        this.idComisiones = idComisiones;
        this.cargo = cargo;
        this.salario = salario;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
        empleadosDao = new empleadoDao();
    }

    public int getId() {
        return Id;
    }

    public int getIdPersona() {
        return idPersona.getId();
    }

    public int getIdComisiones() {
        return idComisiones.getId();
    }

    public String getCargo() {
        return cargo;
    }

    public float getSalario() {
        return salario;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
    
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
    public void guardarEmpleado(Empleado empleado, byte[] bytes, byte[] videoBytes) throws SQLException{
        
        empleadosDao.insertEmpleado(empleado,bytes, videoBytes);
    }
}
