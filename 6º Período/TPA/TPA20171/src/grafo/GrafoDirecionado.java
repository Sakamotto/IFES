package grafo;

import hashmelhorado.TADTabH;
import matrizmelhorada.TADMatH;

import java.util.List;

/**
 * Created by cristian on 29/04/17.
 */
public class GrafoDirecionado extends Grafo{

    TADMatH conteudo;
    TADTabH tabVertices;
    TADTabH tabArestas;

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
        return null;
    }
}
