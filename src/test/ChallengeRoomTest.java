package test;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.testfx.framework.junit.ApplicationTest;
import controller.GameController;
import model.Player;
import org.testfx.matcher.base.NodeMatchers;

import static org.testfx.api.FxAssert.verifyThat;


public class ChallengeRoomTest extends ApplicationTest{
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
    public void testEnterChallengeRoom() {
        clickOn("#topDoor");
        sleep(1000);
        if (!(controller.getRoomGraph().getCurrentRoom().getType() == null) &&
                controller.getRoomGraph().getCurrentRoom().getType().equals("challenge")) {
            verifyThat("#beginChallengeButton", NodeMatchers.isVisible());
            return;
        }
        clickOn("#bottomDoor");
        clickOn("#leftDoor");
        sleep(1000);
        if (!(controller.getRoomGraph().getCurrentRoom().getType() == null) &&
                controller.getRoomGraph().getCurrentRoom().getType().equals("challenge")) {
            verifyThat("#beginChallengeButton", NodeMatchers.isVisible());
            return;
        }
        clickOn("#rightDoor");
        clickOn("#bottomDoor");
        sleep(1000);
        if (!(controller.getRoomGraph().getCurrentRoom().getType() == null) &&
                controller.getRoomGraph().getCurrentRoom().getType().equals("challenge")) {
            verifyThat("#beginChallengeButton", NodeMatchers.isVisible());
            return;
        }
        clickOn("#topDoor");
        clickOn("#rightDoor");
        sleep(1000);
        if (!(controller.getRoomGraph().getCurrentRoom().getType() == null) &&
                controller.getRoomGraph().getCurrentRoom().getType().equals("challenge")) {
            verifyThat("#beginChallengeButton", NodeMatchers.isVisible());
            return;
        }
    }

    @Test
    public void retreatChallengeRoom() {
        clickOn("#topDoor");
        sleep(1000);
        if (!(controller.getRoomGraph().getCurrentRoom().getType() == null) &&
                controller.getRoomGraph().getCurrentRoom().getType().equals("challenge")) {
            verifyThat("#beginChallengeButton", NodeMatchers.isVisible());
            clickOn("#bottomDoor");
            assertEquals(controller.getRoomGraph().getCurrentRoom().getType(), "starting");
            return;
        }
        clickOn("#bottomDoor");
        clickOn("#leftDoor");
        sleep(1000);
        if (!(controller.getRoomGraph().getCurrentRoom().getType() == null) &&
                controller.getRoomGraph().getCurrentRoom().getType().equals("challenge")) {
            verifyThat("#beginChallengeButton", NodeMatchers.isVisible());
            clickOn("#rightDoor");
            assertEquals(controller.getRoomGraph().getCurrentRoom().getType(), "starting");
            return;
        }
        clickOn("#rightDoor");
        clickOn("#bottomDoor");
        sleep(1000);
        if (!(controller.getRoomGraph().getCurrentRoom().getType() == null) &&
                controller.getRoomGraph().getCurrentRoom().getType().equals("challenge")) {
            verifyThat("#beginChallengeButton", NodeMatchers.isVisible());
            clickOn("#topDoor");
            assertEquals(controller.getRoomGraph().getCurrentRoom().getType(), "starting");
            return;
        }
        clickOn("#topDoor");
        clickOn("#rightDoor");
        sleep(1000);
        if (!(controller.getRoomGraph().getCurrentRoom().getType() == null) &&
                controller.getRoomGraph().getCurrentRoom().getType().equals("challenge")) {
            verifyThat("#beginChallengeButton", NodeMatchers.isVisible());
            clickOn("#leftDoor");
            assertEquals(controller.getRoomGraph().getCurrentRoom().getType(), "starting");
            return;
        }
    }

