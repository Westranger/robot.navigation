package de.westranger.robot.navigation.graph.astar;

import de.westranger.robot.navigation.graph.Edge;
import de.westranger.robot.navigation.graph.Graph;

import java.io.Serializable;
import java.util.*;

public final class AStar<T extends Comparable<T>, G extends Comparable<G>> {

    private static final class CostVertexTuple {
        public double cost;
        public int vtx;

        public CostVertexTuple(final int vtx, final double cost) {
            this.vtx = vtx;
            this.cost = cost;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append('(');
            sb.append(this.vtx);
            sb.append(", ");
            sb.append(this.cost);
            sb.append(')');
            return sb.toString();
        }
    }

    private static final class CostVertexTupleComparator implements Comparator<CostVertexTuple>, Serializable {
        @Override
        public int compare(final CostVertexTuple o1, final CostVertexTuple o2) {
            return (int) ((o1.cost - o2.cost) * 1e6);
        }
    }

    private final Graph<T, G> graph;
    private final AStarHeuristic<T> heuristic;
    private final PriorityQueue<CostVertexTuple> openList;
    private final TreeMap<Integer, Double> costList;
    private final TreeMap<Integer, Integer> pathList;
    private final Set<Integer> closelist;

    public AStar(final Graph<T, G> graph, final AStarHeuristic<T> heuristic) {
        this.heuristic = heuristic;
        this.openList = new PriorityQueue<>(new CostVertexTupleComparator());
        this.costList = new TreeMap<>();
        this.pathList = new TreeMap<>();
        this.closelist = new TreeSet<>();

        try {
            this.graph = graph.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    // TODO was tun wenn start und Ziel Gleich sind ? ggf return type zu Optional ändern
    public List<Integer> findShortestPath(final int startVtx, final int endVtx) {
        final List<Integer> path = new ArrayList<>();

        this.openList.offer(new CostVertexTuple(startVtx, 0.0));
        this.costList.put(startVtx, 0.0);

        do {
            final int currentNode = this.openList.poll().vtx;

            if (currentNode == endVtx) {
                int vtx = endVtx;
                do {
                    path.add(vtx);
                    vtx = this.pathList.get(vtx);
                } while (vtx != startVtx);
                path.add(vtx);

                this.cleanUp();
                Collections.reverse(path);
                return Collections.unmodifiableList(path);
            }

            this.closelist.add(currentNode);
            this.expand(currentNode, endVtx);
        } while (!this.openList.isEmpty());

        this.cleanUp();
        return Collections.emptyList();
    }

    // https://de.wikipedia.org/wiki/A*-Algorithmus
    private void expand(final int currentNode, final int endVtx) {
        //System.out.println("expanding " + currentNode + " (end=" + endVtx + ")");
        for (Edge<G> edge : this.graph.getEdgeList()) {
            if (edge.getFrom() == currentNode) {
                if (this.closelist.contains(edge.getTo())) {
                    continue;
                }
                final T dataFrom = this.graph.getVertex(currentNode).getData();
                final T dataTo = this.graph.getVertex(edge.getTo()).getData();
                final double tentativeG = this.costList.get(currentNode) + this.heuristic.distance(dataFrom, dataTo);

                //System.out.println("  to " + edge.getTo() + " cost=" + tentativeG + "(" + this.costlist.get(currentNode) + "+" + this.heuristic.distance(dataFrom, dataTo) + ") from:" + this.graph.getVertex(currentNode) + " to:" + this.graph.getVertex(edge.getTo()));
                /*
                if (!this.costlist.containsKey(edge.getTo())) {
                    this.costlist.put(edge.getTo(), tentativeG);
                }
                */

                //System.out.println("       =>" + tentativeG + " " + this.costlist.get(edge.getTo()) + " exp: " + openListContains(edge.getTo()) + " " + (this.costlist.get(edge.getTo()) == null ? "null" : (tentativeG >= this.costlist.get(edge.getTo()).doubleValue())));
                if (openListContains(edge.getTo()) && tentativeG >= this.costList.get(edge.getTo())) {
                    continue;
                }

                this.pathList.put(edge.getTo(), currentNode);
                this.costList.put(edge.getTo(), tentativeG);

                final double f = tentativeG + this.heuristic.distance(this.graph.getVertex(edge.getTo()).getData(), this.graph.getVertex(endVtx).getData());
                this.openList.offer(new CostVertexTuple(edge.getTo(), f));

                //final T dataA = this.graph.getVertex(edge.getTo()).getData();
                //final T dataB = this.graph.getVertex(endVtx).getData();
                //System.out.println("       -> adding " + edge.getTo() + " total_cost=" + f + " heuristic=" + this.heuristic.distance(dataA, dataB) + " A=" + dataA + " B=" + dataB);
            }
        }
    }

    private boolean openListContains(final int vtx) {
        for (CostVertexTuple cvt : this.openList) {
            if (cvt.vtx == vtx) {
                return true;
            }
        }
        return false;
    }

    private void cleanUp() {
        this.openList.clear();
        this.costList.clear();
        this.pathList.clear();
        this.closelist.clear();
    }
}
