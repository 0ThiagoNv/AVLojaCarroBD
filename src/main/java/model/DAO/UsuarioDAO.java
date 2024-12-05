package model.DAO;

import model.Admin;
import model.Cliente;
import model.Usuario;
import connection.DataBase;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public boolean autenticar(String email, String senha) {
        String sql = "SELECT senha FROM usuario WHERE email = ?";

        try (Connection conn = DataBase.getInstance().connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String senhaHash = rs.getString("senha");
                return BCrypt.checkpw(senha, senhaHash);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean emailJaCadastrado(String email) {
        String sql = "SELECT COUNT(*) FROM usuario WHERE email = ?";
        try (Connection conn = DataBase.getInstance().connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void cadastrarUsuario(Usuario usuario) {

        if (emailJaCadastrado(usuario.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado: " + usuario.getEmail());
        }

        if (usuario.getNome().length() > 100) {
            throw new IllegalArgumentException("Nome muito longo. Máximo de 100 caracteres.");
        }
        if (usuario.getEmail().length() > 100) {
            throw new IllegalArgumentException("Email muito longo. Máximo de 100 caracteres.");
        }
        if (usuario.getSenha().length() > 100) {
            throw new IllegalArgumentException("Senha muito longa. Máximo de 100 caracteres.");
        }
        String sql = "INSERT INTO usuario (nome, email, senha, tipo_usuario) VALUES (?, ?, ?, ?)";

        try (Connection conn = DataBase.getInstance().connection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setString(3, usuario.getSenha());
            pstmt.setString(4, usuario.getTipo());

            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    usuario.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Falha ao recuperar o ID do usuário.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Usuario> listarUsuarios(int idUsuarioLogado) {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario WHERE id = ?";

        try (Connection conn = DataBase.getInstance().connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idUsuarioLogado);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Usuario usuario;

                if ("admin".equals(rs.getString("tipo_usuario"))) {
                    usuario = new Admin(rs.getString("nome"), rs.getString("email"), rs.getString("senha"));
                } else {
                    usuario = new Cliente(rs.getString("nome"), rs.getString("email"), rs.getString("senha"));
                }
                usuario.setId(rs.getInt("id"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public void atualizarUsuario(Usuario usuario, int idUsuarioLogado) {
        if (usuario.getId() != idUsuarioLogado) {
            throw new SecurityException("Usuário não autorizado a atualizar este registro.");
        }

        String sql = "UPDATE usuario SET nome = ?, email = ?, senha = ? WHERE id = ?";

        try (Connection conn = DataBase.getInstance().connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setString(3, usuario.getSenha());
            pstmt.setInt(4, usuario.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarUsuario(int id, int idUsuarioLogado) {
        if (id != idUsuarioLogado) {
            throw new SecurityException("Usuário não autorizado a deletar este registro.");
        }

        String sql = "DELETE FROM usuario WHERE id = ?";

        try (Connection conn = DataBase.getInstance().connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Usuario> buscarUsuariosPorNome(String nome) {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario WHERE nome LIKE ? ORDER BY nome";

        try (Connection conn = DataBase.getInstance().connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nome + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Usuario usuario;

                if ("admin".equals(rs.getString("tipo_usuario"))) {
                    usuario = new Admin(rs.getString("nome"), rs.getString("email"), rs.getString("senha"));
                } else {
                    usuario = new Cliente(rs.getString("nome"), rs.getString("email"), rs.getString("senha"));
                }
                usuario.setId(rs.getInt("id"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public List<Usuario> listarUsuariosOrdenadosPorEmail() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario ORDER BY email";

        try (Connection conn = DataBase.getInstance().connection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario;

                if ("admin".equals(rs.getString("tipo_usuario"))) {
                    usuario = new Admin(rs.getString("nome"), rs.getString("email"), rs.getString("senha"));
                } else {
                    usuario = new Cliente(rs.getString("nome"), rs.getString("email"), rs.getString("senha"));
                }
                usuario.setId(rs.getInt("id"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }
}