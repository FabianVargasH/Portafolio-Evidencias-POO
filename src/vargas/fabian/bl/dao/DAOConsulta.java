package vargas.fabian.bl.dao;

import vargas.fabian.bl.entities.Consulta;
import vargas.fabian.dl.Conector;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOConsulta {
    private static String statement;
    private static String query;

    // Insertar consulta
    public static String insertarConsulta(Consulta consulta) throws SQLException, IOException, ClassNotFoundException {
        statement = "INSERT INTO t_consulta VALUES ('" + consulta.getId() + "', '" +
                consulta.getDiagnostico() + "', '" +
                consulta.getTratamiento() + "', '" +
                Date.valueOf(consulta.getFecha()) + "', '" +
                consulta.getPaciente().getId() + "');";
        Conector.getConexion().ejecutarStatement(statement);
        return "Consulta registrada exitosamente. ID: " + consulta.getId();
    }

    // Obtener historial médico de un paciente (todas sus consultas)
    public static void listarHistorialPorPaciente(String pacienteId) throws SQLException, IOException, ClassNotFoundException {
        query = "SELECT * FROM t_consulta WHERE paciente_id = ? ORDER BY fecha DESC;";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query, pacienteId);
        if (!resultado.next()) {
            System.out.println("No hay consultas registradas en el historial de este paciente.");
            return;
        }
        do {
            System.out.println("\nID Consulta: " + resultado.getString("id"));
            System.out.println("Fecha de la consulta: " + resultado.getDate("fecha"));
            System.out.println("Diagnóstico: " + resultado.getString("diagnostico"));
            System.out.println("Tratamiento: " + resultado.getString("tratamiento"));
        } while (resultado.next());
    }
}