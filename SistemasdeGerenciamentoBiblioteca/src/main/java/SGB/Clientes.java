package SGB;

public class Clientes {
    private int idCliente;
    private String nome;
    private boolean temEmprestimo;
    private String email;
    private String cpf;
    private String telefone;
    
    public Clientes (int idCliente, String nome, String email,String  cpf, String telefone) {
        this.idCliente=idCliente;
        this.nome=nome;
        this.email=email;
        this.cpf=cpf;
        this.telefone=telefone;
        this.temEmprestimo=false;
        
    }
    
    public String getNome() {
        return nome;
    }

    public int getIdCliente() {
        return idCliente;
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
