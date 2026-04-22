package vargas.fabian.bl.logic;

import vargas.fabian.bl.dao.DAOPaciente;
import vargas.fabian.bl.entities.Paciente;

import java.io.IOException;
import java.sql.SQLException;

public class GestorPaciente {

    // Registrar nuevo paciente
    public static String registrarPaciente(String nombre, String telefono) throws SQLException, IOException, ClassNotFoundException {
        Paciente paciente = new Paciente(nombre, telefono);
        return DAOPaciente.insertarPaciente(paciente);
    }

    // Buscar paciente por ID
    public static Paciente buscarPaciente(String id) throws SQLException, IOException, ClassNotFoundException {
        return DAOPaciente.seleccionarPaciente(id);
    }

    // Verificar si existe un paciente
    public static boolean existePaciente(String id) throws SQLException, IOException, ClassNotFoundException {
        return DAOPaciente.existePaciente(id);
    }

    // Listar todos los pacientes
    public static void listarPacientes() throws SQLException, IOException, ClassNotFoundException {
        DAOPaciente.listarPacientes();
    }
}