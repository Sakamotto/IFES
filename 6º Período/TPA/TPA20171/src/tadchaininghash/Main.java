package tadchaininghash;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 20142bsi0054 on 23/03/2017.
 */

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        String linha;
        TabHash<String, Dado> tabHash = new TabHash<>(50);
        BufferedReader br = new BufferedReader(new FileReader(
                new File("").getAbsolutePath() + File.separator + "basesTestes/nomes.txt"
        ));

        try {
            while(br.ready()){
                linha = br.readLine();
                tabHash.add(linha, new Dado(linha));
                System.out.println(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("***********************************");
        for(String key: tabHash.getKeys()){
            System.out.println(tabHash.getElement(key));
        }

        System.out.println("Size: " + tabHash.size());
        System.out.println("Vazio? " + tabHash.isEmpty());

        System.out.println("1º: " + tabHash.getElement("Ana Clara"));

        tabHash.remove("Ana Clara");

        System.out.println("Size: " + tabHash.size());
        System.out.println("Vazio? " + tabHash.isEmpty());

        System.out.println("2º: " + tabHash.getElement("Ana Clara"));

    }
}