    @Test
    public void beginChallengeRoom() {
        clickOn("#topDoor");
        sleep(1000);
        if (!(controller.getRoomGraph().getCurrentRoom().getType() == null) &&
                controller.getRoomGraph().getCurrentRoom().getType().equals("challenge")) {
            verifyThat("#beginChallengeButton", NodeMatchers.isVisible());
            clickOn("#beginChallengeButton");
            verifyThat("#monster", NodeMatchers.isVisible());
            verifyThat("#monster2", NodeMatchers.isVisible());
            return;
        }
        clickOn("#bottomDoor");
        clickOn("#leftDoor");
        sleep(1000);
        if (!(controller.getRoomGraph().getCurrentRoom().getType() == null) &&
                controller.getRoomGraph().getCurrentRoom().getType().equals("challenge")) {
            verifyThat("#beginChallengeButton", NodeMatchers.isVisible());
            clickOn("#beginChallengeButton");
            verifyThat("#monster", NodeMatchers.isVisible());
            verifyThat("#monster2", NodeMatchers.isVisible());
            return;
        }
        clickOn("#rightDoor");
        clickOn("#bottomDoor");
        sleep(1000);
        if (!(controller.getRoomGraph().getCurrentRoom().getType() == null) &&
                controller.getRoomGraph().getCurrentRoom().getType().equals("challenge")) {
            verifyThat("#beginChallengeButton", NodeMatchers.isVisible());
            clickOn("#beginChallengeButton");
            verifyThat("#monster", NodeMatchers.isVisible());
            verifyThat("#monster2", NodeMatchers.isVisible());
            return;
        }
        clickOn("#topDoor");
        clickOn("#rightDoor");
        sleep(1000);
        if (!(controller.getRoomGraph().getCurrentRoom().getType() == null) &&
                controller.getRoomGraph().getCurrentRoom().getType().equals("challenge")) {
            verifyThat("#beginChallengeButton", NodeMatchers.isVisible());
            clickOn("#beginChallengeButton");
            verifyThat("#monster", NodeMatchers.isVisible());
            verifyThat("#monster2", NodeMatchers.isVisible());
            return;
        }
    }

    @Test
    public void simultaneousMonsterAttack() {
        clickOn("#topDoor");
        sleep(1000);
        if (!(controller.getRoomGraph().getCurrentRoom().getType() == null) &&
                controller.getRoomGraph().getCurrentRoom().getType().equals("challenge")) {
            verifyThat("#beginChallengeButton", NodeMatchers.isVisible());
            clickOn("#beginChallengeButton");
            verifyThat("#monster", NodeMatchers.isVisible());
            verifyThat("#monster2", NodeMatchers.isVisible());
            for (int i = 0; i < 2; i++) {
                clickOn("#monster");
                clickOn("#attackMonsterBox");
                clickOn("#monster2");
                clickOn("#attackMonsterBox");
            }
            return;
        }
        clickOn("#bottomDoor");
        clickOn("#leftDoor");
        sleep(1000);
        if (!(controller.getRoomGraph().getCurrentRoom().getType() == null) &&
                controller.getRoomGraph().getCurrentRoom().getType().equals("challenge")) {
            verifyThat("#beginChallengeButton", NodeMatchers.isVisible());
            clickOn("#beginChallengeButton");
            verifyThat("#monster", NodeMatchers.isVisible());
            verifyThat("#monster2", NodeMatchers.isVisible());
            for (int i = 0; i < 2; i++) {
                clickOn("#monster");
                clickOn("#attackMonsterBox");
                clickOn("#monster2");
                clickOn("#attackMonsterBox");
            }
            return;
        }
        clickOn("#rightDoor");
        clickOn("#bottomDoor");
        sleep(1000);
        if (!(controller.getRoomGraph().getCurrentRoom().getType() == null) &&
                controller.getRoomGraph().getCurrentRoom().getType().equals("challenge")) {
            verifyThat("#beginChallengeButton", NodeMatchers.isVisible());
            clickOn("#beginChallengeButton");
            verifyThat("#monster", NodeMatchers.isVisible());
            verifyThat("#monster2", NodeMatchers.isVisible());
            for (int i = 0; i < 2; i++) {
                clickOn("#monster");
                clickOn("#attackMonsterBox");
                clickOn("#monster2");
                clickOn("#attackMonsterBox");
            }
            return;
        }
        clickOn("#topDoor");
        clickOn("#rightDoor");
        sleep(1000);
        if (!(controller.getRoomGraph().getCurrentRoom().getType() == null) &&
                controller.getRoomGraph().getCurrentRoom().getType().equals("challenge")) {
            verifyThat("#beginChallengeButton", NodeMatchers.isVisible());
            clickOn("#beginChallengeButton");
            verifyThat("#monster", NodeMatchers.isVisible());
            verifyThat("#monster2", NodeMatchers.isVisible());
            for (int i = 0; i < 2; i++) {
                clickOn("#monster");
                clickOn("#attackMonsterBox");
                clickOn("#monster2");
                clickOn("#attackMonsterBox");
            }
            return;
        }
    }

