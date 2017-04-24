package respostas.matriz;

/**
 * Created by cristian on 23/04/17.
 */
public class TADMatHEA extends TADMatH {

    TADMatHEA(int linhas, int colunas) {
        super(linhas, colunas);
    }

    @Override
    public int getLinhas() {
        return 0;
    }

    @Override
    public int getColunas() {
        return 0;
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
