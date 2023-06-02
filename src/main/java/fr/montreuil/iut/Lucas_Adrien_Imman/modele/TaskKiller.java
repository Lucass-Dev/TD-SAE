package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import fr.montreuil.iut.Lucas_Adrien_Imman.vue.TowerVue;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;


public class TaskKiller extends Tower {
Projectile projectile ;
    public TaskKiller(){
        super();
    }

    public TaskKiller(int x, int y){
        super(x,y);
       this.projectile = new Projectile(xProperty(),yProperty());
    }

    public TaskKiller(int range, int flopPrice, int ramPrice, String name, int level, int upgradeCost, int x, int y , Projectile projectile , Pane levelPane){
        super(range, flopPrice, ramPrice, name, level, upgradeCost, new SimpleIntegerProperty(x), new SimpleIntegerProperty(y) , levelPane);

    }

    @Override
    public  Ennemy attack(ObservableList<Ennemy> en , Pane levelPane ){

        for (Ennemy m : en) {
            if ((this.getY()-150<=m.getY() && m.getY()<= this.getY()+150) && (this.getX()-150<=m.getX() && m.getX() <= this.getX()+150)){



                return m;
            }
        }
        return null;
    }

    public void projectile(){

    }

    @Override
    public void detect(){
        System.out.println("test");
    }

    @Override
    public String toString() {
        return this.getName() + this.getLevel() +this.getRange();
    }
}
