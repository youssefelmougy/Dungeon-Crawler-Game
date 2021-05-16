package model;

public class Room {
    private String type;

    private Room left;
    private Room right;
    private Room top;
    private Room bottom;
    private boolean checked;
    private Monster monster;
    private Item droppedItem;
    private Monster[] challengeMonsters;
    private Item[] droppedChallengeItems;
    private boolean challengeStarted;

    public Room() {
        new Room(null, null, null, null, null);
        monster = (Math.random() < 0.5) ? new Skeleton() : new Spider();

        int randomIndexForDroppedWeapon = (int) (Math.random() * 6);
        switch (randomIndexForDroppedWeapon) {
        case 0:
            droppedItem = new Sword();
            break;
        case 1:
            droppedItem = new Axe();
            break;
        case 2:
            droppedItem = new Bow();
            break;
        case 3:
            droppedItem = new HealthPotion();
            break;
        case 4:
            droppedItem = new DamagePotion();
            break;
        default:
            droppedItem = new AccuracyPotion();
        }
    }

    public Room(String type) {
        this(type, null, null, null, null);
        monster = (type.equals("exit")) ? new Minotaur() : null;
        if (type.equals("challenge")) {
            challengeMonsters = new Monster[2];
            challengeMonsters[0] = new Spider();
            challengeMonsters[1] = new Skeleton();

            droppedChallengeItems  = new Item[4];

            for (int i = 0; i < droppedChallengeItems.length; i++) {
                int randomIndexForDroppedWeapon = (int) (Math.random() * 6);
                switch (randomIndexForDroppedWeapon) {
                case 0:
                    droppedChallengeItems[i] = new Sword();
                    break;
                case 1:
                    droppedChallengeItems[i] = new Axe();
                    break;
                case 2:
                    droppedChallengeItems[i] = new Bow();
                    break;
                case 3:
                    droppedChallengeItems[i] = new HealthPotion();
                    break;
                case 4:
                    droppedChallengeItems[i] = new DamagePotion();
                    break;
                default:
                    droppedChallengeItems[i] = new AccuracyPotion();
                }
            }

            challengeStarted = false;
        }
    }

    public Room(String type, Room left, Room right, Room top, Room bottom) {
        this.type = type;
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
    }

    public void setLeft(Room left) {
        this.left = left;
    }

    public void setRight(Room right) {
        this.right = right;
    }

    public void setTop(Room top) {
        this.top = top;
    }

    public void setBottom(Room bottom) {
        this.bottom = bottom;
    }

    public void setCheckedTrue() {
        checked = true;
    }

    // static methods that create two way edges between Rooms
    public static void linkAbove(Room curr, Room above) {
        curr.setTop(above);
        above.setBottom(curr);
    }

    public static void linkRight(Room curr, Room right) {
        curr.setRight(right);
        right.setLeft(curr);
    }

    public static void linkBelow(Room curr, Room below) {
        curr.setBottom(below);
        below.setTop(curr);
    }

    public static void linkLeft(Room curr, Room left) {
        curr.setLeft(left);
        left.setRight(curr);
    }

    public Room getLeft() {
        return left;
    }

    public Room getRight() {
        return right;
    }

    public Room getTop() {
        return top;
    }

    public Room getBottom() {
        return bottom;
    }

    public String getType() {
        return type;
    }

    public boolean getChecked() {
        return checked;
    }

    public Monster getMonster() {
        return monster;
    }

    public void killMonster(int index) {
        if (index == -1) {
            monster = null;
        } else {
            challengeMonsters[index] = null;
        }
    }

    public Item getDroppedItem() {
        return droppedItem;
    }

    public void pickUpItem(int index) {
        if (index == -1) {
            droppedItem = null;
        } else {
            droppedChallengeItems[index] = null;
        }
    }

    public void startChallenge() {
        challengeStarted = true;
    }

    public boolean getChallengeStarted() {
        return challengeStarted;
    }

    public Monster[] getChallengeMonsters() {
        return challengeMonsters;
    }

    public Item[] getDroppedChallengeItems() {
        return droppedChallengeItems;
    }
}
