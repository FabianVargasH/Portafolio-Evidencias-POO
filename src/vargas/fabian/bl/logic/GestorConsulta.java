package vargas.fabian.bl.logic;

import vargas.fabian.bl.dao.DAOConsulta;
import vargas.fabian.bl.entities.Consulta;
import vargas.fabian.bl.entities.Paciente;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class GestorConsulta {

    // Registrar una nueva consulta con la validación de cita previa
    public static String registrarConsulta(String diagnostico, String tratamiento, LocalDate fecha, Paciente paciente) throws SQLException, IOException, ClassNotFoundException {
        String idCita = GestorCita.buscarCitaPreviaNoCompletada(paciente.getId(), fecha);
        if (idCita == null) {
            return "Error: No se puede registrar una consulta sin una cita previa válida (no completada).";
        }
        Consulta consulta = new Consulta(diagnostico, tratamiento, fecha, paciente);
        String resultado = DAOConsulta.insertarConsulta(consulta);
        GestorCita.actualizarEstadoCita(idCita, "Completada");
        return resultado + " | Cita " + idCita + " marcada como Completada";
    }

    // Ver historial médico de un paciente
    public static void verHistorial(String pacienteId) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("\n--- HISTORIAL MÉDICO ---");
        DAOConsulta.listarHistorialPorPaciente(pacienteId);
    }
}