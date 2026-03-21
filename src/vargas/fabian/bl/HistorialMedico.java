package vargas.fabian.bl;

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
    public void agregarConsulta(String diagnostico, String tratamiento, LocalDate fecha){
        consultas.add(new Consulta(diagnostico, tratamiento, fecha));
    }

    @Override
    public String toString(){
        return "Consultas: " + consultas.size();
    }
}
