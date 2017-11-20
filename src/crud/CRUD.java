package crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CRUD {
    
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/Arduino";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    
    public static Connection conectar(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DATABASE_URL,USERNAME,PASSWORD);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        //System.out.println("conectado");
        return conn;
    }
    
        
    /***************************************************************************
        Envía un query a la base de datos, este puede ser un INSERT, UPDATE O
        DELETE (éste último no se realizará por permanencia de información).
        
        *@param sql  contiene el query a ejecutar por la función.
         
    ****************************************************************************/
    public static void sendQuery (String sql){  
        try {
            Connection send = conectar();
            Statement stmt = send.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    
    /***************************************************************************
        Envía un query a la base de datos donde solicita información, esta será 
        retornada en un ResultSet.
    
        *@param sql  contiene el query a ejecutar.
        
        *@return retorna un ResultSet con la información obtenida del query, esto
            jamás retornará un ResultSet null a menos que falle la conexión a la 
            base de datos. 
        
    ****************************************************************************/    
    public static ResultSet getQuery (String sql){
        ResultSet resultset = null;
        try {
            Connection get = conectar();
            PreparedStatement ps = get.prepareStatement(sql);
            resultset = ps.executeQuery(sql);   
        } catch (SQLException ex){
            ex.printStackTrace();
        }   
        return resultset;
    }   
}