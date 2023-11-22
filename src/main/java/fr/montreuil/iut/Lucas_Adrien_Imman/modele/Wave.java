package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import fr.montreuil.iut.Lucas_Adrien_Imman.Forges.FabricEnnemis;
import fr.montreuil.iut.Lucas_Adrien_Imman.Forges.TypeEnnemis;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplacement.DeplacementBFS;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplacement.ModeDeplacement;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.*;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public class Wave {

    private SimpleIntegerProperty actualWaveNumber;
    private ArrayList<Ennemy> actualWave;
    private int waveSize;

    private FabricEnnemis fabricEnnemis ;


    public Wave(){
        this.actualWaveNumber = new SimpleIntegerProperty(0);
        this.actualWave = new ArrayList<>();
        this.waveSize = 3;
        this.fabricEnnemis = new FabricEnnemis();
    }



    public int getActualWaveNumber() {
        return actualWaveNumber.get();
    }

    public int getWaveSize() {
        return waveSize;
    }

    public SimpleIntegerProperty getActualWaveNumberProperty(){return this.actualWaveNumber;}

    public ArrayList<Ennemy> getActualWave() {
        return actualWave;
    }


    // SETTER

    public void setActualWaveNumber(int actualWaveNumber) {
        this.actualWaveNumber.set(actualWaveNumber);
    }

    public void setWaveSize(int waveSize) {
        this.waveSize = waveSize;
    }


    // OTHER METHODS

    public void createWave(int size, Ground ground){

        int direction = ground.getStartDirection();
        ModeDeplacement md = new DeplacementBFS();
        for (int i = 0; i < size; i++) {
            TypeEnnemis type = selectEnemyType();
            this.actualWave.add(fabricEnnemis.creeEnnemi(type, direction, md));
        }
        setActualWaveNumber(actualWaveNumber.get() + 1);
    }

    private TypeEnnemis selectEnemyType() {
        int waveNumber = this.actualWaveNumber.get();
        int randomNum = (int) ((Math.random() * (6 - 1)) + 1);
        if (waveNumber > 20 && randomNum == 5) {
            return TypeEnnemis.DotExe;
        } else if (waveNumber > 15 && randomNum == 4) {
            return TypeEnnemis.Scam;
        } else if (waveNumber > 10 && randomNum == 3) {
            return TypeEnnemis.Virus;
        } else if (waveNumber > 5 && randomNum == 2) {
            return TypeEnnemis.Archive;
        } else {
            return TypeEnnemis.DotSh;
        }
    }

}

