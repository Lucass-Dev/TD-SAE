package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Score {

    private File file;
    private int diificulty;
    private int mapindex;
    private int challengerWave;
    private String challengerTime;
    private String username;

    public Score(int diificulty, String challengerTime, String username, int mapindex, int challengerWave) {
        this.diificulty = diificulty;
        this.challengerTime = challengerTime;
        this.username = username;
        this.mapindex = mapindex;
        this.challengerWave = challengerWave;
    }

    public void newBestScores() throws IOException {
        this.file = new File("src/main/resources/fr/montreuil/iut/Lucas_Adrien_Imman/scores/map"+this.mapindex+"/"+diificulty);
        Scanner sc = new Scanner(this.file);
        ArrayList<String> totalScores = new ArrayList<>();
        String newScore = "";

        while (sc.hasNext()){
            totalScores.add(sc.nextLine());
        }

        for (int i = 0; i < totalScores.size(); i++) {
            String[] splitedScoreReference = totalScores.get(i).split("/");
            String[] referenceTimeSplited = splitedScoreReference[1].split("h|m|s");
            String[] challengerTimeSplited = this.challengerTime.split("h|m|s");
            int referenceWaveNumber = Integer.parseInt(splitedScoreReference[2]);

            if (isBetterWave(referenceWaveNumber, this.challengerWave)){
                if (isBetterTime(referenceTimeSplited, challengerTimeSplited)){

                    for (int j = totalScores.size()-1; j > i; j--) {
                        totalScores.set(j, totalScores.get(j-1));
                    }
                    newScore = this.username+"/"+this.challengerTime+"/"+this.challengerWave;
                    totalScores.set(i, newScore);
                    break;
                }
            }
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.file));
        for (int i = 0; i < totalScores.size(); i++){
            if (i < totalScores.size()-1){
                writer.write(totalScores.get(i)+"\n");
            }else{
                writer.write(totalScores.get(i));
            }


        }
        writer.close();
    }

    public boolean isBetterWave(int reference, int challenger){
        return challenger >= reference;
    }

    public boolean isBetterTime(String[] reference, String[] challenger){
        if(Integer.parseInt(challenger[0])==Integer.parseInt(reference[0]) && Integer.parseInt(challenger[1]) == Integer.parseInt(reference[1])){
            return Integer.parseInt(challenger[2]) > Integer.parseInt(reference[2]);
        }else if (Integer.parseInt(challenger[0])==Integer.parseInt(reference[0])){
            return Integer.parseInt(challenger[1]) > Integer.parseInt(reference[1]);
        }else{
            return Integer.parseInt(challenger[0]) > Integer.parseInt(reference[0]);
        }
    }
}
