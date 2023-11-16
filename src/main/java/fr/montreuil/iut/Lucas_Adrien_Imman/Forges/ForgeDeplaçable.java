package fr.montreuil.iut.Lucas_Adrien_Imman.Forges;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplacement.ModeDeplacement;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplaçable;

public abstract class ForgeDeplaçable{
    private int x , y ;
    private ModeDeplacement md ;

    public  ForgeDeplaçable(int x, int y , ModeDeplacement md) {
        this.x = x;
        this.y = y;
        this.md = md;

    }

    public abstract Deplaçable  creeDeplaçable(TypeDeplaçable type);


    public Deplaçable fabriquerDeplaçable (TypeEnnemis type) {
        Deplaçable deplaçable =
        return deplaçable;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ModeDeplacement getMd() {
        return md;
    }
}
