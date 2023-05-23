package fr.montreuil.iut.Lucas_Adrien_Imman.controller;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Level;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.LevelDataTransit;
import fr.montreuil.iut.Lucas_Adrien_Imman.vue.LevelVue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.TilePane;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LevelController implements Initializable {

    private Level level;

    private LevelDataTransit LDT;

    private LevelVue lv;

    @FXML
    TilePane paneTuiles;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void createLevel(){
        this.lv = new LevelVue();
        int mapIndex = this.LDT.getMapIndex();
        System.out.println(this.LDT.getMapIndex());
        printData();

        try {
            ArrayList<ArrayList<Integer>> map = this.lv.createMap("src/main/resources/fr/montreuil/iut/Lucas_Adrien_Imman/csv/map"+mapIndex+".csv", paneTuiles);
            this.level = new Level("test", map);
            for (ArrayList<Integer> arrayList:
                    this.level.getTravelingMap()) {
                System.out.println(arrayList);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    public void setLDT(LevelDataTransit LDT){
        this.LDT = LDT;
    }

    public void printData(){
        System.out.println(this.LDT.toString());
    }
}
