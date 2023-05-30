package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import javafx.scene.layout.Pane;

public class DotSH extends Ennemy{
    public DotSH(int x, int y, Pane tilePane, Level level) {
        super(x, y, tilePane, level);
    }

    @Override
    public void doDamage() {

    }

    @Override
    public void move() {
        int[] pos = new int[2];
        pos[0] = this.getX()/32;
        pos[1] = this.getY()/32;
        int travelingValue = this.getLevel().getTileValue(pos);

        //code pour le déplacement
    }
}
