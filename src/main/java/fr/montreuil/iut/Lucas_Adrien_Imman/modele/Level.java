package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import fr.montreuil.iut.Lucas_Adrien_Imman.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Level {
    private String levelName;
    private String difficulty;
    private int actualWaveNumber;
    //private Wave actualWave;
    private ArrayList<ArrayList<Integer>> tileMap;
    private ArrayList<ArrayList<Integer>> travelingMap;
    private ObservableList<Tower> placedTower;
    private ObservableList<Acteur> acteurs;

    public Level(String name){
        this.levelName = name;
        this.tileMap = new ArrayList<>();
        this.travelingMap = new ArrayList<>();
        this.placedTower = FXCollections.observableArrayList();
    }

    public Level(String name, ArrayList<ArrayList<Integer>> tileMap){
        this.levelName = name;
        this.tileMap = tileMap;
        this.travelingMap = tileMapToTraveling(this.tileMap);
    }

    public ArrayList<ArrayList<Integer>> tileMapToTraveling(ArrayList<ArrayList<Integer>> tileMap){
        ArrayList<ArrayList<Integer>> traveling = new ArrayList<>();

        int index = 0;
        for (ArrayList<Integer>  a: tileMap) {
            traveling.add(new ArrayList<>());
            for (Integer i : a) {
                if (i == 10){
                    traveling.get(index).add(0);
                } else if (i == 6) {
                    traveling.get(index).add(2);
                }else if (i == 7) {
                    traveling.get(index).add(3);
                }else if (i == 8) {
                    traveling.get(index).add(4);
                }else if (i == 9) {
                    traveling.get(index).add(5);
                }else{
                    traveling.get(index).add(1);
                }
            }
            index++;
        }
        return traveling;
    }

    public ArrayList<ArrayList<Integer>> getTravelingMap() {
        return travelingMap;
    }

    public int getTile(int x, int y){
        return this.travelingMap.get(x/32).get(y/32);
    }

    public boolean validTile(int x, int y){
        return getTile(x, y) == 0;
    }

    public void setTileMap(ArrayList<ArrayList<Integer>> tileMap) {
        this.tileMap = tileMap;
    }

    public void setTravelingMap(ArrayList<ArrayList<Integer>> tileMap){
        this.travelingMap = tileMapToTraveling(this.tileMap);
    }

    public ArrayList<ArrayList<Integer>> getTileMap() {
        return tileMap;
    }

    public ArrayList<ArrayList<Integer>> createMap(String path, Pane pane) throws FileNotFoundException {
        File f = new File(path);

        Scanner sc = new Scanner(f);

        ArrayList<ArrayList<Integer>> map = new ArrayList<>();

        int i = 0;

        while (sc.hasNextLine()){
            String l = sc.nextLine();
            String[] ls = l.split(",");
            map.add(new ArrayList<>());
            for (int j = 0; j < ls.length; j++) {
                map.get(i).add(Integer.valueOf(ls[j]));
            }
            i++;
        }


        for (int j = 0; j < map.size(); j++) {
            for (int k = 0; k < map.get(j).size(); k++) {
                ImageView newImageView = new ImageView();
                String s = "graphics/tiles/"+map.get(j).get(k)+".png";
                String newImagePath = Main.class.getResource(s).toString();
                newImageView.setImage(new Image(newImagePath));
                pane.getChildren().add(newImageView);
            }
        }
        return map;
    }

    public void addTower(Tower tower){
        this.placedTower.add(tower);
    }

    public ObservableList<Tower> getPlacedTower() {
        return placedTower;
    }
}
