package test;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import controller.GameController;
import model.Player;
import org.testfx.matcher.base.NodeMatchers;

import static org.testfx.api.FxAssert.verifyThat;

public class LostGameTest extends ApplicationTest {
    private Player player;
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/game.fxml"));
        Parent root = fxmlloader.load();
        GameController controller = fxmlloader.getController();
        player = new Player("Player", 1, "Sword");
        player.setHealthZero();
        controller.setPlayer(player);
        System.out.println(primaryStage);
        primaryStage.setTitle("Dungeon Crawler");
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.show();
    }

    @Test
    public void testLostGame() {
        clickOn("#topDoor");
        sleep(500);
        clickOn("#monster");

        try {
            clickOn("#attackMonsterBox");
        } catch (org.testfx.api.FxRobotException e) {
            try {
                clickOn("bottomDoor");
                clickOn("bottomDoor");
                clickOn("#monster");
                while (player.isAlive()) {
                    clickOn("#attackMonsterBox");
                }
            } catch (org.testfx.api.FxRobotException f) {
                System.out.println(f.getMessage());
            }
        }

        sleep(1000);

        verifyThat("#restartGameButton", NodeMatchers.isNotNull());
        verifyThat("#exitGameButton", NodeMatchers.isNotNull());
    }
}
