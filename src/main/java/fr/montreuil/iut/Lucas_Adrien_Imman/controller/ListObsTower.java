package fr.montreuil.iut.Lucas_Adrien_Imman.controller;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Player;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Tours.Tower;
import fr.montreuil.iut.Lucas_Adrien_Imman.vue.TowerVue;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ListObsTower implements ListChangeListener<Tower> {


    private Pane levelpane ;
    private Player player;

    public ListObsTower(Pane levelpane, Player player) {
        this.levelpane = levelpane ;
        this.player =  player;
    }


    @Override
    public void onChanged(Change<? extends Tower> c) {
        while (c.next()){
            for (Tower newTower :c.getAddedSubList()) {
                TowerVue towerVue = new TowerVue(levelpane);
                try {
                    towerVue.createTowerSprite(newTower);
                    player.setRam(player.getRam()-newTower.getRamPrice());
                    player.setFlop(player.getFlop()- newTower.getFlopPrice());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            for(Tower oldTower : c.getRemoved()){
                levelpane.getChildren().remove(levelpane.lookup("#"+oldTower.getId()));
                player.setRam(player.getRam()+oldTower.getRamPrice());
                player.setFlop(player.getFlop()+oldTower.getFlopPrice());
            }

        }
    }
}
