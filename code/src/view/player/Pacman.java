package view.player;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Pacman extends Circle {

    // Member class variables for x and y coordinates of Pacman
    private double x_cor;
    private double y_cor;

    // Constructor for Pacman
    // Creates a basic yellow circle for the playable character
    // Takes in two parameters x and y for the coordinate system
    public Pacman(double x, double y) {
        this.setX_cor(x);
        this.setY_cor(y);

        this.setCenterX(x);
        this.setCenterY(y);
        this.setRadius(25); // Radius is pre-defined here
        this.setFill(Color.YELLOW); // Remain yellow until sprite is uploaded
    }

    // Added Getter and Setter for x and y coordinates methods to improve encapsulation
    public double getX_cor() {
        return x_cor;
    }

    public void setX_cor(double x_cor) {
        this.x_cor = x_cor;
    }

    public double getY_cor() {
        return y_cor;
    }

    public void setY_cor(double y_cor) {
        this.y_cor = y_cor;
    }


    // Other functions that support Pacman directions and handling placed here ***
}
