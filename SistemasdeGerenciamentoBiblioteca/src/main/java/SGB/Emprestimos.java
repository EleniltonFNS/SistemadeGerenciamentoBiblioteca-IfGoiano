package SGB;

import java.time.LocalDate;

public class Emprestimos {
    //Atributos
    private Livros livro;
    private Usuarios usuario;
    LocalDate dataEmprestimo;
    LocalDate dataPrevistaDevolucao;
    
    //Contrutores
    public Emprestimos (Livros livro, Usuarios usuario, LocalDate dataPrevistaDevolucao) {
        if (!usuario.getTemEmprestimo() && livro.get_num_exemp_disp() > 0){
        //Verifica se tem livro não está emprestado a alguem e se o usuário não tem nenhum livro empretado
            this.livro = livro;
            this.usuario = usuario;
            dataEmprestimo = LocalDate.now();
            this.dataPrevistaDevolucao = dataPrevistaDevolucao;
            this.usuario.emprestimo_true();
            this.livro.atualizacaoNum_exemp(-1);
        } else if (livro.get_num_exemp_disp() == 0){//Caso o livro esteja emprestado a alguém
            System.out.println(" Nenhum livro disponível para empréstimo. ");
        } else if (usuario.getTemEmprestimo()) { //Caso o usuário já tenha algum livro empretado
            System.out.println(" Usuário já possui emprestimo realizado. ");
        }
    }
}
