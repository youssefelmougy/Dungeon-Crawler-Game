package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeController {
    @FXML
    private Button beginButton;

    public WelcomeController() { }

    @FXML
    private void initialize() { }

    @FXML
    private void startGame(ActionEvent event) throws IOException {
        System.out.println("Game Started");

        Parent loader = FXMLLoader.load(getClass().getResource("/view/configScreen.fxml"));

        Scene scene = new Scene(loader);

        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
