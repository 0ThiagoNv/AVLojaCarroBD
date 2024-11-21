package utilidades;

import connection.DataBase;
import entidades.Automovel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AutomovelUtilidades {

    public static void adicionarAutomovel(Automovel automovel) {
        String sql = "INSERT INTO automoveis (nome, chassi, condicao, placa, valor) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DataBase.getInstance().connection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, automovel.getNome());
            stmt.setBoolean(3, automovel.isCondicao());
            stmt.setString(2, automovel.getChassi());
            stmt.setString(4, automovel.getPlaca());
            stmt.setDouble(5, automovel.getValor());

            stmt.executeUpdate();
            System.out.println("Automóvel cadastrado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao adicionar automóvel: " + e.getMessage());
        }
    }

    public static List<Automovel> listarAutomoveis() {
        String sql = "SELECT * FROM automoveis ORDER BY nome ASC";
        List<Automovel> automoveis = new ArrayList<>();

        try (Connection conn = DataBase.getInstance().connection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Automovel automovel = new Automovel(
                        rs.getString("nome"),
                        rs.getString("chassi"),
                        rs.getString("placa"),
                        rs.getBoolean("condicao"),
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

    public static void excluirAutomovel(int id) {
        String sql = "DELETE FROM automoveis WHERE id = ?";

        try (Connection conn = DataBase.getInstance().connection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Automóvel excluído com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao excluir automóvel: " + e.getMessage());
        }
    }
}
