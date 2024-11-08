package SGB;

public class Clientes { // Atributos, declaração das variaveis que irá receber os dados principais
    private int idCliente;
    private String nome;
    private boolean temEmprestimo;
    private String email;
    private String cpf;
    private String telefone;
    private Livros livroEmprestado;
    
    // Método construtor que inicializa um novo cliente com os dados essenciais e já marcando como sem empréstimo
    public Clientes (int idCliente, String nome, String email,String  cpf, String telefone) {
        try {
            this.idCliente = idCliente;
            this.nome = nome;
            this.email = email;
            this.cpf = cpf;
            this.telefone = telefone;
            this.temEmprestimo = false;
            this.livroEmprestado = null;
        } catch (Exception e) {
            System.out.println(" - Erro ao cadastrar cliente. ");
        }
    }
    
    public String getNome() { // Retorna o nome do cliente
        return nome;
    }
    public int getIdCliente() { // Retorna o ID do cliente
        return idCliente;
    }
    public boolean getTemEmprestimo() { // Retorna "temEmprestimo", se for verdadeiro (usuário tem empréstimo), se for falso (usuário não tem empréstimo)
        return temEmprestimo;
    }
    public void emprestimo_true(Livros livro){ // Marca que o cliente está com algum livro emprestado, associando o livro ao cliente
        temEmprestimo = true;
        this.livroEmprestado = livro;
    }
    public void emprestimo_false(){ // Marca que o cliente não está com nenhum livro, liberando o livro ao cadastro do cliente
        temEmprestimo = false;
        this.livroEmprestado = null;
    }

    @Override
    public String toString (){
        if(!temEmprestimo) { // Retorna os dados do cliente, caso ele não tenha algum empréstimo em seu nome
            return " | ID Cliente: " + idCliente + "\n | Nome: " + nome + "\n | Email: " + email + "\n | CPF: " + cpf +
                    "\n | Telefone: " + telefone + "\n | Não possui empréstimo ativo.";
            
        } else { // Retorna os dados do cliente junto ao titulo e Id do livro, caso ele tenha algum empréstimo em seu nome
            return " | ID Cliente: " + idCliente + "\n | Nome: " + nome + "\n | Email: " + email + "\n | CPF: " + cpf +
                    "\n | Telefone: " + telefone + "\n --------------------------------" + "\n | Empréstimo ativo:" +
                    "\n | Título: " + livroEmprestado.get_Titulo() + " - Id Livro: " + livroEmprestado.getId_livro();
        }
    }
}



