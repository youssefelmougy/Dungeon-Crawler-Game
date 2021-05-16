package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import model.AccuracyPotion;
import model.Axe;
import model.Bow;
import model.DamagePotion;
import model.HealthPotion;
import model.Item;
import model.Minotaur;
import model.Monster;
import model.Player;
import model.Potion;
import model.Room;
import model.RoomGraph;
import model.Sword;
import model.Weapon;

import java.io.IOException;


public class GameController {
    private Player player;

    private RoomGraph roomGraph = new RoomGraph();

    private Item selectedItem;

    private int selectedDroppedItemIndex;

    private int selectedMonsterIndex;

    @FXML
    private ImageView playerImage;
    @FXML
    private Text playerNameText;
    @FXML
    private Text playerMoneyText;
    @FXML
    private Rectangle healthBarPlayer;
    @FXML
    private Text healthTextPlayer;
    @FXML
    private ImageView potionEffect;

    @FXML
    private ImageView monster;
    @FXML
    private ImageView monster2;
    @FXML
    private Rectangle healthBarMonster;
    @FXML
    private Text healthTextMonster;
    @FXML
    private Rectangle healthBarMonster2;
    @FXML
    private Text healthTextMonster2;
    @FXML
    private ImageView monsterHealth2ImageViewHeart;
    @FXML
    private ImageView monsterHealth2ImageViewHeartmonsterHealth2ImageViewText;


    @FXML
    private ImageView topDoor;
    @FXML
    private ImageView rightDoor;
    @FXML
    private ImageView bottomDoor;
    @FXML
    private ImageView leftDoor;
    @FXML
    private ImageView exitDoor;
    @FXML
    private ImageView exitDungeonText;

    @FXML
    private ImageView attackMonsterBox;
    @FXML
    private ImageView addToInventoryBox;
    @FXML
    private ImageView equipBox;
    @FXML
    private ImageView beginChallengeButton;

    @FXML
    private ImageView weaponSword;
    @FXML
    private ImageView weaponAxe;
    @FXML
    private ImageView weaponBow;

    @FXML
    private Text potionHealthCounter;
    @FXML
    private Text potionAccuracyCounter;
    @FXML
    private Text potionDamageCounter;

    @FXML
    private ImageView droppedItemImageView;
    @FXML
    private ImageView droppedItemImageView2;
    @FXML
    private ImageView droppedItem2ImageView;
    @FXML
    private ImageView droppedItem2ImageView2;

    private ImageView[] droppedItemImageViews;

    public GameController() { }

    @FXML
    private void initialize() {
        exitDoor.setVisible(false);
        exitDungeonText.setVisible(false);

        resetRoom();
    }

    public void setPlayer(Player player) {
        this.player = player;
        playerNameText.setText(player.getName());
        playerMoneyText.setText("$" + player.getMoney());

        weaponSword.setVisible(false);
        weaponAxe.setVisible(false);
        weaponBow.setVisible(false);

        switch (player.getCurrWeapon().getType()) {
        case "Sword":
            playerImage.setImage(new Image("view/images/playerSword.png"));
            weaponSword.setVisible(true);
            break;
        case "Axe":
            playerImage.setImage(new Image("view/images/playerAxe.png"));
            weaponAxe.setVisible(true);
            break;
        default:
            playerImage.setImage(new Image("view/images/playerBow.png"));
            weaponBow.setVisible(true);
        }

        updatePlayerHealthView();

        potionHealthCounter.setText("0");
        potionDamageCounter.setText("0");
        potionAccuracyCounter.setText("0");

        potionEffect.setVisible(false);

        droppedItemImageViews = new ImageView[4];
        droppedItemImageViews[0] = droppedItemImageView;
        droppedItemImageViews[1] = droppedItemImageView2;
        droppedItemImageViews[2] = droppedItem2ImageView;
        droppedItemImageViews[3] = droppedItem2ImageView2;
    }

