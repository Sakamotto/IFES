package Controle;

/**
 * Created by cristian on 07/04/16.
 */
public class Excecao {
    private Saida s = new Saida();

    public void deltaMenorZero(double delta){
        if(delta < 0){
            s.imprime("Delta menor que zero!");
        }
    }
}
