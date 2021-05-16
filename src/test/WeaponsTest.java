package test;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Axe;
import model.Bow;
import model.Player;
import model.Sword;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.testfx.framework.junit.ApplicationTest;
import controller.GameController;

public class WeaponsTest extends ApplicationTest {
    private GameController controller;
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/game.fxml"));
        Parent root = fxmlloader.load();
        controller = fxmlloader.getController();
        controller.setPlayer(new Player("Player", 1, "Bow"));
        controller.getPlayer().pickUpWeapon(new Bow());
        controller.getPlayer().pickUpWeapon(new Axe());
        controller.getPlayer().pickUpWeapon(new Sword());
        controller.setAxeVisible();
        controller.setBowVisible();
        controller.setSwordVisible();
        System.out.println(primaryStage);
        primaryStage.setTitle("Dungeon Crawler");
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.show();
    }

    @Test
    public void testSword() {
        clickOn("#weaponSword");
        clickOn("#equipBox");
        assertEquals(36, controller.getPlayer().getCurrWeapon().getBaseDamage());
    }

    @Test
    public void testBow() {
        clickOn("#weaponAxe");
        clickOn("#equipBox");
        clickOn("#weaponBow");
        clickOn("#equipBox");
        assertEquals(47, controller.getPlayer().getCurrWeapon().getBaseDamage());
    }

    @Test
    public void testAxe() {
        clickOn("#weaponAxe");
        clickOn("#equipBox");
        assertEquals(30, controller.getPlayer().getCurrWeapon().getBaseDamage());
    }

    @Test
    public void switchWeaponInFight() {
        clickOn("#topDoor");
        try {
            clickOn("#weaponAxe");
            clickOn("#equipBox");
            clickOn("#monster");
            clickOn("#attackMonsterBox");
            clickOn("#weaponSword");
            clickOn("#equipBox");
            clickOn("#monster");
            clickOn("#attackMonsterBox");
            clickOn("#weaponBow");
            clickOn("#equipBox");
            clickOn("#monster");
            clickOn("#attackMonsterBox");
        } catch (org.testfx.api.FxRobotException e) {
            clickOn("#bottomDoor");
            clickOn("#bottomDoor");
            clickOn("#monster");
            clickOn("#attackMonsterBox");
            clickOn("#weaponBow");
            clickOn("#equipBox");
            clickOn("#monster");
            clickOn("#attackMonsterBox");
        }
    }
}
