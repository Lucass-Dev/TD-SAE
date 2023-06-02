package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import javafx.scene.layout.Pane;

public class Scam extends Ennemy{
    public Scam(int x, int y, Pane tilePane, Level level, int spriteIndex) {
        super(x, y, tilePane, level, spriteIndex);
    }

    @Override
    public void doDamage() {

    }
}
