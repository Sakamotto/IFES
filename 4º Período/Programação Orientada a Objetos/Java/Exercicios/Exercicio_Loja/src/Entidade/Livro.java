package Entidade;

/**
 * Created by 20142bsi0054 on 12/04/2016.
 */
public class Livro extends Produto{

    public Livro(String autor){
        super("Saraiva", "Livro muito bom: ", 50.99);
        this.setAutor(autor);
    }

    private String autor;

    public String descricaoProduto(){
        return getAutor();
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
