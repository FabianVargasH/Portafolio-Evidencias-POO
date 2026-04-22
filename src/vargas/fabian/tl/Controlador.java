package vargas.fabian.tl;

import vargas.fabian.bl.entities.Doctor;
import vargas.fabian.bl.entities.Paciente;
import vargas.fabian.bl.logic.GestorCita;
import vargas.fabian.bl.logic.GestorConsulta;
import vargas.fabian.bl.logic.GestorDoctor;
import vargas.fabian.bl.logic.GestorPaciente;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Controlador {
    public static BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Registrar paciente
    public static void registrarPaciente() throws IOException, SQLException, ClassNotFoundException {
        System.out.println("\n-- Registro de Paciente --");
        System.out.print("Nombre completo: ");
        String nombre = entrada.readLine();
        System.out.print("Teléfono: ");
        String telefono = entrada.readLine();

        String resultado = GestorPaciente.registrarPaciente(nombre, telefono);
        System.out.println(resultado);
    }

    // Registrar doctor
    public static void registrarDoctor() throws IOException, SQLException, ClassNotFoundException {
        System.out.println("\n-- Registro de Doctor --");
        System.out.print("Nombre completo: ");
        String nombre = entrada.readLine();
        System.out.print("Teléfono: ");
        String telefono = entrada.readLine();
        System.out.print("Especialidad: ");
        String especialidad = entrada.readLine();

        String resultado = GestorDoctor.registrarDoctor(nombre, telefono, especialidad);
        System.out.println(resultado);
    }

    // Crear cita
    public static void crearCita() throws IOException, SQLException, ClassNotFoundException {
        System.out.println("\n-- Crear Cita --");

        System.out.print("ID del paciente: ");
        String idPaciente = entrada.readLine();
        Paciente paciente = GestorPaciente.buscarPaciente(idPaciente);

        if(paciente == null){
            System.out.println("No se encontró un paciente con el ID: " + idPaciente);
            return;
        }
        System.out.println("Paciente encontrado: " + paciente.getNombre());

        System.out.print("ID del doctor: ");
        String idDoctor = entrada.readLine();
        Doctor doctor = GestorDoctor.buscarDoctor(idDoctor);

        if(doctor == null){
            System.out.println("No se encontró un doctor con el ID: " + idDoctor);
            return;
        }
        System.out.println("Doctor encontrado: " + doctor.getNombre() + " - " + doctor.getEspecialidad());

        LocalDate fecha = null;
        while(fecha == null){
            try{
                System.out.print("Fecha de la cita (dd/MM/yyyy): ");
                String fechaStr = entrada.readLine();
                fecha = LocalDate.parse(fechaStr, FORMATO_FECHA);
            } catch(DateTimeParseException e){
                System.out.println("Formato de fecha incorrecto. Use dd/MM/yyyy");
            }
        }

        String resultado = GestorCita.crearCita(fecha, paciente, doctor, "Pendiente");
        System.out.println(resultado);
    }

    // Registrar consulta
    public static void registrarConsulta() throws IOException, SQLException, ClassNotFoundException {
        System.out.println("\n-- Registrar Consulta --");

        System.out.print("ID del paciente: ");
        String idPaciente = entrada.readLine();
        Paciente paciente = GestorPaciente.buscarPaciente(idPaciente);

        if(paciente == null){
            System.out.println("No se encontró un paciente con el ID: " + idPaciente);
            return;
        }
        System.out.println("Paciente encontrado: " + paciente.getNombre());

        // Mostrar citas del paciente
        System.out.println("\n--- CITAS DEL PACIENTE ---");
        GestorCita.listarCitasPorPaciente(idPaciente);

        LocalDate fecha = null;
        while(fecha == null){
            try{
                System.out.print("\nFecha de la consulta (dd/MM/yyyy): ");
                String fechaStr = entrada.readLine();
                fecha = LocalDate.parse(fechaStr, FORMATO_FECHA);
            } catch(DateTimeParseException e){
                System.out.println("Formato de fecha incorrecto. Use dd/MM/yyyy");
            }
        }

        System.out.print("Diagnóstico: ");
        String diagnostico = entrada.readLine();

        System.out.print("Tratamiento: ");
        String tratamiento = entrada.readLine();

        String resultado = GestorConsulta.registrarConsulta(diagnostico, tratamiento, fecha, paciente);
        System.out.println(resultado);
    }

    // Ver historial médico
    public static void verHistorial() throws IOException, SQLException, ClassNotFoundException {
        System.out.println("\n-- Ver Historial Médico --");

        System.out.print("ID del paciente: ");
        String idPaciente = entrada.readLine();

        Paciente paciente = GestorPaciente.buscarPaciente(idPaciente);

        if(paciente == null){
            System.out.println("No se encontró un paciente con el ID: " + idPaciente);
            return;
        }

        System.out.println("\n--- Historial médico de " + paciente.getNombre().toUpperCase() + " ---");
        GestorConsulta.verHistorial(idPaciente);
    }

    // Listar pacientes
    public static void listarPacientes() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("\n-- Lista de Pacientes --");
        GestorPaciente.listarPacientes();
    }

    // Listar doctores
    public static void listarDoctores() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("\n-- Lista de Doctores --");
        GestorDoctor.listarDoctores();
    }
}