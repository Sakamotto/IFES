package Entidade;

/**
 * Created by 20142BSI0054 on 13/04/2016.
 */
public class Teatro extends Local{

    public static int numCadeirasCamarote = 3;
    public static int numCadeirasEspeciais = 5;
    public static int numCadeirasSimples = 7;


    public int getNiCamarote(){
        return numCadeirasCamarote;
    }

    public int getNiEspecial(){
        return numCadeirasEspeciais;
    }

    public int getNiSimples(){
        return numCadeirasSimples;
    }

    public int getTotalCadeiras(){
        return numCadeirasCamarote + numCadeirasEspeciais + numCadeirasSimples;
    }

}
