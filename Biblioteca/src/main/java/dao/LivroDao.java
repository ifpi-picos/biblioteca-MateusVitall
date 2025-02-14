package dao;
import java.sql.*;
import dominio.Livro;

public class LivroDao {

    private Connection connection;

    public LivroDao(){
        this.connection = ConexaoDao.conexao();
    }

    
    public void salvar(Livro livro) {
        String sql = "INSERT INTO livro (titulo, autor, edicao, ano, emprestado) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setString(1, livro.getTitulo());
            preparedStatement.setString(2, livro.getAutor());
            preparedStatement.setString(3, livro.getEd());
            preparedStatement.setInt(4, livro.getAno());
            preparedStatement.setBoolean(5, livro.getEmprestado());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao salvar o livro: " + e.getMessage());
        }
    }

    public void listarTodos() {
        String sql = "SELECT * FROM livro";

        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                System.out.println("Título: " + resultSet.getString("titulo"));
                System.out.println("Autor: " + resultSet.getString("autor"));
                System.out.println("Edição: " + resultSet.getString("edicao"));
                System.out.println("Ano: " + resultSet.getInt("ano"));
                System.out.println("Emprestado: " + resultSet.getBoolean("emprestado"));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar livros: " + e.getMessage());
        }
    }
}