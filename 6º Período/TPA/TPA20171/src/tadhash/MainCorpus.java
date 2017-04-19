package tadhash;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by cristian on 12/04/17.
 */
public class MainCorpus {

    public static void main(String[] args) throws IOException {

        TabHashOA tabHashOA = new TabHashOA(37000);
        String nomeArq = "corpus.txt";
        String linha;

        String[] result;

        BufferedReader br = new BufferedReader(
                new FileReader( new File("data_sets" + File.separator +nomeArq).getAbsolutePath()));

        //br.readLine();
        System.out.println("Executando ...");
        while(br.ready()){
            linha = br.readLine();
            result = linha.split("[ ,.!?:;()-/|{}\\[\\]\"“”']+");

            for(int i = 0; i < result.length; i++){
                String lower = result[i].toLowerCase().trim();
                Integer d = (Integer) tabHashOA.getElement(lower);
                if(d != null){
                    d += 1;
                    tabHashOA.get(lower).setDado(d);
                }else{
                    tabHashOA.add(lower, 1);
                }
            }
        }

        for(Object key: tabHashOA.getKeys()){
            System.out.println("Key: " + key + " | Value: " + tabHashOA.getElement(key));
        }




    }
}
