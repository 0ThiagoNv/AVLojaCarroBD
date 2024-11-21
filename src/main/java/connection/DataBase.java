package connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {

    private static DataBase instance;

    private final Connection connection;

    private DataBase() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "post");
            System.out.println("Conexão bem sucedida!");
        }catch (SQLException e){
            System.out.println("Erro " + e.getMessage());
            throw new RuntimeException("Erro ao tentar conectar com o banco de dados");
        }
    }

    public static DataBase getInstance(){
        if(instance == null){
            instance = new DataBase();
        }
        return instance;
    }

    public Connection connection(){
        return connection;
    }
}
