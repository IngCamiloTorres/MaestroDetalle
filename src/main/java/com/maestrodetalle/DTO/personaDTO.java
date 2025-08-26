/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.maestrodetalle.DTO;

import java.util.Date;

/**
 *
 * @author Emmanuel Martinez Si
 */
public class personaDTO {
    int Id;
    String documentoIdentidad;
    String nombres;
    String apellidos;
    String fechaNacimiento;
    String email;
    String direccion;
    String numeroContacto;
    String genero;

    public personaDTO(int Id, String documentoIdentidad, String nombres, String apellidos, String fechaNacimiento, String email, String direccion, String numeroContacto, String genero) {
        this.Id = Id;
        this.documentoIdentidad = documentoIdentidad;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.direccion = direccion;
        this.numeroContacto = numeroContacto;
        this.genero = genero;
    }

    public personaDTO(){}
    
    public int getId() {
        return Id;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getNumeroContacto() {
        return numeroContacto;
    }

    public String getGenero() {
        return genero;
    }
}
