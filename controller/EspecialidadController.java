package controller;

import Model.EspecialidadModel;
import entity.Especialidad;
import utils.Utils;

import javax.swing.*;

public class EspecialidadController {

    public static void findAll(){
        EspecialidadModel objModel = new EspecialidadModel();

        String listaEspecialidades = "LISTA DE ESPECIALIDADES \n";

        for (Object iterador: objModel.findAll()){

            //Convertimos objeto a especiliadad
            Especialidad objEspecialidad = (Especialidad) iterador;
            listaEspecialidades += objEspecialidad.toString() + "\n";

        }

        JOptionPane.showMessageDialog(null,listaEspecialidades);
    }

    public static void create(){

        String nombre = JOptionPane.showInputDialog("Ingrese nombre de especialidad");
        String descripcion = JOptionPane.showInputDialog("Ingrese descripcion");

        instanceModel().insert(new Especialidad(nombre,descripcion));

    }

    public static EspecialidadModel instanceModel(){
        return new EspecialidadModel();
    }

    public static void delete(){

        Object[] options = Utils.listToArray(instanceModel().findAll());

        Especialidad objSedlected = (Especialidad) JOptionPane.showInputDialog(null,
                "Selecciona una especialidad",
                "",JOptionPane.QUESTION_MESSAGE,null,options,options[0]);

        instanceModel().delete(objSedlected);

    }

    public static void update(){

        Object[] options = Utils.listToArray(instanceModel().findAll());
        Especialidad objSedlected = (Especialidad) JOptionPane.showInputDialog(null,
                "Selecciona una especialidad",
                "",JOptionPane.QUESTION_MESSAGE,null,options,options[0]);

        objSedlected.setNombre(JOptionPane.showInputDialog(null,"Ingrese el nuevo nombre", objSedlected.getNombre()));
        objSedlected.setDescripcion(JOptionPane.showInputDialog(null,"Ingrese la nueva descripción", objSedlected.getDescripcion()));

        instanceModel().update(objSedlected);
    }
}
