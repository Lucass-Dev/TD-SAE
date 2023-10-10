package fr.montreuil.iut.Lucas_Adrien_Imman.modele;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Waves {

    private Player player;
    private SimpleIntegerProperty actualWaveNumber;
    private ArrayList<Ennemy> actualWave;
    private ArrayList<ArrayList<Integer>> tileMap;
    private int[] startTilePos;
    private int[] endTilePos;
    private int startDirection;
    private ArrayList<ArrayList<Integer>> travelingMap;

    public Waves(){
        this.tileMap = new ArrayList<>();
        this.startTilePos = new int[2];
        this.endTilePos = new int[2];
        this.actualWave = new ArrayList<>();
        this.actualWaveNumber = new SimpleIntegerProperty(0);
    }

    public void createWave(int size, Level level, Pane levelPane){
        this.startDirection = getStartDirection();
        for (int i = 0; i < size; i++) {
            switch ((int) ((Math.random() * (6 - 1)) + 1)){
                case 1 -> {
                    this.actualWave.add(new DotSH(startTilePos[0]*32 +16, startTilePos[1]*32 +16, levelPane, level, this.player, this.getStartDirection()));
                }
                case 2 -> {
                    if (this.actualWaveNumber.get() <= 5){
                        this.actualWave.add(new DotSH(startTilePos[0]*32 +16, startTilePos[1]*32 +16, levelPane, level, this.player, this.getStartDirection()));
                    }else{
                        this.actualWave.add(new Archive(startTilePos[0]*32 +16, startTilePos[1]*32 +16, levelPane, level, this.player, this.getStartDirection()));
                    }
                }
                case 3 -> {
                    if (this.actualWaveNumber.get() <= 10){
                        this.actualWave.add(new DotSH(startTilePos[0]*32 +16, startTilePos[1]*32 +16, levelPane, level, this.player, this.getStartDirection()));
                    }else{
                        this.actualWave.add(new Virus(startTilePos[0]*32 +16, startTilePos[1]*32 +16, levelPane, level, this.player, this.getStartDirection()));
                    }
                }
                case 4 -> {
                    if (this.actualWaveNumber.get() <= 15){
                        this.actualWave.add(new DotSH(startTilePos[0]*32 +16, startTilePos[1]*32 +16, levelPane, level, this.player, this.getStartDirection()));
                    }else{
                        this.actualWave.add(new Scam(startTilePos[0]*32 +16, startTilePos[1]*32 +16, levelPane, level, this.player, this.getStartDirection()));
                    }
                }
                case 5 -> {
                    if (this.actualWaveNumber.get() <= 20){
                        this.actualWave.add(new DotSH(startTilePos[0]*32 +16, startTilePos[1]*32 +16, levelPane, level, this.player, this.getStartDirection()));
                    }else{
                        this.actualWave.add(new DotExe(startTilePos[0]*32 +16, startTilePos[1]*32 +16, levelPane, level, this.player, this.getStartDirection()));
                    }
                }
            }
        }
        setActualWaveNumber(actualWaveNumber.get() + 1);
    }


    public int getActualWaveNumber() {
        return actualWaveNumber.get();
    }

    public void setActualWaveNumber(int actualWaveNumber) {
        this.actualWaveNumber.set(actualWaveNumber);
    }

    public SimpleIntegerProperty actualWaveNumberProperty() {
        return actualWaveNumber;
    }

    /**
     *Renvoie une position cardinal (cf Enemy) par rapport à la position de départ dans la travelingMap
     */
    public int getStartDirection(){
        int startTile = this.tileMap.get(startTilePos[0]).get(startTilePos[1]);
        //System.out.println(startTile);
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

    public void setTileMap(ArrayList<ArrayList<Integer>> tileMap) {
        this.tileMap = tileMap;
    }

    public void setTravelingMap(ArrayList<ArrayList<Integer>> tileMap){
        this.travelingMap = tileMapToTraveling(this.tileMap);
    }

    public ArrayList<ArrayList<Integer>> getTravelingMap(){
        return this.travelingMap;
    }
}