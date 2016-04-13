package Entidade;

/**
 * Created by 20142BSI0054 on 13/04/2016.
 */
public class Temporada {


    public static int MAX_INGRESSOS;
    public Ingresso ing[] = new Ingresso[MAX_INGRESSOS];
    public int qtdIngressosVendidos = 0;
    private int tipoLocal;// 1 - Teatro, 2 - Cinema

    /*
    * Ingresso tipo 1: Simples
    * Ingresso tipo 2: Especial
    * Ingresso tipo 3: Camarote
    */

    public void venderIngresso(int tipoIngresso){
        if(tipoLocal == 1){
            if(tipoIngresso == 1){
                ing = new Simples[Teatro.numCadeirasSimples];
            }else if(tipoIngresso == 2){
                ing = new Especial[Teatro.numCadeirasEspeciais];
            }
        }

    }

    public void definirTemporada(){

    }

    public void imprimirDadosDaTemporada(){

    }

    public int getQtdIngressos() {
        return qtdIngressosVendidos;
    }

    public void setQtdIngressos(int qtdIngressos) {
        this.qtdIngressosVendidos = qtdIngressos;
    }

    public int getTipoLocal() {
        return tipoLocal;
    }

    public void setTipoLocal(int tipoLocal) {
        this.tipoLocal = tipoLocal;
    }
}
