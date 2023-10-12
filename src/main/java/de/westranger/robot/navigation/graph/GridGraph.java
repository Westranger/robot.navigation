package de.westranger.robot.navigation.graph;

import de.westranger.geometry.common.simple.Point2D;

import java.util.Optional;

public final class GridGraph extends Graph<Point2D, Double> {

    public GridGraph(final int width, final int height) {
        super();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                final Point2D pt = new Point2D(x, y);
                final int id = getID(width, height, x, y).get();
                this.addVertex(id, pt);

                final Optional<Integer> idRight = getID(width, height, x + 1, y);
                final Optional<Integer> idDown = getID(width, height, x, y - 1);

                if (idRight.isPresent()) {
                    this.addEdge(id, idRight.get(), 1.0);
                    this.addEdge(idRight.get(), id, 1.0);
                }

                if (idDown.isPresent()) {
                    this.addEdge(id, idDown.get(), 1.0);
                    this.addEdge(idDown.get(), id, 1.0);
                }

                // TODO diagonalen einfügen
            }
        }
    }

    private Optional<Integer> getID(final int width, final int height, final int posX, final int posY) {
        if (0 <= posX && posX < width && 0 <= posY && posY < height) {
            return Optional.of(height * posX + posY);
        }
        return Optional.empty();
    }
}
