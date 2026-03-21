package vargas.fabian.bl;

import java.util.ArrayList;

public class Paciente extends Persona{
    private HistorialMedico historial;
    private ArrayList<Cita>citas;

    //Constructor vacio
    public Paciente(){
        this.historial = new HistorialMedico();
        this.citas = new ArrayList<>();
    }

    //Constructor completo
    public Paciente(String id, String nombre, String telefono, HistorialMedico historial, ArrayList<Cita>citas){
        super(id,nombre,telefono);
        this.historial = historial;
        this.citas = citas;
    }

    public Paciente(String id, String nombre, String telefono) {
        super(id, nombre, telefono);
        this.historial = new HistorialMedico();
        this.citas = new ArrayList<>();
    }

    //getters
    public HistorialMedico getHistorial(){
        return historial;
    }
    public ArrayList<Cita>getCitas(){
        return citas;
    }

    //Setters
    public void setHistorial(HistorialMedico historial){
        this.historial = historial;
    }
    public void setCitas(ArrayList<Cita>citas){
        this.citas = citas;
    }

    //Metodo propio del paciente
    public void agregarCita(Cita citaAgregada){
        citas.add(citaAgregada);
    }
    //toString
    @Override
    public String toString(){
        return super.toString() + "\nCitas: " + citas.size();
    }
}
