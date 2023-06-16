package fr.montreuil.iut.Lucas_Adrien_Imman.modele.Projectiles;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Ennemy;

public class ProjectileDotSH extends Projectile {
    public ProjectileDotSH(int x, int y, Ennemy ennemyCible) {
        super(x, y, ennemyCible);
    }


    public void agitSurLaCible() {
        if (cibleAtteint()) {
            getEnnemyCible().reductionPv(1);
        }
    }

}
