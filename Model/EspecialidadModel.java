package Model;

import database.CRUD;
import database.ConfigDb;
import entity.Especialidad;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EspecialidadModel implements CRUD {
    @Override
    public Object insert(Object obj) {


        //Abrir conexión
        Connection objConnection = ConfigDb.openConnection();

        //Convertir Objeto que se recibe a tipo Especialidad
        Especialidad objEspecialidad = (Especialidad) obj;

        try{

            String sql = "insert into especialidad (nombre,descripcion) values (?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS); //retorna los id generados por la base de datos

            //Asignar los values
            objPrepare.setString(1,objEspecialidad.getNombre());
            objPrepare.setString(2,objEspecialidad.getDescripcion());


            //Ejecutamos el codigo en la base pero no se nos regresa nada, de esta manera por el id autogenerado
            objPrepare.execute();

            //Obtenemos todas las lleves generadas incluyendo id autogenerado
            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){

                objEspecialidad.setIdEspecialidad(objResult.getInt(1));

            }

            JOptionPane.showMessageDialog(null,"Especialidad inscrita exitosamente");

        }catch (SQLException e){

            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        ConfigDb.closeConnection();


        return objEspecialidad;
    }

    @Override
    public List<Object> findAll() {

        //Abrir conexión
        Connection objConnection  = ConfigDb.openConnection();

        //Crear lista para guardar lo que nos devuelve la base de datos
        List<Object> listaEspecialidades = new ArrayList<>();

        try{

            //Escribimos el query codigo sql
            String sql = "select * from especialidad;";

            //Usar el prepare Statement para subrallar codigo a ejecutar
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            //Ejecutar y obtener resultado
            ResultSet objResult = objPrepared.executeQuery();

            //Generar objetos de la tabla uno a uno
            while(objResult.next()){

                //Llamamos constructor vacio para generarlo aun sin info
                Especialidad objEspecialidad = new Especialidad();

                objEspecialidad.setNombre(objResult.getString("nombre"));
                objEspecialidad.setDescripcion(objResult.getString("descripcion"));
                objEspecialidad.setIdEspecialidad(objResult.getInt("id_especialidad"));

                //Agregar esa especialidad a la lista
                listaEspecialidades.add(objEspecialidad);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        ConfigDb.closeConnection();

        return listaEspecialidades;
    }

    @Override
    public boolean update(Object obj) {

        Connection objConnection = ConfigDb.openConnection();
        Especialidad objEspecialidad = (Especialidad) obj;

        boolean isUpdated = false;

        try{
            String sql = "update especialidad set nombre = ?, descripcion = ? where id_especialidad = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,objEspecialidad.getNombre());
            objPrepare.setString(2,objEspecialidad.getDescripcion());
            objPrepare.setInt(3,objEspecialidad.getIdEspecialidad());

            int totalAffected = objPrepare.executeUpdate();

            if (totalAffected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null,"La especialidad fue actualizada");
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());

        }

        ConfigDb.closeConnection();

        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {
        Connection objConnection = ConfigDb.openConnection();
        Especialidad objEspecialidad = (Especialidad) obj;

        boolean isDeleted = false;

        try{
            String sql = "delete from especialidad where id_especialidad = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objEspecialidad.getIdEspecialidad());

            int totalaffected = objPrepare.executeUpdate();

            if (totalaffected > 0){
                isDeleted = true;

                JOptionPane.showMessageDialog(null,"registro eliminado correctamente");
            }


        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        ConfigDb.closeConnection();

        return isDeleted;
    }
}
