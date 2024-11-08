package SGB;

import java.time.LocalDate;
import java.util.Scanner;

public class AppMenu {
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        int opcMenu;

        Livros[] livro = new Livros[5];
        Clientes[] cliente = new Clientes[5];
        Emprestimos[] emprestimo = new Emprestimos[10];

        int contadorEmp = 0, contadorLivro = 0, contadorCliente = 0;
        
        do {
            System.out.println("\n ||||||||||Sistema de Gerenciamento de Biblioteca|||||||||| ");
            System.out.println("\n - Opções disponíveis: ");
            System.out.println(" |  1 - Cadastro");
            System.out.println(" |  2 - Excluir casdastro");
            System.out.println(" |  3 - Registrar Empréstimo e devolução");
            System.out.println(" |  4 - Listar Cadastros e Empréstimos Ativos");
            System.out.println(" |  5 - Exportar dados para arquivo de texto");
            System.out.println(" |  6 - Sair");

            System.out.print(" -> ");
            while (!scan.hasNextInt()) {
                System.out.println(" - Digite um número válido. ");
                scan.next();
            }
            opcMenu = scan.nextInt();
            scan.nextLine();

// ---------------------------------------------------------------------------------------------------------------------------------------------------------------
            switch (opcMenu) {
                case 1: //Cadastro em geral
                    for (int i = 0; i < 100; i++) {
                        System.out.println();
                    }
                    System.out.println("\n -=-=- Adicionar cadastros -=-=- ");
                    int opcCadastro;
                    do{
                        System.out.println("\n - Opções disponíveis: ");
                        System.out.println(" | 1 - Cadastrar Livro");
                        System.out.println(" | 2 - Cadastrar Usuário");
                        System.out.println(" | 3 - Voltar ao Menu Principal");

                        System.out.print(" -> ");
                        while (!scan.hasNextInt()) {
                            System.out.println(" - Digite um número válido. ");
                            scan.next();
                        }
                        opcCadastro = scan.nextInt();
                        scan.nextLine();
// ---------------------------------------------------------------------------------------------------------------------------------------------------------------
                        switch (opcCadastro) {
                            case 1: //Cadastro de Livros
                                System.out.println("\n -=-=- CADASTRO DE LIVROS -=-=- ");
                                if (contadorLivro < livro.length) { // Verifica se tem espaço para cadastrar um novo Livro
                                    String titulo;
                                    do{
                                        System.out.print("Título: ");
                                        titulo = scan.nextLine().trim();
                                        if (titulo.isEmpty()) {
                                            System.out.println(" - O título do livro não poed estar vazio.");
                                        }
                                    } while (titulo.isEmpty());
                                    
                                    int idLivro = -1;
                                    boolean idLivroUsado; //Verifica se o Id já não está sendo usado

                                    do {
                                        System.out.print("Id do livro: ");
                                        while (!scan.hasNextInt()) {
                                            System.out.println(" - Insira um número válido para o ID do livro.");
                                            scan.next();
                                        }
                                        idLivro = scan.nextInt();
                                        scan.nextLine();

                                        idLivroUsado = false;
                                        for (int i = 0; i < livro.length; i++) {
                                            if (livro[i] != null && livro[i].getId_livro() == idLivro) {
                                                idLivroUsado = true;
                                                break;
                                            }
                                        }
                                        if (idLivroUsado) {
                                            System.out.println(" - ID já está em uso. Favor inserir outro ID. ");
                                        }
                                    } while (idLivroUsado);

                                    System.out.print("Autor: ");
                                    String autor = scan.next();
                                    System.out.print("Editora: ");
                                    String editora = scan.next();

                                    int ano_publi;
                                    do { // Verifica se o ano não está no futuro
                                        System.out.print("Ano de publicação: ");
                                        while (!scan.hasNextInt()) {
                                            System.out.println(" - Digite um ano válido. ");
                                            scan.next();
                                        }
                                        ano_publi = scan.nextInt();
                                        scan.nextLine();

                                        if (ano_publi > LocalDate.now().getYear()) {
                                            System.out.println(" - Ano inválido. Digite um ano no presente ou passado. ");
                                        }
                                    } while (ano_publi > LocalDate.now().getYear());

                                    int num_exemplares;
                                    do {
                                        System.out.print("Número de exemplares: ");
                                        while (!scan.hasNextInt()) {
                                            System.out.println(" - Digite um número válido. ");
                                            scan.next();
                                        }
                                        num_exemplares = scan.nextInt();
                                        scan.nextLine();

                                        if (num_exemplares <= 0) {
                                            System.out.println(" - O número de exemplares deve ser maior que zero.");
                                        }
                                    } while (num_exemplares <= 0);

                                    System.out.println("\n - Deseja confirmar o cadastro do livro (S)im ou (N)ão: ");
                                    char opcConfirmar = scan.next().charAt(0);

                                    if (opcConfirmar == 'S' || opcConfirmar == 's') {
                                        for(int arreioLivro = 0; arreioLivro < livro.length; arreioLivro++) {
                                            if (livro[arreioLivro] == null) {
                                                livro[arreioLivro] = new Livros(idLivro, titulo, autor, editora, ano_publi, num_exemplares);
                                                contadorLivro++;
                                                System.out.println(" - Livro cadastrado com sucesso. ");
                                                break;
                                            }
                                        }
                                    } else { // Cancela o cadastro do Livro
                                    System.out.println("\n - Cadastro do livro cancelado. ");
                                }
                                } else {
                                    System.out.println(" - Limite de cadastros de livros atingido. ");
                                }
                                break;
// ---------------------------------------------------------------------------------------------------------------------------------------------------------------
                            case 2: //Cadastro de Usuários
                                System.out.println("\n -=-=- CADASTRO DE USUÁRIOS -=-=- \n");
                                if (contadorLivro < livro.length) {
                                    String nome;
                                    do{
                                        System.out.print("Nome: ");
                                        nome = scan.nextLine().trim();
                                        if (nome.isEmpty()) {
                                            System.out.println(" - O nome do cliente não pode estar vazio.");
                                        }
                                    } while (nome.isEmpty());

                                    int idCliente = -1;
                                    boolean idClienteUsado; // Verifica se o id já não está sendo usado

                                    do{
                                        System.out.print("Id do cliente: ");
                                        while (!scan.hasNextInt()) {
                                            System.out.println(" - ID já está em uso. Favor inserir outro ID. ");
                                            scan.next();
                                        }
                                        idCliente = scan.nextInt();
                                        scan.nextLine();

                                        idClienteUsado = false;
                                        for (int i = 0; i < cliente.length; i++) {
                                            idClienteUsado = true;
                                            break;
                                        }
                                        if (idClienteUsado) {
                                            System.out.println(" - ID ");
                                        }
                                    } while (idClienteUsado);


                                    System.out.print("Email: ");
                                    String email = scan.next();

                                    String cpf;
                                    do{
                                        System.out.print("CPF (apenas números): ");
                                        cpf = scan.next().trim().replaceAll("[^0-9]", "");

                                        if (cpf.length() != 11) {
                                            System.out.println(" - CPF deve conter 11 dígitos. ");
                                            continue;
                                        }

                                        cpf = cpf.substring(0,3) + "." + cpf.substring(3,6) + "." + cpf.substring(6,9) + "-" + cpf.substring(9);

                                    } while (cpf.isEmpty());

                                    String telefone;
                                    do{
                                        System.out.print("Telefone (apenas números): ");
                                        telefone = scan.next().trim().replaceAll("[^0-9]", "");

                                        if (telefone.length() != 11) {
                                            System.out.println(" - Número de telefone deve conter 11 dígitos. ");
                                            continue;
                                        }

                                        telefone = "(" + telefone.substring(0,2) + ")" + telefone.substring(2,7) + "-"
                                                + telefone.substring(7);

                                    } while (telefone.isEmpty());

                                    System.out.println("\n - Deseja confirmar o cadastro do cliente (S)im ou (N)ão: ");
                                    char opcConfirmar = scan.next().charAt(0);
                                    scan.nextLine();

                                    if (opcConfirmar == 'S' || opcConfirmar == 's') {
                                        for(int i = 0; i < cliente.length; i++) {
                                            if (cliente[i] == null) {
                                                cliente[i] = new Clientes(idCliente, nome, email, cpf, telefone);
                                                contadorCliente++;
                                                System.out.println(" - Cliente cadastrado com sucesso. ");
                                                break;
                                            }
                                        }

                                    } else { // Cancela o cadastro do Livro
                                        System.out.println("\n - Cadastro do cliente cancelado. ");
                                    }
                                } else {
                                    System.out.println(" - Limite de cadastros de clientes atingido. ");
                                }
                                break;
// ---------------------------------------------------------------------------------------------------------------------------------------------------------------
                            case 3:
                                System.out.println(" - Voltando para o menu principal. ");
                                break;

                            default:
                                System.out.println(" - Opção inválida. ");
                        }
                    } while (opcCadastro <= 3);

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
