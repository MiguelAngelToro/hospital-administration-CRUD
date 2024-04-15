package controller;

import Model.MedicoModel;
import entity.Especialidad;
import entity.Medico;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class MedicoController {

    public static void getAll(){

        String list = getAll(instanceModel().findAll());
        JOptionPane.showMessageDialog(null,list);
    }
    public static String getAll(List<Object> list){
        String listString = "Lista de Medicos \n";

        for (Object temp:list){
            Medico objMedico = (Medico) temp;
            listString += objMedico.toString() + "\n";
        }

        return listString;
    }

    public static void deleted(){
        Object[] options = Utils.listToArray(instanceModel().findAll());
        Medico objMedico = (Medico) JOptionPane.showInputDialog(null,
                "Selecciona el médico a eliminar",
                "",JOptionPane.QUESTION_MESSAGE,null,options,options[0]
        );

        instanceModel().delete(objMedico);

    }

    public static void update(){
        Object[] options = Utils.listToArray(instanceModel().findAll());

        Medico objMedico = (Medico) JOptionPane.showInputDialog(null,
                "Selecciona el médico a actualizar",
                "",JOptionPane.QUESTION_MESSAGE,null,options,options[0]
        );
        

        String nombre = JOptionPane.showInputDialog(null,"Ingrese el nombre del médico: ",objMedico.getNombre());
        String apellidos = JOptionPane.showInputDialog(null,"Ingrese apellidos: ",objMedico.getApellidos());



        Object[] optionsEspecialidades = Utils.listToArray(EspecialidadController.instanceModel().findAll());

        Especialidad objEspecialidad = (Especialidad) JOptionPane.showInputDialog(null,
                "Seleccione una especialidad: ",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsEspecialidades,
                optionsEspecialidades[0]
        );

        objMedico.setNombre(nombre);
        objMedico.setApellidos(apellidos);
        objMedico.setFkIdEspecialidad(objEspecialidad.getIdEspecialidad());
        objMedico.setObjEspecialidad(objEspecialidad);

        instanceModel().update(objMedico);




    }

    public static void insert(){
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del médico: ");
        String apellidos = JOptionPane.showInputDialog("Ingrese apellidos: ");

        Object[] optionsEspecialidades = Utils.listToArray(EspecialidadController.instanceModel().findAll());

        Especialidad objEspecialidad = (Especialidad) JOptionPane.showInputDialog(null,
                "Seleccione una especialidad: ",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsEspecialidades,
                optionsEspecialidades[0]);

        instanceModel().insert(new Medico(nombre,apellidos, objEspecialidad.getIdEspecialidad(),objEspecialidad));
    };

    public static MedicoModel instanceModel(){
        return new MedicoModel();
    }
}
