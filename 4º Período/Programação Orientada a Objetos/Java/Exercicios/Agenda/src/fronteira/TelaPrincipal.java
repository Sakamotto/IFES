package fronteira;

import java.beans.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.prefs.Preferences;

import entidade.*;

import system.*;

public class TelaPrincipal implements PropertyChangeListener {
    MenuPrincipal menu;
    Agenda agenda;

    public TelaPrincipal() {
        menu = new MenuPrincipal();
        menu.addPropertyChangeListener(this);
    }

    public static void main(String[] args) {
        TelaPrincipal telaPrincipal = new TelaPrincipal();
        int option;
        option = telaPrincipal.menu.run();
    }

    public void criarContato() {
        int optContato, secondOption;
        String nome, nivel = "", parentesco;
        agenda = new Agenda();
        Contato c;
        Amigo amigo;
        Colega colega;
        Familia familia;

        System.out.println("\nEscolha o tipo de contado:");
        System.out.println("1 - Amigo");
        System.out.println("2 - Colega");
        System.out.println("3 - Família");
        optContato = system.InputHandler.readInt();

        if (optContato == 1) {
            System.out.println("Nome: ");
            nome = system.InputHandler.readString();
            System.out.println("Nível: ");
            nivel = system.InputHandler.readString();

            System.out.println("\nSegundo tipo:");
            System.out.println("1 - Colega");
            System.out.println("2 - Família");
            System.out.println("3 - Nenhum");
            secondOption = system.InputHandler.readInt();

            if (secondOption != 3) {
                if (secondOption == 1) {
                    colega = new Colega();
                    colega.setNome(nome);
                    c = new Contato(colega);
                    agenda.criaContato(c);
                } else if (secondOption == 2) {
                    familia = new Familia();
                    familia.setNome(nome);
                    parentesco = system.InputHandler.readString();
                    familia.setParentesco(parentesco);
                    c = new Contato(familia);
                    agenda.criaContato(c);
                } else {
                    amigo = new Amigo();
                    amigo.setNome(nome);
                    amigo.setNivel(nivel);
                    agenda.criaContato(amigo);
                }
            }

        } else if (optContato == 2) {

            System.out.println("Nome: ");
            nome = system.InputHandler.readString();

            System.out.println("\nSegundo tipo:");
            System.out.println("1 - Amigo");
            System.out.println("2 - Família");
            System.out.println("3 - Nenhum");
            secondOption = system.InputHandler.readInt();

            if (secondOption != 3) {
                if (secondOption == 1) {
                    amigo = new Amigo();
                    amigo.setNome(nome);
                    System.out.println("Nível: ");
                    nivel = system.InputHandler.readString();
                    amigo.setNivel(nivel);
                    c = new Contato(amigo);
                    agenda.criaContato(c);
                } else if (secondOption == 2) {
                    familia = new Familia();
                    familia.setNome(nome);
                    parentesco = system.InputHandler.readString();
                    familia.setParentesco(parentesco);
                    c = new Contato(familia);
                    agenda.criaContato(c);
                } else {
                    colega = new Colega();
                    colega.setNome(nome);
                    agenda.criaContato(colega);
                }
            }


        } else if (optContato == 3) {
            System.out.println("Nome: ");
            nome = system.InputHandler.readString();
            System.out.println("Parentesco: ");
            parentesco = system.InputHandler.readString();

            System.out.println("\nSegundo tipo:");
            System.out.println("1 - Colega");
            System.out.println("2 - Amigo");
            System.out.println("3 - Nenhum");
            secondOption = system.InputHandler.readInt();

            if (secondOption != 3) {
                if (secondOption == 1) {
                    colega = new Colega();
                    colega.setNome(nome);
                    c = new Contato(colega);
                    agenda.criaContato(c);
                } else if (secondOption == 2) {
                    amigo = new Amigo();
                    amigo.setNome(nome);
                    System.out.println("Nível: ");
                    nivel = system.InputHandler.readString();
                    amigo.setNivel(nivel);
                    c = new Contato(amigo);
                    agenda.criaContato(c);
                } else {
                    familia = new Familia();
                    familia.setNome(nome);
                    familia.setParentesco(parentesco);
                    agenda.criaContato(familia);
                }
            }
        } else {
            System.out.println("**** Opção Inválida ****");
        }


    }

    public void criarEvento() {
        int opcaoEvento;
        Evento e = new Evento();
        Calendar date;

        System.out.println("\nEscolha o tipo de Evento:");
        System.out.println("1 - Balada"); // Balada
        System.out.println("2 - Reunião"); // Reunião
        System.out.println("3 - Almoço"); // Almoço
        opcaoEvento = system.InputHandler.readInt();

        System.out.println("Data: ");
        date = system.InputHandler.readDate();

        if (opcaoEvento == 1) {
            Balada balada = new Balada();
            balada.insereContatos(agenda.getContatos(), 100);
            //Ler Pico
            balada.setPico("O que é Pico?");
            balada.setData(date.getTime());
            agenda.criarEvento(balada);

        } else if (opcaoEvento == 2) {
            System.out.println("Sala: ");
            String sala = system.InputHandler.readString();

            Reuniao reuniao = new Reuniao();
            reuniao.insereContatos(agenda.getContatos(), 100);
            reuniao.setData(date.getTime());
            reuniao.setSala(sala);
            agenda.criarEvento(reuniao);
        } else if (opcaoEvento == 3) {
            System.out.println("Restaurante: ");
            String restaurante = system.InputHandler.readString();

            Almoco almoco = new Almoco();
            almoco.insereContatos(agenda.getContatos(), 100);
            almoco.setData(date.getTime());
            almoco.setRestaurante(restaurante);
            agenda.criarEvento(almoco);
        } else {
            System.out.println("**** Opção Inválida ****");
        }
    }

    public void mostrarConvidados(){
        int opt;
        Evento eventos[] = agenda.getEventos();

        System.out.println("\n --- Escolha o evento que deseja mostrar os contatos --- ");
        System.out.println("1 - Balada");
        System.out.println("2 - Reunião");
        System.out.println("3 - Almoço");
        opt = system.InputHandler.readInt();

        if(opt == 1){
            for(int i = 0; i < eventos.length; i++){
                if(eventos[i] instanceof Balada){
                    System.out.println(eventos[i].getContatos()[0]);
                }
            }
        }


    }

    public void propertyChange(PropertyChangeEvent evt) {
        int option;
        option = menu.getOption();
        menu.setOption(0);
        switch (option) {
        /*
         * private String[] Options = new String[] {"Selecionar Restaurante" ,
		 * "Fazer Pedido", "Sair" };
		 */
            case 1:
                this.criarContato(); // CHAMA O MÉTODO DE CRIAÇÃO DE CONTATOS

                break;
            case 2:
                //System.out.println("Criar Evento");
                this.criarEvento();
                break;
            case 3:
                System.out.println("Mostrar Convidados de um Evento");
                this.mostrarConvidados();

                break;
        }
    }

}
