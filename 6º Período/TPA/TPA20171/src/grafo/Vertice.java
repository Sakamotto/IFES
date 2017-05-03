package grafo;

/**
 * Created by cristian on 29/04/17.
 */
public class Vertice {

    private Object dado;
    private int id;
    private String label;

    public Vertice(int id, Object dado){
        this.id = id;
        this.dado = dado;
        this.label = Integer.toString(id);
    }

    public Object getDado() {
        return dado;
    }

    public void setDado(Object dado) {
        this.dado = dado;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }


}
