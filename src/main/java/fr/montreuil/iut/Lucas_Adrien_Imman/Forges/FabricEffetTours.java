package fr.montreuil.iut.Lucas_Adrien_Imman.Forges;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplacement.DeplacementBFS;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplacement.ModeDeplacement;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetTours.*;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.*;

public class FabricEffetTours {



    public FabricEffetTours() {
    }



    public  EffetTour creeEffetTour(TypeEffetTour type,int x , int y,Ennemy ennemyCible,ModeDeplacement modeDeplacement) {

            switch (type){
                case ProjectileDotSH -> {
                    return new ProjectileDotSH( x ,  y ,ennemyCible,modeDeplacement);
                }
                case ProjectileDegatsBrut -> {
                    return new ProjectileDegatsBrut( x ,  y ,ennemyCible,modeDeplacement);
                }
                case ProjectileKamikaze -> {
                    return new ProjectileKamikaze( x ,  y ,ennemyCible,modeDeplacement);
                }
                case ProjectileKnockBack -> {
                    return new ProjectileKnockBack( x ,  y ,ennemyCible,modeDeplacement);
                }
                case ZoneElectrique -> {
                    return new ZoneElectrique( x ,  y ,ennemyCible,modeDeplacement);
                }
                case ZoneRalentisseur -> {
                    return new ZoneRalentisseur( x ,  y ,ennemyCible,modeDeplacement);
                }
                default -> {
                    return  null ;
                }
            }

        }

    }
