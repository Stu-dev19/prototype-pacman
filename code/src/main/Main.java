package main;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage st) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("pacman.fxml"));
        st.setTitle( "PacmanV7" );
        Group root = new Group();
        Scene sc = new Scene( root );
        st.setScene( sc );
        Canvas canvas = new Canvas( 1225, 600 );
        root.getChildren().add( canvas );
        Manager manager = Manager.GetInstance(root);
        manager.DrawMaze();
        sc.addEventHandler(KeyEvent.KEY_PRESSED, event -> manager.MovePacman(event));
        sc.addEventHandler(KeyEvent.KEY_RELEASED, event -> manager.StopPacman(event));
        sc.addEventHandler(KeyEvent.KEY_PRESSED, event -> manager.restartGame(event));
        st.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}