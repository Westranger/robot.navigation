package test.de.westranger.robot.navigation.graph.astar;

import de.westranger.robot.navigation.graph.Edge;
import de.westranger.robot.navigation.graph.GridGraph;
import de.westranger.robot.navigation.graph.astar.AStar;
import de.westranger.robot.navigation.graph.astar.EuclideanHeuristic;
import de.westranger.geometry.common.simple.Point2D;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AStarTest {


    @Test
    void findShortestPath() {
        final GridGraph graph = new GridGraph(5, 5);
        assertNotNull(graph.removeVertex(12));
        List<Edge<Double>> edges = graph.removeEdgesContaining(12);
        assertEquals(8, edges.size());

        assertNotNull(graph.removeVertex(13));
        edges = graph.removeEdgesContaining(13);
        assertEquals(6, edges.size());

        assertNotNull(graph.removeVertex(14));
        edges = graph.removeEdgesContaining(14);
        assertEquals(4, edges.size());

        final AStar<Point2D, Double> astar = new AStar<>(graph, new EuclideanHeuristic());

        final List<Integer> computedPath = astar.findShortestPath(4, 24);
        final List<Integer> expectedPath = new ArrayList<>(List.of(4, 9, 8, 7, 6, 11, 16, 17, 18, 19, 24));
        assertIterableEquals(expectedPath, computedPath);
    }
}