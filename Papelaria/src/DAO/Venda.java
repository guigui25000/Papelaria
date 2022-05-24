
package DAO;

import java.time.LocalDate;
import java.util.Date;

public class Venda {
    
    private int codigo;
    private String venda;
    private String dt;
    private double preco;

    public Venda() {
    }

    public Venda(int codigo, String venda, String dt, double preco) {
        this.codigo = codigo;
        this.venda = venda;
        this.dt = dt;
        this.preco = preco;
    }
    
    

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getVenda() {
        return venda;
    }

    public void setVenda(String venda) {
        this.venda = venda;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
    
    
}
