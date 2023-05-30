package fr.montreuil.iut.Lucas_Adrien_Imman.controller;

import fr.montreuil.iut.Lucas_Adrien_Imman.Main;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.*;
import fr.montreuil.iut.Lucas_Adrien_Imman.vue.LevelVue;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class LevelController implements Initializable {

    //variables pour la gameloop
    private boolean estFini;
    private Timeline gameLoop;
    private int temps;
    private int nbTours;



    //Données quelconques relatives au niveau
    private Level level;
    private LevelDataTransit LDT;
    private LevelVue levelVue;


    //FXML
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
        Main.stg.getScene().setCursor(Cursor.DEFAULT);
        this.estFini = false;

        /*
        this.tilePane.setHgap(5);
        this.tilePane.setVgap(5);
        */


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

            int[] mousePos = getTilePos(x, y);

            if (Main.stg.getScene().getCursor() != Cursor.DEFAULT && Main.stg.getScene().getCursor() != null){
                if (this.level.validTile(mousePos)){
                    try {
                        this.level.addTower(this.levelVue.placeTower(mousePos, new Image(Main.class.getResource("graphics/tower/0.png").openStream())));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    this.level.addEnnemy(this.levelVue.placeEnnemy(540, 60));
                }
            }
        });
        this.tilePane.setOnMouseMoved(e -> {
            this.mousePos.setText("x : "+ e.getX() + " / y : "+e.getY());
        });
    }

    public void createLevel(){
        playButton.setVisible(false);
        int mapIndex = this.LDT.getMapIndex();
        this.level = new Level("test");
        try {
            ArrayList<ArrayList<Integer>> map = this.level.createMap("src/main/resources/fr/montreuil/iut/Lucas_Adrien_Imman/csv/map"+mapIndex+".csv", tilePane);
            this.level.setTileMap(map);
            this.level.setTravelingMap(this.level.getTileMap());
            ListChangeListener<Ennemy> ennemyListChangeListener = new ListObsEnnemy(levelPane);
            this.level.getEnnemies().addListener(ennemyListChangeListener);
            ListChangeListener<Tower> towerListChangeListener = new ListObsTower(levelPane);
            this.level.getPlacedTower().addListener(towerListChangeListener);
            this.levelVue = new LevelVue(this.level, this.tilePane, this.levelPane);
            this.levelVue.createShopMenu(towerShopVbox);


            //Sout pour le tableau 2D des tuiles et de la version chemin tarversable
            for (ArrayList<Integer> arrayList: this.level.getTileMap()) {
                System.out.println(arrayList);
            }
            System.out.println();
            for (ArrayList<Integer> arrayList: this.level.getTravelingMap()) {
                System.out.println(arrayList);
            }

            //Quand tout est parametré comme il faut j'initialise la gameloop
            initAnimation();
            gameLoop.play();
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

    public void initAnimation() {

        this.estFini = false;
        this.gameLoop = new Timeline();
        temps = 0 ;
        nbTours = 1 ;
        this.gameLoop.setCycleCount(Timeline.INDEFINITE);
        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                //min 0.017
                Duration.seconds(0.017),
                // on définit ce qui se passe à chaque frame
                // c'est un eventHandler d'ou le lambda
                (ev ->{
                    if(estFini){
                        System.out.println("fini");
                        gameLoop.stop();
                    }
                    else{

                        this.level.doTurn(nbTours);

                        nbTours++ ;
                    }
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

    public int[] getTilePos(int x, int y){
     int[] pos = new int[2];
     pos[0] = x/32;
     pos[1] = y/32;
     return pos;
    }
}
