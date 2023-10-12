package test.de.westranger.robot.navigation.graph;

import de.westranger.robot.navigation.graph.Edge;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class EdgeTest {

    // TODO Testcase schreiben bei dem eins der beiden 'Daten' == null ist

    @Test
    void testEquals() {
        // TODO hier ist es tatsächlich gut, wenn eine Exception geworfen wird, ich sehe für meinen code gerade keinen Anwendungsfall in dem der Datentyp der Edges für einen Graphen unterschiedlich sein sollte
        Edge<Integer> abc = new Edge<>(1, 2, 3);
        Edge<String> xyz = new Edge<>(1, 2, "abc");

        assertFalse(abc.equals(xyz));
    }

    /*
    @Test
    void testHashCode() {
        fail("NYI");
    }

    @Test
    void testClone() {
        fail("NYI");
    }

    @Test
    void testToString() {
        fail("NYI");
    }
    */

}
