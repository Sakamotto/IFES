package tadhash;

import java.io.*;

/**
 * Created by cristian on 12/04/17.
 */
public class MainCEP {

    public static void main(String[] args) throws IOException {

        TabHash<String, String[]> tabHashOA = new TabHash<>(100);

        String endereco[];
        String nomeArq = "vix-ruas-ceps.txt";
        String linha;

        BufferedReader br = new BufferedReader(
                new FileReader( new File("data_sets" + File.separator +nomeArq).getAbsolutePath()));

        while(br.ready()){
            endereco = new String[3];
            String cep, linhaSplit[];

            linha = br.readLine();
            linhaSplit = linha.split(";");

            cep = linhaSplit[0].trim();
            endereco[0] = linhaSplit[1].trim();
            endereco[1] = linhaSplit[2].trim();
            endereco[2] = linhaSplit[3].trim();

            tabHashOA.add(cep, endereco);
        }

        System.out.println(tabHashOA.size());

        System.out.println((tabHashOA.getElement("29065-240"))[0]);
        System.out.println((tabHashOA.getElement("29065-240"))[1]);
        System.out.println((tabHashOA.getElement("29065-240"))[2]);


    }

}
