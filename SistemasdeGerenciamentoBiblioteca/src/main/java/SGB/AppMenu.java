package SGB;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

public class AppMenu {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int opcMenu;
        do {
            System.out.println("||||||||||Sistema de Gerenciamento de Biblioteca||||||||||");
            System.out.println("Opções disponíveis: ");
            System.out.println(" | 1 - Cadastrar Livro");
            System.out.println(" | 2 - Cadastrar Usuário");
            System.out.println(" | 3 - Realizar Empréstimo");
            System.out.println(" | 4 - Devolver Livro");
            System.out.println(" | 5 - Listar Livros");
            System.out.println(" | 6 - Listar Usuários");
            System.out.println(" | 7 - Verificar Empréstimos Ativos");
            System.out.println(" | 8 - ");
            System.out.println(" | 9 - Sair");

            System.out.print(" -> ");
            opcMenu = scan.nextInt();

            Livros[] livro = new Livros[5]; //Arreio de livros
            Clientes[] cliente = new Clientes[5];
            Emprestimos[] emprestimo = new Emprestimos[10];
            int contadorEmp = 0, contadorLivro = 0, contadorCliente = 0;

            switch (opcMenu) {
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
                    for (int i = 0; i < 10; i++) {
                        System.out.println();
                    }
                    System.out.println("\n -=-=- EMPRÉSTIMO DE LIVRO -=-=- ");
                    if(contadorEmp < emprestimo.length) {
                        System.out.println("\n - Deseja registrar algum emprestimo (S)im ou (N)ão: ");
                        System.out.println();
                        char opcInicial = scan.next().charAt(0);

                        if (opcInicial == 'S' || opcInicial == 's') {
                            int clienteId = -1, livroId = -1;

                            while (true) {
                                System.out.println(" - Id do Cliente: ");
                                try {
                                    clienteId = scan.nextInt();
                                    break;
                                } catch (Exception e) {
                                    System.out.println(" - Favor, insira um número válido para ID do Cliente.");
                                    scan.next();
                                }
                            }

                            while (true) {
                                System.out.println(" - Id do Livro: ");
                                try {
                                    livroId = scan.nextInt();
                                    break;
                                } catch (Exception e) {
                                    System.out.println(" - Favor, insira um número válido para ID do Cliente.");
                                    scan.next();
                                }
                            }

                            System.out.println(" - Data de Devolução (AAAA-MM-DD): ");
                            String dataDevolucao = scan.next();

                            int livroIndex = -1, clienteIndex = -1; //Variáveis que serão usadas para registrar os arrays

                            // Tenta encontrar o livro
                            for(int arreioLivros = 0; arreioLivros < livro.length; arreioLivros++) {
                                if(livro[arreioLivros].getId_livro() == livroId ) {
                                    livroIndex = arreioLivros;
                                    break;
                                }
                            }
                            // Tenta encontrar o cliente
                            for(int arreioCliente = 0; arreioCliente < cliente.length; arreioCliente++) {
                                if(cliente[arreioCliente].getIdCliente() == clienteId ) {
                                    clienteIndex = arreioCliente;
                                    break;
                                }
                            }

                            if(livroIndex != -1 && clienteIndex != -1) { // Verificam se foram encontrados
                                System.out.println("\n - Deseja confirmar o emprestimo (S)im ou (N)ão: ");
                                System.out.println("\n | Livro: " + livro[livroIndex].get_Titulo());
                                System.out.println(" | Cliente: " + cliente[clienteIndex].getNome());
                                char opcConfirmar = scan.next().charAt(0);

                                if(opcConfirmar == 'S' || opcConfirmar == 's') { // Confirma o empréstimo
                                    Emprestimos empr = new Emprestimos();

                                    try {
                                        if (empr.registrarEmprestimo(livro[livroIndex], cliente[clienteIndex], LocalDate.parse(dataDevolucao))) {
                                            emprestimo[contadorEmp] = empr;
                                            contadorEmp++;
                                            System.out.println(" - Empréstimo registrado com sucesso. ");
                                        } else {
                                            System.out.println(" - Falha ao registrar o empréstimo. ");
                                        }
                                    } catch (DateTimeException e){
                                        System.out.println(" - Data de devolução inválida. Formato esperado: AAAA-MM-DD.");
                                    } catch (Exception e) {
                                        System.out.println(" - Ocorreu um erro ao registrar o empréstimo.");
                                    }

                                } else { // Cancela o empréstimo
                                    System.out.println("\n - Empréstimo cancelado. ");
                                }
                            } else {
                                if(livroIndex == -1){
                                    System.out.println("\n - Nenhum cadastro de livro encontrado com esse ID. ");

                                }
                                if(clienteIndex == -1){
                                    System.out.println("\n - Nenhum cadastro de cliente encontrado com ess ID");

                                }
                            }
                        }
                    } else {
                        System.out.println(" - Limite de emprestimos atingido. ");
                    }
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
        } while (opcMenu != 9);
    }    
}
