package br.edu.ifrs.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Venda {
    private int id;
    private int usuarioId;//Sem get e sets
    private Usuario usuario = null; //Iniciado com null
    private ArrayList<Produto> Produtos;

    public Usuario getUsuario(){
        if(this.usuario == null){
            this.usuario = new Usuario();
            this.usuario.setId(this.usuarioId);
            this.usuario.load();
        }
        return this.usuario;
    }

    //HARD LOAD
    public boolean loadHard(){
        Conexao bd = new Conexao();  
        String sql = "SELECT * FROM venda WHERE id = ?";
        try {                   
            PreparedStatement ps = bd.getConexao().prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                //Observe o carregamento aqui
                int idUsuario =  rs.getInt("usuario_id");
                this.usuario = new Usuario();
                this.usuario.setId(idUsuario);
                this.usuario.load();

                //this.setNome(rs.getString("nome"));
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

    public boolean lazyLoad(){
        Conexao bd = new Conexao();  
        String sql = "SELECT * FROM venda WHERE id = ?";
        try {                   
            PreparedStatement ps = bd.getConexao().prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                //Observe o carregamento aqui
                this.usuarioId =  rs.getInt("usuario_id");                

                //this.setNome(rs.getString("nome"));
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
