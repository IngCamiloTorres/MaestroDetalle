/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.maestrodetalle.DTO;

import com.maestrodetalle.Comisiones;
import com.maestrodetalle.Persona;
import java.util.Date;

/**
 *
 * @author Emmanuel Martinez Si
 */
public class empleadoDTO {
    int Id;
    int idPersona;
    int idComisiones;
    String cargo;
    float salario;
    String fechaIngreso;
    String fechaSalida;

    public empleadoDTO(int Id, int idPersona, int idComisiones, String cargo, float salario, String fechaIngreso, String fechaSalida) {
        this.Id = Id;
        this.idPersona = idPersona;
        this.idComisiones = idComisiones;
        this.cargo = cargo;
        this.salario = salario;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
    }
    
    public empleadoDTO(){}

    public int getId() {
        return Id;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public int getIdComisiones() {
        return idComisiones;
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
}
