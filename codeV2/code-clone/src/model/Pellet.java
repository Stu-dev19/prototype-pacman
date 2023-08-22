/**
 * Pellet.java <br>
 * Date created: 21 Aug 2023 <br> <br>
 *
 * The model package contains classes responsible for managing game data and logic.
 *
 * @author Muhammad Sohail
 * @version 1.0
 */
package model;
// JavaFx and other java imports
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

// **Renamed Cookie class to Pellet, due to original name for the game**
// Class Pellet are objects Pacman collects in the game
// To win Pacman must collect all the pellets in the game with the number of lives
/**
 * Pellet class shows the objects that Pacman will collect in the game.
 * All pellets must be collected to win the game, while only having 3 lives.
 */
public class Pellet extends Circle {

    private int value; // Cannot justify why value is used here
    private double x_cor;
    private double y_cor;

    // Public Getter and Setter methods to support encapsulation ***

    // Get the value of the value?
    /**
     * Gets the point value of the pellet.
     * @return point value of the pellet.
     */
    public int getValue() {
        return value;
    }

    // Set the value of value

    /**
     * Sets the point value of the pellet.
     * @param value point value to set for the pellet.
     */
    public void setValue(int value){
        this.value = value;
    }

    /**
     * Gets the x-coordinate of the pellet.
     * @return x-coordinate of the pellet.
     */
    public double getX_cor() {
        return x_cor;
    }

    /**
     * Sets the x-coordinate of the pellet.
     * @param x_cor x-coordinate to set for the pellet.
     */
    public void setX_cor(double x_cor) {
        this.x_cor = x_cor;
    }

    /**
     * Gets the y-coordinate of the pellet.
     * @return y-coordinate of the pellet.
     */
    public double getY_cor() {
        return y_cor;
    }

    /**
     * Sets the y-coordinate of the pellet.
     * @param y_cor y-coordinate to set for the pellet.
     */
    public void setY_cor(double y_cor) {
        this.y_cor = y_cor;
    }
    // End of Getter and Setter methods ****

    // Constructor class takes in two parameters x and y for the pellets
    // Creates a 2D circle in a brownish color
    /**
     * Constructs a Pellet with the specified coordinates.
     * Pellet is shown as a brown circle.
     * @param x x-coordinate of the pellet.
     * @param y y-coordinate of the pellet.
     */
    public Pellet(double x, double y) {

        this.setX_cor(x);
        this.setY_cor(y);

        this.setValue(5); // value set at 5; changed to use a setter method for encapsulation
        this.setCenterX(x);
        this.setCenterY(y);
        this.setRadius(12.5);
        this.setFill(Color.SADDLEBROWN);
    }

    // Function that sets the visibility of Pacman to invisible
    // Maybe we don't need these functions as they seem unneseecary?

    /**
     * Hides the pellet by setting its visibility to false.
     * Method is called when Pacman eats a pellet.
     */
    public void hide() {
        this.setVisible(false);
    }


}