package SGB;

public class Clientes {
    private int IdCliente;
    private String nome;
    private boolean temEmprestimo;

    public String getNome() {
        return nome;
    }

    public int getIdCliente() {
        return IdCliente;
    }

    public boolean getEmprestimoCliente() { //Verifica se "temEmprestimo" é verdadeiro ou falso
        return temEmprestimo;
    }
    public void emprestimo_true(){ //Marca que o cliente está com algum livro emprestado
        temEmprestimo = true;
    }
    public void emprestimo_false(){ //Marca que o cliente não está com nenhum livro
        temEmprestimo = false;
    }
}
