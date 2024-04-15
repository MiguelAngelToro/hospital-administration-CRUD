package entity;

import java.util.Date;

public class Cita {

    private int idCita;
    private String fechaCita;
    private String horaCita;
    private String motivo;
    private int fkIdPaciente;
    private int fkIdMedico;

    private Paciente paciente;
    private Medico medico;


    public Cita (){

    }

    public Cita(String fechaCita, String horaCita, String motivo, int fkIdPaciente, int fkIdMedico, Paciente paciente, Medico medico) {
        this.fechaCita = fechaCita;
        this.horaCita = horaCita;
        this.motivo = motivo;
        this.fkIdPaciente = fkIdPaciente;
        this.fkIdMedico = fkIdMedico;
        this.paciente = paciente;
        this.medico = medico;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public int getFkIdPaciente() {
        return fkIdPaciente;
    }

    public void setFkIdPaciente(int fkIdPaciente) {
        this.fkIdPaciente = fkIdPaciente;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public int getFkIdMedico() {
        return fkIdMedico;
    }

    public void setFkIdMedico(int fkIdMedico) {
        this.fkIdMedico = fkIdMedico;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public String getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(String fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getHoraCita() {
        return horaCita;
    }

    public void setHoraCita(String horaCita) {
        this.horaCita = horaCita;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    @Override
    public String toString() {
        return "Cita{" +
                "fechaCita='" + fechaCita + '\'' +
                ", horaCita='" + horaCita + '\'' +
                ", motivo='" + motivo + '\'' +
                ", paciente=" + paciente.getNombre() +
                ", medico=" + medico.getNombre() +
                '}';
    }
}
