package model;

public class AccuracyPotion extends Potion {
    private double accuracy;
    public AccuracyPotion() {
        this.setType("AccuracyPotion");
        this.setPathToImage("view/images/potionAccuracy.png");
        this.accuracy = 1.0;
    }
    public double getAccuracy() {
        return accuracy;
    }
}
