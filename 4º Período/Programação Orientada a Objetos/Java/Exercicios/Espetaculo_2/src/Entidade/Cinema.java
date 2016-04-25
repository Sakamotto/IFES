package Entidade;

/**
 * Created by 20142BSI0054 on 13/04/2016.
 */
public class Cinema extends Local{

    public static int numCadeirasEspeciais = 10;
    public static int numCadeirasSimples = 5;

    public int getNiEspecial(){
        return numCadeirasEspeciais;
    }

    public int getNiSimples(){
        return numCadeirasSimples;
    }

    public int getTotalCadeiras(){
        return numCadeirasEspeciais + numCadeirasSimples;
    }

}
