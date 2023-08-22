/**
 * model.java <br>
 * Date created: 21 Aug 2023 <br> <br>
 *
 * The model package contains classes responsible for managing game data and logic.
 *
 * @author Muhammad Sohail
 * @version 1.0
 */
package model;

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

    /**
     * HighScore class manages the high score list and display.
     * Reading and writing high score data to a file and displays the list of high scores.
     */

    private String m_PlayerName;
    private int m_HighScore;
    public ListView<String> m_HighScoreList = new ListView<>();
    private final String m_Filepath = "/Users/Garundi/Desktop/prototype-pacman/codeV2/code-clone/src/view/resources/highscores_player.txt";
    private final ObservableList<String> m_DataList = FXCollections.observableArrayList();

    /**
     * HighScore instance with the specified player name and high score.
     *
     * @param m_PlayerName Name of player.
     * @param highScore  High score achieved by player.
     */
    public HighScore(String m_PlayerName, int highScore){
        // Set the player name and scores
        this.setM_PlayerName(m_PlayerName);
        this.setHighScore(highScore);

        // Add the names to the high score list in the .txt file
        this.UpdateHighScoreList();

        // Create the stage
        Stage stage = new Stage();

        // Create UI Components
        Label highScoreLabel = new Label("High Scores");
        this.GetHighScoreList();
        m_HighScoreList.setPrefHeight(200);

        // Layout
        VBox layout = new VBox(10); //vertical layout
        layout.getChildren().addAll(m_HighScoreList);

        // Set the scene
        Scene highScoreScene = new Scene(layout, 300, 300);

        // Set the stage
        stage.setTitle("High Score Screen");
        stage.setScene(highScoreScene);
        stage.show();
    }

    /**
     * Retrieves player name.
     *
     * @return player name.
     */
    // Getters and Setters
    public String getM_PlayerName() {
        return m_PlayerName;
    }

    /**
     * Sets player name.
     *
     * @param m_PlayerName player name to set.
     */
    public void setM_PlayerName(String m_PlayerName) {
        this.m_PlayerName = m_PlayerName;
    }

    /**
     * Retrieves high score.
     *
     * @return high score.
     */
    public int getHighScore() {
        return m_HighScore;
    }

    /**
     * Sets high score.
     *
     * @param highScore high score to set.
     */
    public void setHighScore(int highScore) {
        this.m_HighScore = highScore;
    }

    /**
     * Retrieves the high score list and populates the list view.
     */
    public void GetHighScoreList(){
        // Looking into an exel file retrieve the data
        try{
            File highScoreFile = new File(m_Filepath);
            Scanner reader = new Scanner(highScoreFile);
            while(reader.hasNextLine()){
                String data = reader.nextLine();
                // append the data to the list view
                m_DataList.add(data);
                m_HighScoreList.setItems(m_DataList);
            }
            reader.close();
        } catch (FileNotFoundException e){
            System.out.println("An error occurred reading the file");
            e.printStackTrace();
        }
    }

    /**
     * Updates the player's name and high score to the high score file.
     */
    public void UpdateHighScoreList(){
        // Looking into an exel file retrieve the data
        try
        {
            FileWriter fw = new FileWriter(m_Filepath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println(m_PlayerName + "  " + m_HighScore);
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
