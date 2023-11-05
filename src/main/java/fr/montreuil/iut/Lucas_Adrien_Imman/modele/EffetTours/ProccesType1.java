package fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetTours;

import javafx.collections.ObservableList;

public  class ProccesType1  {

    public void process(ObservableList listeEffets , EffetTour effetTour) {
        if (effetTour.isOnObjective() || effetTour.isOnBound()) {
            listeEffets.remove(effetTour);
        }
    }
}
