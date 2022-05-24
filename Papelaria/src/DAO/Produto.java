
package DAO;

public class Produto {
    
    private int codigo;
    private String nome;
    private int quantidade;
    private double preco;
    
    //Construtor (da capsula)
    public Produto(){
        
    }
    
    public Produto (int m, String n, int c, double s){
        this.codigo = m;
        this.nome = n;
        this.quantidade = c;
        this.preco = s;
    }
            
    //Métodos setter (armazenar valores nos atributos da classe)
    public void setCodigo(int m){
        this.codigo = m;
    }
    
    public void setNome(String n){
        this.nome = n;
    }
    
    public void setQuantidade(int c){
        this.quantidade = c;
    }
    
    public void setPreco(double s){
        this.preco = s;
    }
    
    //Métodos getter (pegar valores contidos nos atributos da classe)
    
    public int getCodigo(){
        return this.codigo;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public int getQuantidade(){
        return this.quantidade;
    }
    
    public double getPreco(){
        return this.preco;
    }
}
