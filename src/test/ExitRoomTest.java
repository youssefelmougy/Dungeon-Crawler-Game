package test;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import controller.ExitRoomController;
import model.Player;
import org.testfx.matcher.base.NodeMatchers;

import static org.testfx.api.FxAssert.verifyThat;

public class ExitRoomTest extends ApplicationTest {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/exitRoom.fxml"));
        Parent root = fxmlloader.load();
        ExitRoomController controller = fxmlloader.getController();
        controller.setExitRoom(new Player("Player", 1, "Sword"));
        System.out.println(primaryStage);
        primaryStage.setTitle("Dungeon Crawler");
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.show();
    }

    //need to test click on image takes you to win screen
    @Test
    public void testExitDungeon() {
        clickOn("#exitDoor");
        sleep(1500);
        verifyThat("#txtPlayerName", NodeMatchers.isNotNull());
        verifyThat("#txtPlayerName", NodeMatchers.isNotNull());
        verifyThat("#txtPlayerName", NodeMatchers.isNotNull());
    }
}
