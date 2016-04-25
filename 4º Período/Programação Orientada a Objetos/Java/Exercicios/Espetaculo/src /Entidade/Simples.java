package Entidade;

/**
 * Created by 20142BSI0054 on 13/04/2016.
 */
public class Simples extends Ingresso {

    private char fileira;

    public char getFileira() {
        return fileira;
    }

    public void setFileira(char fileira) {
        this.fileira = fileira;
    }

    public void imprimeIngresso(){
        System.out.println("Ingresso Simples");
    }
}
