package Entidade;

/**
 * Created by 20142BSI0054 on 13/04/2016.
 */
public class Cinema extends Local{

    public static int numCadeirasEspeciais;
    public static int numCadeirasSimples
    public static int maxCadeiras = getTotalCadeiras();

    public int getNiEspecial(){
        return numCadeirasEspeciais;
    }

    public int getNiSimples(){
        return numCadeirasSimples;
    }

    public int getTotalCadeiras(){
        return numCadeirasEspeciais + numCadeirasSimples;
    }

    public void setNiEspecial(int especial){
        this.numCadeirasEspecial = especial;
    }

    public void setNiSimples(int simples){
        this.numCadeirasSimples = simples;
    }

}
