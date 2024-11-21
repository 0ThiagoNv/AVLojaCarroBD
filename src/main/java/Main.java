import java.sql.Connection;
import connection.DataBase;

import static menus.MenuPrincipal.MenuInicial;

public class Main {
    public static void main(String[] args) {

        Connection connection = (Connection) DataBase.getInstance();

        MenuInicial();

    }
}
