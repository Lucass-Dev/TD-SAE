package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import fr.montreuil.iut.Lucas_Adrien_Imman.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ground {
    private int[] startTilePos;
    private int[] endTilePos;
    private int startDirection;
    private ArrayList<ArrayList<Integer>> tileMap;
    private ArrayList<ArrayList<Integer>> travelingMap;

    public Ground(){
        this.tileMap = new ArrayList<>();
        this.travelingMap = new ArrayList<>();
        this.startTilePos = new int[2];
        this.endTilePos = new int[2];
    }

    public void initMap(String src, Pane pane) throws FileNotFoundException {
        createTileMap(src, pane);
        tileMapToTraveling();
    }
    public void tileMapToTraveling() {
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
        this.travelingMap = traveling;
    }
    public void createTileMap(String path, Pane pane) throws FileNotFoundException {
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
        this.tileMap = map;
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
    public ArrayList<ArrayList<Integer>> getTileMap() {
        return tileMap;
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
    public boolean validTile(int[] pos){
        return getTile(pos) == 0;
    }

    public int[] getStartTilePos() {
        return startTilePos;
    }
}
