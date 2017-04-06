package tadchaininghash;

import java.io.*;

/**
 * Created by 20142bsi0054 on 23/03/2017.
 */

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        String linha;
        TabHash tabHash = new TabHash(100);
        BufferedReader br = new BufferedReader(new FileReader(
                new File("").getAbsolutePath() + File.separator + "basesTestes/nomes.txt"
        ));

        try {
            while(br.ready()){
                linha = br.readLine();
                tabHash.add(linha, new Dado(linha));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Size: " + tabHash.size());
        System.out.println("Vazio? " + tabHash.isEmpty());

        System.out.println(tabHash.getElement("Ana Clara"));

        tabHash.remove("Ana Clara");

        System.out.println("Size: " + tabHash.size());
        System.out.println("Vazio? " + tabHash.isEmpty());

        System.out.println(tabHash.getElement("Ana Clara"));

    }
}
