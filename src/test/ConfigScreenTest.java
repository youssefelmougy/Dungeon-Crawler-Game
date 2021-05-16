package test;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;


import static org.testfx.api.FxAssert.verifyThat;

public class ConfigScreenTest extends ApplicationTest {
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
    public void testSubmit() {
        clickOn("Begin Your Journey");
        clickOn("Name:");
        write("MyName");
        clickOn("Easy");
        clickOn("Sword");
        clickOn("Submit");
        verifyThat("MyName", NodeMatchers.isNotNull());
    }

    @Test
    public void testSubmitEmptyName() {
        clickOn("Begin Your Journey");
        clickOn("Name:");
        write(" ");
        clickOn("Medium");
        clickOn("Bow");
        clickOn("Submit");
        verifyThat("Please enter a name that is not empty or consists of only whitespace.",
                NodeMatchers.isNotNull());
    }

    @Test
    public void testSubmitNoDifficulty() {
        clickOn("Begin Your Journey");
        clickOn("Name:");
        write("MyName");
        clickOn("Bow");
        clickOn("Submit");
        verifyThat("Please select a difficulty level", NodeMatchers.isNotNull());
    }

    @Test
    public void testSubmitNoWeapon() {
        clickOn("Begin Your Journey");
        clickOn("Name:");
        write("MyName");
        clickOn("Hard");
        clickOn("Submit");
        verifyThat("Please select a starter weapon", NodeMatchers.isNotNull());
    }
}
