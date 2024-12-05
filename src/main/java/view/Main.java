package view;

import connection.DataBase;
import view.menus.MenuPrincipal;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = DataBase.getInstance().connection()) {
            if (conn != null) {
                System.out.println("Conexão bem-sucedida!");
                MenuPrincipal.MenuInicial();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}