package de.westranger.robot.navigation.graph.astar;

import de.westranger.geometry.common.simple.Point2D;

public final class ManhattanHeuristic implements AStarHeuristic<Point2D> {
    @Override
    public double distance(final Point2D a, final Point2D b) {
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    }
}
