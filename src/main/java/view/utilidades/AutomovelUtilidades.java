package view.utilidades;

import connection.DataBase;
import model.Automovel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AutomovelUtilidades {
    static List<Automovel> automoveis = new ArrayList<>();

    public static void adicionarAutomovel(Automovel automovel) {
        String sql = "INSERT INTO veiculo (nome, chassi, condicao, placa, valor) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DataBase.getInstance().connection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, automovel.getNome());
            stmt.setString(2, automovel.getChassi());
            stmt.setString(3, automovel.getCondicao());
            stmt.setString(4, automovel.getPlaca());
            stmt.setDouble(5, automovel.getValor());

            stmt.executeUpdate();
            System.out.println("Automóvel cadastrado com sucesso!");

        } catch (Exception e) {
            System.err.println("Erro ao adicionar automóvel: " + e.getMessage());
        }
    }

    public static List<Automovel> listarAutomoveis() {
        List<Automovel> automoveis = new ArrayList<>(); // Inicializa a lista aqui
        String sql = "SELECT * FROM veiculo ORDER BY nome ASC";

        try (Connection conn = DataBase.getInstance().connection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Automovel automovel = new Automovel(
                        rs.getString("nome"),
                        rs.getString("chassi"),
                        rs.getString("placa"),
                        rs.getString("condicao"),
                        rs.getDouble("valor")
                );
                automovel.setId(rs.getInt("id"));
                automoveis.add(automovel);
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar automóveis: " + e.getMessage());
        }
        return automoveis;
    }

    public static boolean excluirAutomovel(int id) {
        String sql = "DELETE FROM veiculo WHERE id = ?";
        int linhasAfetadas = 0;

        try (Connection conn = DataBase.getInstance().connection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            linhasAfetadas = stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println("Erro ao excluir automóvel: " + e.getMessage());
        }

        return linhasAfetadas > 0;
    }
}