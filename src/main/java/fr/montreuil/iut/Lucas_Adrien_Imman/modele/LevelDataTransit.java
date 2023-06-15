package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

public class LevelDataTransit {
    private int mapIndex;
    private int difficulty;
    private String mapName;
    private Player player;

    public LevelDataTransit(int mapIndex, String mapName, Player player, int difficulty) {
        this.mapIndex = mapIndex;
        this.mapName = mapName;
        this.player = player;
        this.difficulty = difficulty;
    }

    public LevelDataTransit(){}

    public Player getPlayer() {
        return player;
    }

    public int getMapIndex() {
        return mapIndex;
    }

    public String getMapName() {
        return mapName;
    }

    public int getDifficulty() {
        return difficulty;
    }

    @Override
    public String toString() {
        return getMapIndex() + getMapName();
    }
}
