CREATE DATABASE bd_hospital;
USE bd_hospital;

CREATE TABLE t_paciente (
    id VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    telefono VARCHAR(15) NOT NULL
);

CREATE TABLE t_doctor (
   id VARCHAR(20) PRIMARY KEY,
   nombre VARCHAR(100) NOT NULL,
   telefono VARCHAR(15) NOT NULL,
   especialidad VARCHAR(100) NOT NULL
);

CREATE TABLE t_cita (
    id VARCHAR(20) PRIMARY KEY,
    fecha DATE NOT NULL,
    paciente_id VARCHAR(20) NOT NULL,
    doctor_id VARCHAR(20) NOT NULL,
    estado VARCHAR(20) DEFAULT 'Pendiente',
    FOREIGN KEY (paciente_id) REFERENCES t_paciente(id),
    FOREIGN KEY (doctor_id) REFERENCES t_doctor(id)
);

CREATE TABLE t_consulta (
    id VARCHAR(20) PRIMARY KEY,
    diagnostico TEXT NOT NULL,
    tratamiento TEXT NOT NULL,
    fecha DATE NOT NULL,
    paciente_id VARCHAR(20) NOT NULL,
    FOREIGN KEY (paciente_id) REFERENCES t_paciente(id)
);

CREATE TRIGGER validar_consulta_con_cita
    BEFORE INSERT ON t_consulta
    FOR EACH ROW
BEGIN
    DECLARE existe_cita INT;

    SELECT COUNT(*) INTO existe_cita
    FROM t_cita
    WHERE paciente_id = NEW.paciente_id
      AND fecha <= NEW.fecha;

    IF existe_cita = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'No se puede registrar una consulta sin una cita previa.';
END IF;
END;
