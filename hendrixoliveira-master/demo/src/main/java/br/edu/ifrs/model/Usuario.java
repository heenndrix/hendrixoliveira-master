package br.edu.ifrs.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.format.annotation.DateTimeFormat;

public class Usuario {
    private String Usuario;
    private long id =0;
    private String senha;
    private String Email;
    

    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Calendar dataNascimento; //
    

    
    

    @Override
    public String toString() {
        return "Usuario [Usuario=" + Usuario + ", id=" + id + ", senha=" + senha + ", dataNascimento=" + dataNascimento + " Email=" + Email + " ]";
    }
    public String getUsuario() {
        return Usuario;
    }
    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getEmail() {
        return Email;
    }
    public void setEmail(String Email) {
        this.Email = Email;
    }
    
    

    

    public boolean insert(){
        Conexao bd = new Conexao(); 
        String sql =  "INSERT INTO usuario (id, Usuario, senha, dataNascimento, Email) VALUES (usuario_id.NEXTVAL, ?, ?, ?, ?)";
        try {                   
            PreparedStatement ps = bd.getConexao().prepareStatement(sql);
            ps.setString(1, this.getUsuario());
            ps.setString(2, this.getSenha());
            ps.setDate(3, new java.sql.Date(this.getDataNascimento().getTimeInMillis()));
            ps.setString(4, this.getEmail());

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
        String sql = "UPDATE usuario SET Usuario = ?, senha=? WHERE id = ?";

        try {                   
            PreparedStatement ps = bd.getConexao().prepareStatement(sql);
            ps.setLong(1, this.getId());
            ps.setString(2, this.getUsuario());
            ps.setString(3, this.getSenha());
            
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
        String sql = "DELETE FROM Usuario WHERE id = ?";

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
    

    public static ArrayList<Usuario> getAll(){
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

        Conexao bd = new Conexao();  
        String sql = "SELECT * FROM Usuario";
        try {                   
            PreparedStatement ps = bd.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Usuario u = new Usuario();
                u.setId(rs.getLong("id"));
                u.setUsuario(rs.getString("Usuario"));
                usuarios.add(u);
            }            
        } catch (SQLException e) {
            System.out.println("Erro ao consultar dados");
            e.printStackTrace(); //Não façam isso em casa crianças
        } finally{
            bd.desconecta();
        }
        return usuarios;
    }

    public boolean load(){

        Conexao bd = new Conexao();  
        String sql = "SELECT * FROM Usuario WHERE id = ?";
        try {                   
            PreparedStatement ps = bd.getConexao().prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
               
                this.setUsuario(rs.getString("Usuario"));
                this.setSenha(rs.getString("senha"));

                Calendar cal = new GregorianCalendar();
                cal.setTime(rs.getDate("dataNascimento"));//date vem do sql de query
                this.setDataNascimento(cal);
                
                this.setEmail(rs.getString("Email"));

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
    
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public Calendar getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(Calendar dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public boolean verificaSenha() {
        Conexao bd = new Conexao();  
        String sql = "SELECT * FROM Usuario WHERE usuario = ? and senha = ?";
        try {                   
            PreparedStatement ps = bd.getConexao().prepareStatement(sql);
            ps.setString(1, this.Usuario);
            ps.setString(2, this.senha);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
               
                this.setId(rs.getLong("id"));
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
