package fr.montreuil.iut.Lucas_Adrien_Imman.controller;

import fr.montreuil.iut.Lucas_Adrien_Imman.Main;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.LevelDataTransit;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Player;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Scanner;

public class MenuController implements Initializable {

    //BUTTONS
    @FXML
    Button playButton;

    //IMAGE VIEW
    @FXML
    ImageView previewMapImage;

    //LABELS
    @FXML
    Label mapNameLabel;
    @FXML
    Label usernameLabel;
    @FXML
    Label difficultyLabel;

    //VBOX
    @FXML
    VBox usernamesVbox;
    @FXML
    VBox timesVbox;
    @FXML
    VBox wavesVbox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mapNameLabel.setText("Map n° 1");
        usernameLabel.setText("Lucasss");

        try {
            setScores("easy", 0);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadLevel() throws IOException {

        String[] level = mapNameLabel.getText().split(" ");
        int mapIndex = Integer.parseInt(level[level.length-1])-1;

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("map.fxml"));


        Parent root = fxmlLoader.load();
        Scene nS= new Scene(root, 940, 900);

        Main.stg.setScene(nS);
        //Main.stg.setFullScreen(true);

        LevelController levelController = fxmlLoader.getController();
        Player p = new Player(new SimpleIntegerProperty(10), new SimpleIntegerProperty(10));
        sendData(levelController, mapIndex, "mapName", p);
    }

    public void changeDifficulty(Event e) throws FileNotFoundException {
        Button sourceButton = (Button) e.getSource();
        String sourceID = sourceButton.getId();
        System.out.println(sourceID);

        if (sourceID.equals("nextDifficultyButton")){
            switch (difficultyLabel.getText()){
                default -> System.out.println("Error !");
                case "Easy" -> difficultyLabel.setText("Medium");
                case "Medium" -> difficultyLabel.setText("Hard");
                case "Hard" -> difficultyLabel.setText("Easy");
            }
        }else if (sourceID.equals("previousDifficultyButton")){
            switch (difficultyLabel.getText()){
                default -> System.out.println("Error !");
                case "Easy" -> difficultyLabel.setText("Hard");
                case "Medium" -> difficultyLabel.setText("Easy");
                case "Hard" -> difficultyLabel.setText("Medium");
            }
        }
        String[] mapNameSplitted = mapNameLabel.getText().split(" ");
        System.out.println(Arrays.toString(mapNameSplitted));
        setScores(difficultyLabel.getText(), Integer.parseInt(mapNameSplitted[mapNameSplitted.length-1])-1);
    }

    public void changeMapPreview(Event e) throws FileNotFoundException {

        Button sourceButton = (Button) e.getSource();
        String sourceID = sourceButton.getId();
        String[] fullPath = previewMapImage.getImage().getUrl().split("/");
        String[] image = fullPath[fullPath.length-1].split("\\.");
        String newFullPath = "", mapName;
        int totalMap = Objects.requireNonNull(new File("src/main/resources/fr/montreuil/iut/Lucas_Adrien_Imman/graphics/map_preview").list()).length, newValue;
        int actualMap = Integer.parseInt(image[0]);
        String[] listMap = new File("src/main/resources/fr/montreuil/iut/Lucas_Adrien_Imman/graphics/map_preview").list();

        //Ce code permet de baisser le compteur de fichier des preview de map si un fichier n'est pas au format '*.jpeg'
        //Je l'ai mis à cause du .DS_STORE
        for (String file: listMap) {
            if (!file.contains(".jpeg")){
                totalMap--;
            }
        }

        if (actualMap < totalMap && actualMap >= 0){

            if (sourceID.equals("nextMapButton")){
                if (actualMap != totalMap -1){
                    newValue = Integer.parseInt(image[0]) + 1;
                    image[0]= String.valueOf(newValue) ;
                }
            }else{
                if (actualMap != 0){
                    newValue = Integer.parseInt(image[0]) - 1;
                    image[0]= String.valueOf(newValue) ;
                }
            }

            for (int i = 0; i < fullPath.length-1; i++) {
                newFullPath += fullPath[i] + '/';
            }
            newFullPath += image[0] +'.'+image[1];
            actualMap = Integer.parseInt(image[0]);

            previewMapImage.setImage(new Image(newFullPath));
            mapName = "Map n° "+ (actualMap+1);
            mapNameLabel.setText(mapName);
        }

        setScores(difficultyLabel.getText().toLowerCase(), actualMap);

    }

    public void setScores(String difficulty, int mapIndex) throws FileNotFoundException {
        usernamesVbox.getChildren().clear();
        timesVbox.getChildren().clear();
        wavesVbox.getChildren().clear();
        String[] line;
        File f = new File("src/main/resources/fr/montreuil/iut/Lucas_Adrien_Imman/scores/map"+mapIndex+"/"+difficulty);
        System.out.println(f.getPath());
        Scanner sc = new Scanner(f);
        while (sc.hasNextLine()){
            line = sc.nextLine().split("/");
            usernamesVbox.getChildren().add(new Label(line[0]));
            timesVbox.getChildren().add(new Label(line[1]));
            wavesVbox.getChildren().add(new Label(line[2]));
        }
    }

    public void sendData(LevelController levelController, int mapIndex, String mapName, Player player){
        LevelDataTransit LDT = new LevelDataTransit(mapIndex, mapName, player);
        levelController.setLDT(LDT);
    }
}
