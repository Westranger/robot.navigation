package de.westranger.robot.navigation.graph.astar;

import de.westranger.geometry.common.simple.Point2D;

public final class EuclideanHeuristic implements AStarHeuristic<Point2D> {

    @Override
    public double distance(final Point2D a, final Point2D b) {
        return Math.sqrt((a.getX() - b.getX()) * (a.getX() - b.getX()) + (a.getY() - b.getY()) * (a.getY() - b.getY()));
    }
}
