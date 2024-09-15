import java.sql.*;

public class App {
    
    public static void main(String[] args) throws SQLException {
        
        String url = "jdbc:postgresql://localhost:5432/seu_banco_de_dados";
        String usuario = "seu_usuario";
        String senha = "sua_senha";

        try (Connection connection = DriverManager.getConnection(url, usuario, senha)) {
            ContaDAO contaDAO = new ContaDAO(connection);

            Conta conta1 = new Conta(1234, "João Silva", 5000.00, 1000.00);
            contaDAO.incluirConta(conta1);
            System.out.println("Conta incluída com sucesso!");

            Conta contaConsultada = contaDAO.consultarConta(1234);
            if (contaConsultada != null) {
                System.out.println("Conta consultada:");
                System.out.println("Número: " + contaConsultada.getNumero());
                System.out.println("Titular: " + contaConsultada.getTitular());
                System.out.println("Limite de Crédito: " + contaConsultada.getLimiteCredito());
                System.out.println("Saldo: " + contaConsultada.getSaldo());
            } else {
                System.out.println("Conta não encontrada.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}