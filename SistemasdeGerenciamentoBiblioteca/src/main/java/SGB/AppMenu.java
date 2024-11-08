package SGB;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

// Classe principal do sistema de gerenciamento de biblioteca.

public class AppMenu {
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in); // Objeto Scanner para leituras das entradas
        int opcMenu;  // Opção selecionada do menu principal

        // Arrays para armazenar dados do livros, clientes e empréstimos
        Livros[] livro = new Livros[5];
        Clientes[] cliente = new Clientes[5];
        Emprestimos[] emprestimo = new Emprestimos[10];

        int contadorEmp = 0, contadorLivro = 0, contadorCliente = 0; // Contadores para ajudar a limitar a quantidade de cadastro
        
        do { // Exibição do menu principal e opções disponíveis
            System.out.println("\n ||||||||||Sistema de Gerenciamento de Biblioteca|||||||||| ");
            System.out.println("\n - Opções disponíveis: ");
            System.out.println(" |  1 - Cadastro");
            System.out.println(" |  2 - Excluir cadastro");
            System.out.println(" |  3 - Registrar Empréstimo e devolução");
            System.out.println(" |  4 - Listar Cadastros e Empréstimos Ativos");
            System.out.println(" |  5 - Exportar dados para arquivo de texto");
            System.out.println(" |  6 - Sair");

            System.out.print(" -> ");
            while (!scan.hasNextInt()) { // Tratamento e validação da entrada do menu principal
                System.out.println(" - Digite um número válido. ");
                System.out.print(" -> ");
                scan.next();
            }
            opcMenu = scan.nextInt();
            scan.nextLine();

// ---------------------------------------------------------------------------------------------------------------------------------------------------------------
            switch (opcMenu) {
                case 1: //Cadastro em geral
                    System.out.println("\n -=-=- Adicionar cadastros -=-=- ");
                    int opcCadastro;
                    do{ // Menu do cadastro
                        System.out.println("\n - Opções disponíveis: ");
                        System.out.println(" | 1 - Cadastrar Livro");
                        System.out.println(" | 2 - Cadastrar Usuário");
                        System.out.println(" | 3 - Voltar ao Menu Principal");

                        System.out.print(" -> ");
                        while (!scan.hasNextInt()) { // Tratamento e validação da entrada do menu de cadastro
                            System.out.println(" - Digite um número válido. ");
                            System.out.print(" -> ");
                            scan.next();
                        }
                        opcCadastro = scan.nextInt();
                        scan.nextLine();
// ---------------------------------------------------------------------------------------------------------------------------------------------------------------
                        switch (opcCadastro) {
                            case 1: //Cadastro de Livros
                                System.out.println("\n -=-=- CADASTRO DE LIVROS -=-=- ");
                                if (contadorLivro < livro.length) { // Verifica se tem espaço para cadastrar um novo Livro

                                // Solicita e valida os dados do livro a ser cadastrado
                                    String titulo;
                                    do{
                                        System.out.print("Título: ");
                                        titulo = scan.nextLine().trim();
                                        if (titulo.isEmpty()) {
                                            System.out.println(" - O título do livro não poed estar vazio.");
                                        }
                                    } while (titulo.isEmpty()); // Repete para garantir a validação do titulo
                                    
                                    int idLivro = -1;
                                    boolean idLivroUsado; // Variável para verifica se o Id já não está sendo usado

                                    do {
                                        System.out.print("Id do livro: ");
                                        while (!scan.hasNextInt()) { // Repete para garantir a validação do titulo
                                            System.out.println(" - Insira um número válido para o ID do livro.");
                                            scan.next();
                                        }
                                        idLivro = scan.nextInt();
                                        scan.nextLine();

                                        idLivroUsado = false;
                                        for (int i = 0; i < livro.length; i++) { // Verifica se o Id está disponível
                                            if (livro[i] != null && livro[i].getId_livro() == idLivro) {
                                                idLivroUsado = true; // Confirma que o ID já está em uso
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
                                    do { // Verifica se o número não é < ou = a 0
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

                                    // Confirmação do cadastro?
                                    System.out.println("\n - Deseja confirmar o cadastro do livro? Precione (S) para sim: ");
                                    System.out.print(" -> ");
                                    char opcConfirmar = scan.next().charAt(0);

                                    if (opcConfirmar == 'S' || opcConfirmar == 's') {
                                        for(int arreioLivro = 0; arreioLivro < livro.length; arreioLivro++) {
                                            if (livro[arreioLivro] == null) { // Realiza o cadastro na variável do arreio que estiver vazio
                                                livro[arreioLivro] = new Livros(idLivro, titulo, autor, editora, ano_publi, num_exemplares);
                                                contadorLivro++;
                                                System.out.println(" - Livro cadastrado com sucesso. ");
                                                break;
                                            }
                                        }
                                    } else { // Cancela o cadastro do Livro
                                    System.out.println("\n - Cadastro do livro cancelado. ");
                                }
                                } else { // Caso não tenha mais variáveis vazias, então não cabe um novo cadastro
                                    System.out.println(" - Limite de cadastros de livros atingido. ");
                                }
                                break;
// ---------------------------------------------------------------------------------------------------------------------------------------------------------------
                            case 2: //Cadastro de Usuários
                                System.out.println("\n -=-=- CADASTRO DE USUÁRIOS -=-=- \n");
                                // Lógica de cadastro de usuários
                                if (contadorCliente < cliente.length) { // Verifica se tem espaço para cadastrar um novo Livro
                                    
                                    // Solicita e valida os dados do cliente a ser cadastrado
                                    String nome;
                                    do{
                                        System.out.print("Nome: ");
                                        nome = scan.nextLine().trim();
                                        if (nome.isEmpty()) {
                                            System.out.println(" - O nome do cliente não pode estar vazio.");
                                        }
                                    } while (nome.isEmpty());  // Repete para garantir a validação do nome

                                    int idCliente = -1;
                                    boolean idClienteUsado; // Variável para verifica se o Id já não está sendo usado

                                    do{
                                        System.out.print("Id do cliente: ");
                                        while (!scan.hasNextInt()) {
                                            System.out.println(" - Insira um número válido para o ID do cliente. ");
                                            scan.next();
                                        }
                                        idCliente = scan.nextInt();
                                        scan.nextLine();

                                        idClienteUsado = false;
                                        for (int i = 0; i < cliente.length; i++) { // Verifica se o Id está disponível
                                            if (cliente[i] != null && cliente[i].getIdCliente() == idCliente){
                                                idClienteUsado = true; // Confirma que o ID já está em uso
                                                break;
                                            }
                                        }
                                        if (idClienteUsado) {
                                            System.out.println(" - ID já está em uso. Favor inserir outro ID.  ");
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
                                        }else {
                                            // Formata o CPF
                                            cpf = cpf.substring(0,3) + "." + cpf.substring(3,6) + "." + cpf.substring(6,9) + "-" + cpf.substring(9);
                                            break;
                                        }

                                    } while (true); // Repete para garantir a validação do CPF

                                    String telefone;
                                    do{
                                        System.out.print("Telefone (apenas números): ");
                                        telefone = scan.next().trim().replaceAll("[^0-9]", "");

                                        if (telefone.length() != 11) {
                                            System.out.println(" - Número de telefone deve conter 11 dígitos. ");
                                        } else {
                                            // Formata o número de telefone
                                            telefone = "(" + telefone.substring(0,2) + ")" + telefone.substring(2,7) + "-"
                                                    + telefone.substring(7);
                                            break;
                                        }

                                    } while (true); // Repete para garantir a validação do número de telefone

                                    // Confirmação do cadastro?
                                    System.out.println("\n - Deseja confirmar o cadastro do cliente? Precione (S) para sim: ");
                                    System.out.print(" -> ");
                                    char opcConfirmar = scan.next().charAt(0);
                                    scan.nextLine();

                                    if (opcConfirmar == 'S' || opcConfirmar == 's') {
                                        for(int i = 0; i < cliente.length; i++) {
                                            if (cliente[i] == null) { // Realiza o cadastro na variável do arreio que estiver vazio
                                                cliente[i] = new Clientes(idCliente, nome, email, cpf, telefone);
                                                contadorCliente++;
                                                System.out.println(" - Cliente cadastrado com sucesso. ");
                                                break;
                                            }
                                        }

                                    } else { // Cancela o cadastro do Livro
                                        System.out.println("\n - Cadastro do cliente cancelado. ");
                                    }
                                } else { // Caso não tenha mais variáveis vazias, então não cabe um novo cadastro
                                    System.out.println(" - Limite de cadastros de clientes atingido. ");
                                }
                                break;
// ---------------------------------------------------------------------------------------------------------------------------------------------------------------
                            case 3: // Sair do menu principal
                                System.out.println(" - Voltando para o menu principal. ");
                                break;

                            default: // Caso seja informado algum número além dos informados no menu
                                System.out.println(" - Opção inválida. ");
                        }
                    } while (opcCadastro != 3);

                    break;
                    
// ---------------------------------------------------------------------------------------------------------------------------------------------------------------
                case 2: //Exclusão de cadastro
                    System.out.println("\n -=-=- Exclusão de cadastros -=-=- ");
                    int opcExcluir;
                    do{ // Menu do cadastro
                        System.out.println("\n - Opções disponíveis: ");
                        System.out.println(" | 1 - Excluir Cadastro de Livro");
                        System.out.println(" | 2 - Excluir Cadastro de Usuário");
                        System.out.println(" | 3 - Voltar ao Menu Principal");

                        System.out.print(" -> ");
                        while (!scan.hasNextInt()) { // Tratamento e validação da entrada do menu de exclusão
                            System.out.println(" - Digite um número válido. ");
                            System.out.print(" -> ");
                            scan.next();
                        }
                        opcExcluir = scan.nextInt();
                        scan.nextLine();
// ---------------------------------------------------------------------------------------------------------------------------------------------------------------
                        switch (opcExcluir) {
                            case 1: // Excluir livro
                                System.out.println("\n -=-=- Excluir Cadastro de Livro -=-=- ");

                                int contLivros = 0;
                                for (Livros l : livro) if (l != null) contLivros++;

                                if (contLivros <= 0) {
                                    System.out.println("\n - Nenhum livro cadastrado. ");
                                    break;
                                } else {
                                    System.out.println(" - Id do Livro: ");
                                    int livroId;
                                    while (!scan.hasNextInt()) {
                                        System.out.println(" - Digite um número válido. ");
                                        scan.next();
                                    }
                                    livroId = scan.nextInt();
                                    scan.nextLine();

                                    int livroIndex = -1;
                                    boolean livroExiste = false;
                                    for (int i = 0; i < livro.length; i++) {
                                        if (livro[i] != null && livro[i].getId_livro() == livroId) {
                                            livroIndex = i;
                                            livroExiste = true;
                                            break;
                                        }
                                    }

                                    if (livroExiste) {
                                        System.out.println("\n - Dados do Livro: ");
                                        System.out.println(livro[livroIndex]);
                                        if (livro[livroIndex].get_num_exemp_disp() < livro[livroIndex].get_num_exemplares()) {
                                            System.out.println("\n - O livro não pode ser excluído pois há exemplares emprestado.");
                                        } else {
                                            System.out.println("\n - Deseja confirmar a exclusão? Precione (S) para sim: ");
                                            System.out.print(" -> ");
                                            char opcConfirmar = scan.next().charAt(0);

                                            if (opcConfirmar == 'S' || opcConfirmar == 's') {
                                                livro[livroIndex] = null; // Exclui o livro do sistema
                                                contadorLivro --;
                                                System.out.println(" - Livro excluído com sucesso.");

                                            } else {
                                                System.out.println(" - Exclusão cancelada. ");

                                            }
                                        }
                                    } else {
                                        System.out.println(" - Livro não encontrado.");
                                    }
                                }
                                break;
// ---------------------------------------------------------------------------------------------------------------------------------------------------------------
                            case 2: // Excluir cliente
                                System.out.println("\n -=-=- Excluir Cadastro de Livro -=-=- \n");

                                int contClientes = 0;
                                for (Clientes c : cliente) if (c != null) contClientes++;

                                if (contClientes <= 0) {
                                    System.out.println("\n - Nenhum cliente cadastrado. ");
                                    break;

                                } else {
                                    System.out.println(" - Id do Cliente: ");
                                    int clienteId;
                                    while (!scan.hasNextInt()) {
                                        System.out.println(" - Digite um número válido. ");
                                        System.out.print(" -> ");
                                        scan.next();
                                    }
                                    clienteId = scan.nextInt();
                                    scan.nextLine();

                                    int clienteIndex = -1;
                                    boolean clienteExiste = false;

                                    for (int i = 0; i < cliente.length; i++) {
                                        if (cliente[i].getIdCliente() == clienteId) {
                                            clienteIndex = i;
                                            clienteExiste = true;
                                            break;
                                        }
                                    }

                                    if (clienteExiste) {
                                        System.out.println("\n - Dados do Cliente: ");
                                        System.out.println(cliente[clienteIndex]);
                                        if (cliente[clienteIndex].getTemEmprestimo()) {
                                            System.out.println("\n - O cliente não pode ser excluído pois possui empréstimo ativo.");

                                        } else {
                                            System.out.println("\n - Deseja confirmar a exclusão? Precione (S) para sim: ");
                                            System.out.print(" -> ");
                                            char opcConfirmar = scan.next().charAt(0);

                                            if (opcConfirmar == 'S' || opcConfirmar == 's') {
                                                cliente[clienteIndex] = null;
                                                contadorCliente --;
                                                System.out.println(" - Cliente excluído com sucesso.");

                                            } else {
                                                System.out.println(" - Exclusão Cancelada. ");
                                            }
                                        }
                                    } else {
                                        System.out.println(" - Cliente não encontrado.");
                                    }
                                }
                                break;
// ---------------------------------------------------------------------------------------------------------------------------------------------------------------
                            case 3: // Sair do menu exclusão
                                System.out.println(" - Voltando para o menu principal.");
                                break;

                            default: // Caso seja informado algum número além dos informados no menu
                                System.out.println(" - Opção inválida.");
                        }

                    } while (opcExcluir != 3);
                    break;

// ---------------------------------------------------------------------------------------------------------------------------------------------------------------
                case 3: // Registro de empréstimo e devolução
                    System.out.println("\n -=-=- Registro de Empréstimo e Devolução -=-=- ");
                    int opcEmp = 0;
                    do{ // Menu do cadastro
                        System.out.println("\n - Opções disponíveis: ");
                        System.out.println(" | 1 - Registrar Empréstimo");
                        System.out.println(" | 2 - Registrar Devolução");
                        System.out.println(" | 3 - Voltar ao Menu Principal");

                        System.out.print(" -> ");
                        while (!scan.hasNextInt()) { // Tratamento e validação da entrada do menu de empréstimo
                            System.out.println(" - Digite um número válido. ");
                            System.out.print(" -> ");
                            scan.next();
                        }
                        opcEmp = scan.nextInt();
                        scan.nextLine();
// ---------------------------------------------------------------------------------------------------------------------------------------------------------------
                        switch (opcEmp) {
                            case 1: // Empréstimo de livros
                                System.out.println("\n -=-=- Empréstimo de Livro -=-=- ");

                                int contLivros = 0, contClientes = 0;
                                for (Livros l : livro) if (l != null) contLivros++;
                                for (Clientes c : cliente) if (c != null) contClientes++;

                                if(contLivros == 0) {
                                    System.out.println("\n - Nenhum livro cadastrado. ");
                                    break;
                                }

                                if(contClientes == 0) {
                                    System.out.println("\n - Nenhum cliente cadastrado. ");
                                    break;
                                }

                                if (contadorEmp < emprestimo.length && contLivros > 0 && contClientes > 0) {
                                    int clienteId = -1, livroId = -1;
                                    int livroIndex = -1, clienteIndex = -1, emprestimoIndex = -1;

                                    System.out.println("\n - Id do Cliente: ");

                                    while (!scan.hasNextInt()) {
                                        System.out.println(" - Digite um número válido. ");
                                        scan.next();
                                    }
                                    clienteId = scan.nextInt();
                                    scan.nextLine();

                                    System.out.println("\n - Id do Livro: ");

                                    while (!scan.hasNextInt()) {
                                        System.out.println(" - Digite um número válido. ");
                                        scan.next();
                                    }
                                    livroId = scan.nextInt();
                                    scan.nextLine();

                                    // Tenta encontrar o livro pelo Id
                                    for(int arreioLivros = 0; arreioLivros < livro.length; arreioLivros++) {
                                        if(livro[arreioLivros] != null && livro[arreioLivros].getId_livro() == livroId) {
                                            livroIndex = arreioLivros;
                                            break;
                                        }
                                    }

                                    // Tenta encontrar o cliente pelo Id
                                    for(int arreioCliente = 0; arreioCliente < cliente.length; arreioCliente++) {
                                        if(cliente[arreioCliente] != null && cliente[arreioCliente].getIdCliente() == clienteId) {
                                            clienteIndex = arreioCliente;
                                            break;
                                        }
                                    }

                                    // Encontra primeira posição vazia no array de empréstimos
                                    for(int arreioEmprestimo = 0; arreioEmprestimo < emprestimo.length; arreioEmprestimo++) {
                                        if(emprestimo[arreioEmprestimo] == null) {
                                            emprestimoIndex = arreioEmprestimo;
                                            break;
                                        }
                                    }

                                    if(livroIndex != -1 && clienteIndex != -1) {
                                        System.out.println("\n" + livro[livroIndex]);
                                        System.out.println("\n" + cliente[clienteIndex]);
                                        System.out.println("\n ---------------------------------------------------- \n");
                                        System.out.println("\n - Deseja confirmar o emprestimo? Precione (S) para sim: ");
                                        System.out.print(" -> ");
                                        char opcConfirmar = scan.next().charAt(0);

                                        if(opcConfirmar == 'S' || opcConfirmar == 's') {
                                            Emprestimos empr = new Emprestimos();

                                            try {
                                                if (empr.registrarEmprestimo(livro[livroIndex], cliente[clienteIndex])) {
                                                    emprestimo[emprestimoIndex] = empr;
                                                    contadorEmp++;
                                                    System.out.println(" - Empréstimo registrado com sucesso. ");
                                                } else {
                                                    System.out.println(" - Falha ao registrar o empréstimo. ");
                                                }
                                            } catch (Exception e) {
                                                System.out.println(" - Ocorreu um erro ao registrar o empréstimo.");
                                            }

                                        } else {
                                            System.out.println("\n - Empréstimo cancelado. ");
                                        }
                                    } else {
                                        if(livroIndex == -1){
                                            System.out.println("\n - Nenhum cadastro de livro encontrado com esse ID. ");
                                        }
                                        if(clienteIndex == -1){
                                            System.out.println("\n - Nenhum cadastro de cliente encontrado com esse ID");
                                        }
                                    }
                                } else {
                                    System.out.println(" - Limite de empréstimos atingido. ");
                                }
                                break;
// ---------------------------------------------------------------------------------------------------------------------------------------------------------------
                            case 2: //Devolução de Livros
                                System.out.println("\n -=-=- Devolução de Livros -=-=- ");

                                int contEmprestimos = 0;
                                for (Emprestimos e : emprestimo) if (e != null) contEmprestimos++;

                                if(contEmprestimos == 0) {
                                    System.out.println("\n _ Nenhum empréstimo realizado. ");

                                } else if(contadorEmp > 0 && contEmprestimos > 0) {

                                    int clienteId = -1;
                                    int clienteIndex = -1, emprestimoIndex = -1;

                                    System.out.println(" - Id do Cliente: ");

                                    while (!scan.hasNextInt()) {
                                        System.out.println(" - Digite um número válido. ");
                                        scan.next();
                                    }
                                    clienteId = scan.nextInt();
                                    scan.nextLine();

                                    for (int arreioCliente = 0; arreioCliente < cliente.length; arreioCliente++) {
                                        if (cliente[arreioCliente] != null && cliente[arreioCliente].getIdCliente() == clienteId) {
                                            clienteIndex = arreioCliente;
                                            break;
                                        }
                                    }

                                    if (clienteIndex != -1 && cliente[clienteIndex].getTemEmprestimo()) {
                                        System.out.println("\n" + cliente[clienteIndex]);

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
                                            System.out.println("\n - Deseja confirmar a devolução? Precione (S) para sim: ");
                                            System.out.print(" -> ");
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
                                }
                                break;
// ---------------------------------------------------------------------------------------------------------------------------------------------------------------
                            case 3:  // Sair do menu emprestimo
                                System.out.println(" - Voltando para o menu principal.");
                                break;

                            default: // Caso seja informado algum número além dos informados no menu
                                System.out.println(" - Opção inválida.");
                        }

                    } while (opcEmp != 3);
                    break;

// ---------------------------------------------------------------------------------------------------------------------------------------------------------------
                case 4: // Listagem dos cadastros
                    System.out.println("\n -=-=- Listagem de Cadastros e Empréstimos -=-=- ");
                    int opcLista = 0;
                    do{ // Menu do cadastro
                        System.out.println("\n - Opções disponíveis: ");
                        System.out.println(" | 1 - Lista de Livros Cadastrados");
                        System.out.println(" | 2 - Lista de Clientes Cadastrados");
                        System.out.println(" | 3 - Lista de Empréstimos Ativos");
                        System.out.println(" | 4 - Voltar ao Menu Principal");

                        System.out.print(" -> ");
                        while (!scan.hasNextInt()) { // Tratamento e validação da entrada do menu de listagem
                            System.out.println(" - Digite um número válido. ");
                            System.out.print(" -> ");
                            scan.next();
                        }
                        opcLista = scan.nextInt();
                        scan.nextLine();
// ---------------------------------------------------------------------------------------------------------------------------------------------------------------
                        switch (opcLista) {
                            case 1: // Listagem de livros

                                System.out.println("\n -=-=- Listagem de Livros Cadsatrados -=-=- ");
                                
                                int contLivros = 0; // Variável contadora
                                for (Livros l : livro) if (l != null) contLivros++; // Verifica se tem cadastros
                                if(contLivros > 0) {
                                    for (int i = 0; i < livro.length; i++) {
                                        if (livro[i] != null) {
                                            System.out.println(" ---------------------------------- \n");
                                            System.out.println(livro[i].toString());
                                        }
                                    }
                                    System.out.println(" ---------------------------------- \n");
                                } else {
                                    System.out.println("\n - Nenhum livro cadastrado. ");
                                }
                                break;
// ---------------------------------------------------------------------------------------------------------------------------------------------------------------
                            case 2: //Lista de Clientes
                                
                                System.out.println("\n -=-=- Listagem de Clietes Cadastrados -=-=- ");
                                
                                int contClientes = 0; // Variável contadora
                                for (Clientes c : cliente) if (c != null) contClientes++; // Verifica se tem cadastros
                                if(contClientes > 0) {
                                    for (int i = 0; i < cliente.length; i++) {
                                        if (cliente[i] != null) {
                                            System.out.println(" ---------------------------------- \n");
                                            System.out.println(cliente[i].toString());
                                        }
                                    }
                                    System.out.println(" ---------------------------------- \n");
                                } else {
                                    System.out.println("\n - Nenhum cliente cadastrado. ");
                                }
                                break;
// ---------------------------------------------------------------------------------------------------------------------------------------------------------------
                            case 3: //Lista dos emprestimos ativos

                                System.out.println("\n -=-=- Empréstimos Ativos -=-=- ");
                                
                                int contEmprestimos = 0; // Variável contadora
                                for (Emprestimos e : emprestimo) if (e != null) contEmprestimos++; // Verifica se tem empréstimo
                                if(contEmprestimos > 0) {
                                    for (int i = 0; i < cliente.length; i++) {
                                        if (cliente[i] != null && cliente[i].getTemEmprestimo()) {
                                            System.out.println(" ---------------------------------- \n");
                                            System.out.println(cliente[i].toString());
                                        }
                                    }
                                    System.out.println(" ---------------------------------- \n");
                                } else {
                                    System.out.println("\n - Nenhum emprestimo realizado. ");
                                }
                                break;
// ---------------------------------------------------------------------------------------------------------------------------------------------------------------
                            case 4:  // Sair do menu de listagem
                                System.out.println(" - Voltando para o menu principal.");
                                break;

                            default: // Caso seja informado algum número além dos informados no menu
                                System.out.println(" - Opção inválida.");
                        }

                    } while (opcLista != 4);
                    break;

// ---------------------------------------------------------------------------------------------------------------------------------------------------------------
                case 5: //Salvar cadastros em arquivos de textos

                    int contLivros = 0, contClientes = 0, contEmprestimos = 0; // Variáveis contadoras
                    // Verifica se tem cadastros
                    for (Livros l : livro) if (l != null) contLivros++;
                    for (Clientes c : cliente) if (c != null) contClientes++;
                    for (Emprestimos e : emprestimo) if (e != null) contEmprestimos++;
                    
                    System.out.println("\n -=-=- Salvando Cadastros e Empréstimos em Arquivos -=-=- ");

                    try{
                        FileWriter arquivo = new FileWriter("dadosBiblioteca.txt");
                        PrintWriter gravarArquivo = new PrintWriter(arquivo);

                        if(contLivros > 0) {
                            // Grava os lirvos cadastrados
                            gravarArquivo.println("\n -=-=-=- Livros Cadastrados -=-=-=- \n");
                            for (int i = 0; i < livro.length; i++) {
                                if (livro[i] != null) {
                                    gravarArquivo.println(livro[i].toString());
                                    gravarArquivo.println(" ------------------------------------------------------- ");
                                }
                            }
                        } else {
                            System.out.println("\n - Nenhum livro cadastrado. ");
                        }

                        if(contClientes > 0) {
                            // Grava os clientes cadastrados
                            gravarArquivo.println("\n -=-=-=- Clientes Cadastrados -=-=-=- \n");
                            for (int i = 0; i < cliente.length; i++) {
                                if (cliente[i] != null) {
                                    gravarArquivo.println(cliente[i].toString());
                                    gravarArquivo.println(" ------------------------------------------------------- ");
                                }
                            }
                        } else {
                            System.out.println("\n - Nenhum cliente cadastrado. ");
                        }

                        if (contEmprestimos > 0) {
                            // Grava os emprestimos ativos
                            gravarArquivo.println("\n -=-=-=- Empréstimos Ativos -=-=-=- \n");
                            for (int i = 0; i < emprestimo.length; i++) {
                                if (emprestimo[i] != null) {
                                    gravarArquivo.println(emprestimo[i].toString());
                                    gravarArquivo.println(" ------------------------------------------------------- ");
                                }
                            }
                        }else if (contEmprestimos < 0 && contClientes > 0 && contLivros > 0){
                            System.out.println("\n - Nenhum empréstimo realizado. ");
                        }

                        gravarArquivo.close();
                        arquivo.close(); // Fecha o arquivo
                        System.out.println("\n - Dados salvos com sucesso em \"dadosBiblioteca.txt\". ");
                    } catch (IOException e) {
                        System.out.println("\n - Erro ao salvar dados no arquivo. ");
                    }
                    break;

// ---------------------------------------------------------------------------------------------------------------------------------------------------------------
                case 6: // Sair do menu principal
                    System.out.println("Saindo do sistema. Obrigado por utilizar o Sistema de Gerenciamento de Biblioteca!");
                    break;

                default: // Caso seja informado algum número além dos informados no menu
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcMenu != 6);
    }    
}