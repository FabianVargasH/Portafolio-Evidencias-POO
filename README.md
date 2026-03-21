# Portafolio de Evidencias | Sistema Hospitalario <img width="101" height="90" alt="Logo Cenfotec Actual (1)" src="https://github.com/user-attachments/assets/5f9d3642-62e3-4428-8457-f4b7fe77c93e" />


Sistema de gestión hospitalaria básica utilizando los principios de Programación Orientada a Objetos.

## Descripción

Permite registrar pacientes y doctores, coordinar citas médicas entre ellos, y mantener un historial de consultas por paciente. 

## Estructura del proyecto

```
src/
├── vargas.fabian.ui/
│   ├── Main.java            # Punto de entrada, arranca el sistema
│   └── Menu.java            # Interfaz de usuario por consola
└── vargas.fabian.bl/
    ├── Persona.java          # Clase abstracta base
    ├── Paciente.java         # Extiende Persona
    ├── Doctor.java           # Extiende Persona
    ├── Cita.java             # Representa una cita médica
    ├── HistorialMedico.java  # Historial de consultas de un paciente
    └── Consulta.java         # Registro de una consulta médica
```

## Funcionalidades

- Registrar pacientes y doctores
- Crear citas entre paciente y doctor con fecha
- Registrar consultas médicas (requiere cita previa)
- Ver historial médico de un paciente
- Listar pacientes y doctores registrados

## Autor
Fabián Vargas Hidalgo
