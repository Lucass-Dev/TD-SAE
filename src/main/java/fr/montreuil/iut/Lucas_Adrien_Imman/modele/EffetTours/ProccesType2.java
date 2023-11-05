package fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetTours;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.DotSH;
import javafx.collections.ObservableList;

public class ProccesType2 {
    public void process(ObservableList listeEffets , EffetTour effetTour) {
        if (effetTour.isOnObjective()) {
            listeEffets.remove(effetTour);
            cpt++;
            if (cpt == 3) {
                ennemies.remove(effetTour.getEnnemyCible());
                this.ennemies.add(new DotSH(effetTour.getEnnemyCible().getX(), effetTour.getEnnemyCible().getY(), levelPane, this, this.player, this.ground.getStartDirection()));
                cpt = 0;
            }
        }
    }
}
