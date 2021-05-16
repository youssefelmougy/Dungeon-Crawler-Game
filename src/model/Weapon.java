package model;

public abstract class Weapon extends Item {
    private int baseDamage;
    private double hitProbability;

    public int computeAttackDamage() {
        return (Math.random() < hitProbability) ? baseDamage : 0;
    }

    // getters/setters

    public int getBaseDamage() {
        return baseDamage;
    }
    public double getHitProbability() {
        return hitProbability;
    }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }
    public void setHitProbability(double hitProbability) {
        this.hitProbability = hitProbability;
    }
}
