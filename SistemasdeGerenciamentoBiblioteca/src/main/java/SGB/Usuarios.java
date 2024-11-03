package SGB;

public class Usuarios {
    private String nome;
    private boolean temEmprestimo;

    public String getNome() {
        return nome;
    }

    public boolean getTemEmprestimo() { //Verifica se "temEmprestimo" é verdadeiro ou falso
        return temEmprestimo;
    }
    public void emprestimo_true(){ //Marca que o usuario está com algum livro emprestado
        temEmprestimo = true;
    }
    public void emprestimo_false(){ //Marca que o usuario não está com nenhum livro
        temEmprestimo = false;
    }
}
