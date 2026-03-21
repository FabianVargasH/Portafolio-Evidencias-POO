package vargas.fabian.bl;

import java.util.ArrayList;

public class Doctor extends Persona{
    private String especialidad;
    private ArrayList<Cita>citas;

    //constructor vacio
    public Doctor(){
        this.citas = new ArrayList<>();
    }

    //constructor completo
    public Doctor(String id, String nombre, String telefono, String especialidad, ArrayList<Cita>citas){
        super(id,nombre,telefono);
        this.especialidad = especialidad;
        this.citas = citas;
    }

    public Doctor(String id, String nombre, String telefono, String especialidad){
        super(id,nombre,telefono);
        this.especialidad = especialidad;
        this.citas = new ArrayList<>();
    }


    //getters
    public String getEspecialidad(){
        return especialidad;
    }
    public ArrayList<Cita>getCitas(){
        return citas;
    }
    //Setters
    public void setEspecialidad(String especialidad){
        this.especialidad = especialidad;
    }
    public void setCitas(ArrayList<Cita> citas){
        this.citas = citas;
    }

    public void agregarCita(Cita citaAgregada){
        citas.add(citaAgregada);
    }

    @Override
    public String toString(){
        return super.toString() + "\nEspecialidad: " + especialidad;
    }
}