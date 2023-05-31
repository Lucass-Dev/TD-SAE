package fr.montreuil.iut.Lucas_Adrien_Imman.ACO;



import fr.montreuil.iut.Lucas_Adrien_Imman.ACO.Terrain;
import javafx.collections.ObservableList;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class TerrainVue {

    private Terrain terrain;
    private TilePane paneTuiles;

    public TerrainVue(Terrain terrain, TilePane paneTuiles) {
        super();
        this.terrain = terrain;
        this.paneTuiles = paneTuiles;
        this.afficherTerrain();

    }


    public void afficherTerrain() {
        ObservableList<Integer> codesTuiles = terrain.getCodesTuiles();
        for (int i=0 ; i<codesTuiles.size();i++) {

            if (codesTuiles.get(i)==1) {
                Rectangle tuileDepart = new Rectangle(32,32) ;
                tuileDepart.setFill(Color.BLUE);
                paneTuiles.getChildren().add(tuileDepart);
            }
            else if(codesTuiles.get(i)==10) {
                Rectangle tuileRoute = new Rectangle(32,32) ;
                tuileRoute.setFill(Color.RED);
                paneTuiles.getChildren().add(tuileRoute);
            }
            else if(codesTuiles.get(i)==2) {
                Rectangle tuileArrive = new Rectangle(32,32) ;
                tuileArrive.setFill(Color.GREEN);
                paneTuiles.getChildren().add(tuileArrive);
            }

            else {
                Rectangle tuilePierre = new Rectangle(32,32) ;
                tuilePierre.setFill(Color.YELLOW);
                paneTuiles.getChildren().add(tuilePierre);
            }
        }





    }


}