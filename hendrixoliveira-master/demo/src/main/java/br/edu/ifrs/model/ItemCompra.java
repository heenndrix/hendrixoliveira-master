package br.edu.ifrs.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ItemCompra {
    private String dataCompra;
    private long id =0;
    private Long CompraId;
    private double Valor;
    


    @Override
    public String toString() {
        return "ItemCompra [dataCompra=" + dataCompra + ", id=" + id + ", Valor=" + Valor + " CompraId=" + CompraId + " ]";
    } 
    public String getDataCompra() {
        return dataCompra;
    }
    public void setDataCompra(String dataCompra) {
        this.dataCompra = dataCompra;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public Long getCompraId() {
        return CompraId;
    }
    public void setCompraId(Long compraId) {
        CompraId = compraId;
    }
    public void setValor(double valor) {
        Valor = valor;
    }

    public boolean insert(){
        Conexao bd = new Conexao(); 
        String sql =  "INSERT INTO ItemCompra (id ,Compraid, dataCompra, Valor) VALUES (CompraItem.NEXTVAL, ?, ?, ?)";
        try {                   
            PreparedStatement ps = bd.getConexao().prepareStatement(sql);

            ps.setLong(1, this.getCompraId());
            ps.setString(2, this.getDataCompra());
            ps.setString(3, this.getValor());
            
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
        String sql = "UPDATE ItemCompra SET dataCompra = ? , CompraId = ? , CompraId = ?  Valor = ? WHERE id = ?";

        try {                   
            PreparedStatement ps = bd.getConexao().prepareStatement(sql);
            ps.setString(1, this.getDataCompra());
            ps.setLong(2, this.getId());
            ps.setLong(3, this.getCompraId());
            ps.setString(4, this.getValor());
            
            ps.executeUpdate();     
        } catch (Exception e) {        
            e.printStackTrace(); //Não façam isso em casa crianças
            return false;
        } finally{
            bd.desconecta();
        }
        return true;
    }
    
    private String getValor() {
        return null;
    }
    
    public boolean delete(){
        Conexao bd = new Conexao();  
        String sql = "DELETE FROM ItemCompra WHERE id = ?";

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
    

    public static ArrayList<ItemCompra> getAll(){
        ArrayList<ItemCompra> ItemCompras = new ArrayList<ItemCompra>();

        Conexao bd = new Conexao();  
        String sql = "SELECT * FROM ItemCompra";
        try {                   
            PreparedStatement ps = bd.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ItemCompra f = new ItemCompra();
                f.setId(rs.getLong("id"));
                f.setDataCompra(rs.getString("DataCompra"));
                f.setCompraId(rs.getLong("CompraId"));
                f.setValor(rs.getDouble("Valor"));
                ItemCompras.add(f);
            }            
        } catch (SQLException e) {
            System.out.println("Erro ao consultar dados");
            e.printStackTrace(); //Não façam isso em casa crianças
        } finally{
            bd.desconecta();
        }
        return ItemCompras;
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
                this.setDataCompra(rs.getString("DataCompra"));
                this.setCompraId(rs.getLong("CompraId"));
                this.setValor(rs.getDouble("Valor"));

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