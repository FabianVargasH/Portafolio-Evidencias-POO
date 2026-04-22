package vargas.fabian.bl.dao;

import vargas.fabian.bl.entities.Paciente;
import vargas.fabian.dl.Conector;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOPaciente {
    private static String statement;
    private static String query;

    // Insertar paciente
    public static String insertarPaciente(Paciente paciente) throws SQLException, IOException, ClassNotFoundException {
        statement = "INSERT INTO t_paciente VALUES ('" + paciente.getId() + "', '" +
                paciente.getNombre() + "', '" +
                paciente.getTelefono() + "');";
        Conector.getConexion().ejecutarStatement(statement);
        return "Paciente registrado exitosamente. ID: " + paciente.getId();
    }

    // Seleccionar paciente por ID
    public static Paciente seleccionarPaciente(String id) throws SQLException, IOException, ClassNotFoundException {
        query = "SELECT * FROM t_paciente WHERE id = ?;";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query, id);
        if (!resultado.next()) return null;
        return new Paciente(
                resultado.getString("id"),
                resultado.getString("nombre"),
                resultado.getString("telefono")
        );
    }

    // Verificar si existe un paciente
    public static boolean existePaciente(String id) throws SQLException, IOException, ClassNotFoundException {
        query = "SELECT id FROM t_paciente WHERE id = ?;";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query, id);
        return resultado.next();
    }

    // Listar todos los pacientes
    public static void listarPacientes() throws SQLException, IOException, ClassNotFoundException {
        query = "SELECT * FROM t_paciente;";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);
        if (!resultado.next()) {
            System.out.println("No hay pacientes registrados.");
            return;
        }
        do {
            System.out.println("\nID: " + resultado.getString("id"));
            System.out.println("Nombre: " + resultado.getString("nombre"));
            System.out.println("Teléfono: " + resultado.getString("telefono"));
        } while (resultado.next());
    }
}