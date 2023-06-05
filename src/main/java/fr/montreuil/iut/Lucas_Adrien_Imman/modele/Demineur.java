package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Demineur extends Tower{

    public Demineur(){
        super();
    };

    public Demineur(Pane tilePane, int x, int y, Image image, int id){super(tilePane, x, y, image, id);}

    public Demineur(int range, int flopPrice, int ramPrice, String name, int level, int upgradeCost, int x, int y, int id){
        super(range, flopPrice, ramPrice, name, level, upgradeCost, new SimpleIntegerProperty(x), new SimpleIntegerProperty(y), id);
    }


    @Override
    public void attack() {

    }

    @Override
    public void detect() {
        System.out.println("test");
    }
}
