package model;

public class Minotaur extends Monster {
    public Minotaur() {
        this.setType("Minotaur");
        this.setMaxHealth(200);
        this.setHealth(200);
        this.setHitProbability(0.4);
        this.setAttackDamage(12);
    }
}
