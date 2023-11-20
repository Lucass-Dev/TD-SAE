package fr.montreuil.iut.Lucas_Adrien_Imman.controller;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Player;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Tours.Tour;
import fr.montreuil.iut.Lucas_Adrien_Imman.vue.TowerVue;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ListObsTower implements ListChangeListener<Tour> {


    private Pane levelpane ;
    private Player player;

    public ListObsTower(Pane levelpane, Player player) {
        this.levelpane = levelpane ;
        this.player =  player;
    }


    @Override
    public void onChanged(Change<? extends Tour> c) {
        while (c.next()){
            for (Tour newTour :c.getAddedSubList()) {
                TowerVue towerVue = new TowerVue(levelpane);
                try {
                    towerVue.createTowerSprite(newTour);
                    player.setRam(player.getRam()- newTour.getRamPrice());
                    player.setFlop(player.getFlop()- newTour.getFlopPrice());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            for(Tour oldTour : c.getRemoved()){
                levelpane.getChildren().remove(levelpane.lookup("#"+ oldTour.getId()));
                levelpane.getChildren().remove(levelpane.lookup("#c"+ oldTour.getId()));
            }

        }
    }
}
