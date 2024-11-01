package SGB;

import java.util.Scanner;

public class AppMenu {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        System.out.println("||||||||||Sistema de Gerenciamento de Biblioteca||||||||||");
        System.out.println("Opções disponíveis: ");
        System.out.println("1 - Cadastrar Livro");
        System.out.println("2 - Cadastrar Usuário");
        System.out.println("3 - Realizar Empréstimo");
        System.out.println("4 - Devolver Livro");
        System.out.println("5 - Listar Livros");
        System.out.println("6 - Listar Usuários");
        System.out.println("7 - Verificar Empréstimos Ativos");
        System.out.println("8 - Sair");
        
        System.out.println("Qual opção você deseja? ");
        int opcMenu = scan.nextInt();
        
        switch (opcMenu){
            case 1:
                System.out.println("CADASTRO DE LIVROS");
                System.out.print("Título: ");
                livros.titulo = scan.next();
                System.out.print("Autor: ");
                livros.autor = scan.next();
                System.out.print("Ano de publicação: ");
                livros.ano_publi = scan.next();
                System.out.print("Número de exemplares disponíveis: ");
                livros.num_exemplares = scan.next();
                break;
            case 2:
                System.out.println("CADASTRO DE USUÁRIOS");
                System.out.print("Nome: ");
                clientes.nome = scan.next();
                System.out.print("E-mail: ");
                clientes.email = scan.next();
                System.out.print("Número de identificação: ");
                clientes.id = scan.next();
                break;
            case 3:
                //....
                break;
            case 4:
                //....
                break;
            case 5:
                //....
                break;
            case 6:
                //....
                break;
            case 7:
                //....
                break;
            case 8:
                //....
                break;
            default:
                //....
                break;
        }
        
        
    }    
}
