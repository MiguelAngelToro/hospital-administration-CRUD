package controller;

import Model.CitaModel;
import entity.Cita;
import entity.Medico;
import entity.Paciente;
import utils.Utils;

import javax.swing.*;
import java.util.List;
import java.util.Objects;

public class CitaController {

    public static void insert(){
        String fechaCita = JOptionPane.showInputDialog("Ingresa la fecha de la cita: AÑO-MES-DIA");
        String horaCita = JOptionPane.showInputDialog("Ingresa la hora de la cita : HORA:MINUTOS:SEGUNDOS");
        String motivo = JOptionPane.showInputDialog("Ingrese motivo de la cita:");

        Object[] optionsPacientes = Utils.listToArray(PacienteController.instanceModel().findAll());

        Paciente pacienteSeleccionado = (Paciente) JOptionPane.showInputDialog(
                null,
                "Seleccione el paciente",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsPacientes,
                optionsPacientes[0]);

        Object[] optionsMedicos = Utils.listToArray(MedicoController.instanceModel().findAll());

        Medico medicoSeleccionado = (Medico) JOptionPane.showInputDialog(
                null,
                "Seleccione el médico",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsMedicos,
                optionsMedicos[0]
        );

        instanceModel().insert(new Cita(fechaCita,horaCita,motivo,pacienteSeleccionado.getIdPaciente(),medicoSeleccionado.getIdMedico(),pacienteSeleccionado,medicoSeleccionado));

    }

    public static void getAll(){
        String listString = getAll(instanceModel().findAll());
        JOptionPane.showMessageDialog(null,listString);

    }

    public static String getAll(List<Object> list){
        String listString = "Lista de registros \n";

        for (Object temp:list){
            Cita obj = (Cita) temp;

            listString += obj.toString() + "\n";
        }

        return listString;


    }

    public static void delete(){
        Object[] options = Utils.listToArray(instanceModel().findAll());

        Cita CitaSeleccionada  = (Cita) JOptionPane.showInputDialog(
                null,
                "Seleccione la cita a eliminar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        instanceModel().delete(CitaSeleccionada);


    }

    public static void update(){
        Object[] options = Utils.listToArray(instanceModel().findAll());

        Cita CitaSeleccionada  = (Cita) JOptionPane.showInputDialog(
                null,
                "Seleccione la cita a eliminar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        CitaSeleccionada.setFechaCita(JOptionPane.showInputDialog(null,"Ingresa la fecha de la cita: AÑO-MES-DIA",CitaSeleccionada.getFechaCita()));
        CitaSeleccionada.setHoraCita(JOptionPane.showInputDialog(null,"Ingresa la hora de la cita : HORA:MINUTOS:SEGUNDOS",CitaSeleccionada.getHoraCita()));
        CitaSeleccionada.setMotivo(JOptionPane.showInputDialog(null,"Ingrese motivo de la cita:",CitaSeleccionada.getMotivo()));

        Object[] optionsPacientes = Utils.listToArray(PacienteController.instanceModel().findAll());

        CitaSeleccionada.setPaciente((Paciente) JOptionPane.showInputDialog(
                null,
                "Seleccione el paciente",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsPacientes,
                optionsPacientes[0]));

        CitaSeleccionada.setFkIdPaciente(CitaSeleccionada.getPaciente().getIdPaciente());

        Object[] optionsMedicos = Utils.listToArray(MedicoController.instanceModel().findAll());



        CitaSeleccionada.setMedico((Medico) JOptionPane.showInputDialog(
                null,
                "Seleccione el médico",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsMedicos,
                optionsMedicos[0]
        ));

        CitaSeleccionada.setFkIdMedico(CitaSeleccionada.getMedico().getIdMedico());

        instanceModel().update(CitaSeleccionada);

    }
    public static CitaModel instanceModel(){
        return new CitaModel();
    }
}
