package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../controller/StartScreen.fxml"));

        // Declare and show the first screen, the title page
        primaryStage.setScene(new Scene(root)); // set the start screen
        primaryStage.setTitle( "Pacman" ); // set the title of the game
        primaryStage.show(); // show the game
    }

    public static void main(String[] args) {
        launch(args);
    }

}