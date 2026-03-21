package vargas.fabian.bl;

import java.time.LocalDate;

public class Consulta {
    private String diagnostico;
    private String tratamiento;
    private LocalDate fecha;

    //Constructor vacio
    public Consulta() {}

    //Constructor completo
    public Consulta(String diagnostico, String tratamiento, LocalDate fecha) {
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.fecha = fecha;
    }

    //Getters
    public String getDiagnostico() { return diagnostico; }
    public String getTratamiento() { return tratamiento; }
    public LocalDate getFecha() { return fecha; }

    //setters
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }
    public void setTratamiento(String tratamiento) { this.tratamiento = tratamiento; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    @Override
    public String toString() {
        return "Fecha: " + fecha + ", Diagnóstico: " + diagnostico + ", Tratamiento: " + tratamiento;
    }
}
