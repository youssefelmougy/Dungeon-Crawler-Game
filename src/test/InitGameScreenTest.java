package test;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import static org.testfx.api.FxAssert.verifyThat;

public class InitGameScreenTest extends ApplicationTest {
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
    public void testEasyMode() {
        clickOn("Begin Your Journey");
        clickOn("Name:");
        write("MyName");
        clickOn("Easy");
        clickOn("Sword");
        clickOn("Submit");
        verifyThat("MyName", NodeMatchers.isNotNull());
        verifyThat("$800", NodeMatchers.isNotNull());
    }

    @Test
    public void testMediumMode() {
        clickOn("Begin Your Journey");
        clickOn("Name:");
        write("MyName");
        clickOn("Medium");
        clickOn("Bow");
        clickOn("Submit");
        verifyThat("MyName", NodeMatchers.isNotNull());
        verifyThat("$500", NodeMatchers.isNotNull());
    }

    @Test
    public void testHardMode() {
        clickOn("Begin Your Journey");
        clickOn("Name:");
        write("MyName");
        clickOn("Hard");
        clickOn("Axe");
        clickOn("Submit");
        verifyThat("MyName", NodeMatchers.isNotNull());
        verifyThat("$200", NodeMatchers.isNotNull());
    }
}
