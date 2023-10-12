package de.westranger.robot.navigation.graph;

import java.util.Objects;

public final class Edge<G extends Comparable<G>> implements Cloneable {
    private int from;
    private int to;
    private G data;

    public Edge() {
        this(-1, -1, null);
    }

    public Edge(final int from, final int to, final G data) {
        super();
        this.from = from;
        this.to = to;
        this.data = data;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(final int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(final int to) {
        this.to = to;
    }

    public G getData() {
        return data;
    }

    public void setData(final G data) {
        this.data = data;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (this.getData() == null && ((Edge<G>) o).getData() != this.getData()) {
            return false;
        }

        if (this.getData().getClass() != ((Edge<?>) o).getData().getClass()) {
            return false;
        }
        final Edge<G> edge = (Edge<G>) o; // TODO testcase mit unterschiedlichen generic types
        return this.from == edge.getFrom() && this.to == edge.getTo() && this.data.compareTo(edge.getData()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.from, this.to, this.data);
    }

    @Override
    public Edge<G> clone() throws CloneNotSupportedException {
        super.clone();
        return new Edge<>(this.from, this.to, this.data);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Edge(");
        sb.append(this.from);
        sb.append(", ");
        sb.append(this.to);
        sb.append(')');
        return sb.toString();
    }
}
