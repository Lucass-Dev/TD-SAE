package fr.montreuil.iut.Lucas_Adrien_Imman.Forges;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplacement.ModeDeplacement;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetTours.*;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.*;

public class FabricEffetTours extends  ForgeDeplaçable {


    private  Ennemy ennemyCible ;
    public FabricEffetTours(int x , int y , ModeDeplacement md ,Ennemy ennemyCible) {
        super(x,y,md);
        this.ennemyCible = ennemyCible ;
    }
    @Override
    public  EffetTour creeDeplaçable(TypeDeplaçable type) {
            switch (type){
                case ProjectileDotSH -> {
                    return new ProjectileDotSH(getX(),getY(),ennemyCible,getMd());
                }
                case ProjectileDegatsBrut -> {
                    return new ProjectileDegatsBrut(getX(),getY(),ennemyCible,getMd());
                }
                case ProjectileKamikaze -> {
                    return new ProjectileKamikaze(getX(),getY(),ennemyCible,getMd());
                }
                case ProjectileKnockBack -> {
                    return new ProjectileKnockBack(getX(),getY(),ennemyCible,getMd());
                }
                case ZoneElectrique -> {
                    return new ZoneElectrique(getX(),getY(),ennemyCible,getMd());
                }
                case ZoneRalentisseur -> {
                    return new ZoneRalentisseur(getX(),getY(),ennemyCible,getMd());
                }
                default -> {
                    return  null ;
                }
            }

        }

    }
