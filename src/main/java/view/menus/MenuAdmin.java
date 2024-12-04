package menus;

import entidades.Automovel;
import utilidades.AutomovelUtilidades;

import java.util.Scanner;

public class MenuAdmin {

    public static void exibirMenu(Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n=== Menu do Gerente ===");
            System.out.println("1. Adicionar automovel");
            System.out.println("2. Listar automoveis");
            System.out.println("3. Excluir automovel");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> adicionarAutomovel(scanner);
                case 2 -> listarAutomoveis();
                case 3 -> excluirAutomovel(scanner);
                case 0 -> System.out.println("Saindo do menu do gerente...");
                default -> System.out.println("Opção invalida!");
            }
        } while (opcao != 0);
    }

    private static void adicionarAutomovel(Scanner scanner) {
        System.out.println("\n=== Adicionar Automóvel ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Chassi: ");
        String chassi = scanner.nextLine();
        System.out.print("É novo? (true/false): ");
        boolean condicao = scanner.nextBoolean();
        scanner.nextLine();
        System.out.print("Placa: ");
        String placa = scanner.nextLine();
        System.out.print("Valor: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();

        Automovel automovel = new Automovel(nome, chassi, placa, condicao, valor);
        AutomovelUtilidades.adicionarAutomovel(automovel);
    }

    private static void listarAutomoveis() {
        System.out.println("\n=== Lista de Automoveis ===");
        var automoveis = AutomovelUtilidades.listarAutomoveis();
        if (automoveis.isEmpty()) {
            System.out.println("Nenhum automovel disponivel.");
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

    private static void excluirAutomovel(Scanner scanner) {
        System.out.println("\n=== Excluir Automovel ===");
        listarAutomoveis();
        System.out.print("Digite o ID do automovel que deseja excluir: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        AutomovelUtilidades.excluirAutomovel(id);
    }
}
