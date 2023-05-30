package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Environnement {

    private ObservableList<Acteur> acteurs;
    private ObservableList<Tour> tours ;
    private Terrain terrain ;
    private int  nbActeurs ;

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
            if (a.estMort()){
                acteurs.remove(a);
            }
        }
        for (int i = 0; i <tours.size() ; i++) {
            Tour t = tours.get(i);
            Acteur a =  t.attaque(acteurs);
            if(a!=null) {
                a.setPv(0);
                System.out.println(a.getPv()+"LE pv est de");

            }
        }


    }

    public void tourAgir(){

    }

    public boolean verifObjectif() {
        return acteurs.isEmpty();
    }

    public void creationTour(int x , int y){

        Tour t1 = new Tour(terrain);
        if(!t1.estPlaceable(x,y)) {
            tours.add(t1);
            t1.setX(x);
            t1.setY(y);
        }
    }

    public void creeationEnnemi(int  i ,int t) {
        if (t < nbActeurs*100) {
            if (i % 100 == 0) {
                acteurs.add(new Acteur(terrain));
            }
        }
    }
}
