/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.maestrodetalle.DTO;

/**
 *
 * @author Emmanuel Martinez Si
 */
public class comisionesDTO {
    int Id;
    float porcentaje;
    float valorMinimoVenta;
    float valorMaximoVenta;

    public comisionesDTO(int Id, float porcentaje, float valorMinimoVenta, float valorMaximoVenta) {
        this.Id = Id;
        this.porcentaje = porcentaje;
        this.valorMinimoVenta = valorMinimoVenta;
        this.valorMaximoVenta = valorMaximoVenta;
    }

    public int getId() {
        return Id;
    }

    public float getPorcentaje() {
        return porcentaje;
    }

    public float getValorMinimoVenta() {
        return valorMinimoVenta;
    }

    public float getValorMaximoVenta() {
        return valorMaximoVenta;
    }
}
