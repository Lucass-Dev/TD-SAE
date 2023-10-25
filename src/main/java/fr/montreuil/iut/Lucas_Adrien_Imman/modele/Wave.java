package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Wave {

    private SimpleIntegerProperty actualWaveNumber;
    private ArrayList<Ennemy> actualWave;
    private int waveSize;


    public Wave(){
        this.actualWaveNumber = new SimpleIntegerProperty(0);
        this.actualWave = new ArrayList<>();
        this.waveSize = 3;
    }
    public int getActualWaveNumber() {
        return actualWaveNumber.get();
    }

    public IntegerProperty getActualWaveNumberProperty(){return this.actualWaveNumber;}

    public SimpleIntegerProperty actualWaveNumberProperty() {
        return actualWaveNumber;
    }
    public void setActualWaveNumber(int actualWaveNumber) {
        this.actualWaveNumber.set(actualWaveNumber);
    }

    public void createWave(int size, Ground ground, Pane levelPane, Player player, Environment environment){
        int direction = ground.getStartDirection();
        for (int i = 0; i < size; i++) {
            switch ((int) ((Math.random() * (6 - 1)) + 1)){
                case 1 -> {
                    this.actualWave.add(new DotSH(ground.getStartTilePos()[0]*32 +16, ground.getStartTilePos()[1]*32 +16, levelPane, environment, player, direction));
                }
                case 2 -> {
                    if (this.actualWaveNumber.get() <= 5){
                        this.actualWave.add(new DotSH(ground.getStartTilePos()[0]*32 +16, ground.getStartTilePos()[1]*32 +16, levelPane, environment, player, direction));
                    }else{
                        this.actualWave.add(new Archive(ground.getStartTilePos()[0]*32 +16, ground.getStartTilePos()[1]*32 +16, levelPane, environment, player, direction));
                    }
                }
                case 3 -> {
                    if (this.actualWaveNumber.get() <= 10){
                        this.actualWave.add(new DotSH(ground.getStartTilePos()[0]*32 +16, ground.getStartTilePos()[1]*32 +16, levelPane, environment, player, direction));
                    }else{
                        this.actualWave.add(new Virus(ground.getStartTilePos()[0]*32 +16, ground.getStartTilePos()[1]*32 +16, levelPane, environment, player, direction));
                    }
                }
                case 4 -> {
                    if (this.actualWaveNumber.get() <= 15){
                        this.actualWave.add(new DotSH(ground.getStartTilePos()[0]*32 +16, ground.getStartTilePos()[1]*32 +16, levelPane, environment, player, direction));
                    }else{
                        this.actualWave.add(new Scam(ground.getStartTilePos()[0]*32 +16, ground.getStartTilePos()[1]*32 +16, levelPane, environment, player, direction));
                    }
                }
                case 5 -> {
                    if (this.actualWaveNumber.get() <= 20){
                        this.actualWave.add(new DotSH(ground.getStartTilePos()[0]*32 +16, ground.getStartTilePos()[1]*32 +16, levelPane, environment, player, direction));
                    }else{
                        this.actualWave.add(new DotExe(ground.getStartTilePos()[0]*32 +16, ground.getStartTilePos()[1]*32 +16, levelPane, environment, player, direction));
                    }
                }
            }
        }
        setActualWaveNumber(actualWaveNumber.get() + 1);
    }

    public ArrayList<Ennemy> getActualWave() {
        return actualWave;
    }

    public int getWaveSize() {
        return waveSize;
    }

    public void setWaveSize(int waveSize) {
        this.waveSize = waveSize;
    }


}
