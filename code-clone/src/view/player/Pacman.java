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


    private double m_XCor;
    private double m_YCor;

    // Added Getter and Setter for x and y coordinates methods to improve encapsulation

    /**
     * Gets the x-coordinate of Pacman.
     * @return x-coordinate of Pacman.
     */
    public double getM_XCor() {
        return m_XCor;
    }

    /**
     * Sets the x-coordinate of Pacman.
     * @param m_XCor new x-coordinate of Pacman.
     */
    public void setM_XCor(double m_XCor) {
        this.m_XCor = m_XCor;
    }

    /**
     * Gets the y-coordinate of Pacman.
     * @return y-coordinate of Pacman.
     */
    public double getM_YCor() {
        return m_YCor;
    }

    /**
     * Sets the y-coordinate of Pacman.
     * @param m_YCor new y-coordinate of Pacman.
     */
    public void setM_YCor(double m_YCor) {
        this.m_YCor = m_YCor;
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
        this.setM_XCor(x);
        this.setM_YCor(y);

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
