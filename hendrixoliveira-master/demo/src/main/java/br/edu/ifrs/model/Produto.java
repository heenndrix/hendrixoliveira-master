package br.edu.ifrs.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Produto {
    private String NomeProduto;
    private long id =0;
    private Double Valor;
    private String Descricao="";
    


    @Override
    public String toString() {
        return "Produto [NomeProduto=" + NomeProduto + ", id=" + id + ", Valor=" + Valor + " ]";
    }
    public String getNomeProduto() {
        return NomeProduto;
    }
    public void setNomeProduto(String NomeProduto) {
        this.NomeProduto = NomeProduto;
    }
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    
    public Double getValor() {
        return Valor;
    }
    public void setValor(Double Valor) {
        this.Valor = Valor;
    }
    
    public String getDescricao() {
        return Descricao;
    }
    public void setDescricao(String descricao) {
        Descricao = descricao;
    }


    public boolean insert(){
        Conexao bd = new Conexao(); 
        String sql =  "INSERT INTO Produto (id, NomeProduto, Valor, Descricao) VALUES (produto_id.NEXTVAL, ?, ?, ?)";
        try {                   
            PreparedStatement ps = bd.getConexao().prepareStatement(sql);

            ps.setString(1, this.getNomeProduto());
            ps.setDouble(2, this.getValor());
            ps.setString(3, this.getDescricao());
            
            
            ps.executeUpdate();
        
        } catch (SQLException e) {
            e.printStackTrace(); //Não façam isso em casa crianças
            return  false;
        } finally{
            bd.desconecta();
        }
        return true;
    }

    public boolean update(){
        Conexao bd = new Conexao();    
        String sql = "UPDATE Produto SET NomeProduto = ?, Valor=? WHERE id = ?";

        try {                   
            PreparedStatement ps = bd.getConexao().prepareStatement(sql);
            ps.setLong(3, this.getId());
            ps.setString(1, this.getNomeProduto());
            ps.setDouble(2, this.getValor());
            
            ps.executeUpdate();     
        } catch (Exception e) {        
            e.printStackTrace(); //Não façam isso em casa crianças
            return false;
        } finally{
            bd.desconecta();
        }
        return true;
    }
    
    
    public boolean delete(){
        Conexao bd = new Conexao();  
        String sql = "DELETE FROM Produto WHERE id = ?";

        try {                   
            PreparedStatement ps = bd.getConexao().prepareStatement(sql);
            ps.setLong(1, this.id);
            
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); //Não façam isso em casa crianças
            return false;
        } finally{
            bd.desconecta();
        }
        return true;
    
    }
    

    public static ArrayList<Produto> getAll(){
        ArrayList<Produto> Produto = new ArrayList<Produto>();

        Conexao bd = new Conexao();  
        String sql = "SELECT * FROM Produto";
        try {                   
            PreparedStatement ps = bd.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Produto a = new Produto();
                a.setId(rs.getLong("id"));

                a.setNomeProduto(rs.getString("NomeProduto"));
                a.setValor(rs.getDouble("Valor"));
                a.setDescricao(rs.getString("Descricao"));
                Produto.add(a);
            }            
        } catch (SQLException e) {
            System.out.println("Erro ao consultar dados");
            e.printStackTrace(); //Não façam isso em casa crianças
        } finally{
            bd.desconecta();
        }
        return Produto;
    }

    public boolean load(){

        Conexao bd = new Conexao();  
        String sql = "SELECT * FROM Produto WHERE id = ?";
        try {                   
            PreparedStatement ps = bd.getConexao().prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
               
                this.setNomeProduto(rs.getString("NomeProduto"));
                this.setValor(rs.getDouble("Valor"));
                this.setDescricao(rs.getString("Descricao"));

                return true;
            }            
        } catch (SQLException e) {
            System.out.println("Erro ao consultar dados");
            e.printStackTrace(); //Não façam isso em casa crianças
        } finally{
            bd.desconecta();
        }
        return false;
   
    
    }
}