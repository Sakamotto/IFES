package Entidade;

/**
 * Created by 20142BSI0054 on 13/04/2016.
 */
public class Camarote extends Ingresso {

    private String bebida = "Suco de uva";
    private String jantar = "Strogonoff";

    public String getBebida() {
        return bebida;
    }

    public void setBebida(String bebida) {
        this.bebida = bebida;
    }

    public String getJantar() {
        return jantar;
    }

    public void setJantar(String jantar) {
        this.jantar = jantar;
    }
}
