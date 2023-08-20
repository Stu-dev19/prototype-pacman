package model;

// Useful JavaFx and java imports
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

// Class Obstacle is used to create 2D rectangles for the maze
// Maybe implement a factory for obstacles for ease of use?
public class Tiles extends Rectangle {

    // Changed thickness to symbolic constant BCC: Rule 10
    // Constant variable needs changing
    private static double MAX_RECTANGLE_THICKNESS = 25;
    private double x_cor;
    private double y_cor;

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
    public boolean isTouching(double x, double y, double padding, Tiles tiles){
        // Check conditions for the objects x and y coordinates
        return x >= tiles.getX() - padding &&
                x <= tiles.getX() + padding + tiles.getWidth() &&
                y >= tiles.getY() - padding &&
                y <= tiles.getY() + padding + tiles.getHeight();
    }

    // Public Getter and Setter methods for private member variables
    public static double getMaxRectangleThickness() {
        return MAX_RECTANGLE_THICKNESS;
    }

    public static void setMaxRectangleThickness(double maxRectangleThickness) {
        MAX_RECTANGLE_THICKNESS = maxRectangleThickness;
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
}
