package fr.montreuil.iut.Lucas_Adrien_Imman.vue;

import fr.montreuil.iut.Lucas_Adrien_Imman.Main;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class LevelVue {
    private Pane tilePane;
    private Pane levelPane;
    private TowerVue towerVue;
    private Level level;

    public LevelVue(){}

    public LevelVue(Level level, Pane tilePane, Pane levelPane){
        this.level = level;
        this.tilePane = tilePane;
        this.levelPane = levelPane;
    }

    public Tower placeTower(int[] pos, Image image){
        TaskKiller tk = new TaskKiller(this.levelPane, pos[0]*32, pos[1]*32, image);

        return tk;
    }

    public Ennemy placeEnnemy(int[] pos){
         DotSH dsh = new DotSH(pos[0]*32 + 16, pos[1]*32 + 16, this.tilePane, this.level, 0);
         dsh.setId(this.level.getEnnemies().size());
        return dsh;
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
            newShopItem.setId("tower_"+i);
            towerShopVbox.getChildren().add(newShopItem);
        }
    }

    public void createATH(Player p, HBox location) {
        createBar(location, Color.RED, "Life", p.lifeProperty(), p.maxlifeProperty(), "lifebarPane", 35, 400, true);
        createBar(location, Color.CADETBLUE, "RAM", p.ramProperty(), p.maxRAMProperty(), "rambarPane", 35, 400, true);
        Label flopLabel = new Label();
        flopLabel.textProperty().bind(p.flopProperty().asString());
        location.getChildren().add(flopLabel);
    }

    public void createBar(HBox location, Color color, String attributeName, IntegerProperty actualProperty, IntegerProperty maxProperty, String id, int heigth, int width, boolean showContext){
        Pane container = new Pane();
        Pane bar = new Pane();

        int multiplier = width/100;


        if (multiplier == 0){
            multiplier++;
        }


        container.setPrefWidth(Integer.parseInt(maxProperty.getValue().toString())*multiplier);
        container.setMaxHeight(heigth);

        bar.setBackground(Background.fill(color));
        bar.setPrefWidth(Integer.parseInt(actualProperty.getValue().toString())*multiplier);
        bar.setPrefHeight(heigth);

        bar.setId(id);
        bar.getStyleClass().add("barPane");
        container.getStyleClass().add("barContainerPane");
        container.getChildren().add(bar);

        if (showContext){
            Label lifeLabel = new Label();
            HBox lifeLabelHbox = new HBox();
            lifeLabel.textProperty().bind(actualProperty.asString());
            lifeLabelHbox.getChildren().add(new Label(attributeName+" : "));
            lifeLabelHbox.getChildren().add(lifeLabel);
            lifeLabelHbox.getChildren().add(new Label(" / "+maxProperty.get()));
            bar.getChildren().add(lifeLabelHbox);
        }

        location.getChildren().add(container);
    }
}
