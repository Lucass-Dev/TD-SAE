package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Environnement {

    private ObservableList<Acteur> acteurs;
    private ObservableList<Tour> tours ;
    private Terrain terrain ;
    private Pane tilePane ;
    private int  nbActeurs ;

    public Environnement(Pane tilePane){
        this.acteurs = FXCollections.observableArrayList();
        this.tours = FXCollections.observableArrayList();
        this.tilePane = tilePane ;
        this.nbActeurs = 3 ;
    }

    public Environnement(Terrain terrain) {
        this.acteurs = FXCollections.observableArrayList();
        this.tours = FXCollections.observableArrayList();
        this.terrain = terrain ;
        this.nbActeurs = 3 ;
    }

    public ObservableList<Acteur> getActeurs() {
        return acteurs;
    }

    public ObservableList<Tour> getTours() {
        return tours;
    }

    public Terrain getTerrain(){
        return terrain;
    }

    public void acteurAgir(){
        for(int i=0;i<acteurs.size(); i++){
            Acteur a = acteurs.get(i);
            a.seDeplace();
            if (terrain.tuile(a.getX(),a.getY())==2){
                a.setPos(593,625);
                a.setPv(a.getPv()-1);
                if (a.estMort()){
                    acteurs.remove(a);
                }
            }
        }
    }

    public void tourAgir(){
        for (int i = 0; i <tours.size() ; i++) {
            Tour t = tours.get(i);
         Acteur a =  t.attaque(acteurs);
         if(a!=null) {
             a.setPv(-30);


         }
        }
    }

    public boolean verifObjectif() {
        return acteurs.isEmpty();
    }

    public void creationTour(int x , int y){
        Tour t1 = new Tour(tilePane);
        if(!t1.estPlaceable(x,y)) {
            tours.add(t1);
            t1.setX(x);
            t1.setY(y);
        }

        /*
        Tour t1 = new Tour(terrain);
        if(!t1.estPlaceable(x,y)) {
            tours.add(t1);
            t1.setX(x);
            t1.setY(y);
        }

         */
    }

    public void creeationEnnemi(int  i ,int t) {
        if (t < nbActeurs*100) {
            if (i % 100 == 0) {
                acteurs.add(new Acteur(terrain));
            }
        }
    }
}
