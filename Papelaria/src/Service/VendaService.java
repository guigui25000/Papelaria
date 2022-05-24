/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DAO.Produto;
import DAO.ProdutosDAO;
import DAO.Venda;
import DAO.VendaDAO;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author guilh
 */
public class VendaService {
    
    public VendaService(){
        
    }
    public static boolean ComputarVenda(Integer cod, Integer quant){
        
        Produto p = new Produto();
        ProdutosDAO prodDAO = new ProdutosDAO();
        boolean conexaoProd = prodDAO.conectar();
        VendaDAO vendDAO = new VendaDAO();
        boolean venConexa = vendDAO.conectar();
        Double valorTotal = 0.0;
        Integer novaQuant;
        
        if(conexaoProd){
            p = prodDAO.consultar(cod);
            
            valorTotal = p.getPreco() * quant;
            novaQuant = p.getQuantidade() - quant;
         
            prodDAO.alterar(String.valueOf(p.getCodigo()),String.valueOf(p.getNome()),
                    String.valueOf(novaQuant), String.valueOf(p.getPreco()));
            
       
        }
        if(venConexa){
            String text;
            text = "Foi vendida a seguinte quantidade "+ quant+" de "+p.getNome()
                    +" com um valor total de R$:"+valorTotal;
            LocalDate today = LocalDate.now();
            Venda v = new Venda();
            v.setDt(String.valueOf(today));
            v.setPreco(valorTotal);
            v.setVenda(text);
            Integer a = vendDAO.salvar(v);
            JOptionPane.showMessageDialog(null, text);
            if(a != 1){
                return false;
            }
        }
        return true;
    }
}
