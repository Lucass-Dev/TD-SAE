package fr.montreuil.iut.Lucas_Adrien_Imman.vue;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BarVue {

    public BarVue(){}

    public void createBar(HBox location, Color color, String attributeName, IntegerProperty actualProperty, IntegerProperty maxProperty, String id, boolean showContext, boolean isEnnemyBar){
        Pane container = new Pane();
        Rectangle bar = new Rectangle();
        SimpleIntegerProperty barValue = new SimpleIntegerProperty(actualProperty.getValue());
        int width, height;

        if (isEnnemyBar){
            width = 50;
            height = 5;

        }else{
            width = 400;
            height = 35;
        }
        container.setPrefWidth(width);
        container.setMaxHeight(height);
        bar.setFill(color);
        bar.setWidth(width);
        bar.setHeight(height);
        bar.setId(id);
        bar.getStyleClass().add("barPane");
        container.getStyleClass().add("barContainerPane");
        container.getChildren().add(bar);

        bar.widthProperty().bind(Bindings.createIntegerBinding(() -> (width* actualProperty.get()/maxProperty.get()), actualProperty));

        if (showContext){
            Label lifeLabel = new Label();
            HBox lifeLabelHbox = new HBox();
            lifeLabel.textProperty().bind(actualProperty.asString());
            lifeLabelHbox.getChildren().add(new Label(attributeName+" : "));
            lifeLabelHbox.getChildren().add(lifeLabel);
            lifeLabelHbox.getChildren().add(new Label(" / "+maxProperty.get()));
            container.getChildren().add(lifeLabelHbox);
        }

        location.getChildren().add(container);
    }
}
