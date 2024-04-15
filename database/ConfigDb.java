package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDb {

    //Este atributo tendrá el estado de la conexión
    public static Connection objConnection = null;

    //Metodo para conectar la base de datos
    public static Connection openConnection(){
        try{

            //llamamos al driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Variables de conexión
            String url = "jdbc:mysql://bnytosyqwacxzyphnyou-mysql.services.clever-cloud.com:3306/bnytosyqwacxzyphnyou";
            String user = "u1ze13cdjmst9thy";
            String password = "IUQ8dmVWoMBmTe8xiTzD";

            //Establecer conexión
            objConnection = (Connection) DriverManager.getConnection(url,user,password);
            System.out.println("Conexión exitosa");

        }catch (ClassNotFoundException error){
            System.out.println("ERROR >> Driver not found " + error.getMessage());

        }catch (SQLException error){
            System.out.println("ERROR >> error al conectar base de datos " + error.getMessage());
        }

        return objConnection;
    }

    //Método para cerrar una conexión
    public static void closeConnection(){
        try{
            if (objConnection != null){
                objConnection.close();
                System.out.println("Se finalizó la conexión");
            }

        }catch (SQLException error){
            System.out.println("ERROR " + error.getMessage());
        }
    }
}
