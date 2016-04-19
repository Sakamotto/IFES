package Entidade;

/**
 * Created by 20142BSI0054 on 13/04/2016.
 */
public class Temporada {

    public static int MAX_INGRESSOS;
    public Ingresso ing[];
    public int qtdIngressosVendidos = 0;
    //private int tipoLocal;// 1 - Teatro, 2 - Cinema

    /*
    * Ingresso tipo 1: Simples
    * Ingresso tipo 2: Especial
    * Ingresso tipo 3: Camarote
    */

    public void venderIngresso(int tipoLocal, int tipoIngresso){
        private boolean colocou = false;
        private int i = 0;

        if(tipoLocal == 1){
            Teatro t = new Teatro();
            ing = t[Teatro.maxCadeiras];
            while(!colocou){
                if(ing[i] == null){
                    if(tipoIngresso == 1 && t.getNiSimples() > 0){
                        ing[i] = new Simples();
                        t.setNiSimples(t.getNiSimples() - 1);
                    }else if(tipoIngresso == 2 && t.getNiEspecial() > 0){
                        ing[i] = new Especial();
                        t.setNiEspecial(t.getNiEspecial - 1);
                    }else if(tipoIngresso == 3 && t.getNiCamarote() > 0){
                        ing[i] = new Camarote();
                        t.setNiCamarote(t.getNiCamarote() - 1);
                    }
                    colocou = true;
                }
                i++;
            }
        }else if(tipoLocal == 2){
            Cinema c = new Cinema();
            ing = c[Cinema.maxCadeiras];

            while(!colocou){
                if(ing[i] == null){
                    if(tipoIngresso == 1 && c.getNiSimples() > 0){
                        ing[i] = new Simples();
                        c.setNiSimples(c.getNiSimples() - 1);
                    }else if(tipoIngresso == 2 && c.getNiEspecial() > 0){
                        ing[i] = new Especial();
                        c.setNiEspecial(c.getNiEspecial - 1);
                    }
                }
                i++;
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
