package test;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Player;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import controller.GameController;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.matcher.base.NodeMatchers;

public class ItemsTest extends ApplicationTest {
    private GameController controller;
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/game.fxml"));
        Parent root = fxmlloader.load();
        controller = fxmlloader.getController();
        controller.setPlayer(new Player("Player", 1, "Bow"));
        System.out.println(primaryStage);
        primaryStage.setTitle("Dungeon Crawler");
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.show();
    }

    @Test
    public void pickUpDroppedItem() {
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
        clickOn("#droppedItemImageView");
        clickOn("#addToInventoryBox");
        sleep(1000);
        verifyThat("#droppedItemImageView", NodeMatchers.isInvisible());
    }
}
