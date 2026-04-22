# Portafolio de Evidencias | Sistema Hospitalario <img width="101" height="90" alt="Logo Cenfotec Actual (1)" src="https://github.com/user-attachments/assets/5f9d3642-62e3-4428-8457-f4b7fe77c93e" />


Sistema de gestión hospitalaria básica utilizando los principios de Programación Orientada a Objetos, Patrones de Diseño y Base de Datos MySQL.

## Descripción

Permite registrar pacientes y doctores, coordinar citas médicas entre ellos, y mantener un historial de consultas por paciente. Los datos se almacenan de forma persistente en una base de datos MySQL, utilizando una arquitectura por capas (UI, Controlador, Gestor, DAO, BD). 

## Estructura del proyecto

```
src/
├── vargas.fabian.ui/
│ └── Menu.java # Interfaz de usuario por consola
├── vargas.fabian.tl/
│ └── Controlador.java # Coordina la lógica entre UI y Gestores
├── vargas.fabian.bl.logic/
│ ├── GestorPaciente.java
│ ├── GestorDoctor.java
│ ├── GestorCita.java
│ └── GestorConsulta.java
├── vargas.fabian.bl.dao/
│ ├── DAOPaciente.java
│ ├── DAODoctor.java
│ ├── DAOCita.java
│ └── DAOConsulta.java
├── vargas.fabian.bl.entities/
│ ├── Persona.java # Clase abstracta base
│ ├── Paciente.java
│ ├── Doctor.java
│ ├── Cita.java
│ ├── HistorialMedico.java
│ └── Consulta.java
├── vargas.fabian.dl/
│ ├── AccesoBD.java # Conexión y ejecución de queries
│ └── Conector.java # Singleton de conexión a BD
├── vargas.fabian.utils/
│ └── Utilidades.java # Lectura de credenciales (bd.properties)
└── Main.java # Punto de entrada
```
## Patrones de Diseño aplicados

- **DAO (Data Access Object)**: Separa la lógica de acceso a datos de la lógica de negocio.
- **Singleton**: En `Conector.java` para una única instancia de conexión a la BD.
- **Gestor/Service**: Capa intermedia entre Controlador y DAOs.

## Principios POO aplicados

- **Abstracción**: Clase abstracta `Persona`.
- **Herencia**: `Paciente` y `Doctor` heredan de `Persona`.
- **Polimorfismo**: Métodos como `toString()` se comportan diferente según la clase.
- **Encapsulamiento**: Atributos privados con getters/setters.


## Base de Datos

- **Motor**: MySQL
- **Tablas**: `t_paciente`, `t_doctor`, `t_cita`, `t_consulta`
- **Trigger**: `validar_consulta_con_cita` (evita consultas sin cita previa)
- **Credenciales**: Archivo `bd.properties`

## Funcionalidades

- Registrar pacientes y doctores (IDs auto-generados con formato `P-1`, `D-1`)
- Crear citas entre paciente y doctor con fecha (`C-1`, `C-2`)
- Registrar consultas médicas (requiere cita previa y la marca como Completada)
- Ver historial médico completo de un paciente
- Listar todos los pacientes y doctores registrados

## Autor
Fabián Vargas Hidalgo
