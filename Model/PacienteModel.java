package Model;

import database.CRUD;
import database.ConfigDb;
import entity.Especialidad;
import entity.Paciente;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteModel implements CRUD {
    @Override
    public Object insert(Object obj) {

        Connection objConnection = ConfigDb.openConnection();

        Paciente objPaciente = (Paciente) obj;

        try {
            String sql = "insert into paciente (nombre,apellidos,fecha_nacimiento,documento_identidad) values (?,?,?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objPaciente.getNombre());
            objPrepare.setString(2,objPaciente.getApellidos());
            objPrepare.setString(3,objPaciente.getFechaNacimiento());
            objPrepare.setString(4,objPaciente.getDocumentoIdentidad());

           objPrepare.execute();

           ResultSet objResult = objPrepare.getGeneratedKeys();

           while (objResult.next()){

               objPaciente.setIdPaciente(objResult.getInt(1));
           }

            JOptionPane.showMessageDialog(null,"Paciente creado con exito");

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());

        }

        ConfigDb.closeConnection();

        return objPaciente;
    }

    @Override
    public List<Object> findAll() {

        Connection objConnection = ConfigDb.openConnection();

        List<Object> listaPacientes = new ArrayList<>();

        try{
            String sql = "select * from paciente";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){

                Paciente objPaciente = new Paciente();

                objPaciente.setIdPaciente(objResult.getInt("id_paciente"));
                objPaciente.setNombre(objResult.getString("nombre"));
                objPaciente.setApellidos(objResult.getString("apellidos"));
                objPaciente.setFechaNacimiento(objResult.getString("fecha_nacimiento"));
                objPaciente.setDocumentoIdentidad(objResult.getString("documento_identidad"));

                listaPacientes.add(objPaciente);

            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        ConfigDb.closeConnection();

        return listaPacientes;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDb.openConnection();
        Paciente objPaciente = (Paciente) obj;

        boolean isUpdated = false;

        try{
            String sql = "update paciente set nombre = ?, apellidos = ?, fecha_nacimiento = ?, documento_identidad = ? where id_paciente = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,objPaciente.getNombre());
            objPrepare.setString(2,objPaciente.getApellidos());
            objPrepare.setString(3,objPaciente.getFechaNacimiento());
            objPrepare.setString(4,objPaciente.getDocumentoIdentidad());
            objPrepare.setInt(5,objPaciente.getIdPaciente());

            int totalAffected = objPrepare.executeUpdate();

            if (totalAffected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null,"El paciente fue actualizado");
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
        Paciente objPaciente = (Paciente) obj;

        boolean isDeleted = false;

        try{
            String sql = "delete from paciente where id_paciente = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objPaciente.getIdPaciente());

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
