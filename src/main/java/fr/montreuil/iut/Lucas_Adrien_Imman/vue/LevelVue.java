package fr.montreuil.iut.Lucas_Adrien_Imman.vue;

import fr.montreuil.iut.Lucas_Adrien_Imman.Main;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Level;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.TaskKiller;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Tower;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class LevelVue {
    private Pane tilePane;
    private Pane levelPane;
    private TowerVue towerVue;
    private Level level;


    public LevelVue(Level level, Pane tilePane, Pane levelPane){
        this.level = level;
        this.tilePane = tilePane;
        this.levelPane = levelPane;
    }

    public void placeTower(int x, int y){
        System.out.println("la tour est placée en "+ x +" : "+ y);
        TaskKiller tk = new TaskKiller(this.levelPane, x, y);
        this.level.addTower(tk);


    }

    public void createShopMenu(VBox towerShopVbox){
        int totalTower = 0;
        String[] listTower = new File("src/main/resources/fr/montreuil/iut/Lucas_Adrien_Imman/graphics/tower").list();
        for (String file: listTower) {
            if (file.contains(".png")){
                totalTower++;
            }
        }


        for (int i = 0; i < totalTower; i++) {
            HBox newShopItem = new HBox();
            Image newShopItemImage = new Image(Main.class.getResourceAsStream("graphics/tower/"+i+".png"));
            newShopItem.getChildren().add(new ImageView(newShopItemImage));
            newShopItem.getStyleClass().add("shopItem");
            towerShopVbox.getChildren().add(newShopItem);
        }

    }
}
