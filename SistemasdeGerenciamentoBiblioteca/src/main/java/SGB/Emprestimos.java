package SGB;

import java.time.LocalDate;

public class Emprestimos {
    //Atributos
    private Livros livro;
    private Clientes cliente;
    private LocalDate dataEmprestimo;

    //Métodos
    public boolean registrarEmprestimo(Livros livro, Clientes cliente) {
        try {
            if (!cliente.getTemEmprestimo() && livro.get_num_exemp_disp() > 0) {
                //Verifica se tem livro não está emprestado a alguem e se o usuário não tem nenhum livro empretado
                this.livro = livro;
                this.cliente = cliente;
                this.dataEmprestimo = LocalDate.now();
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

    public boolean registrarDevolucao(Clientes cliente) {
        try {
            if (cliente.getTemEmprestimo()) {
                //Verifica se o livro está emprestado
                this.cliente.emprestimo_false();
                this.livro.atualizarNumExemp(1);
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

    @Override
    public String toString() {
        return "\n | ID do Livro: " + livro.getId_livro() + " - Título: " + livro.get_Titulo() +
                "\n | ID do Cliente: " + cliente.getIdCliente() + " - Nome: " + cliente.getNome() +
                "\n | Data do Emprestimo: " + dataEmprestimo.toString();
    }
}