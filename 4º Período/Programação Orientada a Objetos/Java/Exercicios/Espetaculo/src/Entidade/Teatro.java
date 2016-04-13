package Entidade;

/**
 * Created by 20142BSI0054 on 13/04/2016.
 */
public class Teatro extends Local{

    public static int numCadeirasCamarote;
    public static int numCadeirasEspeciais;
    public static int numCadeirasSimples;


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
