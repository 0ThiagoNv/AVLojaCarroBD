package model;

import java.util.ArrayList;
import java.util.List;

public class Admin extends Usuario {

    private String cargo;

    public static List<Admin> listaAdminsCadastrados = new ArrayList<>();

    public Admin(String nome, String email, String senha, String cargo) {
        super(nome, email, senha, "admin");
        this.cargo = cargo;
    }
    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
