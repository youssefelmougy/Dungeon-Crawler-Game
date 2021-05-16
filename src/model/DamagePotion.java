package model;

public class DamagePotion extends Potion {
    private int bonusDamage;
    public DamagePotion() {
        this.setType("DamagePotion");
        this.setPathToImage("view/images/potionAttack.png");
        this.bonusDamage = 15;
    }
    public int getBonusDamage() {
        return bonusDamage;
    }
}
