/**
 * Pacman.java <br>
 * Date created: 21 Aug 2023 <br> <br>
 *
 * The view package contains classes responsible for visualising the enemies in the game.
 *
 * @author Muhammad Sohail
 * @version 1.0
 */

package view.player;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * The Pacman class handles the player character that can move around and interact in the game.
 */
public class Pacman extends Circle {

    // Member class variables for x and y coordinates of Pacman


    private double x_cor;
    private double y_cor;

    // Added Getter and Setter for x and y coordinates methods to improve encapsulation

    /**
     * Gets the x-coordinate of Pacman.
     * @return x-coordinate of Pacman.
     */
    public double getX_cor() {
        return x_cor;
    }

    /**
     * Sets the x-coordinate of Pacman.
     * @param x_cor new x-coordinate of Pacman.
     */
    public void setX_cor(double x_cor) {
        this.x_cor = x_cor;
    }

    /**
     * Gets the y-coordinate of Pacman.
     * @return y-coordinate of Pacman.
     */
    public double getY_cor() {
        return y_cor;
    }

    /**
     * Sets the y-coordinate of Pacman.
     * @param y_cor new y-coordinate of Pacman.
     */
    public void setY_cor(double y_cor) {
        this.y_cor = y_cor;
    }

    // Constructor for Pacman
    // Creates a basic yellow circle for the playable character
    // Takes in two parameters x and y for the coordinate system

    /**
     * Creates the Pacman object with specified position.
     * @param x x-coordinate of Pacman's position.
     * @param y y-coordinate of Pacman's position.
     */
    public Pacman(double x, double y) {
        this.setX_cor(x);
        this.setY_cor(y);

        this.setCenterX(x);
        this.setCenterY(y);
        this.setRadius(25); // Radius is pre-defined here
        this.setFill(Color.YELLOW); // Remain yellow until sprite is uploaded
    }

    // Other functions that support Pacman directions and handling placed here ***

    /**
     * Makes Pacman invincible for a certain amount of time by changing its color.
     * method changes the color of Pacman to blue which indicates that it's invincible.
     */
    public void InvinciblePacman(){
        // Change the color of pacman to blue
        this.setFill(Color.ALICEBLUE);
    }
}
