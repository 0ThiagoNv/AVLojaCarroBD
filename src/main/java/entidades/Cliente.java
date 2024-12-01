package entidades;

import java.util.ArrayList;

public class Cliente extends Usuario {

    private String endereco;

    public static ArrayList<Cliente> listaClientesCadastrados = new ArrayList<>();

    public Cliente(String nome, String email, String senha, String cpf, String telefone, String endereco) {
        super(nome, email, senha);
        this.endereco = endereco;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
