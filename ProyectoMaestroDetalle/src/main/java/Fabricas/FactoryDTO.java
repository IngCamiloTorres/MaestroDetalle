/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Fabricas;

import com.maestrodetalle.DTO.comisionesDTO;
import com.maestrodetalle.DTO.empleadoDTO;
import com.maestrodetalle.DTO.personaDTO;

/**
 *
 * @author ASUS
 */
public class FactoryDTO {
    
    public static empleadoDTO crearEmpleadoDTO(int Id, int idPersona, int idComisiones, String cargo, float salario, String fechaIngreso, String fechaSalida) {
        return new empleadoDTO(Id, idPersona, idComisiones, cargo, salario, fechaIngreso, fechaSalida);
    }
    public static empleadoDTO crearEmpleadoDTO(){

        return new empleadoDTO();
    }
    
    public static comisionesDTO crearComisionesDTO(int id, float porcentaje, float valorMinimoVenta, float valorMaximoVenta) {
        return new comisionesDTO(id, porcentaje, valorMinimoVenta, valorMaximoVenta);
    }
    
    
    
    
    public static personaDTO crearPersonaDTO(int id, String documentoIdentidad, String nombres, String apellidos, String fechaNacimiento, String email, String direccion, String numeroContacto, String genero) {
        return new personaDTO(id, documentoIdentidad, nombres, apellidos, fechaNacimiento, email, direccion, numeroContacto, genero);
    }
    
    
    public static personaDTO crearPersonaDTO(){
        return new personaDTO();
    }
    
}
