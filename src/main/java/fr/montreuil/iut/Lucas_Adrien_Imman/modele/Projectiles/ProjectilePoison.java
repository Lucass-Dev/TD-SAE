package fr.montreuil.iut.Lucas_Adrien_Imman.modele.Projectiles;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Ennemy;

public class ProjectilePoison extends Projectile {
    public ProjectilePoison(int x, int y, Ennemy e) {
        super(x, y, e);
    }


    public void agitSurLaCible() {
        if (cibleAtteint()) {
            // e.setY(e.getY()-e.getSpeed());
            int nouvellePosX = e.getX() + 1;
            int nouvellePosY = e.getY() + 1;

            // Calculer la nouvelle vitesse en fonction du knockback
            int nouvelleVitesse = e.getSpeed()+1;

            // Mettre à jour la position et la vitesse de l'acteur avec le knockback
            e.setX(nouvellePosX);
         //   e.setY(nouvellePosY);
         //   e.setSpeed(nouvelleVitesse);


        }
    }

}
