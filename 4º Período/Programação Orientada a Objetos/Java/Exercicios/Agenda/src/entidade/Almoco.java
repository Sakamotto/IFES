package entidade;

public class Almoco extends Evento {
    private String Restaurante;

    public String getRestaurante() {
        return Restaurante;
    }

    public void setRestaurante(String restaurante) {
        Restaurante = restaurante;
    }

    public void insereContatos(Contato c[], int nc) {
        int i;
        for (i = 0; i < nc; i++) {
            if (c[i] instanceof Familia) {
                this.insereContato(i);
            }
        }
    }

    public String getDadosEvento(){
        return "Restaurante: " + this.getRestaurante() + "\nData: " + this.getData();
    }


}
