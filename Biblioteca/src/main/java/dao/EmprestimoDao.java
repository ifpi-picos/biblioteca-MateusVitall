package dao;

import dominio.Emprestimo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoDao {
    private Connection connection;

    public EmprestimoDao() {
        this.connection = ConexaoDao.conexao();
    }

    public void salvar(Emprestimo emprestimo) {
        String sql = "INSERT INTO emprestimo (usuario_id, livro_id, data_emprestimo, data_devolucao) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setInt(2, Integer.valueOf(emprestimo.getUsuarioId()));
            preparedStatement.setInt(1, Integer.valueOf(emprestimo.getLivroId()));
            preparedStatement.setDate(3, Date.valueOf(emprestimo.getDataEmprestimo()));
            preparedStatement.setDate(4, Date.valueOf(emprestimo.getDataDevolucao()));

            preparedStatement.executeUpdate();
            System.out.println("Empréstimo cadastrado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao salvar empréstimo: " + e.getMessage());
        }
    }

    public List<Emprestimo> buscarTodos() {
        List<Emprestimo> emprestimos = new ArrayList<>();
        String sql = "SELECT usuario_id, livro_id, data_emprestimo, data_devolucao FROM emprestimo";

        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Emprestimo emprestimo = new Emprestimo(
                    resultSet.getInt("usuario_id"),
                    resultSet.getInt("livro_id"),
                    resultSet.getDate("data_emprestimo").toLocalDate(),
                    resultSet.getDate("data_devolucao").toLocalDate()
                );
                emprestimos.add(emprestimo);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar empréstimos: " + e.getMessage());
        }

        return emprestimos;
    }

    public Emprestimo buscarPorId(int id) {
        String sql = "SELECT * FROM emprestimo WHERE id = ?";
        Emprestimo emprestimo = null;

        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                emprestimo = new Emprestimo(
                    resultSet.getInt("usuario_id"),
                    resultSet.getInt("livro_id"),
                    resultSet.getDate("data_emprestimo").toLocalDate(),
                    resultSet.getDate("data_devolucao").toLocalDate()
                    
                );
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar empréstimo: " + e.getMessage());
        }
        return emprestimo;
    }

    public void deletar(int id) {
        String sql = "DELETE FROM emprestimo WHERE id = ?";

        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Empréstimo deletado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao deletar empréstimo: " + e.getMessage());
        }
    }
}
