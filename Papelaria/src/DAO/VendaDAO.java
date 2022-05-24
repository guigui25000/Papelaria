
package DAO;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;


public class VendaDAO {
  private Connection conecta;
  private PreparedStatement st;
  private ResultSet resultado;
  
  public VendaDAO(){
      
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
  

    public int salvar(Venda vend){
     try{
        st = conecta.prepareStatement("INSERT INTO venda VALUES(?,?,?,?)");
        st.setInt(1, vend.getCodigo());
        st.setString(2, vend.getVenda());
        st.setDouble(3, vend.getPreco());
        st.setString(4, vend.getDt());       
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
          st = conecta.prepareStatement("DELETE FROM produto WHERE indice = ?");
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
    
    
    
    public Venda consultar(int codigo){
      Venda vend;
      try {
          st = conecta.prepareStatement("SELECT * FROM venda WHERE indice = ?");
          st.setInt(1, codigo);
          resultado = st.executeQuery(); 
          if(resultado.next()){ 
             vend = new Venda();
             vend.setCodigo(codigo);
             vend.setVenda(resultado.getString("venda"));
             vend.setDt(resultado.getString("data"));
             vend.setPreco(resultado.getDouble("valor"));
             return vend;
          } else {
              return null;
          }
      } catch (SQLException ex) {
          return null;
      }
    }
    
    public ArrayList<Object> consultartudo(){
        try {
          ArrayList<Object> lista = new ArrayList<Object>();
          st = conecta.prepareStatement("SELECT * FROM venda");
          resultado = st.executeQuery();
          while (resultado.next()) {
            Object[] linha = {resultado.getString("indice"), resultado.getString("venda"), resultado.getString("data"), resultado.getString("valor")};
            lista.add(linha);
           }
          return lista;
        }catch (SQLException ex) {
          return null;
      }
    }
  
}
