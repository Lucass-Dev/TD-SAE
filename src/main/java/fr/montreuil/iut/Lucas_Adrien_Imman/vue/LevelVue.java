package fr.montreuil.iut.Lucas_Adrien_Imman.vue;

import fr.montreuil.iut.Lucas_Adrien_Imman.Main;
import fr.montreuil.iut.Lucas_Adrien_Imman.controller.LevelController;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Level;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Player;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Tours.*;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.IOException;

public class LevelVue {
    private Pane tilePane;
    private Pane levelPane;
    private TowerVue towerVue;
    private Level level;
    private LevelController levelController;
    private Tower[] reference;
    private TowerVue tv;
    private BarVue bv;

    public LevelVue(Level level, Pane tilePane, Pane levelPane, LevelController levelController){
        this.level = level;
        this.tilePane = tilePane;
        this.levelPane = levelPane;
        this.levelController = levelController;
        this.reference = new Tower[6];
        this.reference[0] = new TaskKiller(0, 0);
        this.reference[1] = new CCleaner(0, 0);
        this.reference[2] = new Demineur(0, 0);
        this.reference[3] = new InternetExplorer(0, 0);
        this.reference[4] = new NordVPN(0, 0);
        this.reference[5] = new PDFConverter(0, 0);
        this.tv = new TowerVue(levelPane);
        this.bv = new BarVue();
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
            newShopItem.setPadding(new Insets(8));
            Image newShopItemImage = new Image(Main.class.getResourceAsStream("graphics/tower/"+i+".png"));
            ImageView imageView = new ImageView(newShopItemImage);
            newShopItem.getChildren().add(imageView);
            newShopItem.getStyleClass().add("shopItem");
            newShopItem.setId("tower_"+i);
            Label price = new Label("Price : " + reference[i].getFlopPrice());
            price.setPadding(new Insets(15));
            newShopItem.getChildren().add(price);
            VBox stats = this.tv.stats(reference[i]);
            newShopItem.getChildren().add(stats);
            towerShopVbox.getChildren().add(newShopItem);
        }

    }
    public void createATH(Player p, HBox location) throws IOException {
        this.bv.createBar(location, Color.RED, "Life", p.lifeProperty(), p.maxlifeProperty(), "lifebarPane", true, false);
        this.bv.createBar(location, Color.CADETBLUE, "RAM", p.ramProperty(), p.maxRAMProperty(), "rambarPane", true, false);

        HBox flopHBox = new HBox();
        flopHBox.getChildren().add(new ImageView(new Image(Main.class.getResource("graphics/logo/Flops.png").openStream())));
        Label flopLabel = new Label();
        flopLabel.textProperty().bind(Bindings.createStringBinding(() -> "\tFlops : " + p.getFlop(), p.flopProperty()));
        flopHBox.getChildren().add(flopLabel);
        flopLabel.setAlignment(Pos.CENTER);

        location.getChildren().add(flopHBox);
    }
    public void createTowerMenu(Tower tower, Pane location) throws IOException {
        HBox hbox = new HBox();
        VBox towerPresentation = new VBox();
        towerPresentation.getChildren().add(new Label(tower.getName()));

        ImageView imageView = new ImageView(new Image(Main.class.getResource("graphics/tower/"+tower.getSpriteIndex()+".png").openStream()));
        towerPresentation.getChildren().add(imageView);

        HBox levelHbox = new HBox();
        levelHbox.getChildren().add(new Label("Tower level : "));
        Label levelLabel = new Label();
        levelLabel.textProperty().bind(tower.getLevel().asString());
        levelHbox.getChildren().add(levelLabel);

        towerPresentation.getChildren().add(levelHbox);
        towerPresentation.setAlignment(Pos.CENTER);

        hbox.getChildren().add(towerPresentation);

        Button upgradeButton = new Button();
        upgradeButton.textProperty().bind(Bindings.createStringBinding(() -> "Upgrade Tower : " +tower.upgradeCostProperty().get(), tower.upgradeCostProperty()));
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
        Button sell = new Button("Sell Tower");
        sell.setOnAction(e -> {
            this.level.sellTower(tower);
            removeTowerMenu(location);
        });
        VBox stats = tv.stats(tower);
        Label sellingPrice = new Label();
        sellingPrice.textProperty().bind(Bindings.createStringBinding(() -> "Selling price : " + tower.getSellingPrice(), tower.sellingPriceProperty()));
        stats.getChildren().add(sellingPrice);


        hbox.getChildren().add(upgradeButton);
        hbox.getChildren().add(moveButton);
        hbox.getChildren().add(sell);
        hbox.getChildren().add(stats);
        location.getChildren().add(hbox);
    }
    public void removeTowerMenu(Pane location){
        for (int i = location.getChildren().size()-1; i >= 0; i--) {
            location.getChildren().remove(i);
        }
    }
}