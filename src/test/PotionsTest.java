package test;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.AccuracyPotion;
import model.DamagePotion;
import model.HealthPotion;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.testfx.framework.junit.ApplicationTest;
import controller.GameController;
import model.Player;
import org.testfx.matcher.base.NodeMatchers;

import static org.testfx.api.FxAssert.verifyThat;

public class PotionsTest extends ApplicationTest {
    private GameController controller;
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/game.fxml"));
        Parent root = fxmlloader.load();
        controller = fxmlloader.getController();
        controller.setPlayer(new Player("Player", 1, "Bow"));
        controller.getPlayer().setHealth(70);
        controller.getPlayer().pickUpPotion(new HealthPotion());
        controller.getPlayer().pickUpPotion(new AccuracyPotion());
        controller.getPlayer().pickUpPotion(new DamagePotion());
        System.out.println(primaryStage);
        primaryStage.setTitle("Dungeon Crawler");
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.show();
    }

    @Test
    public void testHealthPotion() {
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
        int playerHealth = controller.getPlayer().getHealth();
        clickOn("#potionHealth");
        clickOn("#equipBox");
        sleep(500);
        assertEquals(playerHealth + 30, controller.getPlayer().getHealth());
    }

    @Test
    public void testAccuracyPotion() {
        assertEquals(false, controller.getPlayer().hasActivePotion());
        clickOn("#potionAccuracy");
        clickOn("#equipBox");
        sleep(500);
        assertEquals(true, controller.getPlayer().hasActivePotion());
    }

    @Test
    public void testDamagePotion() {
        assertEquals(false, controller.getPlayer().hasActivePotion());
        clickOn("#potionDamage");
        clickOn("#equipBox");
        sleep(500);
        assertEquals(true, controller.getPlayer().hasActivePotion());
    }
}
