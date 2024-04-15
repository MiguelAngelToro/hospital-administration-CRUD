package controller;

import Model.EspecialidadModel;
import Model.PacienteModel;
import entity.Especialidad;
import entity.Paciente;
import utils.Utils;

import javax.swing.*;

public class PacienteController {

    public static void findAll(){
        PacienteModel objModel = new PacienteModel();

        String listaPacientes = "LISTA DE PACIENTES \n";

        for (Object iterador: objModel.findAll()){

            Paciente objPaciente = (Paciente) iterador;
            listaPacientes += objPaciente.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null,listaPacientes);
    }

    public static void create(){
        PacienteModel objPacienteModel = new PacienteModel();

        String nombre = JOptionPane.showInputDialog("Ingrese nombre");
        String apellidos = JOptionPane.showInputDialog("Ingrese apellidos");
        String fechaNacimiento = JOptionPane.showInputDialog("Ingrese fecha de nacimiento AÑO-MES-DIA");
        String documentoIdentidad = JOptionPane.showInputDialog("Ingrese docuemnto de identidad");

        Paciente objPaciente = new Paciente();

        objPaciente.setNombre(nombre);
        objPaciente.setApellidos(apellidos);
        objPaciente.setFechaNacimiento(fechaNacimiento);
        objPaciente.setDocumentoIdentidad(documentoIdentidad);

        objPaciente = (Paciente) objPacienteModel.insert(objPaciente);

        JOptionPane.showMessageDialog(null,objPaciente.toString());

    }

    public static void update(){
        Object[] options = Utils.listToArray(instanceModel().findAll());

        Paciente objSelected = (Paciente) JOptionPane.showInputDialog(null,
                "Selecciona un paciente",
                "",JOptionPane.QUESTION_MESSAGE,null,options,options[0]);

        objSelected.setNombre(JOptionPane.showInputDialog(null,"Ingrese el nuevo nombre"));
        objSelected.setApellidos(JOptionPane.showInputDialog(null,"Ingrese apellidos"));
        objSelected.setFechaNacimiento(JOptionPane.showInputDialog(null,"Ingrese fecha de nacimiento AÑO-MES-DIA"));
        objSelected.setDocumentoIdentidad(JOptionPane.showInputDialog(null,"Ingrese docuemnto de identidad"));

        instanceModel().update(objSelected);
    }

    public static void delete(){

        Object[] options = Utils.listToArray(instanceModel().findAll());

        Paciente objSedlected = (Paciente) JOptionPane.showInputDialog(null,
                "Seleccione un paciente",
                "",JOptionPane.QUESTION_MESSAGE,null,options,options[0]);

        instanceModel().delete(objSedlected);

    }

    public static PacienteModel instanceModel(){
        return new PacienteModel();
    }
}
