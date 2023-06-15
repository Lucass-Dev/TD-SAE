package fr.montreuil.iut.Lucas_Adrien_Imman.vue;

import fr.montreuil.iut.Lucas_Adrien_Imman.Main;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Popup;

public class PopupVue {

    public PopupVue(){}

    public void createTemporaryPopup(String message){
        Popup popup = new Popup();
        Pane container = new Pane();
        Label label = new Label(message);
        Button quit = new Button("Understood !");

        container.getChildren().add(new VBox(label, quit));

        popup.getContent().add(container);

        quit.setOnAction(e -> {
            popup.hide();
        });

        container.setBackground(Background.fill(Color.RED));
        container.setMinHeight(50);
        container.setMinWidth(50);

        popup.show(Main.stg);

    }

    public void scamPopup(){
        Popup popup = new Popup();
        Button closingButton = new Button("Close");
        popup.setWidth(Main.stg.getWidth());
        popup.setHeight(Main.stg.getHeight());
        Label lb = new Label();
        lb.setMinWidth(popup.getWidth());
        lb.setMinHeight(popup.getHeight());
        lb.setBackground(Background.fill(Color.WHEAT));
        popup.getContent().add(lb);
        popup.getContent().add(closingButton);
        popup.show(Main.stg);
        closingButton.setOnAction(e -> {
            popup.hide();
        });
    }
}
