package fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetTours;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplacement.ModeDeplacement;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.*;

public class ProjectileKnockBack extends EffetTour {

    private static final int DEFAULT_INITIAL_HEALTH = 100;
    private static final int DEFAULT_MAX_HEALTH = 100;

    public ProjectileKnockBack(int x,
                               int y,
                               Ennemy ennemyCible,
                               ModeDeplacement md
    ){

        super(x, y, DEFAULT_INITIAL_HEALTH, DEFAULT_MAX_HEALTH, 0,ennemyCible, 16,md);
    }


    @Override
    public void agirSurLaCible() {
        int knockBack = 2;
        Ennemy e = getEnnemyCible();
        int ennemyDirection = e.getDirection();

        if (e.isCentered()) {
            switch (ennemyDirection) {

                case 2:
                    e.setXValue(e.getXValue() - knockBack);
                    break;
                case 4:
                    e.setXValue(e.getXValue() + knockBack);
                    break;
                case 1:
                    e.setYValue(e.getYValue() + knockBack);
                    break;
                case 3:
                    e.setYValue(e.getYValue() - knockBack);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + ennemyDirection);
            }
        }

     /*   if (e.isCentered()) {
            if(e.getDirection()==2){
                e.setXValue(e.getXValue()-knockBack);
            }
            else if(e.getDirection() == 4){
                e.setXValue(e.getXValue()+knockBack);
            }
            else if(e.getDirection()==1){
                e.setYValue(e.getYValue()+knockBack);
            }
            else if(e.getDirection()==3){
                e.setYValue(e.getYValue()-knockBack);
            }
        }*/
    }


}
