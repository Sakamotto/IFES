package corretor;

import java.util.ArrayList;

/**
 * Created by 20142bsi0054 on 20/04/2017.
 */
public class StringMethods {


    private boolean in(String s1, ArrayList<String> lista){
        for(int i = 0; i < lista.size(); i++){
            if(s1.equals(lista.get(i))){
                return true;
            }
        }
        return false;
    }


    private ArrayList<String> union(ArrayList<String> conj1, ArrayList<String> conj2){

        // crio um novo ArrayList copiando (shadow copy) os elementos de conj1 para result
        ArrayList<String> result = new ArrayList<>(conj1);

        for(int i = 0; i < conj2.size(); i++){
            if(!in(conj2.get(i), conj1)){
                result.add(conj2.get(i));
            }
        }

        return result;
    }

    /**
     *
     * @param w1
     * @param w2
     * @return retorna a quantidade de edições mínimas necessárias para transformar a string w1 em w2
     */

    public int levenshtein(String w1, String w2){

        if(w1.equals(w2)){
            return 0;
        }

        int matriz[][] = new int[w1.length() + 1][w2.length() + 1];
        int custo;
        int lenW1 = w1.length();
        int lenW2 = w2.length();


        // Preenche a primeira coluna ...
        for(int i = 0; i < lenW1 + 1; i ++){
            matriz[i][0] = i;
        }

        // Preenche a primeira linha ...
        for(int j = 0; j < lenW2 + 1; j++){
            matriz[0][j] = j;
        }

        for(int i = 1; i < lenW1 + 1; i++){
            for(int j = 1; j < lenW2 + 1; j++){
                if(w1.charAt(i - 1) == w2.charAt(j - 1)){
                    custo = 0;
                }else{
                    custo = 1;
                }
                matriz[i][j] = Math.min(matriz[i - 1][j] + 1, Math.min(matriz[i][j-1] + 1, matriz[i-1][j-1] + custo));
            }
        }

        return matriz[lenW1][lenW2];
    }


    public float jaccard(String w1, String w2){

        float unionSize = 1;
        float interSize = 0;

        if(w1 == null || w2 == null){
            return -1;
        }

        if(w1.equals(w2)){
            return 1;
        }

        ArrayList<String> conjuntoW1 = nGrama(w1, 2);
        ArrayList<String> conjuntoW2 = nGrama(w2, 2);

        ArrayList<String> union = union(conjuntoW1, conjuntoW2);
        unionSize = union.size();


        for(String s: union){
            if((conjuntoW1.indexOf(s) != -1) && (conjuntoW2.indexOf(s) != -1)){
                interSize++;
            }
        }

        return interSize/unionSize;
    }

    public ArrayList<String> nGrama(String palavra, int tam){
        ArrayList<String> ngramas = new ArrayList<>();

        for(int i = 0; i < palavra.length(); i++){
            if(i <= (palavra.length() - tam)){
                ngramas.add(palavra.substring(i, tam + i));
            }
        }

        return ngramas;
    }

//    public static void main(String args[]){
//
//        StringMethods sm = new StringMethods();
//        String w1 = "advinhar";
//        String w2 = "adivinhar";
//
//        System.out.println(sm.jaccard(w1, w2));
//        System.out.println(sm.levenshtein(w1, w2));
//
//
//    }


}
