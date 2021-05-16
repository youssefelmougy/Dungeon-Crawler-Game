package model;

public class Spider extends Monster {
    public Spider() {
        this.setType("Spider");
        this.setMaxHealth(50);
        this.setHealth(50);
        this.setHitProbability(0.5);
        this.setAttackDamage(7);
    }
}
