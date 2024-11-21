package entidades;

public class Admin extends Usuario{
    private String cargo;

    public Admin(String nome, String email, String senha, String cargo){
        super(nome, email, senha);
        this.cargo = cargo;
    }

    public String getCargo(){
        return cargo;
    }

    public void setCargo(String cargo){
        this.cargo = cargo;
    }
}
