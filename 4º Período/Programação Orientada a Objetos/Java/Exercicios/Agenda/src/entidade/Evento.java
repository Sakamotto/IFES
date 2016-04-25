package entidade;

import java.util.Date;


public class Evento {
    private Date data;
    private int[] contatos = new int[100];
    private int nc = 0;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void insereContato(int c) {
        getContatos()[nc] = c;
        nc++;

    }

    // NÃO SERVE PRA NADA?!
    public void insereContatos(Contato c[], int nc) {

    }

    public int[] getContatos() {
        return contatos;
    }

    public void setContatos(int[] contatos) {
        this.contatos = contatos;
    }
}
