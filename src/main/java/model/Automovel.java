package model;

public class Automovel {
    private int id;
    private String nome;
    private String chassi;
    private String placa;
    private String condicao;
    private double valor;

    public Automovel(String nome, String chassi, String placa, String condicao, double valor){
        this.nome = nome;
        this.chassi = chassi;
        this.placa = placa;
        this.condicao = condicao;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }


    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCondicao() {
        return condicao;
    }

    public void setCondicao(String novo) {
        this.condicao = novo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
