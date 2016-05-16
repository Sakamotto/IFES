package entidade;

public class Balada extends Evento {
    private String pico;

    public String getPico() {
        return pico;
    }

    public void setPico(String pico) {
        this.pico = pico;
    }

    public void insereContatos(Contato c[], int nc) {
        int i;
        for (i = 0; i < nc; i++) {

            if (c[i] instanceof Amigo) {
                this.insereContato(i);
            }
        }
    }

    public String getDadosEvento(){
        return "Pico: " + this.getPico() + "\nData: " + this.getData();
    }
}
