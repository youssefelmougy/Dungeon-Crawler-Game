package model;

public class Skeleton extends Monster {
    public Skeleton() {
        this.setType("Skeleton");
        this.setMaxHealth(80);
        this.setHealth(80);
        this.setHitProbability(0.4);
        this.setAttackDamage(9);
    }
}
