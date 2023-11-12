package fr.montreuil.iut.Lucas_Adrien_Imman.Forges;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplacement.ModeDeplacement;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplaçable;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.*;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Environment;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Player;
import javafx.scene.layout.Pane;

public class FabricEnnemis extends ForgeDeplaçable {

    private Pane levelPane;
    private Environment env;
    private Player player;
    private int startDirection;

    public FabricEnnemis(int x, int y, ModeDeplacement md, Pane levelPane, Environment env, Player player, int startDirection) {
        super(x, y, md);
        this.levelPane = levelPane;
        this.env = env;
        this.player = player;
        this.startDirection = startDirection;
    }

    @Override
    public Deplaçable creeDeplaçable(TypeDeplaçable type) {
        switch (type) {
            case DotSh -> {
                return new DotSH(getX(), getY(), levelPane, env, player, startDirection, getMd());
            }
            case DotExe -> {
                return new DotExe(getX(), getY(), levelPane, env, player, startDirection, getMd());
            }
            case Archive -> {
                return new Archive(getX(), getY(), levelPane, env, player, startDirection, getMd());
            }
            case Kamikaze -> {
                return new Kamikaze(getX(), getY(), levelPane, env, player, startDirection, getMd());
            }
            case Scam -> {
                return new Scam(getX(), getY(), levelPane, env, player, startDirection, getMd());
            }
            case Virus -> {
                return new Virus(getX(), getY(), levelPane, env, player, startDirection, getMd());
            }
            default -> {
                return null;
            }
        }

    }


}

