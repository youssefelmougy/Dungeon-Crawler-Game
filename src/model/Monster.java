package model;

public abstract class Monster {
    private String type;
    private int health;
    private double hitProbability;
    private int attackDamage;
    private int maxHealth;

    public int getHealth() {
        return this.health;
    }

    public void takeDamage(Player player) {
        int playerDamage = player.computeAttackDamage();
        health = Math.max(health - playerDamage, 0);
        player.addDamageDealt(playerDamage);
        if (health <= 0) {
            player.incrementMonstersDefeated();
        }
    }

    public int computeAttackDamage() {
        return (Math.random() < hitProbability) ? attackDamage : 0;
    }

    public String getType() {
        return type;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void resetHealth() {
        this.health = maxHealth;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setHitProbability(double hitProbability) {
        this.hitProbability = hitProbability;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
}
