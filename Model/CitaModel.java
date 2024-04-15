package Model;

import database.CRUD;
import database.ConfigDb;
import entity.Cita;
import entity.Medico;
import entity.Paciente;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDb.openConnection();
        Cita objCita = (Cita) obj;

        try{

            String sql = "insert into cita (fecha_cita,hora_cita,motivo,fk_id_paciente,fk_id_medico) values (?,?,?,?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setDate(1, Date.valueOf(objCita.getFechaCita()));
            objPrepare.setTime(2, Time.valueOf(objCita.getHoraCita()));
            objPrepare.setString(3,objCita.getMotivo());
            objPrepare.setInt(4,objCita.getFkIdPaciente());
            objPrepare.setInt(5,objCita.getFkIdMedico());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while(objResult.next()){
                objCita.setIdCita(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null,"Se creó correctamente");


        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        ConfigDb.closeConnection();

        return objCita;
    }

    @Override
    public List<Object> findAll() {

        Connection objConnection = ConfigDb.openConnection();
        List<Object> listaCitas = new ArrayList<>();

        try{
            String sql = "select * from cita\n" +
                    "inner join paciente on paciente.id_paciente = cita.fk_id_paciente\n" +
                    "inner join medico on medico.id_medico = cita.fk_id_medico;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while(objResult.next()){
                Cita objCita = new Cita();
                Medico objMedico = new Medico();
                Paciente objPaciente = new Paciente();

                objCita.setIdCita(objResult.getInt("cita.id_cita"));
                objCita.setFechaCita(objResult.getString("cita.fecha_cita"));
                objCita.setHoraCita(objResult.getString("cita.hora_cita"));
                objCita.setMotivo(objResult.getString("cita.motivo"));
                objCita.setFkIdPaciente(objResult.getInt("cita.fk_id_paciente"));
                objCita.setFkIdMedico(objResult.getInt("cita.fk_id_medico"));

                objMedico.setNombre(objResult.getString("medico.nombre"));
                objPaciente.setNombre(objResult.getString("paciente.nombre"));

                objCita.setMedico(objMedico);
                objCita.setPaciente(objPaciente);

                listaCitas.add(objCita);

            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        ConfigDb.closeConnection();

        return listaCitas;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDb.openConnection();
        Cita objCita = (Cita) obj;
        boolean isUpdated = false;

        try{
            String sql = "update cita set fecha_cita = ?, hora_cita = ?, motivo = ?, fk_id_paciente = ?, fk_id_medico = ? where id_cita = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setDate(1,Date.valueOf(objCita.getFechaCita()));
            objPrepare.setTime(2,Time.valueOf(objCita.getHoraCita()));
            objPrepare.setString(3,objCita.getMotivo());
            objPrepare.setInt(4,objCita.getFkIdPaciente());
            objPrepare.setInt(5,objCita.getFkIdMedico());
            objPrepare.setInt(6,objCita.getIdCita());

            int totalAffected = objPrepare.executeUpdate();

            if (totalAffected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null,"Actualizado correctamente");

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
        Cita objCita = (Cita) obj;
        boolean isDeleted = false;

        try {

            String sql = "delete from cita where id_cita = ?;";
            PreparedStatement objPrerare = objConnection.prepareStatement(sql);

            objPrerare.setInt(1,objCita.getIdCita());

            int totalAffected = objPrerare.executeUpdate();

            if (totalAffected > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"Eliminado correctamente");

            }


        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        ConfigDb.closeConnection();
        return isDeleted;
    }
}
