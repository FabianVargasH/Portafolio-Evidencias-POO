package vargas.fabian.ui;

import vargas.fabian.tl.Controlador;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Menu {
    public static BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

    public static void menuPrincipal() throws Exception {
        boolean continuar = true;
        while(continuar){
            System.out.println("\n--- SISTEMA HOSPITALARIO ---");
            System.out.println("1. Registrar Paciente");
            System.out.println("2. Registrar Doctor");
            System.out.println("3. Crear Cita");
            System.out.println("4. Registrar Consulta");
            System.out.println("5. Ver Historial Médico");
            System.out.println("6. Listar Pacientes");
            System.out.println("7. Listar Doctores");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");

            int opcion;
            try{
                opcion = Integer.parseInt(entrada.readLine());
            } catch(NumberFormatException e){
                System.out.println("Opción inválida");
                continue;
            }

            switch(opcion){
                case 1:
                    Controlador.registrarPaciente();
                    break;
                case 2:
                    Controlador.registrarDoctor();
                    break;
                case 3:
                    Controlador.crearCita();
                    break;
                case 4:
                    Controlador.registrarConsulta();
                    break;
                case 5:
                    Controlador.verHistorial();
                    break;
                case 6:
                    Controlador.listarPacientes();
                    break;
                case 7:
                    Controlador.listarDoctores();
                    break;
                case 0:
                    System.out.print("¿Está seguro que desea salir? (S/N): ");
                    String confirmacion = entrada.readLine();
                    if(confirmacion.equalsIgnoreCase("S")){
                        System.out.println("Saliendo del sistema...");
                        continuar = false;
                    }
                    break;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }
    }
}