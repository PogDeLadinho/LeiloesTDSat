
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
    
    public boolean venderProduto(int idProduto) {
    String sql = "UPDATE produtos SET status = ? WHERE id = ?";
    try (PreparedStatement prep = conn.prepareStatement(sql)) {
        prep.setString(1, "Vendido");
        prep.setInt(2, idProduto);
        int linhasAfetadas = prep.executeUpdate();
        return linhasAfetadas > 0;
    } catch (SQLException ex) {
        System.err.println("Erro ao vender produto: " + ex.getMessage());
        return false;
    }
}
    
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }

       
}

