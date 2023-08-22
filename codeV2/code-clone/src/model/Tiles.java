package model;

/**
 * Tiles.java <br>
 * Date created: 21 Aug 2023 <br> <br>
 *
 * The model package contains classes responsible for managing game data and logic.
 *
 * @author Muhammad Sohail
 * @version 1.0
 */

// Useful JavaFx and java imports
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

// Class Obstacle is used to create 2D rectangles for the maze
// Maybe implement a factory for obstacles for ease of use?

/**
 * Tiles class shows rectangular tiles which are used to create obstacles in the maze.
 * Tiles used to build the maze layout by specifying their position,
 * orientation and length.
 */
public class Tiles extends Rectangle {

    // Changed thickness to symbolic constant BCC: Rule 10
    // Constant variable needs changing
    private static double MAX_RECTANGLE_THICKNESS = 25;
    private double x_cor;
    private double y_cor;

    /**
     * Constructs Tiles object with specified position, orientation and length.
     * A rectangle is created with the specified dimensions and colored.
     * @param x x-coordinate of the top-left corner of the rectangle.
     * @param y y-coordinate of the top-left corner of the rectangle.
     * @param orientation orientation of the rectangle ("horizontal" or "vertical").
     * @param length length of the rectangle.
     */
    public Tiles(double x, double y, String orientation, double length) {
        // Set x and y coordinates using inherited constructor of Rectangle
        this.setX(x);
        this.setY(y);

        // Set x and y coordinates from private member class variables
        this.setX_cor(x);
        this.setY_cor(y);

        // Creation of horizontal and vertical rectangles
        if (orientation.equals("horizontal")) {
            this.setHeight(MAX_RECTANGLE_THICKNESS);
            this.setWidth(length * MAX_RECTANGLE_THICKNESS);
        } else {
            this.setHeight(length * MAX_RECTANGLE_THICKNESS);
            this.setWidth(MAX_RECTANGLE_THICKNESS);
        }
        // Fill color of the rectangle
        this.setFill(Color.CADETBLUE);
        // Stroke width of rectangle
        this.setStrokeWidth(3);
    }

    // Function for when an object touches the obstacle
    // Moved from class Maze as it seems more practical and reduce length of Maze
    /**
     * Checks if a point is within the boundaries of the tile.
     * @param x x-coordinate of the point.
     * @param y y-coordinate of the point.
     * @param padding touching area defined by padding around the tile.
     * @param tiles tiles object representing the obstacle.
     * @return True if the point is touching the obstacle, otherwise false.
     */
    public boolean isTouching(double x, double y, double padding, Tiles tiles){
        // Check conditions for the objects x and y coordinates
        return x >= tiles.getX() - padding &&
                x <= tiles.getX() + padding + tiles.getWidth() &&
                y >= tiles.getY() - padding &&
                y <= tiles.getY() + padding + tiles.getHeight();
    }

    // Public Getter and Setter methods for private member variables

    /**
     * Gets the maximum thickness of the rectangle.
     * @return maximum rectangle thickness.
     */
    public static double getMaxRectangleThickness() {
        return MAX_RECTANGLE_THICKNESS;
    }

    /**
     * Sets the maximum thickness of the rectangle.
     * @param maxRectangleThickness new maximum rectangle thickness.
     */
    public static void setMaxRectangleThickness(double maxRectangleThickness) {
        MAX_RECTANGLE_THICKNESS = maxRectangleThickness;
    }

    /**
     * Gets the x-coordinate of the tile.
     * @return x-coordinate of the tile.
     */
    public double getX_cor() {
        return x_cor;
    }

    /**
     * Sets the x-coordinate of the tile.
     * @param x_cor new x-coordinate of the tile.
     */
    public void setX_cor(double x_cor) {
        this.x_cor = x_cor;
    }

    /**
     * Gets the y-coordinate of the tile.
     * @return y-coordinate of the tile.
     */
    public double getY_cor() {
        return y_cor;
    }

    /**
     * Sets the y-coordinate of the tile.
     * @param y_cor new y-coordinate of the tile.
     */
    public void setY_cor(double y_cor) {
        this.y_cor = y_cor;
    }
}
