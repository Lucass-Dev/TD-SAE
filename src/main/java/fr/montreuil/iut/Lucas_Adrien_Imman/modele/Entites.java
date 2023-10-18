package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Entites {
    protected IntegerProperty health;
    protected IntegerProperty maxHealth;

    public Entites(int initialHealth, int maxHealth) {
        this.health = new SimpleIntegerProperty(initialHealth);
        this.maxHealth = new SimpleIntegerProperty(maxHealth);
    }



    // Méthodes pour gérer la santé
    public void damage(int amount) {
        if(health.getValue() - amount >= 0) {
            health.set(health.getValue() - amount);
        } else {
            health.set(0);
        }
    }

    public void heal(int amount) {
        if(health.getValue() + amount <= maxHealth.getValue()) {
            health.set(health.getValue() + amount);
        } else {
            health.set(maxHealth.getValue());
        }
    }

    // Getters et Setters pour health
    public int getHealth() {
        return health.get();
    }

    public void setHealth(int health) {
        this.health.set(health);
    }

    public IntegerProperty healthProperty() {
        return health;
    }

    // Getters et Setters pour maxHealth
    public int getMaxHealth() {
        return maxHealth.get();
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth.set(maxHealth);
    }

    public IntegerProperty maxHealthProperty() {
        return maxHealth;
    }


}


