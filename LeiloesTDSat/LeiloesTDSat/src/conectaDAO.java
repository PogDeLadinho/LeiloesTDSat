
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.ResultSet;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
public class conectaDAO {
    
    Connection conn;
    PreparedStatement st;
    
    public boolean connectDB(){
         
        try {

            String url = "jdbc:mysql://127.0.0.1:3306/uc10";
            String user = "root";
            String password = "xandrerock17";
            conn = DriverManager.getConnection(url, user, password);
            return true;
        } catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + erro.getMessage());
            return false;
        }
    }
    
    
}
