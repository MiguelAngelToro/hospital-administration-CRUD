package entity;

public class  Medico {

    private int idMedico;
    private String nombre;
    private String apellidos;

    private int fkIdEspecialidad;

    private Especialidad objEspecialidad;



    public Medico (){

    }
    public Medico(String nombre, String apellidos, int fkIdEspecialidad,Especialidad objEspecialidad) {

        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fkIdEspecialidad = fkIdEspecialidad;
        this.objEspecialidad = objEspecialidad;

    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getFkIdEspecialidad() {
        return fkIdEspecialidad;
    }

    public void setFkIdEspecialidad(int fkIdEspecialidad) {
        this.fkIdEspecialidad = fkIdEspecialidad;
    }

    public Especialidad getObjEspecialidad() {
        return objEspecialidad;
    }

    public void setObjEspecialidad(Especialidad objEspecialidad) {
        this.objEspecialidad = objEspecialidad;
    }

    @Override
    public String toString() {
        return "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                "especialidad=" + this.objEspecialidad.getNombre();
    }
}




