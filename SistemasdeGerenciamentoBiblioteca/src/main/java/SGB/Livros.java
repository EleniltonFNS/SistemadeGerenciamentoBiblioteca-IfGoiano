package SGB;

public class Livros {
  private int id_livro;
  private String titulo;
  private String autor;
  private String editora;
  private int ano_publi;
  private int num_exemplares;
  private boolean statusEmprestimo;
  
  // Todo livro tem que ter id, titulo, autor, editora, ano que foi publicado e numero de exemplares;
  public Livros(int id_livro, String titulo, String autor, String editora, int ano_publi, int num_exemplares) {
    this.id_livro = id_livro;
    this.titulo = titulo;
    this.autor = autor;
    this.editora = editora;
    this.ano_publi = ano_publi;
    this.num_exemplares = num_exemplares;
    this.statusEmprestimo = false;
  }

  public String get_Titulo(){ //Ver titulo
    return titulo;
  }
  public void set_titulo(String titulo){ //Alterar titulo
    this.titulo = titulo;
  }

  public String get_autor(){ //Ver autor
    return autor;}
  public void set_autor(String autor){ //Alterar autor
    this.autor = autor;
  }

  public String get_editora(){ //Ver editora
    return editora;
  }
  public void set_editora(String editora){ //Alterar editora
    this.editora = editora;
  }

  public int get_ano_publi(){ //Ver Ano de publicacao
    return ano_publi;
  }
  public void set_ano_publi(int ano_publi){ //Alterar ano de publicacao
    this.ano_publi = ano_publi;
  }

  public int get_num_exemplares(){ //Ver numero de exemplares
    return num_exemplares;
  }
  public void set_num_exemplares(int num_exemplares){ //Alterar numero de exemplares
    this.num_exemplares = num_exemplares;
  }

  @Override
  public String toString(){ //Utilizar para imprimir os livros.
    return " | ID: " + id_livro + "\n | Título: " + titulo + "\n | Autor: " + autor + "\n | Editora: " + editora +
            "\n | Ano de Publicação: " + ano_publi + "\n | Números de Exemplares: " + num_exemplares;
  }
}