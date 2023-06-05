package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

public class LevelDataTransit {
    private int mapIndex;
    private String mapName;

    public LevelDataTransit(int mapIndex, String mapName) {
        this.mapIndex = mapIndex;
        this.mapName = mapName;
    }

    public LevelDataTransit(){}

    public int getMapIndex() {
        return mapIndex;
    }

    public String getMapName() {
        return mapName;
    }

   @Override
    public String toString() {
        return getMapIndex() + getMapName();
    }
}
