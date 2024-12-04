package utilidades;
import java.util.Scanner;
import connection.DataBase;
import at.favre.lib.crypto.bcrypt.BCrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Autenticador {

    public static boolean login(String userType, Scanner scanner) {
        System.out.print("Digite seu email: ");
        String email = scanner.nextLine();
        System.out.print("Digite sua senha: ");
        String senha = scanner.nextLine();

        String sql = "SELECT senha FROM usuarios WHERE email = ? AND tipo_usuario = ?";
        try (Connection conn = DataBase.getInstance().connection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, userType);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String hashedPassword = rs.getString("senha");
                BCrypt.Result result = BCrypt.verifyer().verify(senha.toCharArray(), hashedPassword);

                if (result.verified) {
                    System.out.println("Login realizado com sucesso!");
                    return true;
                } else {
                    System.out.println("Senha incorreta.");
                }
            } else {
                System.out.println("Usuário não encontrado.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao realizar login: " + e.getMessage());
        }
        return false;
    }

    public static void cadastrarUsuario(String nome, String email, String senha, String tipoUsuario) {
        String hashedPassword = BCrypt.withDefaults().hashToString(12, senha.toCharArray());
        String sql = "INSERT INTO usuarios (nome, email, senha, tipo_usuario) VALUES (?, ?, ?, ?)";

        try (Connection conn = DataBase.getInstance().connection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, hashedPassword);
            stmt.setString(4, tipoUsuario);

            stmt.executeUpdate();
            System.out.println("Usuário cadastrado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }
}
