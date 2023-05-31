package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import javafx.scene.layout.Pane;

public class DotSH extends Ennemy{
    public DotSH(int x, int y, Pane tilePane, Level level) {
        super(x, y, tilePane, level);
    }

    @Override
    public void doDamage() {

    }

    @Override
    public void move() {
        int[] pos = new int[2];
        pos[0] = this.getX()/32;
        pos[1] = this.getY()/32;
        int travelingValue = this.getLevel().getTileValue(pos);
        System.out.println(travelingValue);



        switch (travelingValue){
            case 2 -> {
                if (this.getDirection() == 2){

                    this.setDirection(1);
                } else if (this.getDirection() == 3) {
                    this.setDirection(4);
                }
            }
            case 3 -> {
                if (this.getDirection() == 2){
                    this.setDirection(3);
                } else if (this.getDirection() == 1) {
                    this.setDirection(4);
                }
            }
            case 4 -> {
                if (this.getDirection() == 4){
                    this.setDirection(1);
                } else if (this.getDirection() == 3) {
                    this.setDirection(2);
                }
            }
            case 5 -> {
                if (this.getDirection() == 1){
                    this.setDirection(2);
                } else if (this.getDirection() == 3) {
                    this.setDirection(3);
                }
            }
        }

        if (this.getDirection() == 1){
            this.setY(this.getY()-this.getSpeed());
        }
        else if(this.getDirection() == 2){
            this.setX(this.getX()+getDirection());
        }
        else if(this.getDirection() == 3){
            this.setY(this.getY()+this.getSpeed());
        }
        else if(this.getDirection() == 4){
            this.setX(this.getX()-getDirection());
        }

    }
}
