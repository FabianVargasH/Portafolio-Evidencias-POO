package vargas.fabian.bl.entities;

import vargas.fabian.dl.Conector;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Consulta {
    private String id;
    private String diagnostico;
    private String tratamiento;
    private LocalDate fecha;
    private Paciente paciente;
    private static int contador = 1;

    //Constructor vacio
    public Consulta() {}

    public Consulta(String diagnostico, String tratamiento, LocalDate fecha, Paciente paciente) throws SQLException, IOException, ClassNotFoundException {
        this.id = generarId();
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.fecha = fecha;
        this.paciente = paciente;
    }

    // Constructor para consulta EXISTENTE
    public Consulta(String id, String diagnostico, String tratamiento, LocalDate fecha, Paciente paciente) {
        this.id = id;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.fecha = fecha;
        this.paciente = paciente;
    }

    // Obtener el último número de ID usado
    private static int obtenerUltimoNumero() throws SQLException, IOException, ClassNotFoundException {
        String query = "SELECT id FROM t_consulta ORDER BY id DESC LIMIT 1;";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);
        if (!resultado.next()) return 0;
        String id = resultado.getString("id");
        return Integer.parseInt(id.substring(2));
    }

    // Generar nuevo ID correlativo
    private static String generarId() throws SQLException, IOException, ClassNotFoundException {
        int ultimoNumero = obtenerUltimoNumero();
        contador = ultimoNumero + 1;
        return "R-" + contador;
    }

    //Getters
    public String getId() { return id; }
    public String getDiagnostico() { return diagnostico; }
    public String getTratamiento() { return tratamiento; }
    public LocalDate getFecha() { return fecha; }
    public Paciente getPaciente() { return paciente; }

    //setters
    public void setId(String id) { this.id = id; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }
    public void setTratamiento(String tratamiento) { this.tratamiento = tratamiento; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }

    @Override
    public String toString() {
        return "Consulta: " + id + " | Fecha: " + fecha + " | Diagnóstico: " + diagnostico + " | Tratamiento: " + tratamiento;
    }
}
