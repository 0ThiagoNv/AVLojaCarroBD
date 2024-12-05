package model.DAO;

import model.Admin;
import model.Cliente;
import model.Usuario;
import connection.DataBase;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Types;

public class UsuarioDAO {

    public boolean autenticar(String email, String senha) {
        String sql = "SELECT senha FROM usuarios WHERE email = ?";

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
    public void cadastrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome, email, senha, tipo, cargo, endereco, cpf, telefone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DataBase.getInstance().connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setString(3, BCrypt.hashpw(usuario.getSenha(), BCrypt.gensalt()));
            pstmt.setString(4, usuario.getTipo());

            if (usuario instanceof Admin) {
                pstmt.setString(5, ((Admin) usuario).getCargo());
                pstmt.setNull(6, Types.VARCHAR);
                pstmt.setNull(7, Types.VARCHAR);
                pstmt.setNull(8, Types.VARCHAR);
            }

            else if (usuario instanceof Cliente) {
                pstmt.setNull(5, Types.VARCHAR);
                pstmt.setString(6, ((Cliente) usuario).getEndereco());
                pstmt.setString(7, ((Cliente) usuario).getCpf());
                pstmt.setString(8, ((Cliente) usuario).getTelefone());
            }

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Usuario> listarUsuarios(int idUsuarioLogado) {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios WHERE id = ?";

        try (Connection conn = DataBase.getInstance().connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idUsuarioLogado);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String tipo = rs.getString("tipo");
                Usuario usuario;

                if ("admin".equals(tipo)) {
                    String cargo = rs.getString("cargo");
                    usuario = new Admin(rs.getString("nome"), rs.getString("email"), rs.getString("senha"), cargo);
                } else {
                    String endereco = rs.getString("endereco");
                    String cpf = rs.getString("cpf");
                    String telefone = rs.getString("telefone");
                    usuario = new Cliente(rs.getString("nome"), rs.getString("email"), rs.getString("senha"), endereco, cpf, telefone);
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

        String sql = "UPDATE usuarios SET nome = ?, email = ?, senha = ?, tipo = ?, cargo = ?, endereco = ?, cpf = ?, telefone = ? WHERE id = ?";

        try (Connection conn = DataBase.getInstance().connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setString(3, BCrypt.hashpw(usuario.getSenha(), BCrypt.gensalt()));
            pstmt.setString(4, usuario.getTipo());


            if (usuario instanceof Admin) {
                pstmt.setString(5, ((Admin) usuario).getCargo());
                pstmt.setNull(6, Types.VARCHAR);
                pstmt.setNull(7, Types.VARCHAR);
                pstmt.setNull(8, Types.VARCHAR);
            }

            else if (usuario instanceof Cliente) {
                pstmt.setNull(5, Types.VARCHAR);
                pstmt.setString(6, ((Cliente) usuario).getEndereco());
                pstmt.setString(7, ((Cliente) usuario).getCpf());
                pstmt.setString(8, ((Cliente) usuario).getTelefone());
            }

            pstmt.setInt(9, usuario.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarUsuario(int id, int idUsuarioLogado) {
        if (id != idUsuarioLogado) {
            throw new SecurityException("Usuário não autorizado a deletar este registro.");
        }

        String sql = "DELETE FROM usuarios WHERE id = ?";

        try (Connection conn = DataBase.getInstance().connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Usuario> listarUsuariosComPapel() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT u.*, r.nome AS papel FROM usuarios u INNER JOIN roles r ON u.tipo = r.id ORDER BY u.nome";

        try (Connection conn = DataBase.getInstance().connection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String tipo = rs.getString("tipo");
                Usuario usuario;

                if ("admin".equals(tipo)) {
                    String cargo = rs.getString("cargo");
                    usuario = new Admin(rs.getString("nome"), rs.getString("email"), rs.getString("senha"), cargo);
                } else {
                    String endereco = rs.getString("endereco");
                    String cpf = rs.getString("cpf");
                    String telefone = rs.getString("telefone");
                    usuario = new Cliente(rs.getString("nome"), rs.getString("email"), rs.getString("senha"), endereco, cpf, telefone);
                }

                usuario.setId(rs.getInt("id"));
                String papel = rs.getString("papel");
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public List<Usuario> buscarUsuariosPorNome(String nome) {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios WHERE nome LIKE ? ORDER BY nome";

        try (Connection conn = DataBase.getInstance().connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nome + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String tipo = rs.getString("tipo");
                Usuario usuario;

                if ("admin".equals(tipo)) {
                    String cargo = rs.getString("cargo");
                    usuario = new Admin(rs.getString("nome"), rs.getString("email"), rs.getString("senha"), cargo);
                } else {
                    String endereco = rs.getString("endereco");
                    String cpf = rs.getString("cpf");
                    String telefone = rs.getString("telefone");
                    usuario = new Cliente(rs.getString("nome"), rs.getString("email"), rs.getString("senha"), endereco, cpf, telefone);
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
        String sql = "SELECT * FROM usuarios ORDER BY email";

        try (Connection conn = DataBase.getInstance().connection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String tipo = rs.getString("tipo");
                Usuario usuario;

                if ("admin".equals(tipo)) {
                    String cargo = rs.getString("cargo");
                    usuario = new Admin(rs.getString("nome"), rs.getString("email"), rs.getString("senha"), cargo);
                } else {
                    String endereco = rs.getString("endereco");
                    String cpf = rs.getString("cpf");
                    String telefone = rs.getString("telefone");
                    usuario = new Cliente(rs.getString("nome"), rs.getString("email"), rs.getString("senha"), endereco, cpf, telefone);
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
