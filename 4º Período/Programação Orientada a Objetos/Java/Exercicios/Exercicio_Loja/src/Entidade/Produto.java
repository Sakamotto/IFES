package Entidade;

/**
 * Created by 20142bsi0054 on 12/04/2016.
 */
public class Produto {

    public Produto(){
    }

    public Produto(String nomeLoja, String descricao, double preco){
        this.setNomeLoja(nomeLoja);
        this.setDescricao(descricao);
        this.setPreco(preco);
    }

    private String nomeLoja;
    private String descricao;
    private double preco;

    public String descricaoProduto(){
        return null;
    }

    public String getNomeLoja(){
        return nomeLoja;
    }

    public void setNomeLoja(String nomeLoja) {
        this.nomeLoja = nomeLoja;
    }

    public String getDescricao() {
        return descricao + descricaoProduto();
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
