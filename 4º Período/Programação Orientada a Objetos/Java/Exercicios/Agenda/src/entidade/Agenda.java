package entidade;

public class Agenda {
    public static final int maxc = 100;
    public static final int maxe = 20;
    private Evento[] eventos;
    private int neventos;
    private Contato[] contatos;
    private int ncontatos;

    public Agenda() {
        setEventos(new Evento[maxe]);
        setNeventos(0);
        setContatos(new Contato[maxc]);
        setNcontatos(0);
    }

//    public void criarEvento(Evento e) {
//        int op;
//        System.out.print("Almoço (1), Reunião (2) ou Balada (3)? ");
//        op = system.InputHandler.readInt();
//        if (op == 1) {
//            getEventos()[neventos] = new Almoco();
//        } else if (op == 2) {
//            getEventos()[neventos] = new Reuniao();
//        } else if (op == 3) {
//            getEventos()[neventos] = new Balada();
//        }
//        getEventos()[neventos].insereContatos(this.getContatos());
//        neventos++;
//    }

    public void criarEvento(Evento e) {

        eventos[getNeventos()] = e;
        eventos[getNeventos()].insereContatos(this.getContatos(), 100);
        setNeventos(getNeventos() + 1);
    }

    public void criaContato(Contato c) {
        if (getNcontatos() < maxc) {
            contatos[getNcontatos()] = c;
            this.setNcontatos(this.getNcontatos() + 1);
        }
    }

    public Contato[] getContatos() {
        return contatos;
    }

    public void setContatos(Contato[] contatos) {
        this.contatos = contatos;
    }

    public Evento[] getEventos() {
        return eventos;
    }

    public void setEventos(Evento[] eventos) {
        this.eventos = eventos;
    }

    public int getNeventos() {
        return neventos;
    }

    public void setNeventos(int neventos) {
        this.neventos = neventos;
    }

    public int getNcontatos() {
        return ncontatos;
    }

    public void setNcontatos(int ncontatos) {
        this.ncontatos = ncontatos;
    }
}
