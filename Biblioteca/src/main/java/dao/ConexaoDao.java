package dao;
 
import java.sql.*;

public class ConexaoDao {
    
    public static Connection conexao(){
        try {
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sua_base","postgres" , "mateusdb");
            if (conexao != null) {
                System.out.println("Conectado ao banco de dados!");
                return conexao;
            } 
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco: " + e.getMessage());
        }
        return null;
    }
}
