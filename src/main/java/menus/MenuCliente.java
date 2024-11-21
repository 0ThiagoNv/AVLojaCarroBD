package menus;

import utilidades.AutomovelUtilidades;
import java.util.Scanner;

public class MenuCliente {

    public static void exibirMenu(Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n=== Menu do Cliente ===");
            System.out.println("1. Listar os automoveis disponiveis");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> listarAutomoveis();
                case 0 -> System.out.println("Saindo do menu do cliente...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
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
}
