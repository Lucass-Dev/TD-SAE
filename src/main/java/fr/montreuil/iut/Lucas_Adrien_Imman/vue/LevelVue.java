package fr.montreuil.iut.Lucas_Adrien_Imman.vue;

import fr.montreuil.iut.Lucas_Adrien_Imman.Main;
import fr.montreuil.iut.Lucas_Adrien_Imman.controller.LevelController;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.*;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.IOException;

public class LevelVue {
    private Pane tilePane;
    private Pane levelPane;
    private TowerVue towerVue;
    private Level level;
    private LevelController levelController;

    public LevelVue(){}

    public LevelVue(Level level, Pane tilePane, Pane levelPane, LevelController levelController){
        this.level = level;
        this.tilePane = tilePane;
        this.levelPane = levelPane;
        this.levelController = levelController;
    }

    public void createShopMenu(VBox towerShopVbox){
        int totalTower = 0;
        String[] listTower = new File("src/main/resources/fr/montreuil/iut/Lucas_Adrien_Imman/graphics/tower").list();
        for (String file: listTower) {
            if (file.contains(".png")){
                totalTower++;
            }
        }


        for (int i = 0; i < totalTower; i++) {
            HBox newShopItem = new HBox();
            Image newShopItemImage = new Image(Main.class.getResourceAsStream("graphics/tower/"+i+".png"));
            newShopItem.getChildren().add(new ImageView(newShopItemImage));
            newShopItem.getStyleClass().add("shopItem");
            newShopItem.setId("tower_"+i);
            towerShopVbox.getChildren().add(newShopItem);
        }

    }
    public void createATH(Player p, HBox location) {
        createBar(location, Color.RED, "Life", p.lifeProperty(), p.maxlifeProperty(), "lifebarPane", true, false);
        createBar(location, Color.CADETBLUE, "RAM", p.ramProperty(), p.maxRAMProperty(), "rambarPane", true, false);

        HBox flopHBox = new HBox();
        //flopHBox.getChildren().add(new ImageView(new Image()));
        Label flopLabel = new Label();
        flopLabel.textProperty().bind(p.flopProperty().asString());
        flopHBox.getChildren().add(flopLabel);

        location.getChildren().add(flopHBox);
    }
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
    public void printTowerMenu(Tower tower, Pane location) throws IOException {
        HBox hbox = new HBox();
        VBox towerPresentation = new VBox();
        towerPresentation.getChildren().add(new Label(tower.getName()));

        ImageView imageView = new ImageView(new Image(Main.class.getResource("graphics/enemy/"+tower.getSpriteIndex()+".png").openStream()));
        towerPresentation.getChildren().add(imageView);

        HBox levelHbox = new HBox();
        levelHbox.getChildren().add(new Label("Tower level : "));
        Label levelLabel = new Label();
        levelLabel.textProperty().bind(tower.getLevel().asString());
        levelHbox.getChildren().add(levelLabel);

        towerPresentation.getChildren().add(levelHbox);
        towerPresentation.setAlignment(Pos.CENTER);

        hbox.getChildren().add(towerPresentation);

        Button upgradeButton = new Button("Upgrade Tower : " +tower.getUpgradeCost());
        upgradeButton.setOnAction(e-> tower.upgrade(level.getPlayer()));
        Button moveButton = new Button("Move Tower");
        moveButton.setOnAction(e-> {
            if (this.level.getPlayer().getFlop() >= tower.getMovingPrice()){
                levelController.setMovingTower(true);
                levelController.setMovingTower(tower);
                try {
                    levelController.setCursor(new Image(Main.class.getResource("graphics/enemy/"+tower.getSpriteIndex()+".png").openStream()));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }else{
                System.out.println("Can't move Tower");
            }
        });
        hbox.getChildren().add(upgradeButton);
        hbox.getChildren().add(moveButton);
        location.getChildren().add(hbox);
    }
}
