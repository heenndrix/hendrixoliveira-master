package br.edu.ifrs.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ItemVenda {
    private long id =0;
    private String produtoId;
    private String compraId;
    private double Valor;
    private int Quantidade;
    private boolean Devolucao;
     

    @Override
    public String toString() {
        return "ItemVenda [id=" + id + ", produtoId=" + produtoId + ", compraId=" + compraId + ", Valor=" + Valor + ", Quantidade=" + Quantidade + ", Devolucao=" + Devolucao + " ]";
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getProdutoId() {
        return produtoId;
    }
    public void setProdutoId(String produtoId) {
        this.produtoId = produtoId;
    }
    public String getCompraId() {
        return compraId;
    }
    public void setCompraId(String compraId) {
        this.compraId = compraId;
    }
    public double getValor() {
        return Valor;
    }
    public void setValor(double valor) {
        Valor = valor;
    }
    public int getQuantiade() {
        return Quantidade;
    }
    public void setQuantiade(int quantiade) {
        Quantidade = quantiade;
    }
    public boolean isDevolucao() {
        return Devolucao;
    }
    public void setDevolucao(boolean devolucao) {
        Devolucao = devolucao;
    }



    public boolean insert(){
        Conexao bd = new Conexao(); 
        String sql =  "INSERT INTO ItemVenda (id, ProdutoId, CompraId, Valor, Quantidade, Devolucao) VALUES (?, ?, ?, ?, ?, ?)";
        try {                   
            PreparedStatement ps = bd.getConexao().prepareStatement(sql);
            ps.setLong(1, this.getId());
            ps.setString(2, this.getProdutoId());
            ps.setString(3, this.getCompraId());
            ps.setDouble(4, this.getValor());
            ps.setInt(5, this.getQuantiade());
            ps.setBoolean(6, this.isDevolucao());

            
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
        String sql = "UPDATE ItemVenda SET ProdutoId = ?, CompraId=?, Valor=?, Quantidade=?, Devolucao=?  WHERE id = ?";

        try {                   
            PreparedStatement ps = bd.getConexao().prepareStatement(sql);
            ps.setLong(3, this.getId());
            ps.setString(1, this.getProdutoId());
            ps.setString(4, this.getCompraId());
            ps.setDouble(5, this.getValor());
            ps.setInt(6, this.getQuantiade());
            ps.setBoolean(2, this.isDevolucao());
            
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
        String sql = "DELETE FROM ItemVenda WHERE id = ?";

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
    

    public static ArrayList<ItemVenda> getAll(){
        ArrayList<ItemVenda> vendas = new ArrayList<ItemVenda>();

        Conexao bd = new Conexao();  
        String sql = "SELECT * FROM ItemVenda";
        try {                   
            PreparedStatement ps = bd.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ItemVenda b = new ItemVenda();
                b.setId(rs.getLong("id"));
                b.setProdutoId(rs.getString("ProdutoId"));
                b.setCompraId(rs.getString("CompraId"));
                b.setValor(rs.getDouble("Valor"));
                b.setQuantiade(rs.getInt("Quantidade"));
                b.setDevolucao(rs.getBoolean("Devolução"));
                vendas.add(b);
            }            
        } catch (SQLException e) {
            System.out.println("Erro ao consultar dados");
            e.printStackTrace(); //Não façam isso em casa crianças
        } finally{
            bd.desconecta();
        }
        return vendas;
    }

    public boolean load(){

        Conexao bd = new Conexao();  
        String sql = "SELECT * FROM ItemVenda WHERE id = ?";
        try {                   
            PreparedStatement ps = bd.getConexao().prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                this.setId(rs.getLong("Id"));
                this.setProdutoId(rs.getString("ProdutoId"));
                this.setCompraId(rs.getString("CompraId"));
                this.setValor(rs.getDouble("Valor"));
                this.setQuantiade(rs.getInt("Quantidade"));
                this.setDevolucao(rs.getBoolean("Devolução"));

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
    public void setDataCompra(String string) {
    }
    public void setCompraId(long long1) {
    }
    public void setValor(String string) {
    }
}
