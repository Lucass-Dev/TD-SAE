package fr.montreuil.iut.Lucas_Adrien_Imman.vue;

import fr.montreuil.iut.Lucas_Adrien_Imman.Main;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Tours.Tower;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.IOException;

public class TowerVue {

    private Pane levelPane;
    private ImageView imageView;

    public TowerVue(Pane levelPane){
        this.levelPane = levelPane;
    }

    public void createTowerSprite(Tower newTower) throws IOException {
        this.imageView = new ImageView(new Image(Main.class.getResource("graphics/tower/"+newTower.getSpriteIndex()+".png").openStream()));
        this.imageView.setPickOnBounds(true);
        this.imageView.setId(String.valueOf(newTower.getId()));
        levelPane.getChildren().add(imageView);
        this.imageView.translateXProperty().bind(newTower.xProperty());
        this.imageView.translateYProperty().bind(newTower.yProperty());
        createRange(newTower);
    }
    public void createRange(Tower tower){
        Circle c = new Circle(tower.getRange());
        c.translateXProperty().bind(Bindings.createIntegerBinding(()->tower.xProperty().get() +16, tower.xProperty()));
        c.translateYProperty().bind(Bindings.createIntegerBinding(()->tower.yProperty().get() +16, tower.yProperty()));

        c.visibleProperty().bind(tower.showingRangeProperty());
        c.radiusProperty().bind(tower.rangeProperty());
        c.setOpacity(0.4);
        c.setFill(Color.GREY);
        levelPane.getChildren().add(c);
    }

    public VBox stats(Tower t){
        VBox stats = new VBox();
        //HBox rangeHBox = new HBox();
        Label rangeText = new Label("Range : ");
        Label rangeValue = new Label();
        rangeValue.textProperty().bind(t.rangeProperty().asString());
        stats.getChildren().add(new HBox(rangeText, rangeValue));

        Label reloadSpeedText = new Label("Reload Speed : ");
        Label reloadSpeedValue = new Label();
        reloadSpeedValue.textProperty().bind(t.reloadSpeedProperty().asString());
        stats.getChildren().add(new HBox(reloadSpeedText, reloadSpeedValue));

        Label damageText = new Label("Damage : ");
        Label damageValue = new Label();
        damageValue.textProperty().bind(t.damageProperty().asString());
        stats.getChildren().add(new HBox(damageText, damageValue));
        stats.setAlignment(Pos.CENTER);
        stats.setPadding(new Insets(15));
        return stats;
    }
}
