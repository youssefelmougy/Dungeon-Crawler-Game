package model;

import java.util.HashMap;
import java.util.Map;

public class Player {
    private final String name;
    private final int difficulty;
    private final int maxHealth = 100;
    private Weapon currWeapon;
    private int health;
    private int money;

    private Sword sword;
    private Axe axe;
    private Bow bow;

    private HealthPotion healthPotion;
    private DamagePotion damagePotion;
    private AccuracyPotion accuracyPotion;

    private Map<Item, Integer> inventory;

    private boolean activeDamagePotion;
    private int damagePotionCount;
    private boolean activeAccuracyPotion;
    private int accuracyPotionCount;

    // statistics
    private int damageDealt;
    private int monstersDefeated;

    public Player(String name, int difficulty, String weaponName) throws IllegalArgumentException {
        if (name.equals("") || name.trim().length() == 0) {
            throw new IllegalArgumentException("Cannot set empty or"
                    + " whitespace-only name for player");
        }

        this.name = name;
        this.difficulty = difficulty;

        sword = new Sword();
        axe = new Axe();
        bow = new Bow();

        healthPotion = new HealthPotion();
        damagePotion = new DamagePotion();
        accuracyPotion = new AccuracyPotion();

        inventory = new HashMap<>();
        inventory.put(sword, 0);
        inventory.put(axe, 0);
        inventory.put(bow, 0);
        inventory.put(healthPotion, 0);
        inventory.put(damagePotion, 0);
        inventory.put(accuracyPotion, 0);

        if (weaponName.equals("Axe")) {
            currWeapon = axe;
            inventory.put(axe, 0);
        } else if (weaponName.equals("Sword")) {
            currWeapon = sword;
            inventory.put(sword, 1);
        } else {
            currWeapon = bow;
            inventory.put(bow, 1);
        }

        activeAccuracyPotion = false;
        activeDamagePotion = false;
        accuracyPotionCount = 0;
        damagePotionCount = 0;
        this.health = maxHealth;
        if (difficulty == 3) {
            this.money = 200;
        } else if (difficulty == 2) {
            this.money = 500;
        } else {
            this.money = 800;
        }
        monstersDefeated = 0;
        damageDealt = 0;
    }

    public String getName() {
        return name;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public Weapon getCurrWeapon() {
        return currWeapon;
    }

    public int getMoney() {
        return money;
    }

    public int getHealth() {
        return health;
    }

    public void setHealthZero() {
        health = 0;
    }

    public void setHealth(int hp) {
        health = hp;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public boolean hasActivePotion() {
        return activeDamagePotion || activeAccuracyPotion;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int computeAttackDamage() {
        if (activeAccuracyPotion) {
            int bonus = 0;
            if (activeDamagePotion) {
                bonus = damagePotion.getBonusDamage();
                damagePotionCount--;
            }
            accuracyPotionCount--;
            updatePotions();
            return currWeapon.getBaseDamage() + bonus;
        }
        if (activeDamagePotion) {
            int bonus = damagePotion.getBonusDamage();
            damagePotionCount--;
            updatePotions();
            return (Math.random() > currWeapon.getHitProbability())
                    ? currWeapon.getBaseDamage() + bonus : 0;
        }

        return currWeapon.computeAttackDamage();
    }

    public void takeDamage(Monster monster) {
        health -= monster.computeAttackDamage();
    }

    public int pickUpPotion(Potion potion) {
        if (potion instanceof HealthPotion) {
            inventory.put(healthPotion, inventory.get(healthPotion) + 1);
            return inventory.get(healthPotion);
        } else if (potion instanceof DamagePotion) {
            inventory.put(damagePotion, inventory.get(damagePotion) + 1);
            return inventory.get(damagePotion);
        } else {
            inventory.put(accuracyPotion, inventory.get(accuracyPotion) + 1);
            return inventory.get(accuracyPotion);
        }
    }

    public void pickUpWeapon(Weapon weapon) {
        if (weapon instanceof Sword && inventory.get(sword) == 0) {
            inventory.put(sword, 1);
        } else if (weapon instanceof Axe && inventory.get(axe) == 0) {
            inventory.put(axe, 1);
        } else if (weapon instanceof Bow && inventory.get(bow) == 0) {
            inventory.put(bow, 1);
        }
    }

    public void swapToSword() {
        if (inventory.get(sword) == 0) {
            throw new java.util.NoSuchElementException("Player doesn't have that weapon");
        }
        currWeapon = sword;
    }

    public void swapToAxe() {
        System.out.println(inventory.get(axe));
        if (inventory.get(axe) == 0) {
            throw new java.util.NoSuchElementException("Player doesn't have that weapon");
        }
        currWeapon = axe;
    }

    public void swapToBow() {
        if (inventory.get(bow) == 0) {
            throw new java.util.NoSuchElementException("Player doesn't have that weapon");
        }
        currWeapon = bow;
    }

    public int useHealthPotion() {
        if (!(inventory.get(healthPotion) > 0)) {
            throw new java.util.NoSuchElementException("Player doesn't have any health potions");
        }

        if (health < maxHealth) {
            health = Math.min(health + healthPotion.getHeal(), maxHealth);
            inventory.put(healthPotion, inventory.get(healthPotion) - 1);
        }

        return inventory.get(healthPotion);
    }

    public int useDamagePotion() {
        if (!(inventory.get(damagePotion) > 0)) {
            throw new java.util.NoSuchElementException("Player doesn't have any damage potions");
        }
        if (!activeDamagePotion) {
            activeDamagePotion = true;
            damagePotionCount = 3;
            inventory.put(damagePotion, inventory.get(damagePotion) - 1);
        }

        return inventory.get(damagePotion);
    }

    public int useAccuracyPotion() {
        if (!(inventory.get(accuracyPotion) > 0)) {
            throw new java.util.NoSuchElementException("Player doesn't have any accuracy potions");
        }
        if (!activeAccuracyPotion) {
            activeAccuracyPotion = true;
            accuracyPotionCount = 3;
            inventory.put(accuracyPotion, inventory.get(accuracyPotion) - 1);
        }

        return inventory.get(accuracyPotion);
    }

    private void updatePotions() {
        if (damagePotionCount <= 0) {
            activeDamagePotion = false;
        }
        if (accuracyPotionCount <= 0) {
            activeAccuracyPotion = false;
        }
    }

    public int getDamageDealt() {
        return damageDealt;
    }

    public int getMonstersDefeated() {
        return monstersDefeated;
    }

    public void addDamageDealt(int damage) {
        damageDealt += damage;
    }

    public void incrementMonstersDefeated() {
        monstersDefeated++;
    }
}
