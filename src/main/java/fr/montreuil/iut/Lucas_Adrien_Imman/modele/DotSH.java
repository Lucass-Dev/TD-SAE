package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

public class DotSH extends Ennemy{
    public DotSH(int x, int y) {
        super(x, y);
    }

    @Override
    public void doDamage() {

    }

    @Override
    public void move() {
        this.setX(this.getX()+1);
    }
}
