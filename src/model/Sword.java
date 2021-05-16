package model;

public class Sword extends Weapon {
    public Sword() {
        this.setType("Sword");
        this.setPathToImage("view/images/weaponSword.png");
        this.setBaseDamage(36);
        this.setHitProbability(0.8);
    }
}
