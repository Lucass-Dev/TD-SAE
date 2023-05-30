package fr.montreuil.iut.Lucas_Adrien_Imman.controller;

import fr.montreuil.iut.Lucas_Adrien_Imman.Main;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.*;
import fr.montreuil.iut.Lucas_Adrien_Imman.vue.LevelVue;
import fr.montreuil.iut.Lucas_Adrien_Imman.vue.TowerVue;
import javafx.collections.ListChangeListener;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.FileNotFoundException;
import java.lang.annotation.Target;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LevelController implements Initializable {

    private TowerVue towerVue;

    private Level level;

    private LevelDataTransit LDT;

    private LevelVue levelVue;

    private Scene mainScene;

    private Environnement environnement;

    private Terrain terrain;

    @FXML
    Label mousePos;

    @FXML
    TilePane tilePane;

    @FXML
    Pane levelPane;

    @FXML
    Button playButton;

    @FXML
    VBox towerShopVbox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.terrain = new Terrain();
        this.environnement = new Environnement(tilePane);
        this.towerVue = new TowerVue(tilePane);
        Main.stg.getScene().setCursor(Cursor.DEFAULT);


        this.towerShopVbox.setOnMouseClicked(mouseEvent -> {
            EventTarget target = mouseEvent.getTarget();
            ImageView targetedTowerIV = new ImageView();
            if (target instanceof  HBox){
                HBox targetedTowerHB = (HBox) mouseEvent.getTarget();
                targetedTowerIV = (ImageView) targetedTowerHB.getChildren().get(0);
            } else if (target instanceof  ImageView) {
                targetedTowerIV = (ImageView) target;
            }

            Main.stg.getScene().setCursor(new ImageCursor(targetedTowerIV.getImage()));
        });
        this.towerShopVbox.setOnMouseEntered(mouseEvent ->{
            Main.stg.getScene().setCursor(Cursor.DEFAULT);
        });
        this.tilePane.setOnMouseClicked(mouseEvent -> {

            int x = (int) mouseEvent.getX();
            int y = (int) mouseEvent.getY();

            if (Main.stg.getScene().getCursor() != Cursor.DEFAULT && Main.stg.getScene().getCursor() != null){
                if (this.level.validTile(x, y)){
                    this.levelVue.placeTower(x, y);
                }
            }
            System.out.println(this.level.getTile(x, y));
        });
        this.tilePane.setOnMouseMoved(e -> {
            this.mousePos.setText("x : "+ e.getX() + " / y : "+e.getY());
        });
    }

    public void createLevel(){
        int mapIndex = this.LDT.getMapIndex();
        try {
            this.level = new Level("test");
            ArrayList<ArrayList<Integer>> map = this.level.createMap("src/main/resources/fr/montreuil/iut/Lucas_Adrien_Imman/csv/map"+mapIndex+".csv", tilePane);
            this.level.setTileMap(map);
            this.level.setTravelingMap(this.level.getTileMap());
            ListChangeListener<Tower> towerListChangeListener = new ListObsTower(levelPane);
            this.level.getPlacedTower().addListener(towerListChangeListener);
            this.levelVue = new LevelVue(this.level, this.tilePane, this.levelPane);
            playButton.setVisible(false);
            this.levelVue.createShopMenu(towerShopVbox);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void setLDT(LevelDataTransit LDT){
        this.LDT = LDT;
    }

    public void printData(){
        System.out.println(this.LDT.toString());
    }
}