    @Test
    public void checkItemsDropped() {
        clickOn("#topDoor");
        sleep(1000);
        if (!(controller.getRoomGraph().getCurrentRoom().getType() == null) &&
                controller.getRoomGraph().getCurrentRoom().getType().equals("challenge")) {
            verifyThat("#beginChallengeButton", NodeMatchers.isVisible());
            clickOn("#beginChallengeButton");
            verifyThat("#monster", NodeMatchers.isVisible());
            verifyThat("#monster2", NodeMatchers.isVisible());
            clickOn("#monster");
            while(true) {
                try {
                    clickOn("#attackMonsterBox");
                    sleep(500);
                } catch (org.testfx.api.FxRobotException e) {
                    break;
                }
            }
            clickOn("#monster2");
            while(true) {
                try {
                    clickOn("#attackMonsterBox");
                    sleep(500);
                } catch (org.testfx.api.FxRobotException e) {
                    break;
                }
            }
            verifyThat("#droppedItemImageView", NodeMatchers.isVisible());
            verifyThat("#droppedItemImageView2", NodeMatchers.isVisible());
            verifyThat("#droppedItem2ImageView", NodeMatchers.isVisible());
            verifyThat("#droppedItem2ImageView2", NodeMatchers.isVisible());
            return;
        }
        clickOn("#bottomDoor");
        clickOn("#leftDoor");
        sleep(1000);
        if (!(controller.getRoomGraph().getCurrentRoom().getType() == null) &&
                controller.getRoomGraph().getCurrentRoom().getType().equals("challenge")) {
            verifyThat("#beginChallengeButton", NodeMatchers.isVisible());
            clickOn("#beginChallengeButton");
            verifyThat("#monster", NodeMatchers.isVisible());
            verifyThat("#monster2", NodeMatchers.isVisible());
            clickOn("#monster");
            while(true) {
                try {
                    clickOn("#attackMonsterBox");
                    sleep(500);
                } catch (org.testfx.api.FxRobotException e) {
                    break;
                }
            }
            clickOn("#monster2");
            while(true) {
                try {
                    clickOn("#attackMonsterBox");
                    sleep(500);
                } catch (org.testfx.api.FxRobotException e) {
                    break;
                }
            }
            verifyThat("#droppedItemImageView", NodeMatchers.isVisible());
            verifyThat("#droppedItemImageView2", NodeMatchers.isVisible());
            verifyThat("#droppedItem2ImageView", NodeMatchers.isVisible());
            verifyThat("#droppedItem2ImageView2", NodeMatchers.isVisible());
            return;
        }
        clickOn("#rightDoor");
        clickOn("#bottomDoor");
        sleep(1000);
        if (!(controller.getRoomGraph().getCurrentRoom().getType() == null) &&
                controller.getRoomGraph().getCurrentRoom().getType().equals("challenge")) {
            verifyThat("#beginChallengeButton", NodeMatchers.isVisible());
            clickOn("#beginChallengeButton");
            verifyThat("#monster", NodeMatchers.isVisible());
            verifyThat("#monster2", NodeMatchers.isVisible());
            clickOn("#monster");
            while(true) {
                try {
                    clickOn("#attackMonsterBox");
                    sleep(500);
                } catch (org.testfx.api.FxRobotException e) {
                    break;
                }
            }
            clickOn("#monster2");
            while(true) {
                try {
                    clickOn("#attackMonsterBox");
                    sleep(500);
                } catch (org.testfx.api.FxRobotException e) {
                    break;
                }
            }
            verifyThat("#droppedItemImageView", NodeMatchers.isVisible());
            verifyThat("#droppedItemImageView2", NodeMatchers.isVisible());
            verifyThat("#droppedItem2ImageView", NodeMatchers.isVisible());
            verifyThat("#droppedItem2ImageView2", NodeMatchers.isVisible());
            return;
        }
        clickOn("#topDoor");
        clickOn("#rightDoor");
        sleep(1000);
        if (!(controller.getRoomGraph().getCurrentRoom().getType() == null) &&
                controller.getRoomGraph().getCurrentRoom().getType().equals("challenge")) {
            verifyThat("#beginChallengeButton", NodeMatchers.isVisible());
            clickOn("#beginChallengeButton");
            verifyThat("#monster", NodeMatchers.isVisible());
            verifyThat("#monster2", NodeMatchers.isVisible());
            clickOn("#monster");
            while(true) {
                try {
                    clickOn("#attackMonsterBox");
                    sleep(500);
                } catch (org.testfx.api.FxRobotException e) {
                    break;
                }
            }
            clickOn("#monster2");
            while(true) {
                try {
                    clickOn("#attackMonsterBox");
                    sleep(500);
                } catch (org.testfx.api.FxRobotException e) {
                    break;
                }
            }
            verifyThat("#droppedItemImageView", NodeMatchers.isVisible());
            verifyThat("#droppedItemImageView2", NodeMatchers.isVisible());
            verifyThat("#droppedItem2ImageView", NodeMatchers.isVisible());
            verifyThat("#droppedItem2ImageView2", NodeMatchers.isVisible());
            return;
        }
    }

