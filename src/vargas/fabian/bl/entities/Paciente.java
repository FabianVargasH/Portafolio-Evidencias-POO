package vargas.fabian.bl.entities;

import vargas.fabian.dl.Conector;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Paciente extends Persona {
    private HistorialMedico historial;
    private ArrayList<Cita> citas;
    private static int contador = 1;

    // Constructor para NUEVO paciente
    public Paciente(String nombre, String telefono) throws SQLException, IOException, ClassNotFoundException {
        super(generarId(), nombre, telefono);
        this.historial = new HistorialMedico();
        this.citas = new ArrayList<>();
    }

    // Constructor para paciente EXISTENTE
    public Paciente(String id, String nombre, String telefono) {
        super(id, nombre, telefono, true);
        this.historial = new HistorialMedico();
        this.citas = new ArrayList<>();
    }

    private static int obtenerUltimoNumero() throws SQLException, IOException, ClassNotFoundException {
        String query = "SELECT id FROM t_paciente ORDER BY id DESC LIMIT 1;";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);
        if (!resultado.next()) return 0;
        String id = resultado.getString("id");
        return Integer.parseInt(id.substring(2));
    }

    private static String generarId() throws SQLException, IOException, ClassNotFoundException {
        int ultimoNumero = obtenerUltimoNumero();
        contador = ultimoNumero + 1;
        return "P-" + contador;
    }

    // Getters
    public HistorialMedico getHistorial() { return historial; }
    public ArrayList<Cita> getCitas() { return citas; }

    // Setters
    public void setHistorial(HistorialMedico historial) { this.historial = historial; }
    public void setCitas(ArrayList<Cita> citas) { this.citas = citas; }

    // Metodo para agregar una cita
    public void agregarCita(Cita cita) {
        citas.add(cita);
    }

    @Override
    public String toString() {
        return super.toString() + "\nCitas: " + citas.size();
    }
}