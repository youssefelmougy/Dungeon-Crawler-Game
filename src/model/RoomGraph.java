package model;


public class RoomGraph {
    private final Room startingRoom;
    private Room currentRoom;

    public RoomGraph() {
        this((int) Math.floor(Math.random() * 5));
    }

    public RoomGraph(int random) {
        if (random < 0 || random > 4) {
            throw new java.lang.IllegalArgumentException("seed number should be between 0 and 4");
        }
        this.startingRoom = new Room("starting");
        this.currentRoom = startingRoom;
        if (random == 0) {
            initializeRooms0();
        } else if (random == 1) {
            initializeRooms1();
        } else if (random == 2) {
            initializeRooms2();
        } else if (random == 3) {
            initializeRooms3();
        } else {
            initializeRooms4();
        }
    }

    public Room moveLeft() throws NullPointerException {
        if (currentRoom.getLeft() == null) {
            throw new NullPointerException("Left room does not exist");
        }

        currentRoom = currentRoom.getLeft();
        return currentRoom;
    }

    public Room moveRight() throws NullPointerException {
        if (currentRoom.getRight() == null) {
            throw new NullPointerException("Right room does not exist");
        }

        currentRoom = currentRoom.getRight();
        return currentRoom;
    }

    public Room moveUp() throws NullPointerException {
        if (currentRoom.getTop() == null) {
            throw new NullPointerException("Top room does not exist");
        }

        currentRoom = currentRoom.getTop();
        return currentRoom;
    }

