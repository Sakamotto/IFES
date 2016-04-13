package Entidade;

/**
 * Created by 20142BSI0054 on 13/04/2016.
 */
public class Temporada {


    public static int MAX_INGRESSOS = 10;
    public Ingresso ing[] = new Ingresso[MAX_INGRESSOS];
    public int qtdIngressosVendidos = 0;

    /*
    * Ingresso tipo 1: Simples
    * Ingresso tipo 2: Especial
    * Ingresso tipo 3: Camarote
    */

    public void venderIngresso(int tipoIngresso){
        if(tipoIngresso == 1){ // Preciso Saber o local
            ing = new Simples[3];
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
}