    @Test
    public void pickUpItemsWhileFighting() {
        clickOn("#topDoor");
        sleep(1000);
        if (!(controller.getRoomGraph().getCurrentRoom().getType() == null) &&
                controller.getRoomGraph().getCurrentRoom().getType().equals("challenge")) {
            verifyThat("#beginChallengeButton", NodeMatchers.isVisible());
            clickOn("#beginChallengeButton");
            verifyThat("#monster", NodeMatchers.isVisible());
            verifyThat("#monster2", NodeMatchers.isVisible());
            clickOn("#monster");
            while(true) {
                try {
                    clickOn("#attackMonsterBox");
                    sleep(500);
                } catch (org.testfx.api.FxRobotException e) {
                    break;
                }
            }
            clickOn("#droppedItemImageView");
            clickOn("#addToInventoryBox");
            clickOn("#droppedItemImageView2");
            clickOn("#addToInventoryBox");
            clickOn("#monster2");
            while(true) {
                try {
                    clickOn("#attackMonsterBox");
                    sleep(500);
                } catch (org.testfx.api.FxRobotException e) {
                    break;
                }
            }
            verifyThat("#droppedItemImageView", NodeMatchers.isInvisible());
            verifyThat("#droppedItemImageView2", NodeMatchers.isInvisible());
            verifyThat("#droppedItem2ImageView", NodeMatchers.isVisible());
            verifyThat("#droppedItem2ImageView2", NodeMatchers.isVisible());
            return;
        }
        clickOn("#bottomDoor");
        clickOn("#leftDoor");
        sleep(1000);
        if (!(controller.getRoomGraph().getCurrentRoom().getType() == null) &&
                controller.getRoomGraph().getCurrentRoom().getType().equals("challenge")) {
            verifyThat("#beginChallengeButton", NodeMatchers.isVisible());
            clickOn("#beginChallengeButton");
            verifyThat("#monster", NodeMatchers.isVisible());
            verifyThat("#monster2", NodeMatchers.isVisible());
            clickOn("#monster");
            while(true) {
                try {
                    clickOn("#attackMonsterBox");
                    sleep(500);
                } catch (org.testfx.api.FxRobotException e) {
                    break;
                }
            }
            clickOn("#droppedItemImageView");
            clickOn("#addToInventoryBox");
            clickOn("#droppedItemImageView2");
            clickOn("#addToInventoryBox");
            clickOn("#monster2");
            while(true) {
                try {
                    clickOn("#attackMonsterBox");
                    sleep(500);
                } catch (org.testfx.api.FxRobotException e) {
                    break;
                }
            }
            verifyThat("#droppedItemImageView", NodeMatchers.isInvisible());
            verifyThat("#droppedItemImageView2", NodeMatchers.isInvisible());
            verifyThat("#droppedItem2ImageView", NodeMatchers.isVisible());
            verifyThat("#droppedItem2ImageView2", NodeMatchers.isVisible());
            return;
        }
        clickOn("#rightDoor");
        clickOn("#bottomDoor");
        sleep(1000);
        if (!(controller.getRoomGraph().getCurrentRoom().getType() == null) &&
                controller.getRoomGraph().getCurrentRoom().getType().equals("challenge")) {
            verifyThat("#beginChallengeButton", NodeMatchers.isVisible());
            clickOn("#beginChallengeButton");
            verifyThat("#monster", NodeMatchers.isVisible());
            verifyThat("#monster2", NodeMatchers.isVisible());
            clickOn("#monster");
            while(true) {
                try {
                    clickOn("#attackMonsterBox");
                    sleep(500);
                } catch (org.testfx.api.FxRobotException e) {
                    break;
                }
            }
            clickOn("#droppedItemImageView");
            clickOn("#addToInventoryBox");
            clickOn("#droppedItemImageView2");
            clickOn("#addToInventoryBox");
            clickOn("#monster2");
            while(true) {
                try {
                    clickOn("#attackMonsterBox");
                    sleep(500);
                } catch (org.testfx.api.FxRobotException e) {
                    break;
                }
            }
            verifyThat("#droppedItemImageView", NodeMatchers.isInvisible());
            verifyThat("#droppedItemImageView2", NodeMatchers.isInvisible());
            verifyThat("#droppedItem2ImageView", NodeMatchers.isVisible());
            verifyThat("#droppedItem2ImageView2", NodeMatchers.isVisible());
            return;
        }
        clickOn("#topDoor");
        clickOn("#rightDoor");
        sleep(1000);
        if (!(controller.getRoomGraph().getCurrentRoom().getType() == null) &&
                controller.getRoomGraph().getCurrentRoom().getType().equals("challenge")) {
            verifyThat("#beginChallengeButton", NodeMatchers.isVisible());
            clickOn("#beginChallengeButton");
            verifyThat("#monster", NodeMatchers.isVisible());
            verifyThat("#monster2", NodeMatchers.isVisible());
            clickOn("#monster");
            while(true) {
                try {
                    clickOn("#attackMonsterBox");
                    sleep(500);
                } catch (org.testfx.api.FxRobotException e) {
                    break;
                }
            }
            clickOn("#droppedItemImageView");
            clickOn("#addToInventoryBox");
            clickOn("#droppedItemImageView2");
            clickOn("#addToInventoryBox");
            clickOn("#monster2");
            while(true) {
                try {
                    clickOn("#attackMonsterBox");
                    sleep(500);
                } catch (org.testfx.api.FxRobotException e) {
                    break;
                }
            }
            verifyThat("#droppedItemImageView", NodeMatchers.isInvisible());
            verifyThat("#droppedItemImageView2", NodeMatchers.isInvisible());
            verifyThat("#droppedItem2ImageView", NodeMatchers.isVisible());
            verifyThat("#droppedItem2ImageView2", NodeMatchers.isVisible());
            return;
        }
    }

