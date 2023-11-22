package fr.montreuil.iut.Lucas_Adrien_Imman.Forges;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplacement.ModeDeplacement;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.*;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Environment;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Player;
import javafx.scene.layout.Pane;

public class FabricEnnemis   {

    private Environment env;

    public FabricEnnemis() {
        this.env = Environment.getInstance();
    }




    public Ennemy creeEnnemi(TypeEnnemis type , int startDirection , ModeDeplacement modeDeplacement) {

        int[] startPos = env.getGround().getStartTilePos();
        int x = startPos[0]*32 + 16;
        int y = startPos[1]*32 + 16;
        Pane levelPane = env.getLevelPane();
        Player player = env.getPlayer();

        switch (type) {
            case DotSh -> {
                return new DotSH(x, y, levelPane, env, player, startDirection, modeDeplacement);
            }
            case DotExe -> {
                return new DotExe(x, y, levelPane, env, player, startDirection, modeDeplacement);
            }
            case Archive -> {
                return new Archive(x, y, levelPane, env, player, startDirection, modeDeplacement);
            }
            case Kamikaze -> {
                return new Kamikaze(x, y, levelPane, env, player, startDirection, modeDeplacement);
            }
            case Scam -> {
                return new Scam(x, y, levelPane, env, player, startDirection, modeDeplacement);
            }
            case Virus -> {
                return new Virus(x, y, levelPane, env, player, startDirection, modeDeplacement);
            }
            default -> {
                return null;
            }
        }

    }


}

