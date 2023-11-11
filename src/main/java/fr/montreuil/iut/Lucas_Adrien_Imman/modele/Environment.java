package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplacement.DeplacementBFS;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplacement.ModeDeplacement;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetsTours.*;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.*;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.*;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetTours.*;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Tours.*;
import fr.montreuil.iut.Lucas_Adrien_Imman.vue.PopupVue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Environment {
    // private ArrayList<CooldownState> states; attribut de acteur !!
    private Ground ground;
    private Wave wave;
    private int nbTours;
    private Player player;
    private int difficulty;

    private ModeDeplacement modeDeplacementBFS;


    private ObservableList<Tower> placedTower;
    private ObservableList<Ennemy> ennemies;
    private ObservableList<EffetTour> effetTours;


    private ArrayList<Ennemy> ennemiesDansLaZone ;
    private static Environment instance = null;


    private Pane levelPane;
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

    public Environment(){
        this.ground = new Ground();
        this.wave = new Wave();
        this.ennemiesDansLaZone = new ArrayList<>();
        this.placedTower = FXCollections.observableArrayList();
        this.ennemies = FXCollections.observableArrayList();

        this.modeDeplacementBFS = new DeplacementBFS();
        this.projectiles = FXCollections.observableArrayList();
        this.freezingDelay = 350;
        this.freezingRam = false;
        this.freezedRamAmount = 0;
        this.poisoning = false;
        this.poisoningDelay = 150;
        this.poisonedAmount = 0;
    }


    //GETTER


    public ObservableList<Tower> getPlacedTower() {
        return placedTower;
    }

    public ObservableList<Ennemy> getEnnemies() {
        return ennemies;
    }

    public ObservableList<EffetTour> getEffetTours(){
        return effetTours;
    }

    public Wave getWave() {
        return wave;
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


    //SETTER


    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void setLevelPane(Pane levelPane) {
        this.levelPane = levelPane;
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
                player.setFlop(player.getFlop() + (e.getDropRate()* (int)(this.wave.getActualWaveNumber()*0.5)));
                player.setRam(player.getRam() + (int)(this.wave.getActualWaveNumber()*0.2));
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

    public void towerTurn(int nbTours, ModeDeplacement md) {
        for (int i =  placedTower.size() - 1 ; i>=0 ; i--) {
            Tower t = placedTower.get(i);
            EffetTour p ;
            ennemiesDansLaZone = t.detect(ennemies); //liste des ennemies détectés

            if(ennemiesDansLaZone.size()!=0){
                for (int j = ennemiesDansLaZone.size() - 1; j >= 0; j--) {
                    Ennemy detectedEnnemy = ennemiesDansLaZone.get(j);
                    Ennemy firstDetect = ennemiesDansLaZone.get(0);


                    if(t instanceof InternetExplorer || t instanceof  CCleaner) {
                       p  = t.getEffet(detectedEnnemy);
                    }
                    else {
                        p = t.getEffet(firstDetect);
                    }
                   /* if (t instanceof TaskKiller) { // ajoute au liste des projectiles le projectile correspondant au tour
                        p = new ProjectileDegatsBrut(t.getX() + 16, t.getY() + 16, firstDetect);
                    }
                    else if (t instanceof NordVPN) {
                        p = new ProjectileKnockBack(t.getX() + 16, t.getY() + 16, firstDetect);
                    }
                    else if (t instanceof PDFConverter) {
                        if(firstDetect instanceof DotExe) {
                            p = new ProjectileDotSH(t.getX() + 16, t.getY() + 16, firstDetect);
                        }
                    }
                    else if (t instanceof Demineur) {
                        if((!(firstDetect instanceof DotExe) &&  !(firstDetect instanceof Virus) && !(firstDetect instanceof Scam) && !(firstDetect instanceof Kamikaze)))
                            p = new ProjectileKamikaze(t.getX() + 16, t.getY() + 16, firstDetect);
                    }
                    else if (t instanceof InternetExplorer) {
                        p = new ZoneRalentisseur(t.getX() + 16, t.getY() + 16, detectedEnnemy);
                    }
                    else if (t instanceof CCleaner) {
                        p = new ZoneElectrique(t.getX() + 16, t.getY() + 16, detectedEnnemy);
                    }
                    */
                    ennemiesDansLaZone.remove(detectedEnnemy);
                    if (nbTours % t.getDelais() == 0 && p != null) {//le délais attaque
                        effetTours.add(p);

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

        for (EffetTour p : effetTours) {   //déplacement des projectiles et agit sur la cible
            p.algoDeplacement();
            p.agitSurLaCible();
        }

        for (int j = effetTours.size() - 1; j >= 0; j--) {//enleve les projectiles/zones  par rapport aux conditions
            EffetTour p = effetTours.get(j);

            if(p instanceof ProjectileDegatsBrut || p instanceof  ProjectileKnockBack) {
                if (p.isOnObjective() || p.isOnBound()) {
                    effetTours.remove(p);
                }
            }

            else if (p instanceof ProjectileDotSH) {
                if (p.isOnObjective()) {
                    effetTours.remove(p);
                    cpt++;
                    if (cpt == 3) {
                        ennemies.remove(p.getEnnemyCible());
                        this.ennemies.add(new DotSH(p.getEnnemyCible().getXValue(), p.getEnnemyCible().getYValue(), levelPane, this, this.player, this.ground.getStartDirection(), this.modeDeplacementBFS));
                        cpt = 0;
                    }
                }
            }

            else if(p instanceof ProjectileKamikaze) {

                if ((p.isOnObjective() || p.isOnBound())) {
                    effetTours.remove(p);
                    cpt ++ ;
                    if(cpt == 3) {
                        ennemies.remove(p.getEnnemyCible());
                        this.ennemies.add(new Kamikaze(p.getEnnemyCible().getXValue(), p.getEnnemyCible().getYValue(), levelPane, this, this.player, p.getEnnemyCible().getOppositeDirection(), this.modeDeplacementBFS));
                        cpt = 0 ;
                    }
                }
            }

            else if(p instanceof ZoneRalentisseur || p instanceof ZoneElectrique){
                if (!p.isOnObjective() && ennemiesDansLaZone.size()==0 || p.getEnnemyCible().isDead()) {
                    effetTours.remove(p);
                }
            }
        }
    }

    public boolean enemiesTurn(int nbTours){

        if (this.wave.getActualWave().size() == 0 && ennemies.size() == 0){
            this.wave.createWave(this.wave.getWaveSize(), this.ground, this.levelPane, this.player, this);
            this.wave.setWaveSize(this.wave.getWaveSize() + this.wave.getActualWaveNumber()*this.difficulty/3);
        }else if (nbTours % 20 == 0 && this.wave.getActualWave().size() != 0){
            this.ennemies.add(this.wave.getActualWave().remove(0));
        }
        if (this.ennemies.size() > 0){ //fait déplacer les ennemis, les enleve de la liste  si ils ont atteint l'objectif , ou il sont en dehors de la map  ou s'il sont morts
            for (int i = ennemies.size()-1; i>=0 ; i--) {
                Ennemy e = ennemies.get(i);
                e.move();
                if (e instanceof Kamikaze){
                    if (ground.getTileValue(ground.getTilePos(e.getXValue(), e.getYValue())) == 6){
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

    public void placeTower(int x , int y, int index){
        int[] pos = ground.getTilePos(x, y);
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


    public void startLevel(int nbTours){
        enemiesTurn(nbTours);
        towerTurn(nbTours, this.modeDeplacementBFS);
        bulletTurn();
        flopGain();
    }

    public boolean canPlaceTower(Tower tower){
        return this.player.getFlop() >= tower.getFlopPrice() && this.player.getRam() >= tower.getRamPrice();
    }

    public void addTower(Tower tower){
        this.placedTower.add(tower);
    }

    public static Environment getInstance() {
        if (instance == null) {
            instance = new Environment();
        }
        return instance;
    }

    public Pane getLevelPane() {
        return levelPane;
    }

    public Ground getGround() {
        return ground;
    }
}