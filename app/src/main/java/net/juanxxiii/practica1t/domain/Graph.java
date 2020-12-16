package net.juanxxiii.practica1t.domain;

import java.io.Serializable;
import java.util.Objects;

public class Graph implements Serializable {

    private static final long serialVersionUID = 6529685098267757690L;
    public String title;
    public MyLocation location;

    @Override
    public String toString() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Graph graph = (Graph) o;
        return Objects.equals(title, graph.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
