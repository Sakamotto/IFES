package Entidade;

/**
 * Created by 20142bsi0054 on 12/04/2016.
 */
public class Mouse extends Produto{

    public Mouse(String tipo){
        super("Digital Tiger", "Mouse Show de Bola: ", 22.90);
//        this.setDescricao(descricao);
//        this.setNomeLoja(nomeLoja);
//        this.setPreco(preco);
        this.setTipo(tipo);
    }

    private String tipo;

    public String descricaoProduto(){
        return getTipo();
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
