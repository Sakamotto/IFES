package openaddressing;

import tadchaininghash.Dado;

/**
 * Created by cristian on 26/03/17.
 */
public class TadHash {

    /*Dado[] content;
    private final int base = 16;
    private int functionOpt;

    public static final int POLINOMIAL = 1;
    public static final int ASCII = 2;

    public TadHash(int size, int function){
        this.functionOpt = function;
        this.content = new Dado[size];
    }


    private int getIndex(String key){
        if(functionOpt == TadHash.POLINOMIAL){
            return polynomialAcc(key);
        }else if(functionOpt == TadHash.ASCII){
            return asciiSum(key);
        }

        return -1;
    }

    public void add(Object key, Object value){
        String keyS = (String) key;
        int index = getIndex(keyS);

        if(this.content[index] == null){
            this.content[index] = (Dado) value;
        }else{
            if(this.content[index] == key){
                this.content[index] = (Dado) value;
            }else{
                while(this.content[index] != null){
                    index++;
                }
                this.content[index] = (Dado) value;
            }
        }
    }

    public boolean remove(Object key){
        String keyS = (String) key;
        int index = this.getIndex(keyS);

        if(this.content[index].getTelefone().equals(keyS)){
            this.content[index] = null;
            return true;
        }else{
            while(!this.content[index].getTelefone().equals(keyS)){
                index++;
            }
            if(index >= content.length){
                System.out.println("Elemento inexistente!");
                return false;
            }else{
                this.content[index] = null;
                return true;
            }
        }
    }

    public Dado getElement(Object key){
        String keyS = (String) key;
        int index = getIndex(keyS);

        if(this.content[index].getTelefone().equals(keyS)){
            return content[index];
        }else{
            while(!this.content[index].getTelefone().equals(keyS)){
                index++;
            }
            if(content.length >= index){
                return null;
            }
            return content[index];
        }
    }

    private int polynomialAcc(String key){
        double f = 0;

        for(int i = 0; i < key.length(); i++){
            f = key.codePointAt(i) * Math.pow(base, i);
        }
        return (int)(f % content.length);
    }

    private int asciiSum(String key){
        double f = 0;

        for(int i = 0; i < key.length(); i++){
            f += key.codePointAt(i);
        }

        return (int)(f % content.length);
    }*/

}
