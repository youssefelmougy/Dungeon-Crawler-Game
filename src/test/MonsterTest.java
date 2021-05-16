package test;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Monster;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.testfx.framework.junit.ApplicationTest;
import controller.GameController;
import model.Player;
import org.testfx.matcher.base.NodeMatchers;

import static org.testfx.api.FxAssert.verifyThat;

public class MonsterTest extends ApplicationTest {
    private GameController controller;
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/game.fxml"));
        Parent root = fxmlloader.load();
        controller = fxmlloader.getController();
        controller.setPlayer(new Player("Player", 1, "Sword"));
        System.out.println(primaryStage);
        primaryStage.setTitle("Dungeon Crawler");
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.show();
    }

    @Test
    public void testAttackMonster() {
        clickOn("#topDoor");
        clickOn("#monster");
        while (true) {
            try {
                clickOn("#attackMonsterBox");
                sleep(1000);
            } catch (org.testfx.api.FxRobotException e) {
                break;
            }
        }
        verifyThat("#monster", NodeMatchers.isInvisible());
        sleep(500);
    }

    @Test
    public void testCannotProgress() {
        clickOn("#topDoor");
        try {
            clickOn("#topDoor");
            sleep(1000);
            verifyThat("#monster", NodeMatchers.isVisible());
        } catch (org.testfx.api.FxRobotException e) {
            System.out.println("No top door");
        }
        try {
            clickOn("#rightDoor");
            sleep(1000);
            verifyThat("#monster", NodeMatchers.isVisible());
        } catch (org.testfx.api.FxRobotException e) {
            System.out.println("No right door");
        }
        try {
            clickOn("#leftDoor");
            sleep(1000);
            verifyThat("#monster", NodeMatchers.isVisible());
        } catch (org.testfx.api.FxRobotException e) {
            System.out.println("No left door");
        }
    }

    @Test
    public void testCanProgress() {
        clickOn("#topDoor");
        clickOn("#monster");
        while (true) {
            try {
                clickOn("#attackMonsterBox");
                sleep(1000);
            } catch (org.testfx.api.FxRobotException e) {
                break;
            }
        }
        try {
            clickOn("#topDoor");
            sleep(1000);
            verifyThat("#monster", NodeMatchers.isVisible());
            clickOn("#bottomDoor");
        } catch (org.testfx.api.FxRobotException e) {
            System.out.println("No top door");
        }
        try {
            clickOn("#rightDoor");
            sleep(1000);
            verifyThat("#monster", NodeMatchers.isVisible());
            clickOn("#leftDoor");
        } catch (org.testfx.api.FxRobotException e) {
            System.out.println("No right door");
        }
        try {
            clickOn("#leftDoor");
            sleep(1000);
            verifyThat("#monster", NodeMatchers.isVisible());
            clickOn("#rightDoor");
        } catch (org.testfx.api.FxRobotException e) {
            System.out.println("No left door");
        }
    }

    @Test
    public void testCanRetreat() {
        clickOn("#topDoor");
        clickOn("#monster");
        while (true) {
            try {
                clickOn("#attackMonsterBox");
                sleep(1000);
            } catch (org.testfx.api.FxRobotException e) {
                break;
            }
        }
        try {
            clickOn("#topDoor");
            sleep(1000);
            clickOn("#bottomDoor");
        } catch (org.testfx.api.FxRobotException e) {
            System.out.println("No top door");
            try {
                clickOn("#rightDoor");
                sleep(1000);
                clickOn("#leftDoor");
            } catch (org.testfx.api.FxRobotException f) {
                System.out.println("No right door");
                try {
                    clickOn("#leftDoor");
                    sleep(1000);
                    clickOn("#rightDoor");
                } catch (org.testfx.api.FxRobotException g) {
                    System.out.println("No left door");
                }
            }
        }
        verifyThat("#monster", NodeMatchers.isInvisible());
        sleep(500);
    }

    @Test
    public void retreatInFight() {
        clickOn("#topDoor");
        clickOn("#monster");
        clickOn("#attackMonsterBox");
        clickOn("#bottomDoor");
        sleep(1000);
        verifyThat("#monster", NodeMatchers.isInvisible());
    }

    @Test
    public void retreatReenter() {
        clickOn("#topDoor");
        clickOn("#monster");
        clickOn("#attackMonsterBox");
        clickOn("#bottomDoor");
        clickOn("#topDoor");
        Monster monster = controller.getRoomGraph().getCurrentRoom().getMonster();
        if (monster.getType().equals("Skeleton")) {
            assertEquals(monster.getHealth(), 80);
        } else {
            assertEquals(monster.getHealth(), 50);
        }
    }

    @Test
    public void checkMonsters() {
        clickOn("#topDoor");
        Monster monster = controller.getRoomGraph().getCurrentRoom().getMonster();
        if (monster.getHealth() == 80) {
            assertEquals(monster.getType(), "Skeleton");
        }
        if (monster.getHealth() == 50) {
            assertEquals(monster.getType(), "Spider");
        }
        clickOn("#bottomDoor");
        clickOn("#rightDoor");
        monster = controller.getRoomGraph().getCurrentRoom().getMonster();
        if (monster.getHealth() == 80) {
            assertEquals(monster.getType(), "Skeleton");
        }
        if (monster.getHealth() == 50) {
            assertEquals(monster.getType(), "Spider");
        }
        clickOn("#leftDoor");
        clickOn("#bottomDoor");
        monster = controller.getRoomGraph().getCurrentRoom().getMonster();
        if (monster.getHealth() == 80) {
            assertEquals(monster.getType(), "Skeleton");
        }
        if (monster.getHealth() == 50) {
            assertEquals(monster.getType(), "Spider");
        }
        clickOn("#topDoor");
        clickOn("#leftDoor");
        monster = controller.getRoomGraph().getCurrentRoom().getMonster();
        if (monster.getHealth() == 80) {
            assertEquals(monster.getType(), "Skeleton");
        }
        if (monster.getHealth() == 50) {
            assertEquals(monster.getType(), "Spider");
        }
    }

    @Test
    public void testStartingRoomNoMonsters() {
        sleep(1000);
        verifyThat("#monster", NodeMatchers.isInvisible());
    }
}
