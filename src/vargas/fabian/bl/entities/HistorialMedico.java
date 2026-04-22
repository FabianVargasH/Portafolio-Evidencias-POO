package vargas.fabian.bl.entities;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class HistorialMedico {
    private ArrayList<Consulta> consultas;

    //Constructor vacio
    public HistorialMedico(){
        this.consultas = new ArrayList<>();
    }

    //Constructor completo
    public HistorialMedico(ArrayList<Consulta>consultas){
        this.consultas = consultas;
    }
    public ArrayList<Consulta>getConsultas(){
        return consultas;
    }
    public void setConsultas(ArrayList<Consulta>consultas){
        this.consultas = consultas;
    }

    // Agregar una consulta al historial
    public void agregarConsulta(Consulta consulta) {
        consultas.add(consulta);
    }

    // Metodo para agregar consulta por parámetros (crea el objeto Consulta)
    public void agregarConsulta(String diagnostico, String tratamiento, LocalDate fecha, Paciente paciente) throws SQLException, IOException, ClassNotFoundException, IOException {
        Consulta consulta = new Consulta(diagnostico, tratamiento, fecha, paciente);
        consultas.add(consulta);
    }

    @Override
    public String toString(){
        return "Historial médico registrado: " + consultas.size();
    }
}
