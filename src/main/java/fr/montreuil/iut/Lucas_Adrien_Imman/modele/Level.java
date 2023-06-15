package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import fr.montreuil.iut.Lucas_Adrien_Imman.Main;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.*;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Projectiles.*;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Tours.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
    private IntegerProperty actualWaveNumber;
    private ArrayList<Ennemy> actualWave;
    private ArrayList<ArrayList<Integer>> tileMap;
    private ArrayList<ArrayList<Integer>> travelingMap;
    private ObservableList<Tower> placedTower;
    private ObservableList<Ennemy> ennemies;
    private ArrayList<Ennemy> ennemiesDansLaZone ;
    private ObservableList<Projectile> projectiles;

    private int[] startTilePos;
    private int[] endTilePos;
    private Pane levelPane;
    private int waveSize;
    private int nbActeurs;
    private int cpt ;



    public Level(String name, Pane levelPane) {
        this.levelPane = levelPane;
        this.levelName = name;
        this.tileMap = new ArrayList<>();
        this.travelingMap = new ArrayList<>();
        this.ennemiesDansLaZone = new ArrayList<>();
        this.placedTower = FXCollections.observableArrayList();
        this.ennemies = FXCollections.observableArrayList();
        this.startTilePos = new int[2];
        this.endTilePos = new int[2];
        this.actualWave = new ArrayList<>();
        this.waveSize = 2;
        this.difficulty = 3;
        this.actualWaveNumber = new SimpleIntegerProperty(10);
        this.projectiles = FXCollections.observableArrayList();
        this.nbActeurs = 4;
    }

    public Level(String name, ArrayList<ArrayList<Integer>> tileMap) {
        this.levelName = name;
        this.tileMap = tileMap;
        this.travelingMap = tileMapToTraveling(this.tileMap);
    }

    public void startLevel(int nbTours){
        doTurn(nbTours);
        tourAgir(nbTours);
        animationProjectiles(nbTours);
        flopGain();
    }

    public ArrayList<ArrayList<Integer>> tileMapToTraveling(ArrayList<ArrayList<Integer>> tileMap) {
        ArrayList<ArrayList<Integer>> traveling = new ArrayList<>();

        int index = 0;
        for (ArrayList<Integer> a : tileMap) {
            traveling.add(new ArrayList<>());
            for (Integer i : a) {
                if (i == 10) {
                    traveling.get(index).add(0);
                } else if (i == 6) {
                    traveling.get(index).add(2);
                } else if (i == 7) {
                    traveling.get(index).add(3);
                } else if (i == 8) {
                    traveling.get(index).add(4);
                } else if (i == 9) {
                    traveling.get(index).add(5);
                } else if (i == 11 || i == 12 || i == 13 || i == 14) {
                    traveling.get(index).add(6);
                    startTilePos[0] = index;
                    startTilePos[1] = traveling.get(index).size() - 1;
                } else if (i == 1 || i == 2 || i == 4 || i == 5) {
                    traveling.get(index).add(7);
                    endTilePos[0] = index;
                    endTilePos[1] = traveling.get(index).size() - 1;
                } else {
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

    public int[] getTileCenter(int[] tilePos) {
        int[] center = new int[2];
        if (tilePos[0] >= this.travelingMap.size() || tilePos[1] > this.travelingMap.size()) {
            return null;
        } else {
            center[0] = tilePos[0] * 32 + 16;
            center[1] = tilePos[1] * 32 + 16;
        }

        return center;
    }

    public int getTile(int[] pos) {
        return this.travelingMap.get(pos[1]).get(pos[0]);
    }

    public boolean validTile(int[] pos) {
        return getTile(pos) == 0;
    }

    public void setTileMap(ArrayList<ArrayList<Integer>> tileMap) {
        this.tileMap = tileMap;
    }

    public void setTravelingMap(ArrayList<ArrayList<Integer>> tileMap) {
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

        while (sc.hasNextLine()) {
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
                String s = "graphics/tiles/" + map.get(j).get(k) + ".png";
                String newImagePath = Main.class.getResource(s).toString();
                newImageView.setImage(new Image(newImagePath));
                pane.getChildren().add(newImageView);
            }
        }
        return map;
    }

    public void addTower(Tower tower) {
        this.placedTower.add(tower);
    }

    public ObservableList<Tower> getPlacedTower() {
        return placedTower;
    }

    public ObservableList<Ennemy> getEnnemies() {
        return ennemies;
    }

    public ObservableList<Projectile> getProjectiles() {
        return projectiles;
    }


    public void placeTower(int x, int y, int index) {
        int[] pos = new int[2];
        pos[0] = x / 32;
        pos[1] = y / 32;
        Tower t = null;
        switch (index) {
            case 0 -> {
                t = new TaskKiller(pos[0] * 32, pos[1] * 32);
            }
            case 1 -> {
                t = new CCleaner(pos[0] * 32, pos[1] * 32);
            }
            case 2 -> {
                t = new Demineur(pos[0] * 32, pos[1] * 32);
            }
            case 3 -> {
                t = new InternetExplorer(pos[0] * 32, pos[1] * 32);
            }
            case 4 -> {
                t = new NordVPN(pos[0] * 32, pos[1] * 32);
            }
            case 5 -> {
                t = new PDFConverter(pos[0] * 32, pos[1] * 32);
            }
            default -> System.out.println("Error, index might be from 0 to 5 and found " + index);
        }
        addTower(t);
    }

    public void setActualWaveNumber(int actualWaveNumber) {
        this.actualWaveNumber.set(actualWaveNumber);
    }

    public void createWave(int size) {
        for (int i = 0; i < size; i++) {
            switch ((int) ((Math.random() * (6 - 1)) + 1)) {
                case 1 -> {
                    this.actualWave.add(new DotSH(startTilePos[0] * 32 + 16, startTilePos[1] * 32 + 16, levelPane, this, this.player));
                }
                case 2 -> {
                    if (this.actualWaveNumber.get() <= 5) {
                        this.actualWave.add(new DotSH(startTilePos[0] * 32 + 16, startTilePos[1] * 32 + 16, levelPane, this, this.player));
                    } else {
                        this.actualWave.add(new Archive(startTilePos[0] * 32 + 16, startTilePos[1] * 32 + 16, levelPane, this, this.player));
                    }
                }
                case 3 -> {
                    if (this.actualWaveNumber.get() <= 10) {
                        this.actualWave.add(new DotSH(startTilePos[0] * 32 + 16, startTilePos[1] * 32 + 16, levelPane, this, this.player));
                    } else {
                        this.actualWave.add(new Virus(startTilePos[0] * 32 + 16, startTilePos[1] * 32 + 16, levelPane, this, this.player));
                    }
                }
                case 4 -> {
                    if (this.actualWaveNumber.get() <= 15) {
                        this.actualWave.add(new DotSH(startTilePos[0] * 32 + 16, startTilePos[1] * 32 + 16, levelPane, this, this.player));
                    } else {
                        this.actualWave.add(new Scam(startTilePos[0] * 32 + 16, startTilePos[1] * 32 + 16, levelPane, this, this.player));
                    }
                }
                case 5 -> {
                    if (this.actualWaveNumber.get() <= 20) {
                        this.actualWave.add(new DotSH(startTilePos[0] * 32 + 16, startTilePos[1] * 32 + 16, levelPane, this, this.player));
                    } else {
                        this.actualWave.add(new DotExe(startTilePos[0] * 32 + 16, startTilePos[1] * 32 + 16, levelPane, this, this.player));
                    }
                }
            }
        }
        setActualWaveNumber(actualWaveNumber.get() + 1);
    }

    public void doTurn(int nbTours) {
        if (actualWave.size() == 0 && ennemies.size() == 0) {
            createWave(this.waveSize);
            this.waveSize += actualWaveNumber.get() * difficulty / 3;
            // nextWave();
        } else if (nbTours % 20 == 0 && actualWave.size() != 0) {
            this.ennemies.add(this.actualWave.remove(0));
        }
        if (this.ennemies.size() > 0) {
            for (int i = ennemies.size() - 1; i >= 0; i--) {
                Ennemy e = ennemies.get(i);
                e.move();
                if (e.isOnObjective() || !e.isOnBound() || e.estMort()) {
                    e.doDamage();
                    ennemies.remove(e);
                }
            }
        }
        this.player.isDead();
    }

    public void tourAgir(int nbTours) {
        for (int i =  placedTower.size() - 1 ; i>=0 ; i--) {
            Tower t = placedTower.get(i);
            ennemiesDansLaZone = t.detect(ennemies);

            if(ennemiesDansLaZone.size()!=0){
                for (int j = ennemiesDansLaZone.size() - 1; j >= 0; j--) {
                    Ennemy detectedEnnemy = ennemiesDansLaZone.get(j);
                    Ennemy firstDetect = ennemiesDansLaZone.get(0);

                    Projectile p  = null;

                    if (t instanceof TaskKiller) {
                        p = new ProjectileDegatsBrut(t.getX() + 16, t.getY() + 16, firstDetect);
                    }
                    else if (t instanceof CCleaner) { //ff
                        p = new ProjectileDegatsBrut(t.getX() + 16, t.getY() + 16, detectedEnnemy);
                    }
                    else if (t instanceof PDFConverter) {
                        if(firstDetect instanceof Archive)
                            p = new ProjectileDotSH(t.getX() + 16, t.getY() + 16, firstDetect);
                    }
                    else if (t instanceof InternetExplorer) {
                        p = new ProjectileRalentisseur(t.getX() + 16, t.getY() + 16, detectedEnnemy);
                    }
                    else if (t instanceof NordVPN) {
                        p = new ProjectileKnockBack(t.getX() + 16, t.getY() + 16, firstDetect);
                    }
                    else if (t instanceof Demineur) {
                        if((!(firstDetect instanceof DotExe) &&  !(firstDetect instanceof Virus) && !(firstDetect instanceof DotSH)))
                            p = new ProjectileDotSH(t.getX() + 16, t.getY() + 16, firstDetect);
                    }

                    ennemiesDansLaZone.remove(detectedEnnemy);
                    if (nbTours % t.getDelais() == 0 && p != null) {
                        projectiles.add(p);

                    }
                }
            }

            else {
                for (int n = ennemies.size() - 1; n >= 0; n--) {
                    ennemies.get(n).resetSpped();
                }
            }
        }
    }

    public boolean verifProgression() {
        return player.isDead();
    }


    public void animationProjectiles(int nbT) {

        for (Projectile p : projectiles) {


            if (p instanceof ProjectileDegatsBrut || p instanceof  ProjectileRalentisseur || p instanceof  ProjectileKnockBack) {
                p.moveProjectile();
                p.agitSurLaCible();

            }

            else if (p instanceof ProjectileDotSH) {
                cpt = 0 ;
                {
                    p.moveProjectile();
                    p.agitSurLaCible();
                }
            }
        }

        for (int j = projectiles.size() - 1; j >= 0; j--) {
            Projectile p = projectiles.get(j);

            if(p instanceof ProjectileDegatsBrut || p instanceof  ProjectileKnockBack) {
                if (p.cibleAtteint() || p.isOnBound()) {
                    projectiles.remove(p);
                }
            }

            else if(p instanceof ProjectileDotSH) {

                if ((p.cibleAtteint() || p.isOnBound())) {
                    projectiles.remove(p);
                 cpt ++ ;
                    System.out.println(cpt);
                 if(cpt == 3) {
                     ennemies.remove(p.getE());
                     this.ennemies.add(new DotSH(p.getE().getX(), p.getE().getY(), levelPane, this, this.player));
                 }
                }
            }

            else if(p instanceof ProjectileRalentisseur){
                if (!p.cibleAtteint() && ennemiesDansLaZone.size()==0) {
                    projectiles.remove(p);
                }
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

    public int[] getTilePos(int x, int y) {
        int[] pos = new int[2];
        pos[0] = x / 32;
        pos[1] = y / 32;
        return pos;
    }

    public Player getPlayer() {
        return player;
    }

    public Tower getTower(String id) {
        Tower t = null;
        for (Tower tower : this.getPlacedTower()) {
            if (tower.getId().equals(id)) {
                t = tower;
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
        nextWavePopup.show(Main.stg, Main.stg.getHeight() / 2, Main.stg.getWidth() / 2);
        for (int i = timer; i > 0; i--) {
            nextWaveLabel.setText("New wave in " + i);
        }
        nextWavePopup.hide();
    }

    public void flopGain() {
        for (int i = ennemies.size() - 1; i >= 0; i--) {
            Ennemy e = ennemies.get(i);
            if (e.estMort()) {
                player.setFlop(player.getFlop() + (e.getDropRate()* (int)(actualWaveNumber.getValue()*0.5)));
            }
        }
    }
}