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
        
        Livros[] livro = new Livros[5]; //Arreio de livros
        
        switch (opcMenu){
            case 1: //Cadastro de Livros
                System.out.println("CADASTRO DE LIVROS");
                System.out.print("Título: ");
                String titulo = scan.next();
                System.out.print("Id do livro: ");
                int id = scan.nextByte();
                System.out.print("Autor: ");
                String autor = scan.next();
                System.out.print("Editora: ");
                String editora = scan.next();
                System.out.print("Ano de publicação: ");
                int ano_publi = scan.nextInt();
                System.out.print("Número de exemplares disponíveis: ");
                int num_exemplares = scan.nextInt();
                
                livro[i] = new Livros(id, titulo, autor, editora, ano_publi, num_exemplares);
                break;

            case 2: //Cadastro de Usuários
                System.out.println("CADASTRO DE USUÁRIOS");
                System.out.print("Nome: ");
                clientes.nome = scan.next();
                System.out.print("E-mail: ");
                clientes.email = scan.next();
                System.out.print("Número de identificação: ");
                clientes.id = scan.nextInt();
                break;

            case 3: //Realizar Empréstimos
                Conta cl = new Conta();
                System.out.println("EMPRÉSTIMO DE LIVRO");
                System.out.println("Nome de usuário: ");
                Emprestimos.usu_emp = scan.next();
                System.out.print("Data do empréstimo: ");
                Emprestimos.dataEmprestimo = scan.next();
                emprestimos.livro.statusEmprestimo = true;
                livro.num_exemplares[i] -= 1;
                break;

            case 4: //Devolução de Livros
                System.out.println("DEVOLUÇÃO DE LIVRO");
                System.out.println("Nome de usuário: ");
                Emprestimos.usu_emp = scan.next();
                System.out.print("Data da devolução: ");
                Emprestimos.dataPrevistaDevolucao = scan.next();
                emprestimos.livro.statusEmprestimo = true;
                livro.num_exemplares[i] += 1;
                break;

            case 5: //Lista de Livros
                //....
                break;

            case 6: //Lista de Usuários
                //....
                break;

            case 7: //Livros Disponíveis
                //....
                break;

            case 8: //Sair
                //....
                break;

            default:
                //....
                break;
        }
    }    
}
