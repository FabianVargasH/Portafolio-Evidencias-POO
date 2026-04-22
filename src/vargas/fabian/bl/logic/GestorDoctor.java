package vargas.fabian.bl.logic;

import vargas.fabian.bl.dao.DAODoctor;
import vargas.fabian.bl.entities.Doctor;

import java.io.IOException;
import java.sql.SQLException;

public class GestorDoctor {

    // Registrar nuevo doctor
    public static String registrarDoctor(String nombre, String telefono, String especialidad) throws SQLException, IOException, ClassNotFoundException {
        Doctor doctor = new Doctor(nombre, telefono, especialidad);
        return DAODoctor.insertarDoctor(doctor);
    }

    // Buscar doctor por ID
    public static Doctor buscarDoctor(String id) throws SQLException, IOException, ClassNotFoundException {
        return DAODoctor.seleccionarDoctor(id);
    }

    // Verificar si existe un doctor
    public static boolean existeDoctor(String id) throws SQLException, IOException, ClassNotFoundException {
        return DAODoctor.existeDoctor(id);
    }

    // Listar todos los doctores
    public static void listarDoctores() throws SQLException, IOException, ClassNotFoundException {
        DAODoctor.listarDoctores();
    }
}