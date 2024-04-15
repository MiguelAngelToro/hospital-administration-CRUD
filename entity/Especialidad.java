package entity;

public class Especialidad {

    private int idEspecialidad;
    private String nombre;
    private String descripcion;


    public Especialidad(){

    }

    public Especialidad( String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "idEspecialidad=" + idEspecialidad +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'';
    }
}
