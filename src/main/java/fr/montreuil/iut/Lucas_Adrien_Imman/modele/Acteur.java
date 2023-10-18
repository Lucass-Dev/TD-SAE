package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

public abstract class Acteur extends Entites {
    protected int x; // Position x
    protected int y; // Position y

    public Acteur(int x, int y, int initialHealth, int maxHealth) {
        super(initialHealth, maxHealth);
        this.x = x;
        this.y = y;
    }

    // Getters et Setters pour x et y
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    // Autres méthodes spécifiques à Acteurs
}

