package de.westranger.robot.navigation;

import de.westranger.geometry.common.math.Rotation2D;
import de.westranger.geometry.common.math.Vector2D;

public final class Pose {

    private Vector2D pos;
    private final Rotation2D theta;

    public Pose(final double x, final double y, final double theta) {
        this.pos = new Vector2D(x, y);
        this.theta = new Rotation2D(theta);
    }

    public void add(final Vector2D vec) {
        this.pos = this.pos.add(vec);
    }

    public Vector2D getPos() {
        return pos;
    }

    public Rotation2D getTheta() {
        return theta;
    }
}
