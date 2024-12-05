package model;

import java.util.ArrayList;
import java.util.List;

public class Admin extends Usuario {

    public static List<Admin> listaAdminsCadastrados = new ArrayList<>();

    public Admin(String nome, String email, String senha) {
        super(nome, email, senha, "cliente");
    }
}
