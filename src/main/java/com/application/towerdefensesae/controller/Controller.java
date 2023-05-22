package com.application.towerdefensesae.controller;

import com.application.towerdefensesae.modele.Acteur;
import com.application.towerdefensesae.modele.Terrain;
import com.application.towerdefensesae.vue.ActeurVue;
import com.application.towerdefensesae.vue.TerrainVue;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    TilePane paneTuiles ;
    @FXML
    Pane paneTerrain;

    private  Acteur acteur;
    private  Terrain terrain ;
    private Timeline gameLoop ;

    private int temps ;
    private boolean estFini;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.estFini = false;

        terrain = new Terrain();
        acteur = new Acteur(terrain);
        TerrainVue terrainVue = new TerrainVue(terrain, paneTuiles);
        ActeurVue acteurVue = new ActeurVue(paneTerrain);


        Circle cActeur = acteurVue.creationSpriteActeur() ;

        cActeur.translateXProperty().bind(acteur.xProperty());
        cActeur.translateYProperty().bind(acteur.yProperty());



        initAnimation();
        gameLoop.play();//appel gameLoop




        paneTerrain.setOnMousePressed(mouseEvent -> {

            System.out.println(((int) mouseEvent.getX()));
            System.out.println(((int) mouseEvent.getY())); //Pour avoir les x , y .


        });


    }
    public void initAnimation() {


        this.gameLoop = new Timeline();
        temps = 0 ;
        this.gameLoop.setCycleCount(Timeline.INDEFINITE);
        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(0.017),
                // on définit ce qui se passe à chaque frame
                // c'est un eventHandler d'ou le lambda
                (ev ->{
                    if(estFini){
                        System.out.println("fini");
                        gameLoop.stop();
                    }
                    else if (temps%10==0){
                        acteur.seDeplace();
                        estFini = acteur.verifObjectif();
                        System.out.println("un tour");
                    }
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }





}
