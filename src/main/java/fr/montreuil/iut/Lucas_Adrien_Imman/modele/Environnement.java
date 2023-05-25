package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Environnement {

    private ObservableList<Acteur> acteurs;
    private Terrain terrain ;
    private int  nbActeurs ;

    public Environnement(Terrain terrain) {
        this.acteurs = FXCollections.observableArrayList();
        this.terrain = terrain ;
        this.nbActeurs = 10 ;
    }

    public ObservableList<Acteur> getActeurs() {
        return acteurs;
    }


    public void agir(){
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

    public boolean verifObjectif() {
        return acteurs.isEmpty();
    }



    public void creeationEnnemi(int  i ,int t) {
        if (t < nbActeurs*100) {
            if (i % 100 == 0) {
                acteurs.add(new Acteur(terrain));
            }
        }
    }

//578.608 , 612/637    , milieu // x = 593 y =625
}
