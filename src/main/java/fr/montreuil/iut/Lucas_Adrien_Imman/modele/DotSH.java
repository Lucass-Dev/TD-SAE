package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import javafx.scene.layout.Pane;

public class DotSH extends Ennemy{

    public DotSH(Pane tilePane, Level level , Player player) {
        super(tilePane, level,50 , player);
    }

    @Override
    public void doDamage() {
        super.doDamage();
    }
    @Override
    public void move() {
        int[] pos = new int[2];
        pos[0] = this.getX() / 32;
        pos[1] = this.getY() / 32;
        int travelingValue = this.getLevel().getTileValue(pos);

        switch (travelingValue) {
            case 2 -> {
                if (this.getDirection() == 2) {
                    if (isCentered()) {
                        this.setDirection(1);
                    }
                } else if (this.getDirection() == 3) {
                    if (isCentered()) {
                        this.setDirection(4);
                    }
                }
            }
            case 3 -> {
                if (this.getDirection() == 2) {
                    if (isCentered()) {
                        this.setDirection(3);
                    }
                } else if (this.getDirection() == 1) {
                    if (isCentered()) {
                        this.setDirection(4);
                    }
                }
            }
            case 4 -> {
                if (this.getDirection() == 4) {
                    if (isCentered()) {
                        this.setDirection(1);
                    }
                } else if (this.getDirection() == 3) {
                    if (isCentered()) {
                        this.setDirection(2);
                    }
                }
            }
            case 5 -> {
                if (this.getDirection() == 4) {
                    if (isCentered()) {
                        this.setDirection(3);
                    }
                } else if (this.getDirection() == 1) {
                    if (isCentered()) {
                        this.setDirection(2);
                    }
                }
            }
        }

        if (this.getDirection() == 1) {
            this.setY(this.getY() - this.getSpeed());
        } else if (this.getDirection() == 2) {
            this.setX(this.getX() + this.getSpeed());
        } else if (this.getDirection() == 3) {
            this.setY(this.getY() + this.getSpeed());
        } else if (this.getDirection() == 4) {
            this.setX(this.getX() - this.getSpeed());
        }
    }
}
