package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplacement.DeplacementBFS;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplacement.ModeDeplacement;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetsTours.Projectile;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.*;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Projectiles.*;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Tours.*;
import fr.montreuil.iut.Lucas_Adrien_Imman.vue.PopupVue;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Environnement {
    // private ArrayList<CooldownState> states; attribut de acteur !!
    private Ground ground;
    private int nbTours;
    private Player player;
    private int difficulty;
    private SimpleIntegerProperty actualWaveNumber;
    private ArrayList<Ennemy> actualWave;


    private ObservableList<Tower> placedTower;
    private ObservableList<Ennemy> ennemies;
    private ArrayList<Ennemy> ennemiesDansLaZone ;
    private ObservableList<Projectile> projectiles;


    private Pane levelPane;
    private int waveSize;
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

    public Environnement(Pane levelPane){
        this.levelPane = levelPane;
        this.ground = new Ground();
        this.ennemiesDansLaZone = new ArrayList<>();
        this.placedTower = FXCollections.observableArrayList();
        this.ennemies = FXCollections.observableArrayList();
        this.actualWave = new ArrayList<>();
        this.waveSize = 3;
        this.actualWaveNumber = new SimpleIntegerProperty(0);
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

    public ObservableList<Projectile> getProjectiles(){
        return projectiles ;
    }

    public int getActualWaveNumber() {
        return actualWaveNumber.get();
    }

    public SimpleIntegerProperty actualWaveNumberProperty() {
        return actualWaveNumber;
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
    public void setActualWaveNumber(int actualWaveNumber) {
        this.actualWaveNumber.set(actualWaveNumber);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
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
        moveAndActOnTargetForAllProjectiles();
        removeAndTransformProjectiles();
    }

    private void moveAndActOnTargetForAllProjectiles() {
        for (Projectile p : projectiles) {
            p.placement();
            p.agitSurLaCible();
        }
    }

    private void removeAndTransformProjectiles() {
        int cpt = 0;

        for (int j = projectiles.size() - 1; j >= 0; j--) {
            Projectile p = projectiles.get(j);

            if (p instanceof ProjectileDegatsBrut || p instanceof ProjectileKnockBack) {
                handleDegatsBrutAndKnockBack(p);
            } else if (p instanceof ProjectileDotSH) {
                cpt = handleDotSH(p, cpt);
            } else if (p instanceof Kamikaze) {
                cpt = handleKamikaze(p, cpt);
            } else if (p instanceof ZoneRalentisseur || p instanceof ZoneElectrique) {
                handleZones(p);
            }
        }
    }

    private void handleDegatsBrutAndKnockBack(Projectile p) {
        if (p.cibleAtteint() || p.isOnBound()) {
            projectiles.remove(p);
        }
    }

    private int handleDotSH(Projectile p, int cpt) {
        if (p.cibleAtteint()) {
            projectiles.remove(p);
            cpt++;
            if (cpt == 3) {
                transformToDotSH(p.getEnnemyCible());
                cpt = 0;
            }
        }
        return cpt;
    }

    private void transformToDotSH(Ennemy ennemy) {
        ennemies.remove(ennemy);
        ennemies.add(new DotSH(ennemy.getX(), ennemy.getY(), levelPane, this, this.player, this.ground.getStartDirection()));
    }

    private int handleKamikaze(Projectile p, int cpt) {
        if (p.isOnObjective() || p.isOnBound()) {
            projectiles.remove(p);
            cpt++;
            if (cpt == 3) {
                transformToKamikaze(p.getEnnemyCible());
                cpt = 0;
            }
        }
        return cpt;
    }

    private void transformToKamikaze(Ennemy ennemy) {
        ennemies.remove(ennemy);
        ennemies.add(new Kamikaze(ennemy.getX(), ennemy.getY(), levelPane, this, this.player, ennemy.getOppositeDirection()));
    }

    private void handleZones(Projectile p) {
        if (!p.isOnObjective() && ennemiesDansLaZone.size() == 0 || p.getEnnemyCible().isDead()) {
            projectiles.remove(p);
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
                    if (ground.getTileValue(ground.getTilePos(e.getX(), e.getY())) == 6){
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
        int direction = this.ground.getStartDirection();
        for (int i = 0; i < size; i++) {
            int x = ground.getStartTilePos()[0]*32 + 16;
            int y = ground.getStartTilePos()[1]*32 + 16;
            Pane levelPane = this.levelPane;
            Environnement env = this;
            Player player = this.player;
            ModeDeplacement md = new DeplacementBFS(); // Supposant que vous avez un getter pour ModeDeplacement

            switch ((int) ((Math.random() * (6 - 1)) + 1)){
                case 1:
                    this.actualWave.add(new DotSH(x, y, levelPane, env, player, direction, md));
                    break;
                case 2:
                    if (this.actualWaveNumber.get() <= 5){
                        this.actualWave.add(new DotSH(x, y, levelPane, env, player, direction, md));
                    } else {
                        this.actualWave.add(new Archive(x, y, levelPane, env, player, direction, md));
                    }
                    break;
                case 3:
                    if (this.actualWaveNumber.get() <= 10){
                        this.actualWave.add(new DotSH(x, y, levelPane, env, player, direction, md));
                    } else {
                        this.actualWave.add(new Virus(x, y, levelPane, env, player, direction, md));
                    }
                    break;
                case 4:
                    if (this.actualWaveNumber.get() <= 15){
                        this.actualWave.add(new DotSH(x, y, levelPane, env, player, direction, md));
                    } else {
                        this.actualWave.add(new Scam(x, y, levelPane, env, player, direction, md));
                    }
                    break;
                case 5:
                    if (this.actualWaveNumber.get() <= 20){
                        this.actualWave.add(new DotSH(x, y, levelPane, env, player, direction, md));
                    } else {
                        this.actualWave.add(new DotExe(x, y, levelPane, env, player, direction, md));
                    }
                    break;
            }
        }
        setActualWaveNumber(actualWaveNumber.get() + 1);
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

    public Ground getGround() {
        return ground;
    }
}