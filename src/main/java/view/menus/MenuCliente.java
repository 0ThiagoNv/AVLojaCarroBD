package view.menus;

import view.utilidades.AutomovelUtilidades;
import java.util.Scanner;

public class MenuCliente {

    public static void exibirMenu(Scanner scan) {
        int opcao;

        do {
            System.out.println("\n=== Menu do Cliente ===");
            System.out.println("1. Listar os automóveis disponíveis");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scan.nextInt();
            scan.nextLine();

            switch (opcao) {
                case 1:
                    listarAutomoveis();
                    break;
                case 0:
                    System.out.println("Saindo do menu do cliente...");
                    break;
                default:
                    System.out.println("Opção inválida, digite novamente.");
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
                        automovel.getCondicao(),
                        automovel.getPlaca(),
                        automovel.getValor()
                );
            });
        }
    }
}