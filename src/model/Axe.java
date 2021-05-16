package model;

public class Axe extends Weapon {
    public Axe() {
        this.setType("Axe");
        this.setPathToImage("view/images/weaponAxe.png");
        this.setBaseDamage(30);
        this.setHitProbability(0.95);
    }
}
