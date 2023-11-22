package fr.montreuil.iut.Lucas_Adrien_Imman.Forges;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplacement.DeplacementBFS;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplacement.ModeDeplacement;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetTours.*;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.*;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Tours.*;

public class FabricTour {



    public FabricTour() {
    }

    public Tour creeTour(TypeTours type, int x , int y) {

        switch (type){
            case Demineur -> {
                return new Demineur( x , y );
            }
            case TaskKiller -> {
                return new TaskKiller( x , y );
            }
            case PDFConverter -> {
                return new PDFConverter( x , y);
            }
            case NordVPN -> {
                return new NordVPN( x , y );
            }
            case CCleaner -> {
                return new CCleaner( x , y );
            }
            case InternetExplorer -> {
                return new InternetExplorer( x , y);
            }
            default -> {
                return  null ;
            }
        }

    }

}
