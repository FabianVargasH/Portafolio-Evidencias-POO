package vargas.fabian.bl.entities;

public abstract class Persona {
    protected String id;
    protected String nombre;
    protected String telefono;

    public Persona(){}

    // Constructor para NUEVOS objetos
    public Persona(String id, String nombre, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    // Constructor para objetos EXISTENTES
    public Persona(String id, String nombre, String telefono, boolean esExistente) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    //Getters
    public String getId(){
        return id;
    }
    public String getNombre(){
        return nombre;
    }
    public String getTelefono(){
        return telefono;
    }
    //Setters
    public void setId(String id){
        this.id = id;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setTelefono(String telefono){
        this.telefono = telefono;
    }

    @Override
    public String toString(){
        return "\nId: " + id + "\nNombre: " + nombre + "\nTelefono: " + telefono;
    }
}
