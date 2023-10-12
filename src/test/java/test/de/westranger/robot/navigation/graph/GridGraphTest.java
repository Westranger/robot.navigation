package test.de.westranger.robot.navigation.graph;

import de.westranger.robot.navigation.graph.Edge;
import de.westranger.robot.navigation.graph.GridGraph;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GridGraphTest {

    @Test
    void constructor() {
        final GridGraph graph = new GridGraph(3, 3);

        assertEquals(9, graph.getVertexList().size());
        assertEquals(24, graph.getEdgeList().size());

        for (int i = 0; i < 9; i++) {
            assertNotNull(graph.getVertex(i));
        }
        assertNull(graph.getVertex(666));

        final Map<Integer, Set<Integer>> map = new HashMap<>();
        map.put(0, new HashSet<>(Arrays.asList(1, 3)));
        map.put(1, new HashSet<>(Arrays.asList(0, 2, 4)));
        map.put(2, new HashSet<>(Arrays.asList(1, 5)));
        map.put(3, new HashSet<>(Arrays.asList(0, 4, 6)));
        map.put(4, new HashSet<>(Arrays.asList(1, 3, 5, 7)));
        map.put(5, new HashSet<>(Arrays.asList(2, 4, 8)));
        map.put(6, new HashSet<>(Arrays.asList(3, 7)));
        map.put(7, new HashSet<>(Arrays.asList(4, 6, 8)));
        map.put(8, new HashSet<>(Arrays.asList(5, 7)));

        for (Edge<Double> edge : graph.getEdgeList()) {
            final Set<Integer> set = map.get(edge.getFrom());
            assertTrue(set.remove(edge.getTo()));

            if(set.isEmpty()){
                assertNotNull(map.remove(edge.getFrom()));
            }
        }
        assertTrue(map.isEmpty());
    }
}