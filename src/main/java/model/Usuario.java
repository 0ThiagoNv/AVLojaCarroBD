package model;

import org.mindrot.jbcrypt.BCrypt;

public abstract class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String tipo_usuario;

    public Usuario(String nome, String email, String senha, String tipo) {
        this.nome = nome;
        this.email = email;
        this.setSenha(senha);
        this.tipo_usuario = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo_usuario;
    }

    public void setTipo(String tipo) {
        this.tipo_usuario = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = hashSenha(senha);
    }

    private String hashSenha(String senha) {
        return BCrypt.hashpw(senha, BCrypt.gensalt());
    }

    public boolean verificarSenha(String senha) {
        return BCrypt.checkpw(senha, this.senha);
    }
}