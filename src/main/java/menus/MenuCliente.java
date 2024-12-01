package menus;

import utilidades.AutomovelUtilidades;
import java.util.Scanner;

public class MenuCliente {

    public static void exibirMenu(Scanner scan) {
        int opcao = scan.nextInt();

        while(opcao != 1 && opcao != 0){
            System.out.println("Opção invalidade, digite novamente: ");
            opcao = scan.nextInt();
        }
            System.out.println("\n=== Menu do Cliente ===");
            System.out.println("1. Listar os automoveis disponiveis");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scan.nextInt();
            scan.nextLine();

            switch (opcao) {
                case 1 -> listarAutomoveis();
                case 0 -> {
                    System.out.println("Saindo do menu do cliente...");
                    MenuPrincipal.MenuInicial();
                }
            }
        }

    private static void listarAutomoveis() {
        System.out.println("\n=== Lista de Automóveis ===");
        var automoveis = AutomovelUtilidades.listarAutomoveis();
        if (automoveis.isEmpty()) {
            System.out.println("Nenhum automóvel disponível.");
        } else {
            automoveis.forEach(automovel -> {
                System.out.printf(
                        "ID: %d | Nome: %s | Chassi: %s | Novo: %s | Placa: %s | Valor: R$ %.2f%n",
                        automovel.getId(),
                        automovel.getNome(),
                        automovel.getChassi(),
                        automovel.getPlaca(),
                        automovel.isCondicao() ? "Novo" : "Usado",
                        automovel.getValor()
                );
            });
        }
    }

    public static void exibirMenu() {
    }
}
