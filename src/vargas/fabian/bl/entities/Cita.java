package vargas.fabian.bl.entities;

import vargas.fabian.dl.Conector;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Cita {
    private String id;
    private LocalDate fecha;
    private Paciente paciente;
    private Doctor doctor;
    private String estado;
    private static int contador = 1;

    //Constructor vacio
    public Cita(){}

    //onstructor para NUEVA cita (genera ID)
    public Cita(LocalDate fecha, Paciente paciente, Doctor doctor, String estado) throws SQLException, IOException, ClassNotFoundException {
        this.id = generarId();
        this.fecha = fecha;
        this.paciente = paciente;
        this.doctor = doctor;
        this.estado = estado;
    }

    // Constructor para cita EXISTENTE
    public Cita(String id, LocalDate fecha, Paciente paciente, Doctor doctor, String estado) {
        this.id = id;
        this.fecha = fecha;
        this.paciente = paciente;
        this.doctor = doctor;
        this.estado = estado;
    }

    // Obtener el último número de ID usado
    private static int obtenerUltimoNumero() throws SQLException, IOException, ClassNotFoundException, IOException {
        String query = "SELECT id FROM t_cita ORDER BY id DESC LIMIT 1;";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);
        if (!resultado.next()) return 0;
        String id = resultado.getString("id");
        return Integer.parseInt(id.substring(2));
    }

    // Generar nuevo ID correlativo
    private static String generarId() throws SQLException, IOException, ClassNotFoundException {
        int ultimoNumero = obtenerUltimoNumero();
        contador = ultimoNumero + 1;
        return "C-" + contador;
    }

    //Getters
    public String getId() { return id; }
    public LocalDate getFecha() { return fecha; }
    public Paciente getPaciente() { return paciente; }
    public Doctor getDoctor() { return doctor; }
    public String getEstado() { return estado; }

    //Setters
    public void setId(String id) { this.id = id; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "Cita: " + id + " | Fecha: " + fecha + " | Paciente: " + paciente.getNombre() + " | Doctor: " + doctor.getNombre() + " | Estado: " + estado;
    }

}
