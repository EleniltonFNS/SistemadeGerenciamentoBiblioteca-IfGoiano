package SGB;

public class Clientes {
    private int idCliente;
    private String nome;
    private boolean temEmprestimo;
    private String email;
    private String cpf;
    private String telefone;
    private Livros livroEmprestado;
    
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
    public boolean getTemEmprestimo() { //Verifica se "temEmprestimo" é verdadeiro ou falso
        return temEmprestimo;
    }
    public void emprestimo_true(Livros livro){ //Marca que o cliente está com algum livro emprestado
        temEmprestimo = true;
        this.livroEmprestado = livro;
    }
    public void emprestimo_false(){ //Marca que o cliente não está com nenhum livro
        temEmprestimo = false;
        this.livroEmprestado = null;
    }
    @Override
    public String toString (){
        if(!temEmprestimo) {
            return " | ID: " + idCliente + "\n | Nome: " + nome + "\n | Email: " + email + "\n | CPF: " + cpf +
                    "\n | Telefone: " + telefone + "\n | Não possui empréstimo ativo.";
        } else {
            return " | ID: " + idCliente + "\n | Nome: " + nome + "\n | Email: " + email + "\n | CPF: " + cpf +
                    "\n | Telefone: " + telefone + "\n --------------------------------" + "\n | Empréstimo ativo:" +
                    "\n  | Título: " + livroEmprestado.get_Titulo() + "\n  | Id Livro: " + livroEmprestado.getId_livro();
        }
    }
}



