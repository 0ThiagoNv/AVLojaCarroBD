package view.menus;

import controller.UsuarioService;
import model.Cliente;
import model.Admin;

import java.util.Scanner;

public class MenuPrincipal {
    private static UsuarioService usuarioService = new UsuarioService();
    private static Scanner scan = new Scanner(System.in);

    public static void MenuInicial() {
        int opcao;
        do {
            System.out.println("Bem-vindo à Loja de Carros!");
            System.out.println("1. Login como Cliente");
            System.out.println("2. Login como Admin");
            System.out.println("3. Cadastrar");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scan.nextInt();
            scan.nextLine();

            switch (opcao) {
                case 1 -> loginComoCliente();
                case 2 -> loginComoAdmin();
                case 3 -> cadastrarUsuario();
                case 0 -> System.out.println("Saindo do sistema...");
                default -> System.out.println("Opção inválida, digite novamente.");
            }
        } while (opcao != 0);
    }

    private static void loginComoCliente() {
        System.out.println("Login como Cliente:");
        System.out.print("Email: ");
        String email = scan.nextLine();
        System.out.print("Senha: ");
        String senha = scan.nextLine();

        if (usuarioService.autenticar(email, senha)) {
            System.out.println("Login bem-sucedido!");
            MenuCliente.exibirMenu(scan);
        } else {
            System.out.println("Email ou senha inválidos!");
        }
    }

    private static void loginComoAdmin() {
        System.out.println("Login como Admin:");
        System.out.print("Email: ");
        String email = scan.nextLine();
        System.out.print("Senha: ");
        String senha = scan.nextLine();

        if (usuarioService.autenticar(email, senha)) {
            System.out.println("Login bem-sucedido!");
            MenuAdmin.exibirMenu(scan);
        } else {
            System.out.println("Email ou senha inválidos!");
        }
    }

    private static void cadastrarUsuario() {
        System.out.println("Cadastrar como: ");
        System.out.println("1. Cliente");
        System.out.println("2. Admin");
        System.out.print("Escolha uma opção: ");
        int tipoCadastro = scan.nextInt();
        scan.nextLine();

        switch (tipoCadastro) {
            case 1 -> cadastrarCliente();
            case 2 -> cadastrarAdmin();
            default -> System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private static void cadastrarCliente() {
        System.out.println("Cadastro de Cliente:");
        System.out.print("Nome: ");
        String nome = scan.nextLine();
        System.out.print("Email: ");
        String email = scan.nextLine();
        System.out.print("Senha: ");
        String senha = scan.nextLine();

        usuarioService.cadastrarCliente(nome, email, senha);
        System.out.println("Cadastro de cliente realizado com sucesso!");
    }

    private static void cadastrarAdmin() {
        System.out.println("Cadastro de Admin:");
        System.out.print("Nome: ");
        String nome = scan.nextLine();
        System.out.print("Email: ");
        String email = scan.nextLine();
        System.out.print("Senha: ");
        String senha = scan.nextLine();

        usuarioService.cadastrarAdmin(nome, email, senha);
        System.out.println("Cadastro de administrador realizado com sucesso!");
    }
}