package fr.montreuil.iut.Lucas_Adrien_Imman.modele.State;

public class Poison extends CooldownState{
    public Poison(int startTick, int delay, int amount) {
        super(startTick, delay, amount);
    }

    @Override
    void doEffect() {
    }
}
