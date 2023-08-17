package boarder;

// JavaFx and other java imports
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

// **Renamed Cookie class to Pellet, due to original name for the game**
// Class Pellet are objects Pacman collects in the game
// To win Pacman must collect all the pellets in the game with the number of lives
public class Pellet extends Circle {

    private int value; // Cannot justify why value is used here
    private double x_cor;
    private double y_cor;

    // Constructor class takes in two parameters x and y for the pellets
    // Creates a 2D circle in a brownish color
    public Pellet(double x, double y) {
        this.setX_cor(x);
        this.setY_cor(y);

        this.setValue(5); // value set at 5; changed to use a setter method for encapsulation
        this.setCenterX(x);
        this.setCenterY(y);
        this.setRadius(12.5);
        this.setFill(Color.SADDLEBROWN);
    }

    // Public Getter and Setter methods to support encapsulation ***

    // Get the value of the value?
    public int getValue() {
        return value;
    }

    // Set the value of value
    public void setValue(int value){
        this.value = value;
    }

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
    // End of Getter and Setter methods ****

    // Function that sets the visibility of Pacman to invisible
    // Maybe we don't need these functions as they seem unneseecary?
    public void hide() {
        this.setVisible(false);
    }

    // Function that sets the visibility of Pacman to visible
    public void show() {
        this.setVisible(true);
    }
}