package fr.montreuil.iut.Lucas_Adrien_Imman.controller;

import fr.montreuil.iut.Lucas_Adrien_Imman.Main;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.*;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Ennemy;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Projectiles.Projectile;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Tours.Tower;
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
    private Player player;

    //Variables pour le contrôleur
    private int cursorIndex = 0; //0 for none
    private boolean isMovingTower=  false;
    private Tower movingTower;
    private Tower showedTower;

    //Variables du Timer
    int s;
    int m;
    int h;


    //FXML
    @FXML
    Pane helpPopup;
    @FXML
    TilePane tilePane;
    @FXML
    Pane levelPane;
    @FXML
    Button playButton;
    @FXML
    VBox towerShopVbox;
    @FXML
    HBox athHbox;
    @FXML
    Pane towerMenu;
    @FXML
    Label waveLabel;
    @FXML
    Label timeLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCursor(Cursor.DEFAULT);
        this.estFini = false;

        this.s = 0;
        this.m = 0;
        this.timeLabel.setText("0h0m0s");

        this.towerShopVbox.setOnMouseClicked(mouseEvent -> {
            EventTarget target = mouseEvent.getTarget();
            ImageView targetedTowerIV = new ImageView();
            String[] stringId = new String[2];
            if (target instanceof HBox) {
                HBox targetedTowerHB = (HBox) mouseEvent.getTarget();
                targetedTowerIV = (ImageView) targetedTowerHB.getChildren().get(0);
                stringId = targetedTowerHB.getId().split("_");
            } else if (target instanceof ImageView) {
                targetedTowerIV = (ImageView) target;
                stringId = targetedTowerIV.getParent().getId().split("_");
            }
            cursorIndex = Integer.parseInt(stringId[stringId.length - 1]);
            setCursor(targetedTowerIV.getImage());
        });
        this.towerShopVbox.setOnMouseEntered(mouseEvent -> {
            setCursor(Cursor.DEFAULT);
        });
        this.tilePane.setOnMouseClicked(mouseEvent -> {

            int x = (int) mouseEvent.getX();
            int y = (int) mouseEvent.getY();

            int[] mousePos = this.level.getTilePos(x, y);

            if (this.isMovingTower){
                System.out.println(mouseEvent);
                if (this.level.validTile(mousePos)){
                    moveTowerTo(this.movingTower, mousePos[0]*32, mousePos[1]*32);
                    setCursor(Cursor.DEFAULT);
                    this.movingTower = null;
                    this.isMovingTower = false;
                }
            }else{
                if (Main.stg.getScene().getCursor() != Cursor.DEFAULT && Main.stg.getScene().getCursor() != null) {
                    if (this.level.validTile(mousePos)) {
                        this.level.placeTower(x, y, cursorIndex);
                    }
                }
            }
        });
        this.levelPane.setOnMouseClicked(mouseEvent -> {
            if (this.towerMenu.getChildren().size() > 0) {
                for (int i = towerMenu.getChildren().size() - 1; i >= 0; i--) {
                    towerMenu.getChildren().remove(i);
                }
                showedTower.setShowingRange(false);
            }
            ImageView imageView = (ImageView) mouseEvent.getTarget();
            String imageViewId = imageView.getId();
            if (imageViewId != null) {
                showedTower = this.level.getTower(imageViewId);
                try {
                    this.levelVue.createTowerMenu(showedTower, this.towerMenu);
                    showedTower.setShowingRange(true);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void createLevel() {
        this.helpPopup.setVisible(false);
        this.towerMenu.getChildren().remove(this.playButton);
        this.player = this.LDT.getPlayer();
        int mapIndex = this.LDT.getMapIndex();
        this.level = new Level("test", this.levelPane);
        this.level.setPlayer(this.LDT.getPlayer());
        this.waveLabel.textProperty().bind(this.level.actualWaveNumberProperty().asString());
        try {
            ArrayList<ArrayList<Integer>> map = this.level.createMap("src/main/resources/fr/montreuil/iut/Lucas_Adrien_Imman/csv/map"+mapIndex+".csv", tilePane);
            this.level.setTileMap(map);
            this.level.setTravelingMap(this.level.getTileMap());
            ListChangeListener<Ennemy> ennemyListChangeListener = new ListObsEnnemy(levelPane);
            this.level.getEnnemies().addListener(ennemyListChangeListener);
            ListChangeListener<Tower> towerListChangeListener = new ListObsTower(levelPane, player);
            this.level.getPlacedTower().addListener(towerListChangeListener);
            ListChangeListener<Projectile> projectileListChangeListener = new ListeObsProjectile(levelPane);
            this.level.getProjectiles().addListener(projectileListChangeListener);
            this.levelVue = new LevelVue(this.level, this.tilePane, this.levelPane, this);
            this.levelVue.createShopMenu(towerShopVbox);
            //Sout pour le tableau 2D des tuiles et de la version chemin tarversable
          /*  for (ArrayList<Integer> arrayList: this.level.getTileMap()) {
                System.out.println(arrayList);
            }
            System.out.println();
            for (ArrayList<Integer> arrayList: this.level.getTravelingMap()) {
                System.out.println(arrayList);
            }*/
            this.levelVue.createATH(this.player, athHbox);
            //Quand tout est parametré comme il faut j'initialise la gameloop
            initAnimation();
            gameLoop.play();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void setLDT(LevelDataTransit LDT) {
        this.LDT = LDT;
    }

    public void printData() {
        System.out.println(this.LDT.toString());
    }

    public void initAnimation() {

        this.estFini = false;
        this.gameLoop = new Timeline();
        nbTours = 1 ;
        this.gameLoop.setCycleCount(Timeline.INDEFINITE);
        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                //min 0.017
                //0.02 = 50 FPS
                Duration.seconds(0.02),
                // on définit ce qui se passe à chaque frame
                // c'est un eventHandler d'ou le lambda
                (ev ->{
                    if(estFini /*|| level.verifProgression()*/){
                        System.out.println("fini");
                        gameLoop.stop();
                    }
                    else{
                        this.level.doTurn(nbTours);
                        this.level.tourAgir(nbTours);
                        this.level.animationProjectiles(nbTours);
                        if (nbTours%50 == 0){
                            refreshTimer();
                        }
                        nbTours++ ;
                    }
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

    public void moveTowerTo(Tower t, int x, int y){
        t.setX(x);
        t.setY(y);
    }

    public void setMovingTower(boolean b){
        this.isMovingTower = b;
    }

    public void setCursor(Image image){
        Main.stg.getScene().setCursor(new ImageCursor(image));
    }

    public void setCursor(Cursor cursor){
        Main.stg.getScene().setCursor(cursor);
    }

    public void setMovingTower(Tower t){
        this.movingTower = t;
    }

    public void refreshTimer(){
        this.s ++;
        if (s == 60){
            s = 0;
            m++;
        }
        if (m == 60){
            m = 0;
            h++;
        }

        this.timeLabel.setText(this.h+"h"+this.m+"m"+this.s+"s");
    }
}
