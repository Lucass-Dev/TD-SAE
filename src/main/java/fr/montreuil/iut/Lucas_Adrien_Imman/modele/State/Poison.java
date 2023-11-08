package fr.montreuil.iut.Lucas_Adrien_Imman.modele.State;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Player;

public class Poison extends CooldownState{
    public Poison(int startTick, int delay, int amount) {
        super(startTick, delay, amount);
    }

    @Override
    void doEffect() {
    }

    @Override
    void doEffect(Player p) {

    }
}
