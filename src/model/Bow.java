package model;

public class Bow extends Weapon {
    public Bow() {
        this.setType("Bow");
        this.setPathToImage("view/images/weaponBow.png");
        this.setBaseDamage(47);
        this.setHitProbability(0.6);
    }
}
