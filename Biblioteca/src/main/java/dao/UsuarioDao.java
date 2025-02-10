package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import dominio.Usuario;

public class UsuarioDao {
    public void salvar(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome, cpf, email) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoDao.conexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCPF());
            stmt.setString(3, usuario.getEmail());
            stmt.executeUpdate();

            System.out.println("Usuário cadastrado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao salvar usuário: " + e.getMessage());
        }
    }
}

