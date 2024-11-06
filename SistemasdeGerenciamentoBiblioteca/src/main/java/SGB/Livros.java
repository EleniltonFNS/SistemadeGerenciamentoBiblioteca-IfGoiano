package SGB;

public class Livros {
  private int id_livro;
  private String titulo;
  private String autor;
  private String editora;
  private int ano_publi;
  private int num_exemplares;
  private int num_exemp_disp;
  
  // Todo livro tem que ter id, titulo, autor, editora, ano que foi publicado e numero de exemplares;
  public Livros(int id_livro, String titulo, String autor, String editora, int ano_publi, int num_exemplares) {
    this.id_livro = id_livro;
    this.titulo = titulo;
    this.autor = autor;
    this.editora = editora;
    this.ano_publi = ano_publi;
    this.num_exemplares = num_exemplares;
    this.num_exemp_disp = num_exemplares;
  }

  public int getId_livro() {
    return id_livro;
  }

  public String get_Titulo(){ //Ver titulo
    return titulo;
  }

  public int get_num_exemplares(){ //Ver numero de exemplares
    return num_exemplares;
  }

  public int get_num_exemp_disp(){ //Ver numero de exemplares
    return num_exemp_disp;
  }
  public void atualizarNumExemp(int valor){
    this.num_exemp_disp +=valor;
  }

  @Override
  public String toString(){ //Utilizar para imprimir os livros.
    if(num_exemp_disp == num_exemplares) {
      return " | ID: " + id_livro + "\n | Título: " + titulo + "\n | Autor: " + autor + "\n | Editora: " + editora +
              "\n | Ano de Publicação: " + ano_publi + "\n | Números de Exemplares: " + num_exemplares;
    } else {
      return " | ID: " + id_livro + "\n | Título: " + titulo + "\n | Autor: " + autor + "\n | Editora: " + editora + "\n | Ano de Publicação: "
              + ano_publi + "\n | Números de Exemplares: " + num_exemplares + "\n | Exemplares Disponíveis: " + num_exemp_disp;
    }
  }
}