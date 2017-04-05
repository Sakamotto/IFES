package tadchaininghash;

/**
 * Created by cristian on 22/03/17.
 */

public class Dado {

    private String nome;

    public Dado(){}

    public Dado(String nome){
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
