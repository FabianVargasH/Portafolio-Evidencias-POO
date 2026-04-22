package vargas.fabian.bl.entities;

import vargas.fabian.dl.Conector;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Doctor extends Persona {
    private String especialidad;
    private ArrayList<Cita> citas;  // ← Lista de citas del doctor
    private static int contador = 1;

    public Doctor(String nombre, String telefono, String especialidad) throws SQLException, IOException, ClassNotFoundException {
        super(generarId(), nombre, telefono);
        this.especialidad = especialidad;
        this.citas = new ArrayList<>();
    }

    public Doctor(String id, String nombre, String telefono, String especialidad) {
        super(id, nombre, telefono, true);
        this.especialidad = especialidad;
        this.citas = new ArrayList<>();
    }

    private static int obtenerUltimoNumero() throws SQLException, IOException, ClassNotFoundException {
        String query = "SELECT id FROM t_doctor ORDER BY id DESC LIMIT 1;";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);
        if (!resultado.next()) return 0;
        String id = resultado.getString("id");
        return Integer.parseInt(id.substring(2));
    }

    private static String generarId() throws SQLException, IOException, ClassNotFoundException {
        int ultimoNumero = obtenerUltimoNumero();
        contador = ultimoNumero + 1;
        return "D-" + contador;
    }

    public String getEspecialidad() { return especialidad; }
    public ArrayList<Cita> getCitas() { return citas; }

    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    public void setCitas(ArrayList<Cita> citas) { this.citas = citas; }

    public void agregarCita(Cita cita) {
        citas.add(cita);
    }

    @Override
    public String toString() {
        return super.toString() + "\nEspecialidad: " + especialidad + "\nCitas: " + citas.size();
    }
}