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
    private ObservableList<Projectile> projectiles ;

    private int[] startTilePos;
    private int[] endTilePos;
    private Pane levelPane;
    private int waveSize;
    private int nbActeurs ;

    //  private Projectile projectile ;

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
        this.difficulty = 3;
        this.actualWaveNumber = 1;
        this.projectiles = FXCollections.observableArrayList();
        this.nbActeurs = 4;
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

    public ObservableList<Projectile> getProjectiles(){
        return projectiles ;
    }

/*
    public void addEnnemy(Ennemy ennemy){
        this.ennemies.add(ennemy);
    }

    public void creationEnnemy(int nbTours, Level level){
        if (nbTours %100 == 0){
            ennemies.add(new DotSH(getStartTilePos()[0]*32 +16, getStartTilePos()[1]*32 +16, levelPane,level, 0));
        }
    }

 */

    public void placeTower(int x , int y, int index){
        int[] pos = new int[2];
        pos[0] = x/32;
        pos[1] = y/32;
        Tower t = null;
        switch(index){
            case 0 -> {
                t = new TaskKiller(pos[0]*32, pos[1]*32);
            }
            case 1 -> {
                t = new CCleaner(pos[0]*32, pos[1]*32);
            }
            case 2 -> {
                t = new Demineur(pos[0]*32, pos[1]*32);
            }
            case 3 -> {
                t = new InternetExplorer(pos[0]*32, pos[1]*32);
            }
            case 4 -> {
                t = new NordVPN(pos[0]*32, pos[1]*32);
            }
            case 5 -> {
                t = new PDFConverter(pos[0]*32, pos[1]*32);
            }
            default -> System.out.println("Error, index might be from 0 to 5 and found " + index);
        }
        addTower(t);
    }

    public void createWave(int size){
        for (int i = 0; i < size; i++) {
            switch ((int) ((Math.random() * (6 - 1)) + 1)){
                case 1 -> {
                    this.actualWave.add(new DotSH(startTilePos[0]*32 +16, startTilePos[1]*32 +16, levelPane, this, this.player));
                }
                case 2 -> {
                    if (this.actualWaveNumber <= 5){
                        this.actualWave.add(new DotSH(startTilePos[0]*32 +16, startTilePos[1]*32 +16, levelPane, this, this.player));
                    }else{
                        this.actualWave.add(new Archive(startTilePos[0]*32 +16, startTilePos[1]*32 +16, levelPane, this, this.player));
                    }
                }
                case 3 -> {
                    if (this.actualWaveNumber <= 10){
                        this.actualWave.add(new DotSH(startTilePos[0]*32 +16, startTilePos[1]*32 +16, levelPane, this, this.player));
                    }else{
                        this.actualWave.add(new Virus(startTilePos[0]*32 +16, startTilePos[1]*32 +16, levelPane, this, this.player));
                    }
                }
                case 4 -> {
                    if (this.actualWaveNumber <= 15){
                        this.actualWave.add(new DotSH(startTilePos[0]*32 +16, startTilePos[1]*32 +16, levelPane, this, this.player));
                    }else{
                        this.actualWave.add(new Scam(startTilePos[0]*32 +16, startTilePos[1]*32 +16, levelPane, this, this.player));
                    }
                }
                case 5 -> {
                    if (this.actualWaveNumber <= 20){
                        this.actualWave.add(new DotSH(startTilePos[0]*32 +16, startTilePos[1]*32 +16, levelPane, this, this.player));
                    }else{
                        this.actualWave.add(new DotExe(startTilePos[0]*32 +16, startTilePos[1]*32 +16, levelPane, this, this.player));
                    }
                }
            }
        }
        this.actualWaveNumber++;
    }

    public boolean doTurn(int nbTours){
        if (actualWave.size() == 0 && ennemies.size() == 0){
            createWave(this.waveSize);
            this.waveSize += actualWaveNumber*difficulty/3;
            // nextWave();
        }else if (nbTours % 20 == 0 && actualWave.size() != 0){
            this.ennemies.add(this.actualWave.remove(0));
        }
        if (this.ennemies.size() > 0){
            for (int i = ennemies.size()-1; i>=0 ; i--) {
                Ennemy e = ennemies.get(i);
                e.move();
                if (e.isOnObjective() || !e.isOnBound() || e.estMort()){
                    e.doDamage();
                    ennemies.remove(e);
                }
            }
        }
        return this.player.isDead();
    }

    public void tourAgir(int nbTours){
        for (int i = 0; i <placedTower.size() ; i++) {
            Tower t = placedTower.get(i);
            Ennemy e =  t.detect(ennemies);
            if(e!=null) {
                if (nbTours % 20 == 0) {
                    if(t instanceof TaskKiller)
                        projectiles.add(new ProjectileDegatsBrut(t.getX(), t.getY(), e));
                    else if(t instanceof InternetExplorer)
                        projectiles.add(new ProjectileRalentisseur(t.getX(), t.getY(),e)) ;

                }
            }
        }

    }

    public boolean verifProgression(){
        return player.isDead();
    }



    public void animationProjectiles(){
        for (Projectile p : projectiles){
            p.moveProjectile();
            p.agitSurLaCible();

        }
        for (int j = projectiles.size()-1 ; j>=0;j--) {
            Projectile p = projectiles.get(j);
            if(p.cibleAtteint() || p.isOnBound() ) {
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

    public Player getPlayer() {
        return player;
    }

    public Tower getTower(String id){
        Tower t = null;
        for (Tower tower: this.getPlacedTower()) {
            if (tower.getId().equals(id)){
                t =  tower;
            }
        }
        return t;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void nextWave() {
        int timer = 5;
        Popup nextWavePopup = new Popup();
        Label nextWaveLabel = new Label();

        nextWavePopup.getContent().add(nextWaveLabel);
        nextWavePopup.show(Main.stg, Main.stg.getHeight()/2, Main.stg.getWidth()/2);
        for (int i = timer; i > 0; i--) {
            nextWaveLabel.setText("New wave in "+ i);
        }
        nextWavePopup.hide();
    }
}
