package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Player;

import java.io.IOException;

public class ConfigScreenController {
    @FXML
    private TextField nameField;

    @FXML
    private ToggleGroup difficultyToggleGroup;

    @FXML
    private ToggleGroup weaponToggleGroup;

    @FXML
    private Text errorMessage;

    public ConfigScreenController() { }

    @FXML
    private void initialize() { }

    @FXML
    private void submitGameConfiguration(ActionEvent event) throws IOException {
        String nameInput = nameField.getText();
        RadioButton selectedDifficultyRadioButton
                = (RadioButton) difficultyToggleGroup.getSelectedToggle();
        RadioButton selectedWeaponRadioButton
                = (RadioButton) weaponToggleGroup.getSelectedToggle();

        if (selectedDifficultyRadioButton == null) {
            errorMessage.setText("Please select a difficulty level");
            return;
        } else if (selectedWeaponRadioButton == null) {
            errorMessage.setText("Please select a starter weapon");
            return;
        }

        String selectedDifficultyText = selectedDifficultyRadioButton.getText();
        int selectedDifficulty;
        switch (selectedDifficultyText) {
        case "Medium":
            selectedDifficulty = 2;
            break;
        case "Hard":
            selectedDifficulty = 3;
            break;
        default:
            selectedDifficulty = 1;
        }

        String selectedWeaponName = selectedWeaponRadioButton.getText();

        Player player;
        try {
            player = new Player(nameInput, selectedDifficulty, selectedWeaponName);
        } catch (IllegalArgumentException iae) {
            errorMessage.setText("Please enter a name that is"
                    + " not empty or consists of only whitespace.");
            return;
        }

        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/game.fxml"));
        Parent loader = fxmlloader.load();

        Scene scene = new Scene(loader);

        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        GameController gameController = fxmlloader.getController();

        gameController.setPlayer(player);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
