package fr.montreuil.iut.Lucas_Adrien_Imman.modele.Projectiles;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Ennemy;

public class ProjectileDotSH extends Projectile {
    public ProjectileDotSH(int x, int y, Ennemy e) {
        super(x, y, e);
    }


    public void agitSurLaCible() {
        if (cibleAtteint()) {
            getE().reductionPv(100);
        }
    }

}
