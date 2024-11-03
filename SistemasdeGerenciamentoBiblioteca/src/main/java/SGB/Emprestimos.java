package SGB;

import java.time.LocalDate;

public class Emprestimos {
    //Atributos
    private Livros livro;
    private Clientes cliente;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    
    //Métodos
    public boolean registrarEmprestimo (Livros livro, Clientes usuario, LocalDate dataPrevistaDevolucao) {
        try {
            if (!usuario.getEmprestimoUsuario() && livro.get_num_exemp_disp() > 0) {
                //Verifica se tem livro não está emprestado a alguem e se o usuário não tem nenhum livro empretado
                this.livro = livro;
                this.cliente = usuario;
                this.dataEmprestimo = LocalDate.now();
                this.dataPrevistaDevolucao = dataPrevistaDevolucao;
                this.cliente.emprestimo_true();
                this.livro.atualizarNumExemp(-1);
                return true; //Confirma que o emprestimo foi efetuado

            } else if (livro.get_num_exemp_disp() == 0) {//Caso o livro esteja emprestado a alguém
                System.out.println(" Nenhum livro disponível para empréstimo. ");
            } else if (usuario.getEmprestimoUsuario()) { //Caso o usuário já tenha algum livro empretado
                System.out.println(" Usuário já possui emprestimo realizado. ");
            }
        } catch (Exception e) {
            System.out.println(" Ocorreu um erro ao registrar emprestimo. ");
        }
        return false;
    }

    public boolean registrarDevolucao(Livros livro, Clientes usuario) {
        try {
            if(usuario.getEmprestimoUsuario() && livro.get_num_exemp_disp() != livro.get_num_exemplares()){
                //Verifica se o livro está emprestado
                this.cliente.emprestimo_false();
                this.livro.atualizarNumExemp(1);
                this.livro = null;
                this.cliente = null;
                this.dataEmprestimo = null;
                this.dataPrevistaDevolucao = null;
                return true; //confirma que a devolução foi efetivada

            } else if (livro.get_num_exemp_disp() == livro.get_num_exemplares()){
                System.out.println(" Não há livros emprestados para devolução ");
            } else if (!usuario.getEmprestimoUsuario()) {
                System.out.println(" Usuário não possui empréstimos em seu nome. ");
            }
        } catch (Exception e) {
            System.out.println(" Ocorreu um erro ao registrar emprestimo. ");
        }
        return false;
    }

    public boolean adiarEntrega(LocalDate dataEntrega){
        this.dataPrevistaDevolucao = dataEntrega;
        return true;
    }
}
