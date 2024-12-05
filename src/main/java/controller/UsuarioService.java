package controller;

import model.DAO.UsuarioDAO;
import model.Usuario;
import model.Cliente;
import model.Admin;

import java.util.List;

public class UsuarioService {
    private UsuarioDAO usuarioDAO;

    public UsuarioService() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public boolean autenticar(String email, String senha) {
        return usuarioDAO.autenticar(email, senha);
    }

    public void cadastrarCliente(String nome, String email, String senha) {
        Usuario cliente = new Cliente(nome, email, senha);
        usuarioDAO.cadastrarUsuario(cliente);
    }

    public void cadastrarAdmin(String nome, String email, String senha) {
        Usuario admin = new Admin(nome, email, senha);
        usuarioDAO.cadastrarUsuario(admin);
    }

    public List<Usuario> listarUsuarios(int idUsuarioLogado) {
        return usuarioDAO.listarUsuarios(idUsuarioLogado);
    }

    public void atualizarUsuario(Usuario usuario, int idUsuarioLogado) {
        usuarioDAO.atualizarUsuario(usuario, idUsuarioLogado);
    }

    public void deletarUsuario(int id, int idUsuarioLogado) {
        usuarioDAO.deletarUsuario(id, idUsuarioLogado);
    }

    public List<Usuario> buscarUsuariosPorNome(String nome) {
        return usuarioDAO.buscarUsuariosPorNome(nome);
    }

    public List<Usuario> listarUsuariosOrdenadosPorEmail() {
        return usuarioDAO.listarUsuariosOrdenadosPorEmail();
    }
}