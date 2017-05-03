package grafo;

import hashmelhorado.TADTabH;
import hashmelhorado.TabHEA;
import matrizmelhorada.TADMatH;
import matrizmelhorada.TADMatHChain;

import java.util.List;

/**
 * Created by cristian on 29/04/17.
 */
public class GrafoNDirecionado extends Grafo {

    private TADMatH conteudo = new TADMatHChain(10,10);
    private TADTabH tabVertices;
    private TADTabH tabArestas;
    private int id = 0;

    public GrafoNDirecionado(){
        tabArestas = new TabHEA();
        tabVertices = new TabHEA();
    }

    private int generateID(){
        return this.id++;
    }

    private Vertice createVertice(Object o){
        return new Vertice(generateID(), o);
    }

    @Override
    public List<Aresta> arestas() {
        return null;
    }

    @Override
    public List<Vertice> vertices() {
        return null;
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
    public Aresta insereAresta(Vertice v, Vertice w, Object o) {
        return null;
    }

    @Override
    public Vertice insereVertice(Object o) {
        Vertice v = createVertice(o);
        tabVertices.insertItem(v.getId(), v.getDado());
        conteudo.setElem(v.getId(), v.getId(), 0);
        return v;
    }

    public static void main(String args[]){
        GrafoNDirecionado g = new GrafoNDirecionado();
        System.out.println(g.insereVertice(100).getDado());
    }
}
