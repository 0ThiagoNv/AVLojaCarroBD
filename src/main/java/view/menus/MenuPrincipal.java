package menus;

import java.util.Scanner;
import entidades.Cliente;
import entidades.Admin;
import utilidades.Autenticador;

public class MenuPrincipal {
    public static void MenuInicial() {

        Scanner scan = new Scanner(System.in);

        System.out.println("Bem-vindo à Loja de Carros!");
        System.out.println("1. Login como Cliente");
        System.out.println("2. Login como Admin");
        System.out.println("3. Cadastrar");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
        int opcao = scan.nextInt();
        scan.nextLine();

        while (opcao != 1 && opcao != 2 && opcao != 3 && opcao != 0) {
            System.out.println("Opção invalidade, digite novamente: ");
            opcao = scan.nextInt();
        }
        switch (opcao) {
            case 1: {
                System.out.println("Login como Cliente:");
                System.out.print("Email: ");
                String email = scan.nextLine();
                System.out.print("Senha: ");
                String senha = scan.nextLine();

                boolean encontrado = false;

                for (Cliente cliente : Cliente.listaClientesCadastrados) {
                    if (cliente.getEmail().equals(email) && cliente.getSenha().equals(senha)) {
                        System.out.println("Login bem-sucedido! Bem-vindo, " + cliente.getNome() + "!");
                        MenuCliente.exibirMenu(scan);
                        encontrado = true;
                        break;
                    }
                }
                if (!encontrado) {
                    System.out.println("Email ou senha inválidos!");
                }
                break;
            }
            case 2: {
                System.out.println("Login como Admin:");
                System.out.print("Email: ");
                String email = scan.nextLine();
                System.out.print("Senha: ");
                String senha = scan.nextLine();

                boolean encontrado = false;

                for (Admin admin : Admin.listaAdminsCadastrados) {
                    if (admin.getEmail().equals(email) && admin.getSenha().equals(senha)) {
                        System.out.println("Login bem-sucedido! Bem-vindo, " + admin.getNome() + "!");
                        MenuAdmin.exibirMenu(scan);
                        encontrado = true;
                        break;
                    }
                }
                if (!encontrado) {
                    System.out.println("Email ou senha inválidos!");
                }
                break;
            }
            case 3: {
                System.out.println("Cadastrar como: ");
                System.out.println("1. Cliente");
                System.out.println("2. Admin");
                System.out.print("Escolha uma opção: ");
                int tipoCadastro = scan.nextInt();
                scan.nextLine();

                switch (tipoCadastro) {
                    case 1: {
                        System.out.println("Cadastro de Cliente:");
                        System.out.print("Nome: ");
                        String nome = scan.nextLine();
                        System.out.print("Email: ");
                        String email = scan.nextLine();
                        System.out.print("Senha: ");
                        String senha = scan.nextLine();
                        System.out.print("CPF: ");
                        String cpf = scan.nextLine();
                        System.out.print("Telefone: ");
                        String telefone = scan.nextLine();
                        System.out.print("Endereço: ");
                        String endereco = scan.nextLine();

                        Cliente cliente = new Cliente(nome, email, senha, cpf, telefone, endereco);
                        Cliente.listaClientesCadastrados.add(cliente);

                        System.out.println("Cadastro de cliente realizado com sucesso!");
                        MenuCliente.exibirMenu(scan);
                    }
                    case 2: {
                        System.out.println("Cadastro de Admin:");
                        System.out.print("Nome: ");
                        String nome = scan.nextLine();
                        System.out.print("Email: ");
                        String email = scan.nextLine();
                        System.out.print("Senha: ");
                        String senha = scan.nextLine();
                        System.out.println("Cargo: ");
                        String cargo = scan.nextLine();

                        Admin gerente = new Admin(nome, email, senha, cargo);
                        Admin.listaAdminsCadastrados.add(gerente);

                        System.out.println("Cadastro de administrador realizado com sucesso!");
                        MenuAdmin.exibirMenu(scan);
                    }
                    case 0:
                        System.out.println("Saindo do sistema...");
                        System.exit(0);
                }
                scan.close();
            }
        }
    }
}