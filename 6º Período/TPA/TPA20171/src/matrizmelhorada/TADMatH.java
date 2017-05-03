package matrizmelhorada;

/**
 * Created by cristian on 23/04/17.
 */
public abstract class TADMatH {

    private int linhas;
    private int colunas;

    TADMatH(int linhas, int colunas){
        this.linhas = linhas;
        this.colunas = colunas;
    }

    public abstract int getLinhas();
    public abstract int getColunas();
    public abstract float getElem(int i, int j);
    public abstract void setElem(int i, int j, float elem);
    public abstract TADMatH multi(TADMatH mat);
//    public abstract TADMatH carregaMMF(String nomeArq);
//    public abstract void salvaMMF(String nomeArq);
    public abstract boolean equals(TADMatH pMat);

}
