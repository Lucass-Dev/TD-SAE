package fr.montreuil.iut.Lucas_Adrien_Imman.controller;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Acteur;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Terrain;
import fr.montreuil.iut.Lucas_Adrien_Imman.vue.ActeurVue;
import fr.montreuil.iut.Lucas_Adrien_Imman.vue.TerrainVue;
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

    private Timeline gameLoop ;
    private int temps ;
    private  Acteur acteur;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Circle cActeur = new Circle(6);
        acteur = new Acteur();
        Terrain terrain = new Terrain();
        TerrainVue terrainVue = new TerrainVue(terrain,paneTuiles);
        ActeurVue acteurVue = new ActeurVue(paneTerrain,cActeur);


        cActeur.translateXProperty().bind(acteur.xProperty());
        cActeur.translateYProperty().bind(acteur.yProperty());

        initAnimation();
        gameLoop.play();



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
                    if(temps==100){
                        System.out.println("fini");
                        gameLoop.stop();
                    }
                    else if (temps%10==0){
                        System.out.println("un tour");
                        acteur.agir();

                    }
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }



}
