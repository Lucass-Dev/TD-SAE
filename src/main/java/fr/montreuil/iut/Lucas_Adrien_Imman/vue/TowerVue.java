package fr.montreuil.iut.Lucas_Adrien_Imman.vue;

import fr.montreuil.iut.Lucas_Adrien_Imman.Main;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Tours.Tour;
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

    private final Pane levelPane;
    private ImageView imageView;

    public TowerVue(Pane levelPane){
        this.levelPane = levelPane;
    }

    public void createTowerSprite(Tour newTour) throws IOException { // cree le sprite correspondant avec le index
        this.imageView = new ImageView(new Image(Main.class.getResource("graphics/tower/"+ newTour.getSpriteIndex()+".png").openStream()));
        this.imageView.setPickOnBounds(true);
        this.imageView.setId(String.valueOf(newTour.getId()));
        levelPane.getChildren().add(imageView);
        this.imageView.translateXProperty().bind(newTour.getXProperty());
        this.imageView.translateYProperty().bind(newTour.getYProperty());
        createRange(newTour);
    }
    public void createRange(Tour tour){
        Circle c = new Circle(tour.getRange());
        c.translateXProperty().bind(Bindings.createIntegerBinding(()-> tour.getXProperty().get() +16, tour.getXProperty()));
        c.translateYProperty().bind(Bindings.createIntegerBinding(()-> tour.getYProperty().get() +16, tour.getYProperty()));

        c.visibleProperty().bind(tour.showingRangeProperty());
        c.radiusProperty().bind(tour.rangeProperty());
        c.setOpacity(0.4);
        c.setFill(Color.GREY);
        c.setId("c"+ tour.getId());
        System.out.println(c.getId());
        levelPane.getChildren().add(c);
    }

    public VBox stats(Tour t){
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
