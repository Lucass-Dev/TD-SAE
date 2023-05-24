package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import javafx.scene.image.Image;

public class TaskKiller extends Tower {

    public TaskKiller(int range, int flopPrice, int ramPrice, String name, int level, int upgradeCost, Image sprite){
        super(range, flopPrice, ramPrice, name, level, upgradeCost, sprite);
    }

    @Override
    public void attack(){

    }

    @Override
    public void detect(){

        System.out.println("test");

    }
}
