package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Player;

import java.io.IOException;

public class ExitRoomController {

    private Player player;

    @FXML
    private Text playerNameText;

    @FXML
    private Text playerMoneyText;

    public ExitRoomController() { }

    @FXML
    private void initialize() { }

    public void setExitRoom(Player player) {
        this.player = player;
        playerNameText.setText(this.player.getName());
        playerMoneyText.setText("$" + this.player.getMoney());
    }

    @FXML
    private void finishGame() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/winScreen.fxml"));
        Parent loader = fxmlloader.load();

        Scene scene = new Scene(loader);

        Stage primaryStage = (Stage) playerNameText.getScene().getWindow();
        WinScreenController winScreenController = fxmlloader.getController();

        winScreenController.setTxtFields(player);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
