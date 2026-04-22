package vargas.fabian.bl.logic;

import vargas.fabian.bl.dao.DAOCita;
import vargas.fabian.bl.entities.Cita;
import vargas.fabian.bl.entities.Doctor;
import vargas.fabian.bl.entities.Paciente;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class GestorCita {

    // Crear una nueva cita
    public static String crearCita(LocalDate fecha, Paciente paciente, Doctor doctor, String estado) throws SQLException, IOException, ClassNotFoundException {
        Cita cita = new Cita(fecha, paciente, doctor, estado);
        return DAOCita.insertarCita(cita);
    }

    // Buscar cita por ID
    public static Cita buscarCita(String id) throws SQLException, IOException, ClassNotFoundException {
        return DAOCita.seleccionarCita(id);
    }

    // Listar citas de un paciente
    public static void listarCitasPorPaciente(String pacienteId) throws SQLException, IOException, ClassNotFoundException {
        DAOCita.listarCitasPorPaciente(pacienteId);
    }

    // Verificar si existe una cita previa a una fecha (para validar consulta)
    public static boolean existeCitaPrevia(String pacienteId, LocalDate fecha) throws SQLException, IOException, ClassNotFoundException {
        return DAOCita.existeCitaPrevia(pacienteId, fecha);
    }

    // Buscar cita previa no completada
    public static String buscarCitaPreviaNoCompletada(String pacienteId, LocalDate fecha) throws SQLException, IOException, ClassNotFoundException {
        return DAOCita.buscarCitaPreviaNoCompletada(pacienteId, fecha);
    }

    // Actualizar estado de una cita
    public static String actualizarEstadoCita(String idCita, String nuevoEstado) throws SQLException, IOException, ClassNotFoundException {
        return DAOCita.actualizarEstadoCita(idCita, nuevoEstado);
    }
}