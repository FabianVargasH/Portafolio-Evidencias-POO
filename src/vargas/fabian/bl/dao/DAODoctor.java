package vargas.fabian.bl.dao;

import vargas.fabian.bl.entities.Doctor;
import vargas.fabian.dl.Conector;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAODoctor {
    private static String statement;
    private static String query;

    // Insertar doctor
    public static String insertarDoctor(Doctor doctor) throws SQLException, IOException, ClassNotFoundException {
        statement = "INSERT INTO t_doctor VALUES ('" + doctor.getId() + "', '" +
                doctor.getNombre() + "', '" +
                doctor.getTelefono() + "', '" +
                doctor.getEspecialidad() + "');";
        Conector.getConexion().ejecutarStatement(statement);
        return "Doctor registrado exitosamente. ID: " + doctor.getId();
    }

    // Seleccionar doctor por ID
    public static Doctor seleccionarDoctor(String id) throws SQLException, IOException, ClassNotFoundException {
        query = "SELECT * FROM t_doctor WHERE id = ?;";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query, id);
        if (!resultado.next()) return null;
        return new Doctor(
                resultado.getString("id"),
                resultado.getString("nombre"),
                resultado.getString("telefono"),
                resultado.getString("especialidad")
        );
    }

    // Verificar si existe un doctor
    public static boolean existeDoctor(String id) throws SQLException, IOException, ClassNotFoundException {
        query = "SELECT id FROM t_doctor WHERE id = ?;";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query, id);
        return resultado.next();
    }

    // Listar todos los doctores
    public static void listarDoctores() throws SQLException, IOException, ClassNotFoundException {
        query = "SELECT * FROM t_doctor;";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);
        if (!resultado.next()) {
            System.out.println("No hay doctores registrados.");
            return;
        }
        do {
            System.out.println("\nID: " + resultado.getString("id"));
            System.out.println("Nombre: " + resultado.getString("nombre"));
            System.out.println("Teléfono: " + resultado.getString("telefono"));
            System.out.println("Especialidad: " + resultado.getString("especialidad"));
        } while (resultado.next());
    }
}