package model;

public class HealthPotion extends Potion {
    private int heal;
    public HealthPotion() {
        this.setType("HealthPotion");
        this.setPathToImage("view/images/potionHealth.png");
        this.heal = 30;
    }
    public int getHeal() {
        return heal;
    }
}
