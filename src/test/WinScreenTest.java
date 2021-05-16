package test;

import controller.WinScreenController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Player;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import static org.testfx.api.FxAssert.verifyThat;

public class WinScreenTest extends ApplicationTest {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlloader =
                new FXMLLoader(getClass().getClassLoader().getResource("./view/winScreen.fxml"));
        Parent root = fxmlloader.load();
        WinScreenController controller = fxmlloader.getController();
        controller.setTxtFields(new Player("Player", 1, "Sword"));
        System.out.println(primaryStage);
        primaryStage.setTitle("Dungeon Crawler");
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.show();
    }
    @Test
    public void testNewGame() {
        sleep(1500);
        clickOn("New Game");
        verifyThat("Game Configuration", NodeMatchers.isNotNull());
    }

    @Test
    public void testExitButton() {
        sleep(1500);
        clickOn("Exit Game");
    }

    @Test
    public void testWinStatsAppear() {
        sleep(3000);
        verifyThat("#txtPlayerMoney", NodeMatchers.isNotNull());
        verifyThat("#txtMonstersKilled", NodeMatchers.isNotNull());
        verifyThat("#txtDamageDealt", NodeMatchers.isNotNull());
    }
}
