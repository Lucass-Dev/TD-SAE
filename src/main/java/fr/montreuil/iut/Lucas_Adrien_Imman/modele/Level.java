package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import fr.montreuil.iut.Lucas_Adrien_Imman.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Level {
    private Player player;
    private String levelName;
    private int difficulty;
    private int actualWaveNumber;
    private ArrayList<Ennemy> actualWave;
    private ArrayList<ArrayList<Integer>> tileMap;
    private ArrayList<ArrayList<Integer>> travelingMap;
    private ObservableList<Tower> placedTower;
    private ObservableList<Ennemy> ennemies;
    private int[] startTilePos;
    private int[] endTilePos;
    private Pane levelPane;
    private int waveSize;

    public Level(String name, Pane levelPane){
        this.levelPane = levelPane;
        this.levelName = name;
        this.tileMap = new ArrayList<>();
        this.travelingMap = new ArrayList<>();
        this.placedTower = FXCollections.observableArrayList();
        this.ennemies = FXCollections.observableArrayList();
        this.startTilePos = new int[2];
        this.endTilePos = new int[2];
        this.actualWave = new ArrayList<>();
        this.waveSize = 2;
        this.difficulty = 1;
        this.actualWaveNumber = 0;
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
                } else if (i == 11 || i == 12 || i == 13 || i == 14) {
                    traveling.get(index).add(6);
                    startTilePos[0] = index;
                    startTilePos[1] = traveling.get(index).size()-1;
                }else if (i == 1 || i == 2 || i == 4 || i == 5) {
                    traveling.get(index).add(7);
                    endTilePos[0] = index;
                    endTilePos[1] = traveling.get(index).size()-1;
                } else{
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

    public int[] getTileCenter(int[] tilePos){
        int[] center = new int[2];
        if (tilePos[0] >= this.travelingMap.size() || tilePos[1] > this.travelingMap.size()){
            return null;
        }else{
            center[0] = tilePos[0]*32 + 16;
            center[1] = tilePos[1]*32 + 16;
        }

        return center;
    }

    public int getTile(int[] pos){
        return this.travelingMap.get(pos[1]).get(pos[0]);
    }

    public boolean validTile(int[] pos){
        return getTile(pos) == 0;
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

    public ObservableList<Ennemy> getEnnemies() {
        return ennemies;
    }

    public void addEnnemy(Ennemy ennemy){
        this.ennemies.add(ennemy);
    }

    public void creationEnnemy(int nbTours, Level level){
        if (nbTours %100 == 0){
            ennemies.add(new DotSH(getStartTilePos()[0]*32 +16, getStartTilePos()[1]*32 +16, levelPane,level, 0));
        }
    }

    public void createWave(int size){
        for (int i = 0; i < size; i++) {
            this.actualWave.add(new DotSH(startTilePos[0]*32 +16, startTilePos[1]*32 +16, levelPane, this, 0));
        }
        this.actualWaveNumber++;
    }

    public boolean doTurn(int nbTours) throws InterruptedException {
        if (actualWave.size() == 0 && ennemies.size() == 0){
            createWave(this.waveSize);
            this.waveSize += actualWaveNumber*difficulty/3;
            //nextWave();
        }else if (nbTours % 20 == 0 && actualWave.size() != 0){
            this.ennemies.add(this.actualWave.remove(0));
        }
        if (this.ennemies.size() > 0){
            for (int i = 0; i < ennemies.size() ; i++) {
                Ennemy e = ennemies.get(i);
                e.move();
                if (e.isOnObjective()){
                    this.player.setLife(this.player.getLife()-5);
                    ennemies.remove(e);
                }
            }
        }
        return this.player.isDead();
    }

    public int[] getStartTilePos() {
        return startTilePos;
    }

    public int[] getEndTilePos() {
        return endTilePos;
    }

    public int getTileValue(int[] pos) {
        return this.getTravelingMap().get(pos[1]).get(pos[0]);
    }

    public int[] getTilePos(int x, int y){
        int[] pos = new int[2];
        pos[0] = x/32;
        pos[1] = y/32;
        return pos;
    }

    public Player getPlayer() {
        return player;
    }

    public Tower getTower(int id){
        Tower t = null;
        for (Tower tower: this.getPlacedTower()) {
            if (tower.getId() == id){
                t =  tower;
            }
        }
        return t;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    /*
    public void nextWave() throws InterruptedException {
        int timer = 5;
        Popup nextWavePopup = new Popup();
        Label nextWaveLabel = new Label();

        nextWavePopup.getContent().add(nextWaveLabel);
        nextWavePopup.show(Main.stg, Main.stg.getHeight()/2, Main.stg.getWidth()/2);
        for (int i = timer; i > 0; i--) {
            nextWaveLabel.setText("New wave in "+ i);
            Thread.sleep(1000);
        }
        nextWavePopup.hide();
    }

     */
}
