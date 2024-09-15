import java.sql.*;

public class ContaDAO {
    private Connection connection;

    public ContaDAO(Connection connection) {
        this.connection = connection;
    }

    public void incluirConta(Conta conta) throws SQLException {
        String sql = "INSERT INTO contas (numero, titular, limiteCredito, saldo) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, conta.getNumero());
            stmt.setString(2, conta.getTitular());
            stmt.setDouble(3, conta.getLimiteCredito());
            stmt.setDouble(4, conta.getSaldo());
            stmt.executeUpdate();
        }
    }

    public Conta consultarConta(int numero) throws SQLException {
        String sql = "SELECT * FROM contas WHERE numero = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, numero);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Conta(
                            rs.getInt("numero"),
                            rs.getString("titular"),
                            rs.getDouble("limiteCredito"),
                            rs.getDouble("saldo")
                    );
                }
            }
        }
        return null;
    }
}