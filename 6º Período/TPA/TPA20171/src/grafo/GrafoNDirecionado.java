package grafo;

import hashmelhorado.TADTabH;
import hashmelhorado.TabHEA;
import matrizmelhorada.TADMatH;
import matrizmelhorada.TADMatHChain;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by cristian on 29/04/17.
 */
public class GrafoNDirecionado extends Grafo {

    private TADMatH conteudo;
    private TADTabH tabVertices;
    private TADTabH tabArestas;
    private int id = 0;

    public GrafoNDirecionado(){
        conteudo = new TADMatHChain(10,10);
        tabArestas = new TabHEA();
        tabVertices = new TabHEA();
    }

    private int generateID(){
        return this.id++;
    }

    private Vertice createVertice(String label, Object o){
        return new Vertice(generateID(), o, label);
    }
    private Aresta createAresta(String label, Object o){
        return new Aresta(this.generateID(), o, label);
    }

    @Override
    public List<Aresta> arestas() {
        LinkedList<Aresta> result = new LinkedList<>();

        for(Object o: tabArestas.keys()){
            result.add((Aresta)o);
        }

        return result;
    }

    @Override
    public List<Vertice> vertices() {
        LinkedList<Vertice> result = new LinkedList<>();

        for(Object o: tabArestas.keys()){
            result.add((Vertice)o);
        }

        return result;
    }

    @Override
    public Vertice aVertice() {
        return null;
    }

    @Override
    public int grau(Vertice v) {
        return 0;
    }

    @Override
    public List<Vertice> adjacentes(Vertice v) {
        return null;
    }

    @Override
    public List<Aresta> incidentEdges(Vertice v) {
        return null;
    }

    @Override
    public List<Vertice> endVertices(Aresta a) {
        return null;
    }

    @Override
    public Vertice opposite(Vertice v, Aresta a) {
        return null;
    }

    @Override
    public boolean areAdjacent(Vertice v, Vertice w) {
        return false;
    }

    @Override
    public Aresta insereAresta(String labelAresta, String v, String w, Object o) {
        Aresta a = createAresta(labelAresta, o);
        tabArestas.insertItem(labelAresta, a);
        int idV = ((Vertice)this.tabVertices.findElem(v)).getId();
        int idW = ((Vertice)this.tabVertices.findElem(w)).getId();

        conteudo.setElem(idV, idW, a.getId());

        return a;
    }

    @Override
    public Vertice insereVertice(String label, Object o) {
        Vertice v = createVertice(label, o);
        tabVertices.insertItem(label, v);
        return v;
    }

    public static void main(String args[]){
        GrafoNDirecionado g = new GrafoNDirecionado();
//        System.out.println(g.insereVertice(100).getDado());
    }
}
