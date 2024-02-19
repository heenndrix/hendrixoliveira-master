package br.edu.ifrs.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Categoria {

    private long id = 0;
    private String Descricao;
    private String nome;
    private Double Valor;
    private String Categoria;

    @Override
    public String toString() {
        return "Categoria [id=" + id + ",nome=" + nome + "Valor=" + Valor + "Descricao=" + "Categoria=" + Categoria +  "Descricao=" + Descricao + "]";
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = extracted(nome);
    }

    public Double getValor() {
        return Valor;
    }

    public void setValor(Double valor) {
        Valor = valor;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        Descricao = extracted(Descricao);
    }

    private static String extracted(String Descricao) {
        return Descricao;
    }

    public boolean insert() {
        Conexao bd = new Conexao();
        String sql = "INSERT INTO Categoria (id, nome, Valor, Categoria, Descricao) VALUES (Categoria_Produto.NEXTVAL, ? , ? , ? , ?)";
        try {
            PreparedStatement ps = bd.getConexao().prepareStatement(extracted(sql));
            ps.setString(1, this.getNome());
            ps.setDouble(2, this.getValor());
            ps.setString(3, this.getCategoria());
            ps.setString(4, this.getDescricao());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Não façam isso em casa crianças
            return false;
        } finally {
            bd.desconecta();
        }
        return true;
    }

    public boolean update() {
        Conexao bd = new Conexao();
        String sql = "UPDATE Categoria SET Descricao=? WHERE id = ?";

        try {
            PreparedStatement ps = bd.getConexao().prepareStatement(extracted(sql));
            ps.setLong(3, this.getId());
            ps.setString(2, this.getDescricao());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(); // Não façam isso em casa crianças
            return false;
        } finally {
            bd.desconecta();
        }
        return true;
    }

    public boolean delete() {
        Conexao bd = new Conexao();
        String sql = "DELETE FROM Categoria WHERE id = ?";

        try {
            PreparedStatement ps = bd.getConexao().prepareStatement(extracted(sql));
            ps.setLong(1, this.id);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Não façam isso em casa crianças
            return false;
        } finally {
            bd.desconecta();
        }
        return true;

    }

    public static ArrayList<Categoria> getAll() {
        ArrayList<Categoria> Categorias = new ArrayList<Categoria>();

        Conexao bd = new Conexao();
        String sql = "SELECT * FROM Categoria";
        try {
            PreparedStatement ps = bd.getConexao().prepareStatement(extracted(sql));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Categoria d = new Categoria();
                d.setId(rs.getLong("id"));
                d.setDescricao(rs.getString("Descricao"));
                Categorias.add(d);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar dados");
            e.printStackTrace(); // Não façam isso em casa crianças
        } finally {
            bd.desconecta();
        }
        return Categorias;
    }

    public boolean load() {

        Conexao bd = new Conexao();
        String sql = "SELECT * FROM Categoria WHERE id = ?";
        try {
            PreparedStatement ps = bd.getConexao().prepareStatement(extracted(sql));
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                this.setId(rs.getLong("id"));
                this.setDescricao(rs.getString("Descricao"));

                return true;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar dados");
            e.printStackTrace(); // Não façam isso em casa crianças
        } finally {
            bd.desconecta();
        }
        return false;

    }

}