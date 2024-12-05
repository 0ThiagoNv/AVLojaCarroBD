package model;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {


    public static List<Cliente> listaClientesCadastrados = new ArrayList<>();

    public Cliente(String nome, String email, String senha) {
        super(nome, email, senha, "cliente");
    }
}