import controller.CitaController;
import controller.EspecialidadController;
import controller.MedicoController;
import controller.PacienteController;
import entity.Medico;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        String option = "";
        String option1 = "";


        do {
            option = JOptionPane.showInputDialog("""
                    MENÚ PRINCIPAL
                    
                    1. Administrar Especialidades
                    2. Administrar Medicos
                    3. Administrar Pacientes
                    4. Administrar Citas
                    6. Salir
                    
                    Ingese una opcion:
                    """);

            switch (option){
                case "1":

                    do {
                        option1 = JOptionPane.showInputDialog("""
                            MENÚ ESPECIALIDADES
                            
                            1. Crear
                            2. Actualizar
                            3. Listar
                            4. Eliminar
                            5. Salir
                            """);

                        switch (option1){
                            case "1":
                                EspecialidadController.create();
                                break;

                            case "2":
                                EspecialidadController.update();
                                break;

                            case "3":
                                EspecialidadController.findAll();

                                break;
                            case "4":
                                EspecialidadController.delete();
                                break;
                        }
                    }while (!option1.equals("5"));

                    break;

                case "2":

                    do {
                        option1 = JOptionPane.showInputDialog("""
                            MENÚ MEDICOS
                            
                            1. Crear
                            2. Actualizar
                            3. Listar
                            4. Eliminar
                            5. Salir
                            
                            Ingrese opción:
                            """);

                        switch (option1){
                            case "1":
                                MedicoController.insert();
                                break;

                            case "2":
                                MedicoController.update();
                                break;

                            case "3":
                                MedicoController.getAll();
                                break;

                            case "4":
                                MedicoController.deleted();
                                break;
                        }
                    }while (!option1.equals("5"));

                    break;

                case "3":

                    do {
                        option1 = JOptionPane.showInputDialog("""
                                MENÚ PACIENTES
                                
                                1. Crear
                                2. Actualizar
                                3. Listar
                                4. Eliminar
                                5. Salir
                                """);

                        switch (option1){
                            case "1":
                                PacienteController.create();
                                break;
                            case "2":
                                PacienteController.update();
                                break;
                            case "3":
                                PacienteController.findAll();
                                break;
                            case "4":
                                PacienteController.delete();
                                break;
                        }
                    }while (!option1.equals("5"));
                    break;

                case "4":
                    do {
                        option1 = JOptionPane.showInputDialog("""
                                MENÚ CITAS
                                
                                1. Crear
                                2. Actualizar
                                3. Listar
                                4. Eliminar
                                5. Salir
                                
                                """);

                        switch (option1){
                            case "1":
                                CitaController.insert();
                                break;
                            case "2":
                                CitaController.update();
                                break;
                            case "3":
                                CitaController.getAll();
                                break;
                            case "4":
                                CitaController.delete();
                                break;

                        }

                    }while (!option1.equals("5"));
                    break;
            }

        }while (!option.equals("6"));

    }
}