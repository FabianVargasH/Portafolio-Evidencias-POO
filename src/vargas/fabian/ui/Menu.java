package vargas.fabian.ui;

import java.io.*;
import vargas.fabian.bl.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Menu {

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final ArrayList<Paciente> pacientes = new ArrayList<>();
    private final ArrayList<Doctor> doctores  = new ArrayList<>();
    private final ArrayList<Cita> citas     = new ArrayList<>();

    public void iniciar() throws IOException {
        int opcion;
        do {
            mostrarMenu();
            try {
                opcion = Integer.parseInt(br.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Ingrese un numero.");
                opcion = -1;
                continue;
            }
            switch (opcion) {
                case 1: registrarPaciente(pacientes); break;
                case 2: registrarDoctor(doctores);    break;
                case 3: crearCita(pacientes, doctores, citas); break;
                case 4: registrarConsulta(pacientes); break;
                case 5: verHistorial(pacientes);      break;
                case 6: listarPacientes(pacientes);   break;
                case 7: listarDoctores(doctores);     break;
                case 0:
                    System.out.print("¿Desea salir? Los datos no se guardarán. (s/n): ");
                    String confirmacion = br.readLine();
                    if (!confirmacion.equalsIgnoreCase("s")) {
                        opcion = -1;
                    } else {
                        System.out.println("Saliendo...");
                    }
                    break;
                default: System.out.println("Opcion invalida."); break;
            }
        } while (opcion != 0);
    }

    private void mostrarMenu() {
        System.out.println("\n--- SISTEMA HOSPITALARIO ---");
        System.out.println("1. Registrar Paciente");
        System.out.println("2. Registrar Doctor");
        System.out.println("3. Crear Cita");
        System.out.println("4. Registrar Consulta");
        System.out.println("5. Ver Historial Medico");
        System.out.println("6. Listar Pacientes");
        System.out.println("7. Listar Doctores");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opcion: ");
    }

    private LocalDate pedirFecha(String mensaje) throws IOException {
        while (true) {
            System.out.print(mensaje + " (dd/MM/yyyy): ");
            String entrada = br.readLine();
            try {
                return LocalDate.parse(entrada, FORMATO_FECHA);
            } catch (DateTimeParseException e) {
                System.out.println("Formato invalido. Use dd/MM/yyyy (ej: 25/12/2025).");
            }
        }
    }

    private void registrarPaciente(ArrayList<Paciente> pacientes) throws IOException {
        System.out.print("ID: ");
        String id = br.readLine();

        if (buscarPaciente(pacientes, id) != null) {
            System.out.println("Ya existe un paciente con ese ID.");
            return;
        }

        System.out.print("Nombre: ");
        String nombre = br.readLine();

        System.out.print("Telefono: ");
        String telefono = br.readLine();

        pacientes.add(new Paciente(id, nombre, telefono));
        System.out.println("Paciente registrado.");
    }

    private void listarPacientes(ArrayList<Paciente> pacientes) {
        if (pacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
            return;
        }
        System.out.println("\n--- PACIENTES REGISTRADOS ---");
        for (int i = 0; i < pacientes.size(); i++) {
            Paciente paciente = pacientes.get(i);
            System.out.println("ID: " + paciente.getId() + " | Nombre: " + paciente.getNombre() + " | Tel: " + paciente.getTelefono());
        }
    }

    private Paciente buscarPaciente(ArrayList<Paciente> pacientes, String id) {
        for (int i = 0; i < pacientes.size(); i++) {
            if (pacientes.get(i).getId().equals(id)) return pacientes.get(i);
        }
        return null;
    }

    private void registrarDoctor(ArrayList<Doctor> doctores) throws IOException {
        System.out.print("ID: ");
        String id = br.readLine();

        if (buscarDoctor(doctores, id) != null) {
            System.out.println("Ya existe un doctor con ese ID.");
            return;
        }

        System.out.print("Nombre: ");
        String nombre = br.readLine();

        System.out.print("Telefono: ");
        String telefono = br.readLine();

        System.out.print("Especialidad: ");
        String especialidad = br.readLine();

        doctores.add(new Doctor(id, nombre, telefono, especialidad));
        System.out.println("Doctor registrado.");
    }

    private void listarDoctores(ArrayList<Doctor> doctores) {
        if (doctores.isEmpty()) {
            System.out.println("No hay doctores registrados.");
            return;
        }
        System.out.println("\n--- DOCTORES REGISTRADOS ---");
        for (int i = 0; i < doctores.size(); i++) {
            Doctor doctor = doctores.get(i);
            System.out.println("ID: " + doctor.getId() + " | Nombre: " + doctor.getNombre() + " | Especialidad: " + doctor.getEspecialidad());
        }
    }

    private Doctor buscarDoctor(ArrayList<Doctor> doctores, String id) {
        for (int i = 0; i < doctores.size(); i++) {
            if (doctores.get(i).getId().equals(id)) return doctores.get(i);
        }
        return null;
    }

    private void crearCita(ArrayList<Paciente> pacientes, ArrayList<Doctor> doctores, ArrayList<Cita> citas) throws IOException {
        System.out.print("ID Paciente: ");
        Paciente paciente = buscarPaciente(pacientes, br.readLine());

        System.out.print("ID Doctor: ");
        Doctor doctor = buscarDoctor(doctores, br.readLine());

        if (paciente == null || doctor == null) {
            System.out.println("Paciente o Doctor no encontrado.");
            return;
        }

        LocalDate fecha = pedirFecha("Fecha de la cita");

        Cita cita = new Cita(fecha, paciente, doctor, "Pendiente");
        citas.add(cita);
        paciente.agregarCita(cita);
        doctor.agregarCita(cita);
        System.out.println("Cita creada para el " + fecha.format(FORMATO_FECHA) + ".");
    }

    private void registrarConsulta(ArrayList<Paciente> pacientes) throws IOException {
        System.out.print("ID Paciente: ");
        Paciente paciente = buscarPaciente(pacientes, br.readLine());

        if (paciente == null) {
            System.out.println("Paciente no encontrado.");
            return;
        }
        if (paciente.getCitas().isEmpty()) {
            System.out.println("El paciente no tiene citas registradas. Debe crear una cita primero.");
            return;
        }
        System.out.print("Diagnostico: ");
        String diagnostico = br.readLine();
        System.out.print("Tratamiento: ");
        String tratamiento = br.readLine();
        LocalDate fecha = pedirFecha("Fecha de la consulta");
        paciente.getHistorial().agregarConsulta(diagnostico, tratamiento, fecha);
        System.out.println("Consulta registrada para el " + fecha.format(FORMATO_FECHA) + ".");
    }

    private void verHistorial(ArrayList<Paciente> pacientes) throws IOException {
        System.out.print("ID Paciente: ");
        Paciente paciente = buscarPaciente(pacientes, br.readLine());
        if (paciente == null) {
            System.out.println("Paciente no encontrado.");
            return;
        }
        ArrayList<Consulta> consultas = paciente.getHistorial().getConsultas();
        if (consultas.isEmpty()) {
            System.out.println("No hay consultas registradas para este paciente.");
            return;
        }
        System.out.println("\n--- HISTORIAL DE " + paciente.getNombre() + " ---");
        for (int i = 0; i < consultas.size(); i++) {
            System.out.println(consultas.get(i));
        }
    }
}