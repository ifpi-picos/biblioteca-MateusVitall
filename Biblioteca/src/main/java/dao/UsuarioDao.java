package dao;

import dominio.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {
    // Método para salvar um novo usuário no banco de dados
    public void salvar(Usuario usuario) {
        String sql = "INSERT INTO usuario (nome, cpf, email) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoDao.conexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCPF());
            stmt.setString(3, usuario.getEmail());
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Erro ao salvar usuário: " + e.getMessage());
        }
    }

    // Método para listar todos os usuários do banco de dados
    public List<Usuario> listar() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario";
        try (Connection conn = ConexaoDao.conexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String email = rs.getString("email");
                usuarios.add(new Usuario(nome, cpf, email));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar usuários: " + e.getMessage());
        }
        return usuarios;
    }

    // Método para atualizar um usuário existente no banco de dados
    public void atualizar(Usuario usuario) {
        String sql = "UPDATE usuario SET nome = ?, email = ? WHERE cpf = ?";
        try (Connection conn = ConexaoDao.conexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getCPF());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Usuário atualizado com sucesso!");
            } else {
                System.out.println("Nenhum usuário encontrado com o CPF informado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    // Método para excluir um usuário do banco de dados
    public void excluir(String cpf) {
        String sql = "DELETE FROM usuario WHERE cpf = ?";
        try (Connection conn = ConexaoDao.conexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
               System.out.println("Usuário excluído com sucesso!");
            } else {
                System.out.println("Nenhum usuário encontrado com o CPF informado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao excluir usuário: " + e.getMessage());
        }
    }
}