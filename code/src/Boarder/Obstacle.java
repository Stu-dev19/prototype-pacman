package Boarder;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.*; // separate library from javafx

// More javafx imports for Color and Rectangle

public class Obstacle extends Rectangle {

    public static double THICKNESS = 25;

    public Obstacle(double x, double y, String orientation, double length) {
        this.setX(x);
        this.setY(y); // part of javafx
        if (orientation.equals("horizontal")) {
            this.setHeight(Obstacle.THICKNESS);
            this.setWidth(length * Obstacle.THICKNESS);
        } else {
            this.setHeight(length * Obstacle.THICKNESS);
            this.setWidth(Obstacle.THICKNESS);
        }
        this.setFill(Color.CADETBLUE);
        this.setStrokeWidth(3);
    }
}
