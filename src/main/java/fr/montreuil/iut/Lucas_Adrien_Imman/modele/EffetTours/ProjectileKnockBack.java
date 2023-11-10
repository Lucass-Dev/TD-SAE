package fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetTours;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplacement.ModeDeplacement;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.*;

public class ProjectileKnockBack extends Projectile {
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Ennemy;
import javafx.collections.ObservableList;

    private static final int DEFAULT_INITIAL_HEALTH = 100;
    private static final int DEFAULT_MAX_HEALTH = 100;

    public ProjectileKnockBack(int x, int y, Ennemy ennemyCible, ModeDeplacement md) {
        super(x, y, DEFAULT_INITIAL_HEALTH, DEFAULT_MAX_HEALTH, ennemyCible, md);
public class ProjectileKnockBack extends EffetTour  {
    public ProjectileKnockBack(int x, int y, Ennemy ennemyCible) {
        super(x, y, ennemyCible,16);
    }

    @Override
    public void agitSurLaCible() {
        int knockBack = 2;
        Ennemy e = getEnnemyCible();
        if (e.isCentered()) {
        int knockBack = 2; //fait reculer l'ennemi ciblé de 2 pixel par rapport a sa direction actuel
        Ennemy e = getEnnemyCible() ;

        if(e.isCentered()){
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
        }
    }


}
