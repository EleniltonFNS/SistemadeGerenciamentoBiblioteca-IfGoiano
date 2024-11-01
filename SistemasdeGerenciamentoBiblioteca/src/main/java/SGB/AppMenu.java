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
                //....
                break;
            case 2:
                //....
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
