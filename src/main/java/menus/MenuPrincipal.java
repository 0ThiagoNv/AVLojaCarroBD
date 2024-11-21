package menus;

import java.util.Scanner;

import utilidades.Autenticador;

public class MenuPrincipal {
    public static void MenuInicial() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo à Loja de Carros!");
        System.out.println("1. Login como Cliente");
        System.out.println("2. Login como Gerente");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1 -> {
                if (Autenticador.login("CLIENTE", scanner)) {
                    MenuCliente.exibirMenu(scanner);
                }
            }
            case 2 -> {
                if (Autenticador.login("FUNCIONARIO", scanner)) {
                    MenuAdmin.exibirMenu(scanner);
                }
            }
            case 0 -> System.out.println("Saindo do sistema...");
            default -> System.out.println("Opção inválida!");
        }
        scanner.close();
    }
}
