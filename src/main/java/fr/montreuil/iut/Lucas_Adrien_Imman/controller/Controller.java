package fr.montreuil.iut.Lucas_Adrien_Imman.controller;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Acteur;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Environnement;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Terrain;
import fr.montreuil.iut.Lucas_Adrien_Imman.vue.TerrainVue;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    TilePane paneTuiles ;
    @FXML
    Pane paneTerrain;

    private  Terrain terrain ;
    private Timeline gameLoop ;
    private Environnement environnement;

    private int temps ;
    private boolean estFini;
    private int nbTours ;





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        terrain = new Terrain();
        environnement = new Environnement(terrain);


        TerrainVue terrainVue = new TerrainVue(terrain, paneTuiles);


        ListChangeListener<Acteur> listenActeurs = new ListeObsActeurs(paneTerrain);
        environnement.getActeurs().addListener(listenActeurs); // lisent sur liste acteurs


        initAnimation();
        gameLoop.play();//appel gameLoop



        paneTerrain.setOnMousePressed(mouseEvent -> {
            System.out.println("x"+((int) mouseEvent.getX()));
            System.out.println("y"+((int) mouseEvent.getY())); // Pour avoir le x et y
        });

    }



    public void initAnimation() {

        this.estFini = false;
        this.gameLoop = new Timeline();
        temps = 0 ;
        nbTours = 1 ;
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
                    else{ //

                        environnement.creeationEnnemi(temps,nbTours);
                        environnement.agir();
                        estFini = environnement.verifObjectif();
                        System.out.println("un tour");
                        nbTours++ ;
                        System.out.println(nbTours);
                    }
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

}
