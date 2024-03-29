package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import fr.montreuil.iut.Lucas_Adrien_Imman.Main;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.*;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Projectiles.*;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Tours.*;
import fr.montreuil.iut.Lucas_Adrien_Imman.vue.PopupVue;
import javafx.beans.property.SimpleIntegerProperty;
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
    private int nbTours;
    private Player player;
    private int difficulty;
    private SimpleIntegerProperty actualWaveNumber;
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
    private int nbActeurs ;
    private int startDirection;
    private int cpt ;


    //For freezing ram state
    private boolean freezingRam;
    private int startFreezingRam;
    private int freezingDelay;
    private int freezedRamAmount;

    //For poisoning state
    private boolean poisoning;
    private int startPoisoning;
    private int poisoningDelay;
    private int poisonedAmount;
    private int poisonTicks;

    public Level(Pane levelPane){
        this.levelPane = levelPane;
        this.tileMap = new ArrayList<>();
        this.travelingMap = new ArrayList<>();
        this.ennemiesDansLaZone = new ArrayList<>();
        this.placedTower = FXCollections.observableArrayList();
        this.ennemies = FXCollections.observableArrayList();
        this.startTilePos = new int[2];
        this.endTilePos = new int[2];
        this.actualWave = new ArrayList<>();
        this.waveSize = 3;
        this.actualWaveNumber = new SimpleIntegerProperty(0);
        this.projectiles = FXCollections.observableArrayList();
        this.nbActeurs = 4;
        this.freezingDelay = 350;
        this.freezingRam = false;
        this.freezedRamAmount = 0;
        this.poisoning = false;
        this.poisoningDelay = 150;
        this.poisonedAmount = 0;
    }


    //GETTER
    public ArrayList<ArrayList<Integer>> getTileMap() {
        return tileMap;
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

    public int getActualWaveNumber() {
        return actualWaveNumber.get();
    }

    public SimpleIntegerProperty actualWaveNumberProperty() {
        return actualWaveNumber;
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

    /***
     * Donne la valeur de la position dans la Traveling Map
     */
    public int getTileValue(int[] pos) {
        return this.getTravelingMap().get(pos[1]).get(pos[0]);
    }

    /**
     *Obliger de diviser par 32 pour la convertir en index d'ArrayList
     */
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

    /**
     *Renvoie une position cardinal (cf Enemy) par rapport à la position de départ dans la travelingMap
     */
    public int getStartDirection(){
        int startTile = this.tileMap.get(startTilePos[0]).get(startTilePos[1]);
        System.out.println(startTile);
        int direction = 0;
        if (startTile == 13){
            direction = 4;
        }else if (startTile == 14){
            direction = 3;
        }else if (startTile == 12){
            direction = 2;
        }else if (startTile == 11){
            direction = 1;
        }
        return direction;
    }

    //SETTER
    public void setActualWaveNumber(int actualWaveNumber) {
        this.actualWaveNumber.set(actualWaveNumber);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void setTileMap(ArrayList<ArrayList<Integer>> tileMap) {
        this.tileMap = tileMap;
    }

    public void setTravelingMap(ArrayList<ArrayList<Integer>> tileMap){
        this.travelingMap = tileMapToTraveling(this.tileMap);
    }

    //OTHER METHODS

    public void freezeRam(int amount){
        this.freezingRam = true;
        this.freezedRamAmount += amount;
        this.startFreezingRam = this.nbTours;
        this.player.setRam(this.player.getRam() - amount);
    }

    public void defreezeRam(){
        if (freezingRam){
            if (this.nbTours - this.startFreezingRam >= freezingDelay){
                this.freezingRam = false;
                this.player.setRam(this.player.getRam() + freezedRamAmount);
                this.freezedRamAmount = 0;
            }
        }
    }

    public void flopGain() {// système de récompense par rapport aux type d'ennemi , et au nombre de vague
        for (int i = ennemies.size() - 1; i >= 0; i--) {
            Ennemy e = ennemies.get(i);
            if (e.isDead()) {
                player.setFlop(player.getFlop() + (e.getDropRate()* (int)(actualWaveNumber.getValue()*0.5)));
                player.setRam(player.getRam() + (int)(actualWaveNumber.getValue()*0.2));
            }



        }
    }

    public void applyPoison(int amount){
        this.poisoning = true;
        this.poisonedAmount += amount;
        this.startPoisoning = this.nbTours;
        this.poisonTicks = 0;
    }

    public void poison(){
        if (this.poisoning && this.poisonTicks < 4 && this.nbTours - this.startPoisoning >= poisoningDelay){
            this.player.setLife(this.player.getLife() - poisonedAmount);
            this.startPoisoning = this.nbTours;
            poisonTicks++;
            if (poisonTicks == 3){
                poisonTicks = 0;
                poisoning = false;
            }
        }
    }

    public void sellTower(Tower t){
        this.player.setFlop(this.player.getFlop() + t.getSellingPrice());
        this.player.setRam(this.player.getRam()+t.getRamPrice());
        this.placedTower.remove(t);
    }

    public boolean checkProgression() {
        return player.isDead();
    }

    public void towerTurn(int nbTours) {
        for (int i =  placedTower.size() - 1 ; i>=0 ; i--) {
            Tower t = placedTower.get(i);
            ennemiesDansLaZone = t.detect(ennemies); //liste des ennemies détectés

            if(ennemiesDansLaZone.size()!=0){
                for (int j = ennemiesDansLaZone.size() - 1; j >= 0; j--) {
                    Ennemy detectedEnnemy = ennemiesDansLaZone.get(j);
                    Ennemy firstDetect = ennemiesDansLaZone.get(0);

                    Projectile p  = null;

                    if (t instanceof TaskKiller) { // ajoute au liste des projectiles le projectile correspondant au tour
                        p = new ProjectileDegatsBrut(t.getX() + 16, t.getY() + 16, firstDetect);
                    }
                    else if (t instanceof CCleaner) {
                        p = new ZoneElectrique(t.getX() + 16, t.getY() + 16, detectedEnnemy);
                    }
                    else if (t instanceof PDFConverter) {
                        if(firstDetect instanceof DotExe) {
                            p = new ProjectileDotSH(t.getX() + 16, t.getY() + 16, firstDetect);
                        }
                    }
                    else if (t instanceof InternetExplorer) {
                        p = new ZoneRalentisseur(t.getX() + 16, t.getY() + 16, detectedEnnemy);
                    }
                    else if (t instanceof NordVPN) {
                        p = new ProjectileKnockBack(t.getX() + 16, t.getY() + 16, firstDetect);
                    }
                    else if (t instanceof Demineur) {
                        if((!(firstDetect instanceof DotExe) &&  !(firstDetect instanceof Virus) && !(firstDetect instanceof Scam) && !(firstDetect instanceof Kamikaze)))
                            p = new ProjectileKamikaze(t.getX() + 16, t.getY() + 16, firstDetect);
                    }

                    ennemiesDansLaZone.remove(detectedEnnemy);
                    if (nbTours % t.getDelais() == 0 && p != null) {//le délais attaque
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
    public void bulletTurn() {

        for (Projectile p : projectiles) {   //déplacement des projectiles et agit sur la cible
            p.placement();
            p.agitSurLaCible();
        }

        for (int j = projectiles.size() - 1; j >= 0; j--) {//enleve les projectiles/zones  par rapport aux conditions
            Projectile p = projectiles.get(j);

            if(p instanceof ProjectileDegatsBrut || p instanceof  ProjectileKnockBack) {
                if (p.cibleAtteint() || p.isOnBound()) {
                    projectiles.remove(p);
                }
            }

            else if (p instanceof ProjectileDotSH) {
                if (p.cibleAtteint()) {
                    projectiles.remove(p);
                    cpt++;
                    if (cpt == 3) {
                        ennemies.remove(p.getEnnemyCible());
                        this.ennemies.add(new DotSH(p.getEnnemyCible().getX(), p.getEnnemyCible().getY(), levelPane, this, this.player, this.getStartDirection()));
                        cpt = 0;
                    }
                }
            }

            else if(p instanceof ProjectileKamikaze) {

                if ((p.cibleAtteint() || p.isOnBound())) {
                    projectiles.remove(p);
                    cpt ++ ;
                    System.out.println(cpt);
                    if(cpt == 3) {
                        ennemies.remove(p.getEnnemyCible());
                        this.ennemies.add(new Kamikaze(p.getEnnemyCible().getX(), p.getEnnemyCible().getY(), levelPane, this, this.player, p.getEnnemyCible().getOppositeDirection()));
                        cpt = 0 ;
                    }
                }
            }



            else if(p instanceof ZoneRalentisseur || p instanceof ZoneElectrique){
                if (!p.cibleAtteint() && ennemiesDansLaZone.size()==0 || p.getEnnemyCible().isDead()) {
                    projectiles.remove(p);
                }
            }
        }
    }

    public boolean enemiesTurn(int nbTours){

        if (actualWave.size() == 0 && ennemies.size() == 0){
            createWave(this.waveSize);
            this.waveSize += actualWaveNumber.get()*difficulty/3;
        }else if (nbTours % 20 == 0 && actualWave.size() != 0){
            this.ennemies.add(this.actualWave.remove(0));
        }
        if (this.ennemies.size() > 0){ //fait déplacer les ennemis , les enleve de la liste  si ils ont atteint l'objectif , ou il sont en dehors de la map  ou s'il sont morts
            for (int i = ennemies.size()-1; i>=0 ; i--) {
                Ennemy e = ennemies.get(i);
                e.move();
                if (e instanceof Kamikaze){
                    if (getTileValue(getTilePos(e.getX(), e.getY())) == 6){
                        ennemies.remove(e);
                    }
                    Ennemy newEnemy = e.isTouching(ennemies);
                    if (e.isTouching(ennemies) != null){
                        newEnemy.setLife(newEnemy.getLife().get()-e.getDamage());
                    }
                }else{
                    if (e.isOnObjective() || !e.isOnBound()) {
                        e.doDamage();
                        ennemies.remove(e);
                    } else if (e.isDead()) {
                        e.die();
                        ennemies.remove(e);
                    }
                }

            }
        }
        this.nbTours = nbTours;
        defreezeRam();
        poison();
        return this.player.isDead();
    }

    public void createWave(int size){
        this.startDirection = getStartDirection();
        for (int i = 0; i < size; i++) {
            switch ((int) ((Math.random() * (6 - 1)) + 1)){
                case 1 -> {
                    this.actualWave.add(new DotSH(startTilePos[0]*32 +16, startTilePos[1]*32 +16, levelPane, this, this.player, this.getStartDirection()));
                }
                case 2 -> {
                    if (this.actualWaveNumber.get() <= 5){
                        this.actualWave.add(new DotSH(startTilePos[0]*32 +16, startTilePos[1]*32 +16, levelPane, this, this.player, this.getStartDirection()));
                    }else{
                        this.actualWave.add(new Archive(startTilePos[0]*32 +16, startTilePos[1]*32 +16, levelPane, this, this.player, this.getStartDirection()));
                    }
                }
                case 3 -> {
                    if (this.actualWaveNumber.get() <= 10){
                        this.actualWave.add(new DotSH(startTilePos[0]*32 +16, startTilePos[1]*32 +16, levelPane, this, this.player, this.getStartDirection()));
                    }else{
                        this.actualWave.add(new Virus(startTilePos[0]*32 +16, startTilePos[1]*32 +16, levelPane, this, this.player, this.getStartDirection()));
                    }
                }
                case 4 -> {
                    if (this.actualWaveNumber.get() <= 15){
                        this.actualWave.add(new DotSH(startTilePos[0]*32 +16, startTilePos[1]*32 +16, levelPane, this, this.player, this.getStartDirection()));
                    }else{
                        this.actualWave.add(new Scam(startTilePos[0]*32 +16, startTilePos[1]*32 +16, levelPane, this, this.player, this.getStartDirection()));
                    }
                }
                case 5 -> {
                    if (this.actualWaveNumber.get() <= 20){
                        this.actualWave.add(new DotSH(startTilePos[0]*32 +16, startTilePos[1]*32 +16, levelPane, this, this.player, this.getStartDirection()));
                    }else{
                        this.actualWave.add(new DotExe(startTilePos[0]*32 +16, startTilePos[1]*32 +16, levelPane, this, this.player, this.getStartDirection()));
                    }
                }
            }
        }
        setActualWaveNumber(actualWaveNumber.get() + 1);
    }

    public void placeTower(int x , int y, int index){
        int[] pos = getTilePos(x, y);
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
        if (canPlaceTower(t)){
            addTower(t);
        }else{
            PopupVue popupVue = new PopupVue();
            popupVue.createTemporaryPopup("Sorry but you haven't enough ressources");
        }

    }

    public boolean validTile(int[] pos){
        return getTile(pos) == 0;
    }

    public void startLevel(int nbTours){
        enemiesTurn(nbTours);
        towerTurn(nbTours);
        bulletTurn();
        flopGain();
    }

    public boolean canPlaceTower(Tower tower){
        return this.player.getFlop() >= tower.getFlopPrice() && this.player.getRam() >= tower.getRamPrice();
    }

    public void addTower(Tower tower){
        this.placedTower.add(tower);
    }

    public ArrayList<ArrayList<Integer>> tileMapToTraveling(ArrayList<ArrayList<Integer>> tileMap) {
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
}