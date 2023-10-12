package de.westranger.robot.navigation.graph;

import java.util.Objects;

public final class Vertex<T extends Comparable> implements Cloneable {
    private int id;
    private T data;

    public Vertex() {
        this(-1, null);
    }

    public Vertex(final int id, final T data) {
        super();
        this.id = id;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public T getData() {
        return data;
    }

    public void setData(final T data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Vertex<T> vertex = (Vertex<T>) o;
        return id == vertex.id && this.data.compareTo(vertex.getData()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public Vertex<T> clone() throws CloneNotSupportedException {
        super.clone();
        return new Vertex<>(this.id, this.data);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vertex(id=");
        sb.append(this.id);
        sb.append(", ");
        sb.append(this.data);
        sb.append(')');
        return sb.toString();
    }
}
