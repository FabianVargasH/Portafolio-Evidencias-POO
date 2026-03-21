package vargas.fabian.bl;

import java.time.LocalDate;

public class Cita {
    private LocalDate fecha;
    private Paciente paciente;
    private Doctor doctor;
    private String estado;

    //Constructor vacio
    public Cita(){}

    //Constructor completo
    public Cita(LocalDate fecha, Paciente paciente, Doctor doctor, String estado){
        this.fecha = fecha;
        this.paciente = paciente;
        this.doctor = doctor;
        this.estado = estado;
    }

    //Getters
    public LocalDate getFecha(){
        return fecha;
    }
    public Paciente getPaciente(){
        return paciente;
    }
    public Doctor getDoctor(){
        return doctor;
    }
    public String getEstado(){
        return estado;
    }

    //Setters
    public void setFecha(LocalDate fecha){
        this.fecha = fecha;
    }
    public void setPaciente(Paciente paciente){
        this.paciente = paciente;
    }
    public void setDoctor(Doctor doctor){
        this.doctor = doctor;
    }
    public void setEstado (String estado){
        this.estado = estado;
    }
    @Override
    public String toString(){
        return "Cita: " + fecha + "\nPaciente: " + paciente.getNombre() + "\nDoctor: " + doctor.getNombre();
    }
}