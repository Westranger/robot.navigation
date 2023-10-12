package de.westranger.robot.navigation.graph.astar;

public interface AStarHeuristic<T> {
    double distance(final T a, final T b);
}
