package Entity.Survivor.Type;

import Entity.Entity;
import Entity.Survivor.Survivor;

public class Child extends Survivor {
    private int health = 20;
    final private int damage = 2;
    private boolean isAlive = true;
    private int typeCount;

    public Child(int num) {
        typeCount = num;
    }
    
    @Override
    public void doAttack(Entity target) {
        target.onAttack(damage);
    }

    @Override
    public void onAttack(int damage) {
        health -= damage;
        // if this entities health is 0 or less they are dead
        if (health <= 0) {
            isAlive = false;
        }
    }

    @Override
    public boolean checkAlive() {
        return isAlive;
    }

    @Override
    public int getTypeCount() {
        return typeCount;
    }
}
