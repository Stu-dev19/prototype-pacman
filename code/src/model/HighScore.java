package model;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.util.Scanner;


// Class handles the high score list to be used
public class HighScore {

    private String playerName;
    private int highScore;
    public ListView<String> highScoreList = new ListView<>();
    private final String filepath = "/Users/Garundi/Desktop/prototype-pacman/code/src/view/resources/highcores_player.txt";
    private final ObservableList<String> dataList = FXCollections.observableArrayList();

    public HighScore(String playerName, int highScore){
        // Set the player name and scores
        this.setPlayerName(playerName);
        this.setHighScore(highScore);

        // Add the names to the high score list in the .txt file
        this.UpdateHighScoreList();

        // Create the stage
        Stage stage = new Stage();

        // Create UI Components
        Label highScoreLabel = new Label("High Scores");
        this.GetHighScoreList();
        highScoreList.setPrefHeight(200);

        // Layout
        VBox layout = new VBox(10); //vertical layout
        layout.getChildren().addAll(highScoreList);

        // Set the scene
        Scene highScoreScene = new Scene(layout, 300, 300);

        // Set the stage
        stage.setTitle("High Score Screen");
        stage.setScene(highScoreScene);
        stage.show();
    }

    // Getters and Setters
    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public void GetHighScoreList(){
        // Looking into an exel file retrieve the data
        try{
            File highScoreFile = new File(filepath);
            Scanner reader = new Scanner(highScoreFile);
            while(reader.hasNextLine()){
                String data = reader.nextLine();
                // append the data to the list view
                dataList.add(data);
                highScoreList.setItems(dataList);
            }
            reader.close();
        } catch (FileNotFoundException e){
            System.out.println("An error occurred reading the file");
            e.printStackTrace();
        }
    }

    public void UpdateHighScoreList(){
        // Looking into an exel file retrieve the data
        try
        {
            FileWriter fw = new FileWriter(filepath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println(playerName + "  " + highScore);
            System.out.println("Data Successfully appended into file");
            pw.flush();
            fw.close();
            bw.close();
            pw.close();
        } catch (IOException e) {
                System.out.println("An error occurred writing to this file");
                e.printStackTrace();
            }
    }
}
