package fronteira;

import system.*;

import java.io.*;
import java.beans.*;

public class MenuPrincipal implements Serializable, PropertyChangeListener {
    private String[] Options = new String[]{"Criar Contato", "Criar Evento",
            "Mostrar Convidados do Evento",
            "Sair"};

    private int option;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public void setOption(int option) {
        int old = this.option;
        this.option = option;
        this.pcs.firePropertyChange("option", old, option);
    }

    public int getOption() {
        return option;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }

    public MenuPrincipal() {
    }

    void displayOptions() {
        int i;
        for (i = 0; i < Options.length; i++)
            System.out.println((i + 1) + " - " + Options[i].toString());

    }

    public int run() {
        int op = 0;
        while (op != Options.length) {
            displayOptions();
            op = InputHandler.readInt();
            if ((op < 1) || (op > Options.length)) {
                System.out.println("**** Opçãoo Inválida ****");
            } else {
                this.setOption(op);
            }
        }
        return this.option;
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
    }

    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
    }

    public void propertyChange(PropertyChangeEvent e) {
    }
}
