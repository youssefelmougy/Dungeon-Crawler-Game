package test;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import static org.testfx.api.FxAssert.verifyThat;

public class WelcomeScreenTest extends ApplicationTest {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root =
                FXMLLoader.load(getClass().getClassLoader().getResource("./view/welcome.fxml"));
        System.out.println(primaryStage);
        primaryStage.setTitle("Dungeon Crawler");
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.show();
    }
    @Test
    public void testBegin() {
        clickOn("Begin Your Journey");
        verifyThat("Game Configuration", NodeMatchers.isNotNull());
    }
}
