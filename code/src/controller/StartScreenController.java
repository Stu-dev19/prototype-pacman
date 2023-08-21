package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Manager;
import model.Tiles;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class StartScreenController implements Initializable {

    @FXML
    private Button button;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void startButtonClicked(javafx.event.ActionEvent actionEvent) throws IOException {
        // Create UI components
        TextField playerNameInput = new TextField();
        Label nameLabel = new Label("Player Name: ");

        // Create button to enter details
        Button submitButton = new Button("Submit");

        //Add UI components to the new pane
        VBox playerDisplay = new VBox(10);
        playerDisplay.getChildren().addAll(nameLabel, playerNameInput, submitButton);
        playerDisplay.setStyle("-fx-padding: 20px;");

        //Create a new scene for the player input
        Scene scene = new Scene(playerDisplay, 200, 150);

        //Set the stage and display the input
        Stage stage1 = new Stage();
        stage1.setTitle("Input Player Name");
        stage1.setScene(scene);
        stage1.show();

        // Once press of the button hold the player name
        // Display the game screen and add the name on there
        submitButton.setOnAction(e -> {
            // Group to add maze, player name etc.
            Group root = new Group();
            Manager manager = Manager.GetInstance(root);

            // Set new Stage
            Stage stage2 = new Stage(); // current stage
            stage2.setTitle("Pacman Game");

            //Setup new canvas
            Scene sc = new Scene(root);
            stage2.setScene(sc);
            Canvas canvas = new Canvas( 1200, 600 );  // 1225 and 600
            root.getChildren().add(canvas);

            // player name display on canvas
            String playerName = playerNameInput.getText();
            manager.DisplayPlayerName(playerName);

            // Draw the maze
            manager.DrawMaze();
            sc.addEventHandler(KeyEvent.KEY_PRESSED, manager::MovePacman);
            sc.addEventHandler(KeyEvent.KEY_RELEASED, manager::StopPacman);
            sc.addEventHandler(KeyEvent.KEY_PRESSED, manager::InvincibleMode);
            sc.addEventHandler(KeyEvent.KEY_PRESSED, manager::restartGame);
            stage2.show();

            // Exit the previous windows
            stage1.close();
        });
    }

    private void showGameplay(){


    }
}
