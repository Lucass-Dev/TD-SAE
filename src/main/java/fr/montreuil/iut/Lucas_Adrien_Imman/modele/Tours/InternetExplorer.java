package fr.montreuil.iut.Lucas_Adrien_Imman.modele.Tours;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.AgirSurCible;

public class InternetExplorer extends Tower implements AgirSurCible {

    public InternetExplorer(int x, int y){
        super(x, y, "Internet Explorer", 100, 325, 475, 75, 125, 3, 10, 50, 50);
    }

    @Override
    public void agitSurLaCible() {
        if (cibleAtteint()){
            getEnnemyCible().setSpeed(1);
        }
    }
}