    @Test
    public void leaveDefeatedRoom() {
        clickOn("#topDoor");
        sleep(1000);
        if (!(controller.getRoomGraph().getCurrentRoom().getType() == null) &&
                controller.getRoomGraph().getCurrentRoom().getType().equals("challenge")) {
            verifyThat("#beginChallengeButton", NodeMatchers.isVisible());
            clickOn("#beginChallengeButton");
            verifyThat("#monster", NodeMatchers.isVisible());
            verifyThat("#monster2", NodeMatchers.isVisible());
            clickOn("#monster");
            while(true) {
                try {
                    clickOn("#attackMonsterBox");
                    sleep(500);
                } catch (org.testfx.api.FxRobotException e) {
                    break;
                }
            }
            clickOn("#monster2");
            while(true) {
                try {
                    clickOn("#attackMonsterBox");
                    sleep(500);
                } catch (org.testfx.api.FxRobotException e) {
                    break;
                }
            }
            verifyThat("#monster", NodeMatchers.isInvisible());
            verifyThat("#monster2", NodeMatchers.isInvisible());
            clickOn("#bottomDoor");
            return;
        }
        clickOn("#bottomDoor");
        clickOn("#leftDoor");
        sleep(1000);
        if (!(controller.getRoomGraph().getCurrentRoom().getType() == null) &&
                controller.getRoomGraph().getCurrentRoom().getType().equals("challenge")) {
            verifyThat("#beginChallengeButton", NodeMatchers.isVisible());
            clickOn("#beginChallengeButton");
            verifyThat("#monster", NodeMatchers.isVisible());
            verifyThat("#monster2", NodeMatchers.isVisible());
            clickOn("#monster");
            while(true) {
                try {
                    clickOn("#attackMonsterBox");
                    sleep(500);
                } catch (org.testfx.api.FxRobotException e) {
                    break;
                }
            }
            clickOn("#monster2");
            while(true) {
                try {
                    clickOn("#attackMonsterBox");
                    sleep(500);
                } catch (org.testfx.api.FxRobotException e) {
                    break;
                }
            }
            verifyThat("#monster", NodeMatchers.isInvisible());
            verifyThat("#monster2", NodeMatchers.isInvisible());
            clickOn("#rightDoor");
            return;
        }
        clickOn("#rightDoor");
        clickOn("#bottomDoor");
        sleep(1000);
        if (!(controller.getRoomGraph().getCurrentRoom().getType() == null) &&
                controller.getRoomGraph().getCurrentRoom().getType().equals("challenge")) {
            verifyThat("#beginChallengeButton", NodeMatchers.isVisible());
            clickOn("#beginChallengeButton");
            verifyThat("#monster", NodeMatchers.isVisible());
            verifyThat("#monster2", NodeMatchers.isVisible());
            clickOn("#monster");
            while(true) {
                try {
                    clickOn("#attackMonsterBox");
                    sleep(500);
                } catch (org.testfx.api.FxRobotException e) {
                    break;
                }
            }
            clickOn("#monster2");
            while(true) {
                try {
                    clickOn("#attackMonsterBox");
                    sleep(500);
                } catch (org.testfx.api.FxRobotException e) {
                    break;
                }
            }
            verifyThat("#monster", NodeMatchers.isInvisible());
            verifyThat("#monster2", NodeMatchers.isInvisible());
            clickOn("#topDoor");
            return;
        }
        clickOn("#topDoor");
        clickOn("#rightDoor");
        sleep(1000);
        if (!(controller.getRoomGraph().getCurrentRoom().getType() == null) &&
                controller.getRoomGraph().getCurrentRoom().getType().equals("challenge")) {
            verifyThat("#beginChallengeButton", NodeMatchers.isVisible());
            clickOn("#beginChallengeButton");
            verifyThat("#monster", NodeMatchers.isVisible());
            verifyThat("#monster2", NodeMatchers.isVisible());
            clickOn("#monster");
            while(true) {
                try {
                    clickOn("#attackMonsterBox");
                    sleep(500);
                } catch (org.testfx.api.FxRobotException e) {
                    break;
                }
            }
            clickOn("#monster2");
            while(true) {
                try {
                    clickOn("#attackMonsterBox");
                    sleep(500);
                } catch (org.testfx.api.FxRobotException e) {
                    break;
                }
            }
            verifyThat("#monster", NodeMatchers.isInvisible());
            verifyThat("#monster2", NodeMatchers.isInvisible());
            clickOn("#leftDoor");
            return;
        }
    }
}
