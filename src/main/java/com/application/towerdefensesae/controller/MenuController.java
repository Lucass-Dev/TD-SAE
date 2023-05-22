package com.application.towerdefensesae.controller;

import com.application.towerdefensesae.Main;
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
import javafx.stage.Stage;

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
        System.out.println("test");
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        Main.stg.close();
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

    public void changeMap(Event e) throws FileNotFoundException {

        Button sourceButton = (Button) e.getSource();
        String sourceID = sourceButton.getId();
        String[] fullPath = previewMapImage.getImage().getUrl().split("/");
        String[] image = fullPath[fullPath.length-1].split("\\.");
        String newFullPath = "", mapName;
        int totalMap = Objects.requireNonNull(new File("src/main/resources/com/application/towerdefensesae/graphics/map_preview").list()).length, newValue;
        int actualMap = Integer.parseInt(image[0]);
        String[] listMap = new File("src/main/resources/com/application/towerdefensesae/graphics/map_preview").list();

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
        File f = new File("src/main/resources/com/application/towerdefensesae/scores/map"+mapIndex+"/"+difficulty);
        System.out.println(f.getPath());
        Scanner sc = new Scanner(f);
        while (sc.hasNextLine()){
            line = sc.nextLine().split("/");
            usernamesVbox.getChildren().add(new Label(line[0]));
            timesVbox.getChildren().add(new Label(line[1]));
            wavesVbox.getChildren().add(new Label(line[2]));
        }
    }
}
