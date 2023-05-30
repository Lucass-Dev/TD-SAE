package fr.montreuil.iut.Lucas_Adrien_Imman.controller;

import fr.montreuil.iut.Lucas_Adrien_Imman.Main;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Level;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.LevelDataTransit;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.TaskKiller;
import fr.montreuil.iut.Lucas_Adrien_Imman.vue.LevelVue;
import fr.montreuil.iut.Lucas_Adrien_Imman.vue.TowerVue;
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

    @FXML
    Label mousePos;

    @FXML
    TilePane tilePane;

    @FXML
    Pane travelingPane;

    @FXML
    Button playButton;

    @FXML
    VBox towerShopVbox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.levelVue = new LevelVue();
        this.towerVue = new TowerVue();
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
            if (Main.stg.getScene().getCursor() != Cursor.DEFAULT && Main.stg.getScene().getCursor() != null){
                System.out.println(Main.stg.getScene().getCursor());
                this.levelVue.placeTower(this.tilePane, mouseEvent.getX(), mouseEvent.getY());
            }
        });
        this.tilePane.setOnMouseMoved(e -> {
            this.mousePos.setText("x : "+ e.getX() + " / y : "+e.getY());
        });
    }

    public void createLevel(){
        int mapIndex = this.LDT.getMapIndex();
        try {
            ArrayList<ArrayList<Integer>> map = this.levelVue.createMap("src/main/resources/fr/montreuil/iut/Lucas_Adrien_Imman/csv/map"+mapIndex+".csv", tilePane);
            this.level = new Level("test", map);
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
