package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

abstract public class Ennemie {
    private int pv;
    private int vitesse;
    private int level;

    public Ennemie(int vie, int vitesse, int level){
        this.pv = vie;
        this.vitesse = vitesse;
        this.level = level;
    }
    public abstract void attack();
    public int getPv(){
        return this.pv;
    }

    public int getVitesse(){
        return this.vitesse;
    }

    public int getLevel(){
        return this.level;
    }

    public void setPv(int pv){
        this.pv -= pv;
    }

    public void setLevel(int lv){
        this.level = level;
    }
}
