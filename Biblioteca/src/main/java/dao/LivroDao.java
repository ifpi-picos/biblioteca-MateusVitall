package dao;
import java.sql.*;
import dominio.Livro;

public class LivroDao {

    private Connection connection;

    public LivroDao(){
        this.connection = ConexaoDao.conexao();
    }

    
    public void salvar(Livro livro) {
        String sql = "INSERT INTO livros (titulo, autor, edicao, ano, emprestado) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setString(1, livro.getTitulo());
            preparedStatement.setString(2, livro.getAutor());
            preparedStatement.setString(3, livro.getEd());
            preparedStatement.setInt(4, livro.getAno());
            preparedStatement.setBoolean(5, livro.getEmprestado());

            preparedStatement.executeUpdate();
            System.out.println("Livro cadastrado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao salvar o livro: " + e.getMessage());
        }
    }
}