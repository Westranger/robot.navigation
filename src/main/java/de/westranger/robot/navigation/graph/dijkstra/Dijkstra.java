package de.westranger.robot.navigation.graph.dijkstra;

import de.westranger.robot.navigation.graph.Graph;

public class Dijkstra<T extends Comparable<T>, G extends Comparable<G>> {

    protected final Graph<T, G> graph;

    public Dijkstra(final Graph<T, G> graph) {
        try {
            this.graph = graph.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

}
