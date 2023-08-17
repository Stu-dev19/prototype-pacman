package player;


// Javafx imports need to be handled with maven/JDK build

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Pacman extends Circle { // Circle class not defined or module not imported; part of javafx

    // Constructor class to be defined when javafx is imported
    public Pacman(double x, double y) { // Pacman extends Circle which needs to be imported
        this.setCenterX(x);
        this.setCenterY(y);
        this.setRadius(25);
        this.setFill(Color.YELLOW); // Color was not imported
    }
}
