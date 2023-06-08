package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Demineur extends Tower{

    public Demineur(int x, int y){
        super(x, y, "Demineur", 100, 250, 300, 75, 175, 2);
    }
}