    public Room moveDown() throws NullPointerException {
        if (currentRoom.getBottom() == null) {
            throw new NullPointerException("Bottom room does not exist");
        }

        currentRoom = currentRoom.getBottom();
        return currentRoom;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    /*
        Comment Legend:
        S = starting room
        R = normal room
        E = exit room
     */
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
    private void initializeRooms0() {
        currentRoom = startingRoom;
        Room.linkAbove(currentRoom, new Room());
        Room.linkBelow(currentRoom, new Room());
        Room.linkLeft(currentRoom, new Room("challenge"));
        Room.linkRight(currentRoom, new Room());

        currentRoom = currentRoom.getLeft();
        Room.linkLeft(currentRoom, new Room());
        Room.linkBelow(currentRoom, new Room());

        currentRoom = currentRoom.getBottom();
        Room.linkRight(currentRoom, startingRoom.getBottom());

        currentRoom = startingRoom.getTop();
        Room.linkRight(currentRoom, new Room());
        Room.linkAbove(currentRoom, new Room());

        currentRoom = startingRoom.getRight();
        Room.linkAbove(currentRoom, startingRoom.getTop().getRight());
        Room.linkRight(currentRoom, new Room());

        currentRoom = currentRoom.getRight();
        Room.linkAbove(currentRoom, new Room());

        currentRoom = currentRoom.getTop();
        Room.linkLeft(currentRoom, startingRoom.getTop().getRight());
        Room.linkAbove(currentRoom, new Room());

        Room anchorRoom = currentRoom.getTop();
        Room.linkLeft(anchorRoom, new Room("challenge"));

        currentRoom = anchorRoom.getLeft();
        Room.linkLeft(currentRoom, startingRoom.getTop().getTop());

        currentRoom = anchorRoom;
        Room.linkAbove(currentRoom, new Room());
        Room.linkRight(currentRoom, new Room());

        currentRoom = anchorRoom.getTop();
        Room.linkRight(currentRoom, new Room("challenge"));

        currentRoom = anchorRoom.getRight();
        Room.linkAbove(currentRoom, anchorRoom.getTop().getRight());

        currentRoom = currentRoom.getTop();
        Room.linkRight(currentRoom, new Room("exit"));

        currentRoom = startingRoom;
    }
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
    private void initializeRooms1() {
        currentRoom = startingRoom;
        Room.linkAbove(currentRoom, new Room());
        Room.linkBelow(currentRoom, new Room());
        Room.linkLeft(currentRoom, new Room());
        Room.linkRight(currentRoom, new Room("challenge"));

        currentRoom = startingRoom.getRight();
        Room.linkRight(currentRoom, new Room());
        Room.linkBelow(currentRoom, new Room());

        currentRoom = startingRoom.getBottom();
        Room.linkRight(currentRoom, startingRoom.getRight().getBottom());
        Room.linkBelow(currentRoom, new Room());

        currentRoom = currentRoom.getBottom();
        Room.linkLeft(currentRoom, new Room());

        currentRoom = currentRoom.getLeft();
        Room.linkAbove(currentRoom, new Room("challenge"));

        currentRoom = currentRoom.getTop();
        Room.linkAbove(currentRoom, startingRoom.getLeft());
        Room.linkLeft(currentRoom, new Room());

        currentRoom = startingRoom.getLeft();
        Room.linkLeft(currentRoom, new Room());
        Room.linkAbove(currentRoom, new Room());

        currentRoom = currentRoom.getTop();
        Room.linkRight(currentRoom, startingRoom.getTop());

        currentRoom = startingRoom.getTop();
        Room.linkAbove(currentRoom, new Room());

        currentRoom = currentRoom.getTop();
        Room.linkLeft(currentRoom, new Room());

        currentRoom = currentRoom.getLeft();
        Room.linkLeft(currentRoom, new Room());

        currentRoom = currentRoom.getLeft();
        Room.linkBelow(currentRoom, new Room());

        currentRoom = currentRoom.getBottom();
        Room.linkLeft(currentRoom, new Room());
        Room.linkBelow(currentRoom, startingRoom.getLeft().getLeft());

        currentRoom = startingRoom.getLeft().getLeft();
        Room.linkBelow(currentRoom, startingRoom.getLeft().getBottom().getLeft());

        currentRoom = currentRoom.getTop().getLeft();
        Room.linkBelow(currentRoom, new Room());

        Room anchorRoom = currentRoom.getBottom();
        Room.linkLeft(anchorRoom, new Room());
        Room.linkBelow(anchorRoom, new Room());

        currentRoom = anchorRoom.getBottom();
        Room.linkRight(currentRoom, startingRoom.getLeft().getLeft().getBottom());
        Room.linkLeft(currentRoom, new Room("challenge"));

        currentRoom = currentRoom.getLeft();
        Room.linkLeft(currentRoom, new Room());

        currentRoom = anchorRoom.getLeft();
        Room.linkLeft(currentRoom, new Room("exit"));

        currentRoom = currentRoom.getLeft();
        Room.linkBelow(currentRoom, anchorRoom.getBottom().getLeft().getLeft());

        currentRoom = startingRoom;
    }
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
    private void initializeRooms2() {
        currentRoom = startingRoom;
        Room.linkAbove(currentRoom, new Room());
        Room.linkBelow(currentRoom, new Room());
        Room.linkLeft(currentRoom, new Room("challenge"));
        Room.linkRight(currentRoom, new Room());

        currentRoom = startingRoom.getTop();
        Room.linkAbove(currentRoom, new Room());
        Room.linkLeft(currentRoom, new Room());
        Room.linkRight(currentRoom, new Room());

        currentRoom = currentRoom.getLeft();
        Room.linkLeft(currentRoom, new Room());

        currentRoom = startingRoom.getLeft();
        Room.linkBelow(currentRoom, new Room());

        currentRoom = currentRoom.getBottom();
        Room.linkRight(currentRoom, startingRoom.getBottom());

        currentRoom = startingRoom.getBottom();
        Room.linkBelow(currentRoom, new Room());

        currentRoom = startingRoom.getTop().getTop();
        Room.linkRight(currentRoom, new Room());

        currentRoom = currentRoom.getRight();
        Room.linkRight(currentRoom, new Room("challenge"));

        currentRoom = currentRoom.getRight();
        Room.linkBelow(currentRoom, new Room());

        currentRoom = currentRoom.getBottom();
        Room.linkBelow(currentRoom, new Room());

        currentRoom = currentRoom.getBottom();
        Room.linkBelow(currentRoom, new Room());

        currentRoom = currentRoom.getBottom();

        Room tempRoom = startingRoom.getRight();
        Room.linkBelow(tempRoom, new Room());

        tempRoom = tempRoom.getBottom();
        Room.linkRight(tempRoom, currentRoom);
        Room.linkBelow(tempRoom, new Room());

        tempRoom = tempRoom.getBottom();
        Room.linkLeft(tempRoom, startingRoom.getBottom().getBottom());
        Room.linkBelow(tempRoom, new Room());

        tempRoom = tempRoom.getBottom();
        Room.linkRight(tempRoom, new Room());

        tempRoom = tempRoom.getRight();
        Room.linkRight(tempRoom, new Room());

        tempRoom = tempRoom.getRight();
        Room.linkRight(tempRoom, new Room("exit"));

        tempRoom = tempRoom.getRight();

        Room.linkBelow(currentRoom, new Room());

        currentRoom = currentRoom.getBottom();
        Room.linkRight(currentRoom, new Room("challenge"));

        currentRoom = currentRoom.getRight();
        Room.linkRight(currentRoom, new Room());

        currentRoom = currentRoom.getRight();
        Room.linkBelow(currentRoom, tempRoom);

        currentRoom = startingRoom;
    }
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
    private void initializeRooms3() {
        currentRoom = startingRoom;
        Room.linkAbove(currentRoom, new Room("challenge"));
        Room.linkBelow(currentRoom, new Room());
        Room.linkLeft(currentRoom, new Room());
        Room.linkRight(currentRoom, new Room());

        currentRoom = startingRoom.getLeft();
        Room.linkLeft(currentRoom, new Room());

        currentRoom = startingRoom.getLeft().getLeft();
        Room.linkLeft(currentRoom, new Room());
        Room.linkAbove(currentRoom, new Room());
        Room.linkBelow(currentRoom, new Room());

        currentRoom = startingRoom.getTop();
        Room.linkLeft(currentRoom, new Room());
        Room.linkAbove(currentRoom, new Room());
        Room.linkRight(currentRoom, new Room());

        currentRoom = startingRoom.getBottom();
        Room.linkLeft(currentRoom, new Room());
        Room.linkBelow(currentRoom, new Room());

        currentRoom = currentRoom.getBottom();
        Room.linkRight(currentRoom, new Room());

        currentRoom = currentRoom.getRight();
        Room.linkAbove(currentRoom, new Room());
        Room.linkRight(currentRoom, new Room());

        Room tempRoom = currentRoom;

        currentRoom = currentRoom.getTop();
        Room.linkAbove(currentRoom, startingRoom.getRight());

        currentRoom = tempRoom.getRight();
        Room.linkAbove(currentRoom, new Room());
        Room.linkRight(currentRoom, new Room());

        Room anchorRoom = currentRoom.getRight();

        currentRoom = anchorRoom;
        Room.linkRight(currentRoom, new Room("challenge"));
        Room.linkBelow(currentRoom, new Room());

        currentRoom = currentRoom.getBottom();
        Room.linkRight(currentRoom, new Room("exit"));

        currentRoom = startingRoom.getRight();
        Room.linkRight(currentRoom, new Room());

        currentRoom = currentRoom.getRight();
        Room.linkAbove(currentRoom, new Room());
        Room.linkRight(currentRoom, new Room());

        currentRoom = currentRoom.getRight();
        Room.linkBelow(currentRoom, new Room("challenge"));

        currentRoom = currentRoom.getBottom();
        Room.linkBelow(currentRoom, anchorRoom);

        currentRoom = startingRoom.getTop().getRight();
        Room.linkRight(currentRoom, startingRoom.getRight().getRight().getTop());

        currentRoom = currentRoom.getRight();
        Room.linkAbove(currentRoom, new Room());

        currentRoom = currentRoom.getTop();
        Room.linkRight(currentRoom, new Room());

        currentRoom = currentRoom.getRight();
        Room.linkBelow(currentRoom, new Room());

        currentRoom = startingRoom;
    }
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
    private void initializeRooms4() {
        currentRoom = startingRoom;
        Room.linkAbove(currentRoom, new Room());
        Room.linkBelow(currentRoom, new Room("challenge"));
        Room.linkLeft(currentRoom, new Room());
        Room.linkRight(currentRoom, new Room());

        currentRoom = startingRoom.getTop();
        Room.linkLeft(currentRoom, new Room());
        Room.linkRight(currentRoom, new Room());

        currentRoom = startingRoom.getTop().getLeft();
        Room.linkBelow(currentRoom, startingRoom.getLeft());

        currentRoom = startingRoom.getTop().getRight();
        Room.linkRight(currentRoom, new Room());

        currentRoom = currentRoom.getRight();
        Room.linkBelow(currentRoom, new Room("challenge"));

        currentRoom = currentRoom.getBottom();
        Room.linkLeft(currentRoom, startingRoom.getRight());

        currentRoom = startingRoom.getBottom();
        Room.linkBelow(currentRoom, new Room());

        currentRoom = startingRoom.getRight();
        Room.linkBelow(currentRoom, new Room());

        currentRoom = currentRoom.getBottom();
        Room.linkBelow(currentRoom, new Room());

        currentRoom = currentRoom.getBottom();
        Room.linkLeft(currentRoom, startingRoom.getBottom().getBottom());

        currentRoom = startingRoom.getLeft();
        Room.linkBelow(currentRoom, new Room());

        currentRoom = currentRoom.getBottom();
        Room.linkBelow(currentRoom, new Room());

        currentRoom = currentRoom.getBottom();

        Room anchorRoom = currentRoom;

        Room.linkRight(currentRoom, startingRoom.getBottom().getBottom());
        Room.linkLeft(currentRoom, new Room());
        Room.linkBelow(currentRoom, new Room());

        currentRoom = currentRoom.getLeft();
        Room.linkBelow(currentRoom, new Room());

        currentRoom = currentRoom.getBottom();
        Room.linkBelow(currentRoom, new Room());

        currentRoom = anchorRoom.getBottom();
        Room.linkRight(currentRoom, new Room());
        Room.linkBelow(currentRoom, new Room());

        currentRoom = currentRoom.getRight();
        Room.linkBelow(currentRoom, new Room("challenge"));

        currentRoom = currentRoom.getBottom();
        Room.linkLeft(currentRoom, anchorRoom.getBottom().getBottom());

        currentRoom = anchorRoom.getBottom().getBottom();
        Room.linkLeft(currentRoom, anchorRoom.getLeft().getBottom().getBottom());

        currentRoom = currentRoom.getLeft();
        Room.linkLeft(currentRoom, new Room("exit"));

        currentRoom = startingRoom;
    }
}
