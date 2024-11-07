package SGB;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

public class AppMenu {
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        int opcMenu;

        Livros[] livro = new Livros[5]; //Arreio de livros
        Clientes[] cliente = new Clientes[5];
        Emprestimos[] emprestimo = new Emprestimos[10];

        int contadorEmp = 0, contadorLivro = 0, contadorCliente = 0;
        
        do {
            System.out.println("||||||||||Sistema de Gerenciamento de Biblioteca||||||||||");
            System.out.println("\n - Opções disponíveis: ");
            System.out.println(" |  1 - Cadastrar Livro");
            System.out.println(" |  2 - Cadastrar Usuário");
            System.out.println(" |  3 - Excluir casdastro");
            System.out.println(" |  4 - Realizar Empréstimo");
            System.out.println(" |  5 - Devolver Livro");
            System.out.println(" |  6 - Listar Livros");
            System.out.println(" |  7 - Listar Usuários");
            System.out.println(" |  8 - Verificar Empréstimos Ativos");
            System.out.println(" |  9 - Exportar dados para arquivo de texto");
            System.out.println(" | 10 - Sair");

            System.out.print(" -> ");
            opcMenu = scan.nextInt();

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

                case 3: //Exclusão de cadastro
                    for (int i = 0; i < 10; i++) {
                        System.out.println();
                    }
                    System.out.println("\n -=-=- Exclusão de cadastros -=-=- ");
                    int opcExcluir;
                        do{
                            System.out.println("\n - Opções disponíveis: ");
                            System.out.println(" | 1 - Excluir Cadastro de Livro");
                            System.out.println(" | 2 - Excluir Cadastro de Usuário");
                            System.out.println(" | 3 - sair");

                            System.out.print(" -> ");
                            opcExcluir = scan.nextInt();

                            switch (opcExcluir) {
                                case 1:
                                    int livroId = -1;
                                    while (true) {
                                        System.out.println(" - Id do Livro: ");
                                        try {
                                            livroId = scan.nextInt();
                                            break;
                                        } catch (Exception e) {
                                            System.out.println(" - Favor, insira um número válido para ID do Livro.");
                                            scan.next();
                                        }
                                    }

                                    int livroIndex = -1;
                                    for (int arreioLivros = 0; arreioLivros < livro.length; arreioLivros++) {
                                        if (livro[arreioLivros].getId_livro() == livroId) {
                                            livroIndex = arreioLivros;
                                            break;
                                        }
                                    }

                                    if (livroIndex != -1) {
                                        System.out.println("\n" + livro[livroIndex]);
                                        if (livro[livroIndex].get_num_exemp_disp() < livro[livroIndex].get_num_exemplares()) {
                                            System.out.println(" - O livro não pode ser excluído pois está emprestado.");
                                        } else {
                                            System.out.println("\n - Deseja confirmar a exclusão (S)im ou (N)ão: ");
                                            char opcConfirmar = scan.next().charAt(0);
                                            if (opcConfirmar == 'S' || opcConfirmar == 's') {
                                                livro[livroIndex] = null; // Exclui o livro do sistema
                                                System.out.println(" - Livro excluído com sucesso.");
                                            }
                                        }
                                    } else {
                                        System.out.println(" - Livro não encontrado.");
                                    }
                                    break;

                                case 2:
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
                                    }

                                    int clienteIndex = -1;
                                    for (int i = 0; i < cliente.length; i++) {
                                        if (cliente[i].getIdCliente() == clienteId) {
                                            clienteIndex = i;
                                            break;
                                        }
                                    }

                                    if (clienteIndex != -1) {
                                        System.out.println("\n" + cliente[clienteIndex]);
                                        if (cliente[clienteIndex].getTemEmprestimo()) {
                                            System.out.println(" - O cliente não pode ser excluído pois possui empréstimo ativo.");
                                        } else {
                                            System.out.println("\n - Deseja confirmar a exclusão (S)im ou (N)ão: ");
                                            char opcConfirmar = scan.next().charAt(0);
                                            if (opcConfirmar == 'S' || opcConfirmar == 's') {
                                                cliente[clienteIndex] = null;
                                                System.out.println(" - Cliente excluído com sucesso.");
                                            }
                                        }
                                    } else {
                                        System.out.println(" - Cliente não encontrado.");
                                    }
                                    break;

                                case 3:
                                    System.out.println(" - Saindo da exclusão de cadastros.");
                                    break;

                                default:
                                    System.out.println(" - Opção inválida.");
                                    break;
                            }

                            System.out.println("\n - Deseja excluir algum oujro cadastro (S)im ou (N)ão: ");
                            System.out.print(" -> ");
                            opcExcluir = scan.next().charAt(0);

                        } while (opcExcluir <= 3);
                    }
                    break;
                case 4: //Empréstimo de livros
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
                             int livroIndex = -1, clienteIndex = -1, emprestimoIndex = -1; //Variáveis que serão usadas para registrar os arrays
                            
                            System.out.println(" - Id do Cliente: ");

                            while (true) {
                                try {
                                    clienteId = scan.nextInt();
                                    break;
                                } catch (Exception e) {
                                    System.out.println(" - Favor, insira um número válido para ID do Cliente.");
                                    scan.next();
                                }
                            }
                            System.out.println(" - Id do Livro: ");

                            while (true) {
                                try {
                                    livroId = scan.nextInt();
                                    break;
                                } catch (Exception e) {
                                    System.out.println(" - Favor, insira um número válido para ID do Livro.");
                                    scan.next();
                                }
                            }

                            System.out.println(" - Data de Devolução (AAAA-MM-DD): ");
                            String dataDevolucao = scan.next();

                            // Tenta encontrar o livro pelo Id
                            for(int arreioLivros = 0; arreioLivros < livro.length; arreioLivros++) {
                                if(livro[arreioLivros].getId_livro() == livroId ) {
                                    livroIndex = arreioLivros;
                                    break;
                                }
                            }
                            // Tenta encontrar o cliente pelo Id
                            for(int arreioCliente = 0; arreioCliente < cliente.length; arreioCliente++) {
                                if(cliente[arreioCliente].getIdCliente() == clienteId ) {
                                    clienteIndex = arreioCliente;
                                    break;
                                }
                            }

                            for(int arreioEmprestimo = 0; arreioEmprestimo < emprestimo.length; arreioEmprestimo++) {
                                if(emprestimo[emprestimoIndex] == null){
                                    emprestimoIndex = arreioEmprestimo;
                                    break;
                                }
                            }

                            if(livroIndex != -1 && clienteIndex != -1) { // Verificam se foram encontrados
                                System.out.println("\n " + livro[livroIndex]);
                                System.out.println("\n " + cliente[clienteIndex]);
                                System.out.println("\n ---------------------------------------------------- \n");
                                System.out.println("\n - Deseja confirmar o emprestimo (S)im ou (N)ão: ");
                                char opcConfirmar = scan.next().charAt(0);

                                if(opcConfirmar == 'S' || opcConfirmar == 's') { // Confirma o empréstimo
                                    Emprestimos empr = new Emprestimos();

                                    try {
                                        if (empr.registrarEmprestimo(livro[livroIndex], cliente[clienteIndex], LocalDate.parse(dataDevolucao))) {
                                            emprestimo[emprestimoIndex] = empr;
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
                        System.out.println(" - Limite de empréstimos atingido. ");
                    }
                    break;

                case 5: //Devolução de Livros
                    for (int i = 0; i < 10; i++) {
                        System.out.println();
                    }
                    System.out.println("\n -=-=- DEVOLUÇÃO DE LIVROS -=-=- ");
                    if(contadorEmp > 0) {
                        System.out.println("\n - Deseja registrar alguma devolução (S)im ou (N)ão: ");
                        System.out.print(" -> ");
                        char opcInicial = scan.next().charAt(0);

                        while (opcInicial == 'S' || opcInicial == 's') {

                            System.out.println(" - Id do Cliente: ");
                            int clienteId = -1;
                            int clienteIndex = -1, emprestimoIndex = -1;

                            while (true) {
                                try {
                                    clienteId = scan.nextInt();
                                    break;
                                } catch (Exception e) {
                                    System.out.println(" - Favor, insira um número válido para ID do Cliente.");
                                    scan.next();
                                }
                            }

                            for (int arreioCliente = 0; arreioCliente < cliente.length; arreioCliente++) {
                                if (cliente[arreioCliente] != null && cliente[arreioCliente].getIdCliente() == clienteId) {
                                    clienteIndex = arreioCliente;
                                    break;
                                }
                            }

                            if (clienteIndex != -1 && cliente[clienteIndex].getTemEmprestimo()) {
                                System.out.println("\n " + cliente[clienteIndex]);

                                for (int arreioEmprestimo = 0; arreioEmprestimo < emprestimo.length; arreioEmprestimo++) {
                                    if (emprestimo[arreioEmprestimo] != null && emprestimo[arreioEmprestimo].getIdCliente() == cliente[clienteIndex].getIdCliente()) {
                                        emprestimoIndex = arreioEmprestimo;
                                        break;
                                    }
                                }

                                if (emprestimoIndex != -1) {
                                    System.out.println("\n - Empréstimo encontrado:");
                                    System.out.println(emprestimo[emprestimoIndex].getLivro());
                                    System.out.println("\n ------------------------------------------------ \n");
                                    System.out.println("\n - Deseja confirmar a devolução (S)im ou (N)ão: ");
                                    char opcConfirmar = scan.next().charAt(0);

                                    if (opcConfirmar == 'S' || opcConfirmar == 's') {
                                        Emprestimos empr = emprestimo[emprestimoIndex];

                                        try {
                                            if (empr.registrarDevolucao(cliente[clienteIndex])) {
                                                emprestimo[emprestimoIndex] = null;
                                                contadorEmp--;
                                                System.out.println(" - Devolução registrada com sucesso. ");
                                            } else {
                                                System.out.println(" - Falha ao registrar a devolução. ");
                                            }
                                        } catch (Exception e) {
                                            System.out.println(" - Ocorreu um erro ao registrar a devolução.");
                                        }

                                    } else {
                                        System.out.println("\n - Devolução cancelada. ");
                                    }
                                } else {
                                    System.out.println("\n - Não há empréstimos ativos para esse cliente.");
                                }
                            } else {
                                System.out.println("\n - Nenhum cadastro de cliente encontrado com ess ID");
                            }

                            System.out.println("\n - Deseja registrar uma nova devolução (S)im ou (N)ão: ");
                            System.out.print(" -> ");
                            opcInicial = scan.next().charAt(0);
                        }
                    } else {
                        System.out.println(" - Nenhum empréstimo realizado. ");
                    }
                    break;

                case 6: //Lista de Livros
                    System.out.println(" -=-=- LISTAGEM DE LIVROS CADASTRADO -=-=- ");
                    for (int i = 0; i < livro.length; i++) {
                        if (livro[i] != null) {
                            System.out.println(" ---------------------------------- \n");
                            System.out.println(livro[i].toString());
                        }
                    }
                    System.out.println(" ---------------------------------- \n");
                    break;

                case 7: //Lista de Clientes
                    System.out.println(" -=-=- LISTAGEM DE USUÁRIOS DO SISTEMA -=-=- ");
                    for (int i = 0; i < cliente.length; i++) {
                        if (cliente[i] != null) {
                            System.out.println(" ---------------------------------- \n");
                            System.out.println(cliente[i].toString());
                        }
                    }
                    System.out.println(" ---------------------------------- \n");
                    break;

                case 8: //Lista clientes com emprestimos ativos
                    System.out.println(" -=-=- CLIENTES COM EMPRÉSTIMOS ATIVOS -=-=- ");
                    for (int i = 0; i < cliente.length; i++) {
                        if (cliente[i].getTemEmprestimo() == true) {
                            System.out.println(" ---------------------------------- \n");
                            System.out.println(cliente[i].toString());
                        }
                    }
                    System.out.println(" ---------------------------------- \n");
                    break;

                case 9: //Salvar cadastros em arquivos de textos

                case 10: //Sair
                    System.out.println("Saindo do sistema. Obrigado por utilizar o Sistema de Gerenciamento de Biblioteca!");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcMenu >= 9);
    }    
}