    private void updatePlayerHealthView() {
        healthBarPlayer.setWidth(
                ((double) player.getHealth() / player.getMaxHealth()) * 130
        );
        healthTextPlayer.setText(player.getHealth() + "/" + player.getMaxHealth());
    }

    public Player getPlayer() {
        return player;
    }

    @FXML
    private void movePlayerToTopRoom() {
        if (roomGraph.getCurrentRoom().getTop().getChecked()
                || roomGraph.getCurrentRoom().getMonster() == null) {
            roomGraph.moveUp();
            resetRoom();
        } else {
            System.out.println("Cannot move to unknown room without defeating current monster");
        }
    }

    @FXML
    private void movePlayerToRightRoom() {
        if (roomGraph.getCurrentRoom().getRight().getChecked()
                || roomGraph.getCurrentRoom().getMonster() == null) {
            roomGraph.moveRight();
            resetRoom();
        } else {
            System.out.println("Cannot move to unknown room without defeating current monster");
        }
    }

    @FXML
    private void movePlayerToBottomRoom() {
        if (roomGraph.getCurrentRoom().getBottom().getChecked()
                || roomGraph.getCurrentRoom().getMonster() == null) {
            roomGraph.moveDown();
            resetRoom();
        } else {
            System.out.println("Cannot move to unknown room without defeating current monster");
        }
    }

    @FXML
    private void movePlayerToLeftRoom() {
        if (roomGraph.getCurrentRoom().getLeft().getChecked()
                || roomGraph.getCurrentRoom().getMonster() == null) {
            roomGraph.moveLeft();
            resetRoom();
        } else {
            System.out.println("Cannot move to unknown room without defeating current monster");
        }
    }

