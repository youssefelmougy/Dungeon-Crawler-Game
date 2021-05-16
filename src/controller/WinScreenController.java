package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Player;

import java.io.IOException;

public class WinScreenController {

    private Player player;

    @FXML
    private Text txtPlayerName;

    @FXML
    private Text txtPlayerDifficulty;

    @FXML
    private Text txtPlayerMoney;

    @FXML
    private Text txtMonstersKilled;

    @FXML
    private Text txtDamageDealt;

    @FXML
    private Button newGameButton;

    @FXML
    private Button exitGameButton;

    public WinScreenController() { }

    @FXML
    private void initialize() { }

    public void setTxtFields(Player player) {
        this.player = player;
        txtPlayerName.setText(this.player.getName());
        if (this.player.getDifficulty() == 1) {
            txtPlayerDifficulty.setText("Easy");
        } else if (this.player.getDifficulty() == 2) {
            txtPlayerDifficulty.setText("Medium");
        } else {
            txtPlayerDifficulty.setText("Hard");
        }

        txtPlayerMoney.setText("$" + this.player.getMoney());
        txtMonstersKilled.setText(this.player.getMonstersDefeated() + "");
        txtDamageDealt.setText(this.player.getDamageDealt() + "");
    }

    @FXML
    private void newGame(ActionEvent event) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("/view/configScreen.fxml"));

        Scene scene = new Scene(loader);

        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void exitGame(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.close();
    }
}
