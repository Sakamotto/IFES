package grafo;

import java.util.List;

/**
 * Created by cristian on 29/04/17.
 */
public abstract class Grafo {

    public int qtdVertices;
    public int qtdArestas;

    public int numVertices(){
        return this.qtdVertices;
    }
    public int numArestas(){
        return this.qtdArestas;
    }

    public abstract List<Aresta> arestas();
    public abstract List<Vertice> vertices();
    public abstract Vertice aVertice();
    public abstract int grau(Vertice v);
    public abstract List<Vertice> adjacentes(Vertice v);
    public abstract List<Aresta> incidentEdges(Vertice v);
    public abstract List<Vertice> endVertices(Aresta a);
    public abstract Vertice opposite(Vertice v, Aresta a);
    public abstract boolean areAdjacent(Vertice v, Vertice w);
    public abstract Aresta insereAresta(String labelAresta, String v, String w, Object o);
    public abstract Vertice insereVertice(String label, Object o);

}