    private void resetRoom() {
        roomGraph.getCurrentRoom().setCheckedTrue();

        Room currRoom = roomGraph.getCurrentRoom();

        monster.setVisible(false);
        monster2.setVisible(false);
        healthBarMonster2.setVisible(false);
        healthTextMonster2.setVisible(false);
        monsterHealth2ImageViewHeart.setVisible(false);
        monsterHealth2ImageViewHeartmonsterHealth2ImageViewText.setVisible(false);
        beginChallengeButton.setVisible(false);
        droppedItemImageView2.setVisible(false);
        droppedItem2ImageView.setVisible(false);
        droppedItem2ImageView2.setVisible(false);

        droppedItemImageView.setVisible(false);
        addToInventoryBox.setVisible(false);
        equipBox.setVisible(false);
        selectedItem = null;

        if (currRoom.getType() != null && currRoom.getType().equals("challenge")) {
            if (currRoom.getChallengeStarted()) {
                Monster[] challengeMonsters = currRoom.getChallengeMonsters();
                Item[] droppedChallengeItems = currRoom.getDroppedChallengeItems();

                healthBarMonster2.setVisible(true);
                healthTextMonster2.setVisible(true);
                monsterHealth2ImageViewHeart.setVisible(true);
                monsterHealth2ImageViewHeartmonsterHealth2ImageViewText.setVisible(true);

                if (challengeMonsters[0] != null || challengeMonsters[1] != null) {
                    topDoor.setVisible(false);
                    rightDoor.setVisible(false);
                    bottomDoor.setVisible(false);
                    leftDoor.setVisible(false);

                    if (challengeMonsters[0] != null) {
                        monster.setVisible(true);

                        healthBarMonster.setWidth(
                                ((double) challengeMonsters[0].getHealth()
                                        / challengeMonsters[0].getMaxHealth()) * 130
                        );
                        healthTextMonster.setText(
                                challengeMonsters[0].getHealth()
                                        + "/" + challengeMonsters[0].getMaxHealth()
                        );

                        switch (challengeMonsters[0].getType()) {
                        case "Spider":
                            monster.setImage(new Image("view/images/monsterSpider.png"));
                            break;
                        case "Skeleton":
                            monster.setImage(new Image("view/images/monsterSkeleton.png"));
                            break;
                        default:
                            monster.setImage(new Image("view/images/monsterMinotaur.png"));
                        }
                    }

                    if (challengeMonsters[1] != null) {
                        monster2.setVisible(true);

                        healthBarMonster2.setWidth(
                                ((double) challengeMonsters[1].getHealth()
                                        / challengeMonsters[1].getMaxHealth()) * 130
                        );
                        healthTextMonster2.setText(
                                challengeMonsters[1].getHealth()
                                        + "/" + challengeMonsters[1].getMaxHealth()
                        );

                        switch (challengeMonsters[1].getType()) {
                        case "Spider":
                            monster2.setImage(new Image("view/images/monsterSpider.png"));
                            break;
                        case "Skeleton":
                            monster2.setImage(new Image("view/images/monsterSkeleton.png"));
                            break;
                        default:
                            monster2.setImage(new Image("view/images/monsterMinotaur.png"));
                        }
                    }
                } else {
                    topDoor.setVisible(roomGraph.getCurrentRoom().getTop() != null);
                    rightDoor.setVisible(roomGraph.getCurrentRoom().getRight() != null);
                    bottomDoor.setVisible(roomGraph.getCurrentRoom().getBottom() != null);
                    leftDoor.setVisible(roomGraph.getCurrentRoom().getLeft() != null);

                    for (int i = 0; i < droppedChallengeItems.length; i++) {
                        if (droppedChallengeItems[i] != null) {
                            droppedItemImageViews[i].setImage(
                                    new Image(droppedChallengeItems[i].getPathToImage())
                            );
                        }
                        droppedItemImageViews[i].setVisible(droppedChallengeItems[0] != null);
                    }
                }
            } else {
                beginChallengeButton.setVisible(true);
            }
        } else {
            if (currRoom.getMonster() != null) {
                Monster currMonster = currRoom.getMonster();

                monster.setVisible(currMonster != null);

                currMonster.resetHealth();
                healthBarMonster.setWidth(
                        ((double) currMonster.getHealth() / currMonster.getMaxHealth()) * 130
                );
                healthTextMonster.setText(
                        currMonster.getHealth() + "/" + currMonster.getMaxHealth()
                );

                switch (currMonster.getType()) {
                case "Spider":
                    monster.setImage(new Image("view/images/monsterSpider.png"));
                    break;
                case "Skeleton":
                    monster.setImage(new Image("view/images/monsterSkeleton.png"));
                    break;
                default:
                    monster.setImage(new Image("view/images/monsterMinotaur.png"));
                }
            } else if (currRoom.getDroppedItem() != null) {
                droppedItemImageView.setImage(
                        new Image(currRoom.getDroppedItem().getPathToImage())
                );
                droppedItemImageView.setVisible(true);
            }
        }

        attackMonsterBox.setVisible(false);

        if (currRoom.getType() != null
                && currRoom.getType().equals("exit")) {
            topDoor.setVisible(false);
            rightDoor.setVisible(false);
            bottomDoor.setVisible(false);
            leftDoor.setVisible(false);
        } else {
            topDoor.setVisible(roomGraph.getCurrentRoom().getTop() != null);
            rightDoor.setVisible(roomGraph.getCurrentRoom().getRight() != null);
            bottomDoor.setVisible(roomGraph.getCurrentRoom().getBottom() != null);
            leftDoor.setVisible(roomGraph.getCurrentRoom().getLeft() != null);
        }
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

    @FXML
    private void askAttackMonster() {
        attackMonsterBox.setVisible(true);
        equipBox.setVisible(false);
        selectedItem = null;

        Room currRoom = roomGraph.getCurrentRoom();
        if (currRoom.getType() != null && currRoom.getType().equals("challenge")) {
            selectedMonsterIndex = 0;
        } else {
            selectedMonsterIndex = -1;
        }
    }

    @FXML
    private void askAttackMonster2() {
        attackMonsterBox.setVisible(true);
        equipBox.setVisible(false);
        selectedItem = null;

        selectedMonsterIndex = 1;
    }

    @FXML
    private void attackMonster() {
        Room currRoom = roomGraph.getCurrentRoom();
        Monster currMonster =
                selectedMonsterIndex == -1
                        ? currRoom.getMonster()
                        : currRoom.getChallengeMonsters()[selectedMonsterIndex];

        currMonster.takeDamage(player);

        if (!player.hasActivePotion()) {
            potionEffect.setVisible(false);
        }

        if (selectedMonsterIndex != 1) {
            resetMonsterAfterAttack(currRoom, currMonster, healthBarMonster, healthTextMonster);
        } else {
            resetMonsterAfterAttack(currRoom, currMonster, healthBarMonster2, healthTextMonster2);
        }

        player.takeDamage(currMonster);
        updatePlayerHealthView();
        if (!player.isAlive()) {
            try {
                FXMLLoader fxmlloader
                        = new FXMLLoader(getClass().getResource("/view/loseScreen.fxml"));
                Parent loader = fxmlloader.load();

                Scene scene = new Scene(loader);

                Stage primaryStage = (Stage) playerNameText.getScene().getWindow();
                LoseScreenController loseScreenController = fxmlloader.getController();

                loseScreenController.setTxtFields(player);

                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        }
    }

    private void resetMonsterAfterAttack(
            Room currRoom,
            Monster currMonster,
            Rectangle healthBarMonster,
            Text healthTextMonster
    ) {
        healthBarMonster.setWidth(
                ((double) currMonster.getHealth() / currMonster.getMaxHealth()) * 130
        );
        healthTextMonster.setText(currMonster.getHealth() + "/" + currMonster.getMaxHealth());
        if (!currMonster.isAlive()) {
            if (currMonster instanceof Minotaur) {
                exitDungeonText.setVisible(true);
                exitDoor.setVisible(true);
            } else if (selectedMonsterIndex == -1) {
                droppedItemImageView.setImage(
                        new Image(currRoom.getDroppedItem().getPathToImage())
                );
                droppedItemImageView.setVisible(true);

                monster.setVisible(false);
            } else {
                Item[] droppedChallengeItems = currRoom.getDroppedChallengeItems();
                if (selectedMonsterIndex == 0) {
                    droppedItemImageView.setImage(
                            new Image(droppedChallengeItems[0].getPathToImage())
                    );
                    droppedItemImageView.setVisible(droppedChallengeItems[0] != null);

                    droppedItemImageView2.setImage(
                            new Image(droppedChallengeItems[1].getPathToImage())
                    );
                    droppedItemImageView2.setVisible(droppedChallengeItems[1] != null);

                    monster.setVisible(false);
                } else if (selectedMonsterIndex == 1) {
                    droppedItem2ImageView.setImage(
                            new Image(droppedChallengeItems[2].getPathToImage())
                    );
                    droppedItem2ImageView.setVisible(droppedChallengeItems[2] != null);

                    droppedItem2ImageView2.setImage(
                            new Image(droppedChallengeItems[3].getPathToImage())
                    );
                    droppedItem2ImageView2.setVisible(droppedChallengeItems[3] != null);

                    monster2.setVisible(false);
                }
            }

            roomGraph.getCurrentRoom().killMonster(selectedMonsterIndex);

            attackMonsterBox.setVisible(false);
        }
    }

    @FXML
    private void droppedItemClicked() {
        Room currRoom = roomGraph.getCurrentRoom();
        if (currRoom.getType() != null && currRoom.getType().equals("challenge")) {
            selectedDroppedItemIndex = 0;
        } else {
            selectedDroppedItemIndex = -1;
        }
        addToInventoryBox.setVisible(true);
    }

    @FXML
    private void droppedItemClicked2() {
        selectedDroppedItemIndex = 1;
        addToInventoryBox.setVisible(true);
    }

    @FXML
    private void droppedItem2Clicked() {
        selectedDroppedItemIndex = 2;
        addToInventoryBox.setVisible(true);
    }

    @FXML
    private void droppedItem2Clicked2() {
        selectedDroppedItemIndex = 3;
        addToInventoryBox.setVisible(true);
    }

    @FXML
    private void addDroppedItemToInventory() {
        Room currRoom = roomGraph.getCurrentRoom();
        Item droppedItem
                = selectedDroppedItemIndex == -1
                ? currRoom.getDroppedItem()
                : currRoom.getDroppedChallengeItems()[selectedDroppedItemIndex];

        if (droppedItem instanceof Potion) {
            int potionCount = player.pickUpPotion((Potion) droppedItem);
            if (droppedItem instanceof HealthPotion) {
                potionHealthCounter.setText(potionCount + "");
            } else if (droppedItem instanceof DamagePotion) {
                potionDamageCounter.setText(potionCount + "");
            } else if (droppedItem instanceof AccuracyPotion) {
                potionAccuracyCounter.setText(potionCount + "");
            }
        } else {
            player.pickUpWeapon((Weapon) droppedItem);
            if (droppedItem instanceof Sword) {
                weaponSword.setVisible(true);
            } else if (droppedItem instanceof Axe) {
                weaponAxe.setVisible(true);
            } else {
                weaponBow.setVisible(true);
            }
        }

        currRoom.pickUpItem(selectedDroppedItemIndex);
        switch (selectedDroppedItemIndex) {
        case 1:
            droppedItemImageView2.setVisible(false);
            break;
        case 2:
            droppedItem2ImageView.setVisible(false);
            break;
        case 3:
            droppedItem2ImageView2.setVisible(false);
            break;
        default:
            droppedItemImageView.setVisible(false);
        }

        addToInventoryBox.setVisible(false);
    }

    @FXML
    private void swapToSword() {
        selectedItem = new Sword();

        equipBox.setVisible(true);
    }

    @FXML
    private void swapToAxe() {
        selectedItem = new Axe();

        equipBox.setVisible(true);
    }

    @FXML
    private void swapToBow() {
        selectedItem = new Bow();

        equipBox.setVisible(true);
    }

    @FXML
    private void useHealthPotion() {
        selectedItem = new HealthPotion();

        equipBox.setVisible(true);
    }

    @FXML
    private void useDamagePotion() {
        selectedItem = new DamagePotion();

        equipBox.setVisible(true);
    }

    @FXML
    private void useAccuracyPotion() {
        selectedItem = new AccuracyPotion();

        equipBox.setVisible(true);
    }

    @FXML
    private void equipButtonClicked() {
        if (selectedItem instanceof HealthPotion) {
            int healthPotionCount = player.useHealthPotion();
            potionHealthCounter.setText(healthPotionCount + "");

            updatePlayerHealthView();
        } else if (selectedItem instanceof DamagePotion) {
            int damagePotionCount = player.useDamagePotion();
            potionDamageCounter.setText(damagePotionCount + "");

            potionEffect.setVisible(true);
        } else if (selectedItem instanceof AccuracyPotion) {
            int accuracyPotionCount = player.useAccuracyPotion();
            potionAccuracyCounter.setText(accuracyPotionCount + "");

            potionEffect.setVisible(true);
        } else if (selectedItem instanceof Sword) {
            player.swapToSword();
            playerImage.setImage(new Image("view/images/playerSword.png"));
        } else if (selectedItem instanceof Axe) {
            player.swapToAxe();
            playerImage.setImage(new Image("view/images/playerAxe.png"));
        } else if (selectedItem instanceof Bow) {
            player.swapToBow();
            playerImage.setImage(new Image("view/images/playerBow.png"));
        }

        selectedItem = null;
        equipBox.setVisible(false);
    }

    @FXML
    private void beginChallenge() {
        roomGraph.getCurrentRoom().startChallenge();
        resetRoom();
    }

    public RoomGraph getRoomGraph() {
        return roomGraph;
    }

    public void setSwordVisible() {
        weaponSword.setVisible(true);
    }

    public void setBowVisible() {
        weaponBow.setVisible(true);
    }

    public void setAxeVisible() {
        weaponAxe.setVisible(true);
    }
}
