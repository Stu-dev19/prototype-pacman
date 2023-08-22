/**
 * Main.java <br>
 * Date created: 21 Aug 2023 <br> <br>
 *
 * The controller package which contains classes responsible for controlling game elements and interactions.
 *
 * @author Muhammad Sohail
 * @version 1.0
 */

package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class which is the entry point for the Pacman game.
 * initialises and sets up the primary stage, loading the start screen.
 */
public class Main extends Application {

    /**
     * start method called when the JavaFX application is launched.
     *
     * @param primaryStage primary stage for the JavaFX application.
     * @throws Exception If an error occurs during the initialisation
     */

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Load the first instance of the screen
        Parent root = FXMLLoader.load(getClass().getResource("../controller/StartScreen.fxml"));

        // Declare and show the first screen, the title page
        primaryStage.setScene(new Scene(root)); // set the start screen
        primaryStage.setTitle( "Pacman" ); // set the title of the start screen
        primaryStage.show(); // show the start screen
    }

    public static void main(String[] args) {
        launch(args);
    }

}