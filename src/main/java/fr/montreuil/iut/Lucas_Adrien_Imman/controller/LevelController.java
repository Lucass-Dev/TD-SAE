package fr.montreuil.iut.Lucas_Adrien_Imman.controller;

import fr.montreuil.iut.Lucas_Adrien_Imman.Main;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Ennemy;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Level;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.LevelDataTransit;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Player;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Projectiles.Projectile;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Score;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Tours.Tower;
import fr.montreuil.iut.Lucas_Adrien_Imman.vue.LevelVue;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        this.waveLabel.setText("0");

        //Change le curseur de la souris quand on clique sur les imageView résentent dans le menu d'achat à droite
        this.towerShopVbox.setOnMouseClicked(mouseEvent -> {
            EventTarget target = mouseEvent.getTarget();
            if (target instanceof ImageView){
                ImageView targetedTowerIV ;
                String[] stringId;
                targetedTowerIV = (ImageView) target;
                stringId = targetedTowerIV.getParent().getId().split("_");
                cursorIndex = Integer.parseInt(stringId[stringId.length - 1]);
                setCursor(targetedTowerIV.getImage());
            }
        });

        //Reset le curseur par défaut quand le curseur est modifé et hover le menu d'achat
        this.towerShopVbox.setOnMouseEntered(mouseEvent -> {
            setCursor(Cursor.DEFAULT);
        });

        //Gère la pose de de tour en cliquant sur le tile pane
        //Récupère la position de la souris et la trnsforme en coordonées utilisable
        this.tilePane.setOnMouseClicked(mouseEvent -> {

            int x = (int) mouseEvent.getX();
            int y = (int) mouseEvent.getY();

            int[] mousePos = this.level.getTilePos(x, y);

            //Quand on bouge une tour
            if (this.isMovingTower){
                System.out.println(mouseEvent);
                if (this.level.validTile(mousePos)){
                    moveTowerTo(this.movingTower, mousePos[0]*32, mousePos[1]*32);
                    setCursor(Cursor.DEFAULT);
                    this.movingTower = null;
                    this.isMovingTower = false;
                }
            }
            //Quand on veut poser une tour
            else{
                if (Main.stg.getScene().getCursor() != Cursor.DEFAULT && Main.stg.getScene().getCursor() != null) {
                    if (this.level.validTile(mousePos)) {
                        this.level.placeTower(x, y, cursorIndex);
                    }
                }
            }
        });

        //Gère l'affichage du menu de tour ou l'enlèvement de celui-ci quand on clique sur le pane ou sur une tour
        this.levelPane.setOnMouseClicked(mouseEvent -> {
            if (this.towerMenu.getChildren().size() > 0) {
                this.levelVue.removeTowerMenu(this.towerMenu);
                showedTower.setShowingRange(false);
            }
            if (mouseEvent.getTarget() instanceof ImageView){
                ImageView imageView = (ImageView) mouseEvent.getTarget();

                String imageViewId = imageView.getId();
                System.out.println(imageViewId);
                if (imageViewId != null) {
                    showedTower = this.level.getTower(imageViewId);
                    try {
                        this.levelVue.createTowerMenu(showedTower, this.towerMenu);
                        showedTower.setShowingRange(true);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    //Créer le niveau sur plusieurs aspects (niveau code donc modèle et niveau vue)
    public void createLevel() {
        this.helpPopup.setVisible(false);
        this.towerMenu.getChildren().remove(this.playButton);
        this.player = this.LDT.getPlayer();
        int mapIndex = this.LDT.getMapIndex();
        this.level = new Level(this.levelPane);
        this.level.setPlayer(this.LDT.getPlayer());
        this.level.setDifficulty(this.LDT.getDifficulty());
        this.waveLabel.textProperty().bind(this.level.actualWaveNumberProperty().asString());

        //Ce try catch est pour la méthode createMap de la classe Level parce que l'on essaye de trouver un fichier
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
            try {
                this.levelVue.createATH(this.player, athHbox);
            }catch (IOException e){
                throw new RuntimeException(e);
            }
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
                    if(estFini || level.checkProgression()){
                        System.out.println("fini");
                        gameLoop.stop();
                        Score s = new Score(this.LDT.getDifficulty(), this.timeLabel.getText(), this.LDT.getPlayerName(), this.LDT.getMapIndex(), this.level.getActualWaveNumber());
                        //Ce try catch est pour la méthode newBestScores de la classe Level parce que l'on essaye de trouver un fichier
                        try {
                            s.newBestScores();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("menu.fxml"));


                        Parent root = null;
                        try {
                            root = fxmlLoader.load();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                        //Ensuite on change la scene pour revenir au menu principal
                        Scene nS= new Scene(root, 1040, 900);

                        Main.stg.setScene(nS);
                    }
                    else{
                        this.level.startLevel(nbTours);
                        if (nbTours%50 == 0){
                            refreshTimer();
                        }
                        nbTours++ ;
                    }

                    temps++;
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
