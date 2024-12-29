
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
    
    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
    ArrayList<ProdutosDTO> produtosVendidos = new ArrayList<>();
    String sql = "SELECT * FROM produtos WHERE status = ?";
    try (PreparedStatement prep = conn.prepareStatement(sql)) {
        prep.setString(1, "Vendido");
        ResultSet resultset = prep.executeQuery();
        while (resultset.next()) {
            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(resultset.getInt("id"));
            produto.setNome(resultset.getString("nome"));
            produto.setValor(resultset.getInt("valor"));
            produto.setStatus(resultset.getString("status"));
            produtosVendidos.add(produto);
        }
    } catch (SQLException ex) {
        System.err.println("Erro ao listar produtos vendidos: " + ex.getMessage());
    }
    return produtosVendidos;
}
    
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }

       
}

