
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ProdutosDAO {
  private Connection conecta;
  private PreparedStatement st;
  private ResultSet resultado;
  
  public ProdutosDAO(){
      
  }

    public boolean conectar(){
      try {
          Class.forName("com.mysql.cj.jdbc.Driver");
          conecta = DriverManager.getConnection("jdbc:mysql://localhost:3306/papelaria", "root", "");        
          return true;
      } catch (ClassNotFoundException | SQLException ex) {
          return false; 
      }
    }
  

    public int salvar(Produto prod){
     try{
        st = conecta.prepareStatement("INSERT INTO produto VALUES(?,?,?,?)");
        st.setInt(1, prod.getCodigo());
        st.setString(2, prod.getNome());
        st.setDouble(3, prod.getPreco());
        st.setInt(4, prod.getQuantidade());
        st.executeUpdate();
        return 1;
     }catch(SQLException ex){
        if (ex.getErrorCode()==1062){
            return 1062;
        }else{
            return 0;
        }
     }
    }
    
    

    public boolean excluir(int codigo){
      try {
          st = conecta.prepareStatement("DELETE FROM produto WHERE codigo = ?");
          st.setInt(1, codigo);
          int r = st.executeUpdate(); //executar o delete preparado acima
          if(r==1){
           return true;
          }else{
              return false;
          }
      } catch (SQLException ex) {
          return false;
      }
    }
    
    
    
    public Produto consultar(int codigo){
      Produto prod;
      try {
          st = conecta.prepareStatement("SELECT * FROM produto WHERE codigo = ?");
          st.setInt(1, codigo);
          resultado = st.executeQuery(); 
          if(resultado.next()){ 
             prod = new Produto();
             prod.setCodigo(codigo);
             prod.setNome(resultado.getString("nome"));
             prod.setQuantidade(resultado.getInt("quantidade"));
             prod.setPreco(resultado.getDouble("preco"));
             return prod;
          } else {
              return null;
          }
      } catch (SQLException ex) {
          return null;
      }
    }
    
    
    public void alterar(String i, String nome, String quantidade, String preco ){
        try{
            st = conecta.prepareStatement("UPDATE produto SET nome = ?, quantidade = ?, preco = ? WHERE codigo = ?");
            st.setString(1, nome);
            st.setInt(2, Integer.parseInt(quantidade));
            st.setDouble(3, Double.parseDouble(preco));
            st.setInt(4, Integer.parseInt(i));
            st.executeUpdate();
        }catch (SQLException ex) {        
      }
    }
    
    public ArrayList<Object> consultartudo(){
        try {
          ArrayList<Object> lista = new ArrayList<Object>();
          st = conecta.prepareStatement("SELECT * FROM produto");
          resultado = st.executeQuery();
          while (resultado.next()) {
            Object[] linha = {resultado.getString("codigo"), resultado.getString("nome"), resultado.getString("quantidade"), resultado.getString("preco")};
            lista.add(linha);
           }
          return lista;
        }catch (SQLException ex) {
          return null;
      }
    }
}
    
   