/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.maestrodetalle;

import com.maestrodetalle.facade.Fachada;
import com.maestrodetalle.DTO.personaDTO;
import com.maestrodetalle.GIU.camaraConsola;
import com.maestrodetalle.GIU.empleadoGestion;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.opencv.videoio.VideoCapture;

/**
 *
 * @author Emmanuel Martinez Si
 */
public class Consola implements Runnable {
    public Fachada fachada = new Fachada();
    public byte[] bytes;
    public byte[] videoBytes;
    //int contador;
    //camaraConsola imagen;

    personaDTO personaDto;
    String Ruta = "C:\\Users\\emman\\OneDrive\\Escritorio\\maestroDetalle\\maestroDetalle\\empleado.png";
    empleadoGestion empleadogestion = new empleadoGestion();
    
    @Override
    public void run() {
        // Implementar la lógica de la interfaz de línea de comandos
        Scanner scanner = new Scanner(System.in);
        bytes = empleadogestion.devolverImagen(Ruta);
        
        while (true) {
            System.out.println("Menú:");
            System.out.println("1. Guardar nuevo empleado");
            System.out.println("2. Mostrar empleado por id");
            System.out.println("3. Salir");
            System.out.println("Eleccion: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea

            switch (opcion) {
                case 1:
                    
                    System.out.println("Documento de identidad: ");       
                    String documentoIdentidad = scanner.nextLine();
                    System.out.println("Nombres: ");
                    String nombre = scanner.nextLine();
                    System.out.println("Apellidos: ");
                    String apellidos = scanner.nextLine();
                    System.out.println("Fecha de nacimiento(DD/MM/AAAA): ");
                    String fechaNacimiento = scanner.nextLine();
                    System.out.println("Email: ");
                    String email = scanner.nextLine();
                    System.out.println("Direccion de Residencia: ");
                    String direccionResidencia = scanner.nextLine();
                    System.out.println("Numero de contacto: ");
                    String numeroContacto = scanner.nextLine();
                    System.out.println("Cargo(Gerente/Contador/Vendedor/Equipo Tecnico): ");
                    String cargo = scanner.nextLine();
                    System.out.println("Genero(Hombre/Mujer): ");
                    String genero = scanner.nextLine();
                    System.out.println("Fecha Inicio(DD/MM/AAAA): ");
                    String fechaInicio = scanner.nextLine();
                    System.out.println("Fecha Final(DD/MM/AAAA): ");
                    String fechaFinal = scanner.nextLine();
                    System.out.println("Salario: ");
                    float salario = scanner.nextFloat();
                    System.out.println("ID: ");
                    int id = scanner.nextInt();
                    
                    camaraConsola pantalla = new camaraConsola();
                    pantalla.setVisible(true);
                    
                    pantalla.setLocationRelativeTo(null);
                    
                    System.out.print("");
                    String espacio = scanner.nextLine();
                    System.out.print("");
                    System.out.println("Oprima Enter para continuar");
                    String enter = scanner.nextLine();
                    bytes = pantalla.devolverImagenConsola();
                    videoBytes = pantalla.devolverVideoConsola();
                        {
                            try {
                                fachada.ingresarDatos(documentoIdentidad, nombre, apellidos, fechaNacimiento, email, direccionResidencia, numeroContacto, cargo, genero, fechaInicio, fechaFinal, salario, id, bytes, videoBytes);
                            } catch (SQLException ex) {
                                Logger.getLogger(Consola.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;

                case 2:
                    System.out.println("Por favor dijite el id del empleado");
                    System.out.println("ID: ");
                    int iD = scanner.nextInt();
                    {        
                        try {
                            personaDto = fachada.mostrarDatos(iD);
                        } catch (SQLException ex) {
                            Logger.getLogger(Consola.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                      
                    System.out.println("Documento de Identidad: " + personaDto.getId());
                    System.out.println("Nombre: "+personaDto.getNombres());
                    System.out.println("Apellidos: "+personaDto.getApellidos());
                    System.out.println("Fecha de Nacimiento: "+personaDto.getFechaNacimiento());
                    System.out.println("email: "+personaDto.getEmail());
                    System.out.println("direccion: "+personaDto.getDireccion());
                    System.out.println("Numero de Contacto: "+personaDto.getNumeroContacto());
                    System.out.println("Genero: "+personaDto.getGenero());

                case 3:
                    System.out.println("Saliendo del programa...");
                    System.exit(0);
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}
