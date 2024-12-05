package model;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {

    private String endereco;
    private String cpf;
    private String telefone;

    public static List<Cliente> listaClientesCadastrados = new ArrayList<>();

    public Cliente(String nome, String email, String senha, String cpf, String telefone, String endereco) {
        super(nome, email, senha, "cliente");
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}