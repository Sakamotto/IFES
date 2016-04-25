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
        neventos = 0;
        setContatos(new Contato[maxc]);
        ncontatos = 0;
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
//        int op;
//        System.out.print("Almoço (1), Reunião (2) ou Balada (3)? ");
//        op = system.InputHandler.readInt();

        eventos[neventos] = e;
        if(e instanceof Balada){
            ((Balada)e).insereContatos(this.getContatos(), maxc);
        }else if(e instanceof Reuniao){
            ((Reuniao)e).insereContatos(this.getContatos(), maxc);
        }else if(e instanceof Almoco){
            ((Almoco)e).insereContatos(this.getContatos(), maxc);
        }
        neventos++;


    }

    public void criaContato(Contato c) {
        // Adiciona contatos no vetor
        if (ncontatos < maxc) {
            getContatos()[ncontatos] = c;
        }
        this.ncontatos++;
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
}
