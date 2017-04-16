package tadhash;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by cristian on 12/04/17.
 */
public class MainCorpus {
    public static void main(String[] args) throws IOException {

        TabHashOA tabHashOA = new TabHashOA(250);

        String nomeArq = "corpus.txt";
        String linha;

        BufferedReader br = new BufferedReader(
                new FileReader( new File("data_sets" + File.separator +nomeArq).getAbsolutePath()));

        while(br.ready()){

            linha = br.readLine();

        }




    }
}
