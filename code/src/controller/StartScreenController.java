package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Manager;

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
        // Set new Stage
        Stage stage = new Stage(); // current stage
        stage.setTitle("Pacman Game");

        Group root = new Group();
        Manager manager = Manager.GetInstance(root);

        Scene sc = new Scene(root);
        stage.setScene(sc);
        Canvas canvas = new Canvas( 1200, 600 );  // 1225 and 600
        root.getChildren().add(canvas);

        manager.DrawMaze();
        sc.addEventHandler(KeyEvent.KEY_PRESSED, manager::MovePacman);
        sc.addEventHandler(KeyEvent.KEY_RELEASED, manager::StopPacman);
        sc.addEventHandler(KeyEvent.KEY_PRESSED, manager::restartGame);
        stage.show();
    }

    private void showGameplay(){


    }
}
