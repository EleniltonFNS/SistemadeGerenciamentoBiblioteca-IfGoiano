package SGB;

import java.time.LocalDate;

public class Emprestimos {
//Atributos
    private Livros livro;
    private Clientes cliente;
    private LocalDate dataEmprestimo;

//Métodos
    // Registra um empréstimo, verificando a disponibilidade do livro e o status do cliente  
    public boolean registrarEmprestimo(Livros livro, Clientes cliente) {
        try {
            if (!cliente.getTemEmprestimo() && livro.get_num_exemp_disp() > 0) {
                // Verifica se o cliente não possui empréstimos ativos e se o livro está disponível
                this.livro = livro;
                this.cliente = cliente;
                this.dataEmprestimo = LocalDate.now();

                // Atualiza o status do cliente e a disponibilidade do livro
                this.cliente.emprestimo_true(livro);
                this.livro.atualizarNumExemp(-1);
                return true; //Confirma que o emprestimo foi efetuado

            } else if (livro.get_num_exemp_disp() == 0) {//Caso o livro esteja emprestado a alguém
                System.out.println(" - Nenhum livro disponível para empréstimo. ");
            } else if (cliente.getTemEmprestimo()) { //Caso o usuário já tenha algum livro empretado
                System.out.println(" - Usuário já possui emprestimo ativo. ");
            }
        } catch (Exception e) {
            System.out.println(" - Ocorreu um erro ao registrar emprestimo. ");
        }
        return false;
    }
    
    // Registra a devolução de um livro, atualizando o status do cliente e a disponibilidade do livro
    public boolean registrarDevolucao(Clientes cliente) {
        try {
            if (cliente.getTemEmprestimo()) {
                //Verifica se o livro está emprestado
                this.cliente.emprestimo_false();
                this.livro.atualizarNumExemp(1);

                // Limpa as informações do empréstimo
                this.livro = null;
                this.cliente = null;
                this.dataEmprestimo = null;
                return true; //confirma que a devolução foi efetivada

            } else if (!cliente.getTemEmprestimo()) {
                System.out.println(" - Usuário não possui empréstimo ativo. ");
            }
        } catch (Exception e) {
            System.out.println(" - Ocorreu um erro ao registrar a devolução. ");
        }
        return false;
    }

    public int getIdCliente() {
        return cliente.getIdCliente();
    }

    public String getLivro() {
        return livro.toString();
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }
    
    // Retorna uma representação textual do empréstimo, incluindo detalhes do livro e cliente
    @Override
    public String toString() {
        return "\n | ID do Livro: " + livro.getId_livro() + " - Título: " + livro.get_Titulo() +
                "\n | ID do Cliente: " + cliente.getIdCliente() + " - Nome: " + cliente.getNome() +
                "\n | Data do Emprestimo: " + dataEmprestimo.toString();
    }
}
