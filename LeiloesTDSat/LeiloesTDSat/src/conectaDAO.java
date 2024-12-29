
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
    
    /*public boolean Connection connectDB(){
        Connection conn = null;
        
        try {
        
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/uc10atv","root", "1235");
            
        } catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + erro.getMessage());
        }
        return conn;
    }
    */
    
    Connection conn;
    PreparedStatement st;
    ResultSet rs;
    public boolean conectar(){
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/etapa5","root", "");
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return false;
        }
    }
    
    public List<ProdutosDTO> listagemProdutos(String filtro){
        
        String sql = "select * from produtos";
        
        if (!filtro.isEmpty()){
            sql = sql + " where nome like ?";
        }
        
        try{
            st = (PreparedStatement) conn.prepareStatement(sql);
            
            if(!filtro.isEmpty()){
                st.setString(1,"%" + filtro + "%");
            }
            
            rs = st.executeQuery();
            List<ProdutosDTO> listaProdutos = new ArrayList<>();
            while(rs.next()){
                ProdutosDTO produtos = new ProdutosDTO();
                produtos.setId(rs.getInt("id"));
                produtos.setNome(rs.getString("nome"));
                produtos.setValor(rs.getInt("valor"));
                produtos.setStatus(rs.getString("status"));
                listaProdutos.add(produtos);
            }
            return listaProdutos;            
        }catch(SQLException ex){
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return null;
        } 
    
    }
    
    
}
