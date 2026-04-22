package vargas.fabian.bl.dao;

import vargas.fabian.bl.entities.Cita;
import vargas.fabian.bl.entities.Doctor;
import vargas.fabian.bl.entities.Paciente;
import vargas.fabian.dl.Conector;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class DAOCita {
    private static String statement;
    private static String query;

    // Insertar cita
    public static String insertarCita(Cita cita) throws SQLException, IOException, ClassNotFoundException {
        statement = "INSERT INTO t_cita VALUES ('" + cita.getId() + "', '" +
                Date.valueOf(cita.getFecha()) + "', '" +
                cita.getPaciente().getId() + "', '" +
                cita.getDoctor().getId() + "', '" +
                cita.getEstado() + "');";
        Conector.getConexion().ejecutarStatement(statement);
        return "Cita creada exitosamente. ID: " + cita.getId();
    }

    // Seleccionar cita por ID
    public static Cita seleccionarCita(String id) throws SQLException, IOException, ClassNotFoundException {
        query = "SELECT * FROM t_cita WHERE id = ?;";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query, id);
        if (!resultado.next()) return null;

        Paciente paciente = DAOPaciente.seleccionarPaciente(resultado.getString("paciente_id"));
        Doctor doctor = DAODoctor.seleccionarDoctor(resultado.getString("doctor_id"));

        return new Cita(
                resultado.getString("id"),
                resultado.getDate("fecha").toLocalDate(),
                paciente,
                doctor,
                resultado.getString("estado")
        );
    }

    // Obtener citas por paciente
    public static void listarCitasPorPaciente(String pacienteId) throws SQLException, IOException, ClassNotFoundException {
        query = "SELECT * FROM t_cita WHERE paciente_id = ? ORDER BY fecha DESC;";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query, pacienteId);
        if (!resultado.next()) {
            System.out.println("No hay citas registradas para este paciente.");
            return;
        }
        do {
            System.out.println("\nID: " + resultado.getString("id"));
            System.out.println("Fecha: " + resultado.getDate("fecha"));
            System.out.println("Doctor ID: " + resultado.getString("doctor_id"));
            System.out.println("Estado: " + resultado.getString("estado"));
        } while (resultado.next());
    }

    // Verificar si existe una cita previa a una fecha
    public static boolean existeCitaPrevia(String pacienteId, LocalDate fecha) throws SQLException, IOException, ClassNotFoundException {
        query = "SELECT COUNT(*) FROM t_cita WHERE paciente_id = ? AND fecha <= ?;";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query, pacienteId, Date.valueOf(fecha).toString());
        resultado.next();
        return resultado.getInt(1) > 0;
    }

    // Buscar cita previa que NO esté completada
    public static String buscarCitaPreviaNoCompletada(String pacienteId, LocalDate fecha) throws SQLException, IOException, ClassNotFoundException {
        query = "SELECT id FROM t_cita WHERE paciente_id = ? AND fecha <= ? AND estado != 'Completada' ORDER BY fecha DESC LIMIT 1;";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query, pacienteId, Date.valueOf(fecha).toString());

        if (!resultado.next()) {
            return null;
        }
        return resultado.getString("id");
    }

    // Actualizar estado de una cita
    public static String actualizarEstadoCita(String idCita, String nuevoEstado)
            throws SQLException, IOException, ClassNotFoundException {
        statement = "UPDATE t_cita SET estado = ? WHERE id = ?;";
        Conector.getConexion().ejecutarStatement(statement, nuevoEstado, idCita);
        return "Estado de cita actualizado a: " + nuevoEstado;
    }
}