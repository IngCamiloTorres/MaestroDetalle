/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.maestrodetalle;

import com.maestrodetalle.DAO.comisionesDao;
import java.sql.SQLException;

/**
 *
 * @author Emmanuel Martinez Si
 */
public class Comisiones {
   int Id;
   double porcentaje;
   double valorMinimoVenta;
   double valorMaximoVenta;
   comisionesDao comisionesdao;
    
    public Comisiones(int Id) {
        this.Id = Id;
        comisionesdao = new comisionesDao();
    }
    public int getId() {
        return Id;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public double getValorMinimoVenta() {
        return valorMinimoVenta;
    }

    public double getValorMaximoVenta() {
        return valorMaximoVenta;
    }
   
    public void setId(int Id) {
        this.Id = Id;
    }

    public void setPorcentaje(float porcentaje) {
        this.porcentaje = porcentaje;
    }

    public void setValorMinimoVenta(float valorMinimoVenta) {
        this.valorMinimoVenta = valorMinimoVenta;
    }

    public void setValorMaximoVenta(float valorMaximoVenta) {
        this.valorMaximoVenta = valorMaximoVenta;
    }
    public void guardarComision(Comisiones comision) throws SQLException{
        comisionesdao.insertComision(comision);
    }
}
