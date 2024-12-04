import connection.DataBase;
import menus.MenuPrincipal;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        boolean conexaoBemSucedida = false;

        try (Connection conn = DataBase.connection()) {
            if (conn != null) {
                System.out.println("Conexao bem-sucedida!");
                conexaoBemSucedida = true;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        if (conexaoBemSucedida) {
            MenuPrincipal.MenuInicial();
        }else{
            System.out.println("Erro ao tentar conectar!");
        }
    }
}
