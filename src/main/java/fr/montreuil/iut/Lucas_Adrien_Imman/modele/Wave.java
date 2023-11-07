package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplacement.DeplacementBFS;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplacement.ModeDeplacement;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Wave {

    private SimpleIntegerProperty actualWaveNumber;
    private ArrayList<Ennemy> actualWave;
    private int waveSize;
    private Environment env;


    public Wave(){
        this.actualWaveNumber = new SimpleIntegerProperty(0);
        this.actualWave = new ArrayList<>();
        this.waveSize = 3;
        this.env = Environment.getInstance();
    }

    private enum EnemyType {
        DOT_SH, ARCHIVE, VIRUS, SCAM, DOT_EXE
    }
    // GETTER

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

    public void createWave(int size, Ground ground, Pane levelPane, Player player, Environment environment){
        int direction = ground.getStartDirection();
        ModeDeplacement md = new DeplacementBFS();
        for (int i = 0; i < size; i++) {
            EnemyType type = selectEnemyType();
            this.actualWave.add(createEnemy(type, direction, md));
        }
        setActualWaveNumber(actualWaveNumber.get() + 1);
    }

    private EnemyType selectEnemyType() {
        int waveNumber = this.actualWaveNumber.get();
        int randomNum = (int) ((Math.random() * (6 - 1)) + 1);
        if (waveNumber > 20 && randomNum == 5) {
            return EnemyType.DOT_EXE;
        } else if (waveNumber > 15 && randomNum == 4) {
            return EnemyType.SCAM;
        } else if (waveNumber > 10 && randomNum == 3) {
            return EnemyType.VIRUS;
        } else if (waveNumber > 5 && randomNum == 2) {
            return EnemyType.ARCHIVE;
        } else {
            return EnemyType.DOT_SH;
        }
    }

    private Ennemy createEnemy(EnemyType type, int direction, ModeDeplacement md){
        int[] startPos = env.getGround().getStartTilePos();
        int x = startPos[0]*32 + 16;
        int y = startPos[1]*32 + 16;
        Pane levelPane = env.getLevelPane();
        Player player = env.getPlayer();

        switch (type) {
            case ARCHIVE:
                return new Archive(x, y, levelPane, env, player, direction, md);
            case VIRUS:
                return new Virus(x, y, levelPane, env, player, direction, md);
            case SCAM:
                return new Scam(x, y, levelPane, env, player, direction, md);
            case DOT_EXE:
                return new DotExe(x, y, levelPane, env, player, direction, md);
            case DOT_SH:
            default:
                return new DotSH(x, y, levelPane, env, player, direction, md);
        }
    }
}

