package fr.montreuil.iut.Lucas_Adrien_Imman.modele.State;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Player;

public class RamFreezer extends CooldownState{
    public RamFreezer(int startTick, int delay, int amount) {
        super(startTick, delay, amount);
    }

    @Override
    void doEffect(Player p) {

    }
}
