package ca.cmpt213.FortressDefense.GameLogic;

public class Fortress {
    private int Health;

    public Fortress(int health) {
        Health = health;
    }

    public int getHealth() {
        return Health;
    }
    public void attack(int damage){
        this.Health -= damage;
    }
}
