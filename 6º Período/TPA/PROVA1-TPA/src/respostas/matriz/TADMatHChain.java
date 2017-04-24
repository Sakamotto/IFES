package respostas.matriz;

import respostas.hash.TabHChain;

/**
 * Created by cristian on 23/04/17.
 */
public class TADMatHChain extends TADMatH {

    private ElemMat elem;
    private TabHChain matriz;
    private int linhas;
    private int colunas;

    TADMatHChain(int linhas, int colunas) {
        super(linhas, colunas);
        this.linhas = linhas;
        this.colunas = colunas;
        matriz = new TabHChain();
    }

    @Override
    public int getLinhas() {
        return this.linhas;
    }

    @Override
    public int getColunas() {
        return this.colunas;
    }

    @Override
    public float getElem(int i, int j) {
        return 0;
    }

    @Override
    public void setElem(int i, int j) {


    }

    @Override
    public TADMatH multi(TADMatH mat) {
        return null;
    }

    @Override
    public TADMatH carregaMMF(String nomeArq) {
        return null;
    }

    @Override
    public void salvaMMF(String nomeArq) {

    }

    @Override
    public boolean equals(TADMatH pMat) {
        return false;
    }
}
