package Model;

import database.CRUD;
import database.ConfigDb;
import entity.Especialidad;
import entity.Medico;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicoModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDb.openConnection();

        Medico objMedico = new Medico();

        try{
            String sql = "insert into medico (nombre,apellidos,fk_id_especialidad) values (?,?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objMedico.getNombre());
            objPrepare.setString(2,objMedico.getApellidos());
            objPrepare.setInt(3,objMedico.getFkIdEspecialidad());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while(objResult.next()){
                objMedico.setIdMedico(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null,"Medico creado correctamente");

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());

        }

        ConfigDb.closeConnection();

        return objMedico;
    }

    @Override
    public List<Object> findAll() {

        List<Object> listaMedicos = new ArrayList<>();
        Connection objConnection = ConfigDb.openConnection();
        try{
            String sql = "select * from medico\n" +
                    "inner join especialidad on especialidad.id_especialidad = medico.fk_id_especialidad;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Medico objMedico = new Medico();
                Especialidad objEspecialidad = new Especialidad();

                objMedico.setIdMedico(objResult.getInt("medico.id_medico"));
                objMedico.setNombre(objResult.getString("medico.nombre"));
                objMedico.setApellidos(objResult.getString("medico.apellidos"));
                objMedico.setFkIdEspecialidad(objResult.getInt("medico.fk_id_especialidad"));

                objEspecialidad.setIdEspecialidad(objResult.getInt("especialidad.id_especialidad"));
                objEspecialidad.setNombre(objResult.getString("especialidad.nombre"));
                objEspecialidad.setDescripcion(objResult.getString("especialidad.descripcion"));

                objMedico.setObjEspecialidad(objEspecialidad);

                listaMedicos.add(objMedico);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());

        }

        ConfigDb.closeConnection();
        return listaMedicos;
    }

    @Override
    public boolean update(Object obj) {

        Connection objConnection = ConfigDb.openConnection();

        Medico objMedico = (Medico) obj;

        boolean isupdated = false;

        try{
            String sql = "update medico set nombre = ?, apellidos = ?, fk_id_especialidad = ? where id_medico = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,objMedico.getNombre());
            objPrepare.setString(2,objMedico.getApellidos());
            objPrepare.setInt(3,objMedico.getFkIdEspecialidad());
            objPrepare.setInt(4,objMedico.getIdMedico());

            int totalAffected = objPrepare.executeUpdate();

            if (totalAffected > 0){
                isupdated = true;
                JOptionPane.showMessageDialog(null,"Medico actualizado");

            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        ConfigDb.closeConnection();

        return isupdated;
    }

    @Override
    public boolean delete(Object obj) {
        Connection objConnection = ConfigDb.openConnection();

        Medico objMedico = (Medico) obj;

        boolean isDeleted = false;

        try{
            String sql = "delete from medico where id_medico = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objMedico.getIdMedico());

            int totalAffected = objPrepare.executeUpdate();

            if (totalAffected > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"Medico eliminado correctamente");
            }


        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        ConfigDb.closeConnection();
        return isDeleted;
    }
}
