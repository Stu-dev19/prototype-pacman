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

    private int m_Value; // Cannot justify why value is used here
    private double m_XCor;
    private double m_YCor;

    // Public Getter and Setter methods to support encapsulation ***

    // Get the value of the value?
    /**
     * Gets the point value of the pellet.
     * @return point value of the pellet.
     */
    public int getM_Value() {
        return m_Value;
    }

    // Set the value of value

    /**
     * Sets the point value of the pellet.
     * @param m_Value point value to set for the pellet.
     */
    public void setM_Value(int m_Value){
        this.m_Value = m_Value;
    }

    /**
     * Gets the x-coordinate of the pellet.
     * @return x-coordinate of the pellet.
     */
    public double getM_XCor() {
        return m_XCor;
    }

    /**
     * Sets the x-coordinate of the pellet.
     * @param m_XCor x-coordinate to set for the pellet.
     */
    public void setM_XCor(double m_XCor) {
        this.m_XCor = m_XCor;
    }

    /**
     * Gets the y-coordinate of the pellet.
     * @return y-coordinate of the pellet.
     */
    public double getM_YCor() {
        return m_YCor;
    }

    /**
     * Sets the y-coordinate of the pellet.
     * @param m_YCor y-coordinate to set for the pellet.
     */
    public void setM_YCor(double m_YCor) {
        this.m_YCor = m_YCor;
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

        this.setM_XCor(x);
        this.setM_YCor(y);

        this.setM_Value(5); // value set at 5; changed to use a setter method for encapsulation
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