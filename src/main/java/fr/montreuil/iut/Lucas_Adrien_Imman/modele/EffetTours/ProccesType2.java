
package fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetTours;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.DotSH;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Ennemy;
import javafx.collections.ObservableList;


public class ProccesType2 {
    private int cpt ;

    public void process(ObservableList listeEffets , EffetTour effetTour , ObservableList<Ennemy> ennemies , Ennemy e) {
        if (effetTour.isOnObjective()) {
            listeEffets.remove(effetTour);
            cpt++;
            if (cpt == 3) {
                ennemies.remove(effetTour.getEnnemyCible());
                ennemies.add(e) ;
                cpt = 0;
            }
        }
    }
}

