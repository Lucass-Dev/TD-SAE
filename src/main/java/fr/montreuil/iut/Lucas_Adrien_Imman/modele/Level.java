package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import fr.montreuil.iut.Lucas_Adrien_Imman.Main;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Level {
    private String levelName;
    private String difficulty;
    private int actualWaveNumber;
    //private Wave actualWave;

    private ArrayList<ArrayList<Integer>> tileMap;
    private ArrayList<ArrayList<Integer>> travelingMap;

    private ObservableList<Tower> placedTower;
    private ObservableList<Ennemy> ennemies;
    private ObservableList<Projectile> projectiles ;

    private int[] startTilePos;
    private int[] endTilePos;
    private Pane levelPane;
    private int nbActeurs ;
    private Player player ;

    //  private Projectile projectile ;

    public Level(String name, Pane levelPane){
        this.levelPane = levelPane;
        this.levelName = name;
        this.tileMap = new ArrayList<>();
        this.travelingMap = new ArrayList<>();
        this.placedTower = FXCollections.observableArrayList();
        this.ennemies = FXCollections.observableArrayList();
        this.projectiles = FXCollections.observableArrayList();
        startTilePos = new int[2];
        endTilePos = new int[2];
        this.nbActeurs = 4 ;
        this.player = new Player(new SimpleIntegerProperty(5),new SimpleIntegerProperty(5));
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
        System.out.println(Arrays.toString(endTilePos));
        System.out.println(Arrays.toString(startTilePos));
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
            center[0] = tilePos[0]*32 ;
            center[1] = tilePos[1]*32 ;
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

    public ObservableList<Projectile> getProjectiles(){
        return projectiles ;
    }


    public void addEnnemy(Ennemy ennemy){
        this.ennemies.add(ennemy);
    }

    public void creationEnnemy(int nbTours, Level level , int t){
        if (t < nbActeurs*100) {
            if (nbTours % 100 == 0) {
                ennemies.add(new DotSH(levelPane, level , player));
            }
        }
    }


    public void placeTower(int x , int y ){
        int[] pos = new int[2];
        pos[0] = x/32;
        pos[1] = y/32;
        TaskKiller tk = new TaskKiller(pos[0]*32,pos[1]*32);
        addTower(tk);
    }

    public void doTurn(int nbTours ,Level level, int t){
        creationEnnemy(nbTours,level , t);
        for (int i = 0; i <ennemies.size() ; i++) {
            Ennemy e = ennemies.get(i);
            e.move();
        }
        for (int i = ennemies.size()-1 ; i>=0;i--) {
            Ennemy e = ennemies.get(i);
            if(!e.isOnBound() || e.isOnObjective() || e.estMort()) {
                ennemies.remove(e);
            }
        }
    }

    public void tourAgir(int nbTours){
        for (int i = 0; i <placedTower.size() ; i++) {
            Tower t = placedTower.get(i);
            Ennemy e =  t.ennemiProche(ennemies);
            if(e!=null) {
                if (nbTours % 20 == 0) {
                    projectiles.add(new Projectile2(t.getX(), t.getY(), e));
                }
            }
        }

    }




    public void animationProjectiles(){
        for (Projectile p : projectiles){
            p.moveProjectile();

        }
        for (int j = projectiles.size()-1 ; j>=0;j--) {
            Projectile p = projectiles.get(j);
            if(p.cibleAtteint()) {
                projectiles.remove(p);

            }
        }

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
}
