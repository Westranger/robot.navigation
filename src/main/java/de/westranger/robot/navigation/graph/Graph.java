package de.westranger.robot.navigation.graph;

import java.util.*;

public class Graph<T extends Comparable<T>, G extends Comparable<G>> implements Cloneable {
    private final Set<Vertex<T>> setVertex;
    private final List<Edge<G>> listEdge;

    // TODO Visualiserungsklasse bauen, die eine Graph vom type Graph<Point2D, Double> darstellen kann, und die auch einen Pfad in form von List<Integer> darstellen kann
    public Graph() {
        this.setVertex = new HashSet<>();
        this.listEdge = new ArrayList<>();
    }

    public Vertex<T> addVertex(final int id, final T data) {
        final Vertex<T> vtx = new Vertex<>(id, data);
        setVertex.add(vtx);
        return vtx;
    }

    public Vertex<T> getVertex(final int id) {
        for (Vertex<T> vtx : this.setVertex) {
            if (vtx.getId() == id) {
                return vtx;
            }
        }
        return null;
    }

    public Vertex<T> removeVertex(final int id) {
        Vertex<T> removed = null;
        final Iterator<Vertex<T>> it = this.setVertex.iterator();
        while (it.hasNext()) {
            final Vertex<T> tmp = it.next();
            if (tmp.getId() == id) {
                removed = tmp;
                it.remove();
                break;
            }
        }

        return removed;
    }

    // TODO ggf entfernen und nur noch die Variante dieser methode behalten die ein SET bekommt
    public List<Edge<G>> removeAllEdgesWithVertex(final int id) {
        final List<Edge<G>> removed = new LinkedList<>();

        final Iterator<Edge<G>> it = this.listEdge.iterator();
        while (it.hasNext()) {
            final Edge<G> tmp = it.next();
            if (tmp.getFrom() == id || tmp.getTo() == id) {
                removed.add(tmp);
                it.remove();
            }
        }

        return removed;
    }

    // TODO NYI
    public List<Edge<G>> removeAllEdgesWithVertex(final Set<Integer> ids) {
        return Collections.emptyList();
    }

    public Edge<G> addEdge(final int from, final int to, final G data) {
        final Edge<G> edge = new Edge(from, to, data);
        this.listEdge.add(edge);
        return edge;
    }

    public Edge<G> removeEdge(final int from, final int to) {
        Edge<G> removed = null;
        for (int i = listEdge.size() - 1; i >= 0; i--) {
            final Edge<G> edge = listEdge.get(i);
            if (edge.getFrom() == from && edge.getTo() == to) {
                this.listEdge.remove(edge);
                removed = edge;
                break;
            }
        }
        return removed;
    }

    public List<Edge<G>> removeEdgesContaining(final int id) {
        List<Edge<G>> removed = new ArrayList<>();
        for (int i = listEdge.size() - 1; i >= 0; i--) {
            final Edge<G> edge = listEdge.get(i);
            if (edge.getFrom() == id || edge.getTo() == id) {
                this.listEdge.remove(edge);
                removed.add(edge);
            }
        }
        return Collections.unmodifiableList(removed);
    }

    public void clear() {
        this.setVertex.clear();
        this.listEdge.clear();
    }

    public Set<Vertex<T>> getVertexList() {
        return Collections.unmodifiableSet(this.setVertex);
    }

    public List<Edge<G>> getEdgeList() {
        return Collections.unmodifiableList(this.listEdge);
    }

    public NavigableMap<Integer, Set<Integer>> getSearchableEdgeMap() {
        return Collections.unmodifiableNavigableMap(new TreeMap<>());
    }

    @Override
    public Graph<T, G> clone() throws CloneNotSupportedException {
        super.clone();

        final Graph<T, G> graph = new Graph();
        for (Vertex<T> vtx : this.setVertex) {
            graph.addVertex(vtx.getId(), vtx.getData());
        }

        for (Edge<G> edge : this.listEdge) {
            graph.addEdge(edge.getFrom(), edge.getTo(), edge.getData());
        }

        return graph;
    }
}
