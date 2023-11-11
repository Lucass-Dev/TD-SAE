package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Player extends Entites {
    private IntegerProperty ram;
    private IntegerProperty flop;
    private IntegerProperty maxRAM;
    private String username;

    public Player(String username) {
        super(100, 100); // Initialisation de la santé à 100 et de la santé maximale à 100
        this.ram = new SimpleIntegerProperty(1000);
        this.maxRAM = new SimpleIntegerProperty(1000);
        this.flop = new SimpleIntegerProperty(500);
        this.username = username;
    }

    //OTHER METHODS

    public boolean isDead(){
        return getHealth() == 0;
    }

    public void looseLife(int l){
        if(getHealth() - l >= 0)
            setHealth(getHealth() - l);
        else
            setHealth(0);
    }

    //GETTER
    public int getLife() {
        return getHealth();
    }

    public IntegerProperty lifeProperty() {
        return healthProperty();
    }

    public int getFlop() {
        return flop.get();
    }

    public IntegerProperty flopProperty() {
        return flop;
    }

    public int getRam() {
        return ram.get();
    }

    public IntegerProperty ramProperty() {
        return ram;
    }

    public int getMaxRAM() {
        return maxRAM.get();
    }

    public IntegerProperty maxRAMProperty() {
        return maxRAM;
    }

    public IntegerProperty maxlifeProperty() {
        return maxHealthProperty();
    }

    //SETTER

    public void setLife(int life) {
        setHealth(life);
    }

    public void setRam(int ram) {
        if (ram > this.getMaxRAM()){
            this.ram.set(this.maxRAM.getValue());
        }else{
            this.ram.set(ram);
        }
    }

    public void setFlop(int flop) {
        this.flop.set(flop);
    }
}
