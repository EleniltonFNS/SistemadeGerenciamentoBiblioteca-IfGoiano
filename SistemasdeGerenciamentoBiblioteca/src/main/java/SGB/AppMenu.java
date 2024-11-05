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
            System.out.println(" | 8 - Exportar dados para arquivo de texto");
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

                    // Adiciona o novo livro ao array de livros
                    livro[contadorLivro] = new Livros(id, titulo, autor, editora, ano_publi, num_exemplares);
                    contadorLivro++;
                    break;

                case 2: //Cadastro de Usuários
                    System.out.println("CADASTRO DE USUÁRIOS");
                    System.out.print("Nome: ");
                    String nome = scan.next();
                    System.out.print("Id do cliente: ");
                    int idCliente = scan.nextByte();
                    System.out.print("Endereço: ");
                    String endereco = scan.next();
                    System.out.print("Telefone: ");
                    String telefone = scan.next();
                    System.out.print("Email: ");
                    String email = scan.next();
                    break;

                case 3: //Realizar Empréstimos
                    for (int i = 0; i < 10; i++) {
                        System.out.println();
                    }
                    System.out.println("\n -=-=- EMPRÉSTIMO DE LIVRO -=-=- ");
                    if(contadorEmp < emprestimo.length) {
                        System.out.println("\n - Deseja registrar algum emprestimo (S)im ou (N)ão: ");
                        System.out.print(" -> ");
                        char opcInicial = scan.next().charAt(0);

                        while (opcInicial == 'S' || opcInicial == 's') {
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
                                System.out.println("\n" + livro[livroIndex]);
                                System.out.println("\n" + cliente[clienteIndex]);
                                System.out.println("\n - Deseja confirmar o emprestimo (S)im ou (N)ão: ");
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
                            System.out.println("\n - Deseja registrar um novo emprestimo (S)im ou (N)ão: ");
                            System.out.print(" -> ");
                            opcInicial = scan.next().charAt(0);
                        }
                    } else {
                        System.out.println(" - Limite de emprestimos atingido. ");
                    }
                    break;

                case 4: //Devolução de Livros
                    for (int i = 0; i < 10; i++) {
                        System.out.println();
                    }
                    System.out.println("\n -=-=- DEVOLUÇÃO DE LIVROS -=-=- ");
                    System.out.println("\n - Deseja registrar alguma devolução (S)im ou (N)ão: ");
                    System.out.print(" -> ");
                    char opcInicial = scan.next().charAt(0);

                    while (opcInicial == 'S' || opcInicial == 's') {
                        int clienteId = -1;

                        while (true) {
                            System.out.println(" - Id do Cliente: ");
                            try {
                                clienteId = scan.nextInt();
                                break;
                            } catch (Exception e) {
                                System.out.println(" - Favor, insira um número válido para ID do Cliente.");
                                scan.next();
                            }

                            int clienteIndex = -1, emprestimoIndex = -1;

                            for(int arreioCliente = 0; arreioCliente < cliente.length; arreioCliente++) {
                                if(cliente[arreioCliente].getIdCliente() == clienteId ) {
                                    clienteIndex = arreioCliente;
                                    break;
                                }
                            }

                            if (cliente[clienteIndex].getIdCliente() == clienteId) {
                                System.out.println("\n" + cliente[clienteIndex]);

                                for(int arreioEmprestimo = 0; arreioEmprestimo < livro.length; arreioEmprestimo++) {
                                    if(emprestimo[emprestimoIndex].getIdCliente() == cliente[clienteIndex].getIdCliente()) {}
                                    emprestimoIndex = arreioEmprestimo;
                                }
                            }

                            System.out.println("\n - Deseja registrar uma nova devolução (S)im ou (N)ão: ");
                            System.out.print(" -> ");
                            opcInicial = scan.next().charAt(0);
                        }
                    for (int i = 0; i < emprestimo.length; i++) {
                        if (emprestimo[i] != null && emprestimo[i].getIdEmprestimo() == idEmprestimo) {
                            emprestimo[i].devolverLivro();
                            System.out.println("Livro devolvido com sucesso!");
                            break;
                        }
                    }
                    break;

                case 5: //Lista de Livros
                    System.out.println("LISTAGEM DE LIVROS DO SISTEMA");
                    for (int i = 0; i < livro.length; i++) {
                        if (livro[i] != null) {
                            System.out.println(livro[i].toString());
                        }
                    }
                    break;

                case 6: //Lista de Usuários
                    System.out.println("LISTAGEM DE USUÁRIOS DO SISTEMA");
                    for (int i = 0; i < cliente.length; i++) {
                        if (cliente[i] != null) {
                            System.out.println(cliente[i].toString());
                        }
                    }
                    break;

                case 7: //Livros Disponíveis
                    System.out.println("LIVROS DISPONÍVEIS PARA EMPRÉSTIMO");
                    for (int i = 0; i < livro.length; i++) {
                        if (livro[i].statusEmprestimo == true) {
                            System.out.println(livro[i].toString());
                        }
                    }
                    break;

                case 8: //Salvar cadastros em arquivos de textos

                case 9: //Sair
                    System.out.println("Saindo do sistema. Obrigado por utilizar o Sistema de Gerenciamento de Biblioteca!")
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcMenu != 9);
    }    
}
