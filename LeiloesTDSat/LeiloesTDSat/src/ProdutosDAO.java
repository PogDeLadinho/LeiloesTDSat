
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;


public class ProdutosDAO{
    
    
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public int cadastrarProduto (ProdutosDTO produto){
        
      
        int status;
        try {
            
            prep = conn.prepareStatement("INSERT INTO produtos VALUES(?,?,?,?)");
            
            prep.setInt(1,produto.getId()); 
            prep.setString(2,produto.getNome());
            prep.setInt(3,produto.getValor());
            prep.setString(4, produto.getStatus());
            
            status = prep.executeUpdate();
            return status;
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return ex.getErrorCode();
        }
        
        
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }

       
}

