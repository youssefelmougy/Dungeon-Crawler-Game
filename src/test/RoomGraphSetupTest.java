package test;

import model.Room;
import model.RoomGraph;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RoomGraphSetupTest {
    private static final int TIMEOUT = 200;

    @Test
    public void testRoomGraphSetup0() {
        RoomGraph graph = new RoomGraph(0);
        checkNode(graph.getCurrentRoom());
    }

    @Test
    public void testExitLengthRoomGraph0() {
        /**
         *         R-R-E
         *         | |
         *     R-R-R-R
         *     |   |
         *     R-R-R
         *     | | |
         * R-R-S-R-R
         *   | |
         *   R-R
         */
        RoomGraph graph = new RoomGraph(0);

        //test shortest path lengths
        assertEquals("exit", graph.getCurrentRoom()
                .getTop().getTop().getRight().getRight().getTop().getRight().getRight().getType());
        assertEquals("exit", graph.getCurrentRoom()
                .getTop().getTop().getRight().getRight().getRight().getTop().getRight().getType());
        assertEquals("exit", graph.getCurrentRoom()
                .getTop().getRight().getRight().getTop().getTop().getRight().getRight().getType());
        assertEquals("exit", graph.getCurrentRoom()
                .getTop().getRight().getRight().getTop().getRight().getTop().getRight().getType());
        assertEquals("exit", graph.getCurrentRoom()
                .getRight().getTop().getRight().getTop().getTop().getRight().getRight().getType());
        assertEquals("exit", graph.getCurrentRoom()
                .getRight().getTop().getRight().getTop().getRight().getTop().getRight().getType());
        assertEquals("exit", graph.getCurrentRoom()
                .getRight().getRight().getTop().getTop().getTop().getRight().getRight().getType());
        assertEquals("exit", graph.getCurrentRoom()
                .getRight().getRight().getTop().getTop().getRight().getTop().getRight().getType());
    }

    @Test
    public void testRoomGraphSetup1() {
        RoomGraph graph = new RoomGraph(1);
        checkNode(graph.getCurrentRoom());
    }

    @Test
    public void testExitLengthRoomGraph1() {
        /**
         *       R-R-R
         *       |   |
         *     R-R R-R
         *     | | | |
         * E-R-R R-R-S-R-R
         * |   | | | | |
         * R-R-R-R-R R-R
         *         | |
         *         R-R
         */
        RoomGraph graph = new RoomGraph(1);

        //test shortest path lengths
        assertEquals("exit", graph.getCurrentRoom()
                .getLeft().getLeft().getTop().getLeft().getBottom().getLeft().getLeft().getType());
        assertEquals("exit", graph.getCurrentRoom()
                .getLeft().getLeft().getBottom().getLeft().getLeft().getLeft().getTop().getType());
        assertEquals("exit", graph.getCurrentRoom()
                .getLeft().getLeft().getBottom().getLeft().getTop().getLeft().getLeft().getType());
        assertEquals("exit", graph.getCurrentRoom()
                .getLeft().getBottom().getLeft().getLeft().getLeft().getLeft().getTop().getType());
        assertEquals("exit", graph.getCurrentRoom()
                .getLeft().getBottom().getLeft().getLeft().getTop().getLeft().getLeft().getType());
    }

    @Test
    public void testRoomGraphSetup2() {
        RoomGraph graph = new RoomGraph(2);
        checkNode(graph.getCurrentRoom());
    }

    @Test
    public void testExitLengthRoomGraph2() {
        /**
         *     R-R-R
         *     |   |
         * R-R-R-R R
         *     |   |
         *   R-S-R R
         *   | | | |
         *   R-R R-R
         *     | | |
         *     R-R R-R-R
         *       |     |
         *       R-R-R-E
         *
         */
        RoomGraph graph = new RoomGraph(2);

        //test shortest path lengths
        assertEquals("exit", graph.getCurrentRoom().getBottom().getBottom().getRight()
                .getBottom().getRight().getRight().getRight().getType());
        assertEquals("exit", graph.getCurrentRoom().getRight().getBottom().getBottom()
                .getBottom().getRight().getRight().getRight().getType());
        assertEquals("exit", graph.getCurrentRoom().getRight().getBottom().getRight()
                .getBottom().getRight().getRight().getBottom().getType());
    }

    @Test
    public void testRoomGraphSetup3() {
        RoomGraph graph = new RoomGraph(3);
        checkNode(graph.getCurrentRoom());
    }

    @Test
    public void testExitLengthRoomGraph3() {
        /**
         *       R   R-R
         *       |   | |
         *   R R-R-R-R R
         *   |   |   |
         * R-R-R-S-R-R-R
         *   |   | |   |
         *   R R-R R R R
         *       | | | |
         *       R-R-R-R-R
         *             |
         *             R-E
         */
        RoomGraph graph = new RoomGraph(3);

        //test shortest path lengths
        assertEquals("exit", graph.getCurrentRoom().getBottom().getBottom().getRight()
                .getRight().getRight().getBottom().getRight().getType());
        assertEquals("exit", graph.getCurrentRoom().getRight().getBottom().getBottom()
                .getRight().getRight().getBottom().getRight().getType());
        assertEquals("exit", graph.getCurrentRoom().getRight().getRight().getRight()
                .getBottom().getBottom().getBottom().getRight().getType());
    }

    @Test
    public void testRoomGraphSetup4() {
        RoomGraph graph = new RoomGraph(4);
        checkNode(graph.getCurrentRoom());
    }

    @Test
    public void testExitLengthRoomGraph4() {
        /**
         *     R-R-R-R
         *     | |   |
         *     R-S-R-R
         *     | | |
         *     R R R
         *     | | |
         *   R-R-R-R
         *   | |
         *   R R-R
         *   | | |
         * E-R-R-R
         */
        RoomGraph graph = new RoomGraph(4);

        //test shortest path lengths
        assertEquals("exit", graph.getCurrentRoom().getLeft().getBottom().getBottom()
                .getLeft().getBottom().getBottom().getLeft().getType());
        assertEquals("exit", graph.getCurrentRoom().getLeft().getBottom().getBottom()
                .getBottom().getBottom().getLeft().getLeft().getType());
        assertEquals("exit", graph.getCurrentRoom().getBottom().getBottom().getLeft()
                .getLeft().getBottom().getBottom().getLeft().getType());
        assertEquals("exit", graph.getCurrentRoom().getBottom().getBottom().getLeft()
                .getBottom().getBottom().getLeft().getLeft().getType());
    }

    private void checkNode(Room curr) {
        curr.setCheckedTrue();
        System.out.println("Room checked");
        if (curr.getTop() != null) {
            assertEquals(curr, curr.getTop().getBottom());
        }
        if (curr.getBottom() != null) {
            assertEquals(curr, curr.getBottom().getTop());
        }
        if (curr.getLeft() != null) {
            assertEquals(curr, curr.getLeft().getRight());
        }
        if (curr.getRight() != null) {
            assertEquals(curr, curr.getRight().getLeft());
        }
        if (curr.getTop() != null && !curr.getTop().getChecked()) {
            checkNode(curr.getTop());
        }
        if (curr.getBottom() != null && !curr.getBottom().getChecked()) {
            checkNode(curr.getBottom());
        }
        if (curr.getLeft() != null && !curr.getLeft().getChecked()) {
            checkNode(curr.getLeft());
        }
        if (curr.getRight() != null && !curr.getRight().getChecked()) {
            checkNode(curr.getRight());
        }
    }
}
