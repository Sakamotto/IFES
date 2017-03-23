package tadchaininghash;

/**
 * Created by cristian on 22/03/17.
 */

public class Dado {

    private String nome;
    private String telefone;

    public Dado(){}

    public Dado(String nome, String telefone){
        this.nome = nome;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}
