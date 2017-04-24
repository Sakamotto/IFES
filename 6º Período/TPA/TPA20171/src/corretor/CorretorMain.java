package corretor;
import Utils.StreamLoader;
import tadhash.Dictionary;
import tadhash.TabHashOA;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by 20142bsi0054 on 20/04/2017.
 */
public class CorretorMain {

    public static int EDITS_LEVENSHTEIN = 3;
    public static int BACK_SEARCH = 3;
    public static int FORWARD_SEARCH = 3;
    public static float PERCENT_JACCARD = 0.6f;
    public static int LEVENSHTEIN_METHOD = 1;
    public static int JACCARD_METHOD = 2;

    public static boolean in(String palavra, Dictionary dicionario){
        for(String s: (ArrayList<String>)dicionario.getElement(palavra.length())){
            if(palavra.equals(s)){
                return true;
            }
        }
        return false;
    }

    public static ArrayList<String> sugerePalavrasLevenshtein(Dictionary dicionario, String palavra){
        StringMethods stringMethods = new StringMethods();
        ArrayList<String> sugeridas = new ArrayList<>();
        ArrayList<String> acima = new ArrayList<>();
        ArrayList<String> abaixo = new ArrayList<>();
        Object object;

        for(int i = 0; i < FORWARD_SEARCH; i++){
            object = dicionario.getElement(palavra.length() + i);
            if(object != null){
                acima.addAll((ArrayList<String>) object);
            }
        }

        for(int i = 1; i < BACK_SEARCH; i++){
            object = dicionario.getElement(palavra.length() - i);
            if(object != null){
                abaixo.addAll((ArrayList<String>) object);
            }
        }

        if(acima != null){
            for(int i = 0; i < acima.size(); i++){
                int edits = stringMethods.levenshtein(palavra, acima.get(i));
                if(edits < EDITS_LEVENSHTEIN){
                    sugeridas.add(acima.get(i));
                }
            }
        }

        if(abaixo != null){
            for(int i = 0; i < abaixo.size(); i++){
                int edits = stringMethods.levenshtein(palavra, abaixo.get(i));
                if(edits < EDITS_LEVENSHTEIN){
                    sugeridas.add(abaixo.get(i));
                }
            }
        }

        return sugeridas;

    }

    public static ArrayList<String> sugerePalavrasJaccard(Dictionary dicionario, String palavra){
        StringMethods stringMethods = new StringMethods();
        ArrayList<String> sugeridas = new ArrayList<>();
        ArrayList<String> acima = new ArrayList<>();
        ArrayList<String> abaixo = new ArrayList<>();
        Object object;

        for(int i = 0; i < FORWARD_SEARCH; i++){
            object = dicionario.getElement(palavra.length() + i);
            if(object != null){
                acima.addAll((ArrayList<String>) object);
            }
        }

        for(int i = 1; i < BACK_SEARCH; i++){
            object = dicionario.getElement(palavra.length() - i);
            if(object != null){
                abaixo.addAll((ArrayList<String>) object);
            }
        }

        if(acima != null){
            for(int i = 0; i < acima.size(); i++){
                float percent = stringMethods.jaccard(palavra, acima.get(i));
                if(percent >= PERCENT_JACCARD){
                    sugeridas.add(acima.get(i));
                }
            }
        }

        if(abaixo != null){
            for(int i = 0; i < abaixo.size(); i++){
                float percent = stringMethods.jaccard(palavra, abaixo.get(i));
                if(percent >= PERCENT_JACCARD){
                    sugeridas.add(abaixo.get(i));
                }
            }
        }

        return sugeridas;
    }


    public static ArrayList<String> searchWord(Dictionary dicionario, String palavra, int metodo){
        ArrayList<String> encontradas = new ArrayList<>();

        if(in(palavra, dicionario)){
            encontradas.add(palavra);
            return encontradas;
        }else{
            // sugerir palavras ...
            // escolher uma faixa de tamanho ... (vamos definir variando de 2, pra mais ou pra menos)
            if(metodo == LEVENSHTEIN_METHOD){
                encontradas = sugerePalavrasLevenshtein(dicionario, palavra);
            }else{
                encontradas = sugerePalavrasJaccard(dicionario, palavra);
            }
        }
        return encontradas;
    }


    public static void main(String[] args) throws IOException {

        TabHashOA tabHashOA = new TabHashOA(10000);
        StreamLoader sl = new StreamLoader();
        String path = "/home/cristian/Documentos/IFES/IFES/6º Período/TPA/TPA20171/data_sets";
        BufferedReader br = sl.readArq(path + "/" + "pt_BR.dic");
        String linha;
        ArrayList<String> result;
        String palavra = "teste";

        System.out.println("Carregando dicionário ...");
        while(br.ready()){
            linha = br.readLine();
            String linhaSplit = linha.split("/")[0];
            Object elem = tabHashOA.getElement(linhaSplit.length());

            if(elem != null){
                ((ArrayList<String>) tabHashOA.get(linhaSplit.length()).getDado()).add(linhaSplit);
            }else{
                ArrayList<String> palavras = new ArrayList<>();
                palavras.add(linhaSplit);
                tabHashOA.add(linhaSplit.length(), palavras);
            }
        }
        sl.closeArq(br);
        System.out.println("Pronto!\n");

        System.out.println("Palavra buscada: " + palavra);

        System.out.println("Sugeridas por Jaccard:");
        result = searchWord(tabHashOA, palavra, JACCARD_METHOD);
        for(String s: result){
            System.out.println(s);
        }

        System.out.println("\n------------********------------\n");


        System.out.println("Sugeridas por Levenshtein:");
        result = searchWord(tabHashOA, palavra, LEVENSHTEIN_METHOD);
        for(String s: result){
            System.out.println(s);
        }


    }

}
