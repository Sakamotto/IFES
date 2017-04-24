package Utils;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Created by cristian on 22/04/17.
 */
public class StreamLoader {

    public BufferedReader readArq(String nomeArq){
        BufferedReader br = null;

        try{
            // new FileReader( new File(nomeArq)
            br = new BufferedReader(new InputStreamReader(new FileInputStream(nomeArq), StandardCharsets.ISO_8859_1));
        }catch (IOException e){
            e.printStackTrace();
        }

        return br;
    }

    public void closeArq(BufferedReader br){
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
