package tadchaininghash;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by 20142bsi0054 on 23/03/2017.
 */

public class Main {

    public static void main(String[] args){

        TabHash tabHash = new TabHash(10);
        Dado d = new Dado();

        d.setNome("Cristian");
        d.setTelefone("992832400");

        tabHash.add("992832400", d);

        System.out.println(tabHash.getElement("992832400").getNome());

        tabHash.add("992832400", new Dado("CSA","992832400"));

        LinkedList<ItemTabHash<String, Dado>> lista = tabHash.get("992832400");

        for(ItemTabHash i: lista){
            System.out.println("Chave: " + i.getKey());
            System.out.println("Valor: " + ((Dado)i.getDado()).getNome());
        }

        System.out.println("*******");
        System.out.println(tabHash.remove("992832400"));

        for(ItemTabHash i: lista){
            System.out.println("Chave: " + i.getKey());
            System.out.println("Valor: " + ((Dado)i.getDado()).getNome());
        }

        HashMap<String, Dado> mapa = new HashMap<>();
        mapa.put("888", new Dado("Cristian", "888"));
        System.out.println(mapa.get("888").getNome());
    }
}
