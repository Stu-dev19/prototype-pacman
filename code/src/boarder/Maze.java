package boarder;

// Javafx and java imports
import javafx.scene.Group;
import java.util.HashSet;
import java.util.Set;

// Class maze is a collection of obstacles
// This class uses a lot of unnecessary indentation so simplifying this class is needed
public class Maze {

    // Set public member variables to private: Encapsulation
    private Obstacle obstacle_temp;
    private Set<Obstacle> obstacles;

    // Constructor changed to be public for Manager class
    public Maze() {
        obstacles = new HashSet<>();
        //initaliseObstacles(); // to initialise all obstacles later used
    }

    // Function that checks if the current object with coordinates x and y
    // are touching the hashset of obstacles
    // Changed 'Boolean' to 'boolean' for primitive boolean variables
    public boolean isTouching(double x, double y, double padding) {
        // For loop checking conditions for collisions across entire rectangle
        for (Obstacle obstacle :obstacles) {
            if (obstacle.isTouching(x, y, padding, obstacle)){
                return true;
            }
        }
        return false;
    }

    // Function that checks if obstacles are touching each other
    // Renamed parameters to match consistent conventions
    // Deleted variable isTouching, not needed
    public Boolean hasObstacle(double x_from,  double x_to, double y_from, double y_to) {
        for (double i = x_from; i < x_to; i++) {
            for (double j = y_from; j < y_to; j++) {
                if (this.isTouching(i, j, 0)){
                    return true;
                }
            }
        }
        return false;
    }

    // Function CreateMaze creates multiple objects of obstacles
    // Using the hashset it creates instances of objects and places them in a specified order
    // using magic numbers
    // This function is not appropriate and must be reduced: violates BCC Rule 3
    // It appears to be created a frame around the board and the islands in between
    public void CreateMaze(Group root) {
        //~~~~~~~~~~~~~~~~~~~~~~~~~ frame ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // top
        this.obstacles.add(new Obstacle(0, 0, "horizontal", 48));
        // bottom
        this.obstacles.add(new Obstacle(0, 600, "horizontal", 48));
        // left
        this.obstacles.add(new Obstacle(0, 0, "vertical", 25));
        // right
        this.obstacles.add(new Obstacle(1225 - Obstacle.getMaxRectangleThickness(), 0, "vertical", 25));

        //~~~~~~~~~~~~~~~~~~~~~~~~~ Islands ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // obsTopLeft
        this.obstacles.add(new Obstacle(12 * Obstacle.getMaxRectangleThickness(), Obstacle.getMaxRectangleThickness(), "vertical", 4));
        // obsTopRight
        this.obstacles.add(new Obstacle(36 * Obstacle.getMaxRectangleThickness(), Obstacle.getMaxRectangleThickness(), "vertical", 4));
        // obsBottomLeft
        this.obstacles.add(new Obstacle(12 * Obstacle.getMaxRectangleThickness(), 600 - 4 * Obstacle.getMaxRectangleThickness(), "vertical", 4));
        // obsBottomRight
        this.obstacles.add(new Obstacle(36 * Obstacle.getMaxRectangleThickness(), 600 - 4 * Obstacle.getMaxRectangleThickness(), "vertical", 4));
        // obsTopMiddle
        this.obstacles.add(new Obstacle(16 * Obstacle.getMaxRectangleThickness(), 4 * Obstacle.getMaxRectangleThickness(), "horizontal", 17));
        // obsBottomMiddle
        this.obstacles.add(new Obstacle(16 * Obstacle.getMaxRectangleThickness(), 600 - 4 * Obstacle.getMaxRectangleThickness(), "horizontal", 17));
        // obsLMTop
        this.obstacles.add(new Obstacle(8 * Obstacle.getMaxRectangleThickness(), 8 * Obstacle.getMaxRectangleThickness(), "horizontal", 5));
        // obsLMTop4
        this.obstacles.add(new Obstacle(8 * Obstacle.getMaxRectangleThickness(), 12 * Obstacle.getMaxRectangleThickness(), "horizontal", 5));
        // obsLMBottom
        this.obstacles.add(new Obstacle(8 * Obstacle.getMaxRectangleThickness(), 16 * Obstacle.getMaxRectangleThickness(), "horizontal", 5));
        // obsRMTop
        this.obstacles.add(new Obstacle(36 * Obstacle.getMaxRectangleThickness(), 8 * Obstacle.getMaxRectangleThickness(), "horizontal", 5));
        // obsRMTop2
        this.obstacles.add(new Obstacle(36 * Obstacle.getMaxRectangleThickness(), 12 * Obstacle.getMaxRectangleThickness(), "horizontal", 5));
        // obsRMBottom
        this.obstacles.add(new Obstacle(36 * Obstacle.getMaxRectangleThickness(), 16 * Obstacle.getMaxRectangleThickness(), "horizontal", 5));
        // LobsLeftTop1
        this.obstacles.add(new Obstacle(4 * Obstacle.getMaxRectangleThickness(), 4 * Obstacle.getMaxRectangleThickness(), "horizontal", 5));
        // LobsLeftTop2
        this.obstacles.add(new Obstacle(4 * Obstacle.getMaxRectangleThickness(), 5 * Obstacle.getMaxRectangleThickness(), "vertical", 6));
        // LobsLeftBottom1
        this.obstacles.add(new Obstacle(4 * Obstacle.getMaxRectangleThickness(), 600 - 4 * Obstacle.getMaxRectangleThickness(), "horizontal", 5));
        // LobsLeftBottom2
        this.obstacles.add(new Obstacle(4 * Obstacle.getMaxRectangleThickness(), 600 - 10 * Obstacle.getMaxRectangleThickness(), "vertical", 6));
        // LobsRightTop1
        this.obstacles.add(new Obstacle(40 * Obstacle.getMaxRectangleThickness(), 4 * Obstacle.getMaxRectangleThickness(), "horizontal", 5));
        // LobsRightTop2
        this.obstacles.add(new Obstacle(44 * Obstacle.getMaxRectangleThickness(), 5 * Obstacle.getMaxRectangleThickness(), "vertical", 6));
        // LobsRightBottom1
        this.obstacles.add(new Obstacle(40 * Obstacle.getMaxRectangleThickness(), 600 - 4 * Obstacle.getMaxRectangleThickness(), "horizontal", 5));
        // LobsRightBottom2
        this.obstacles.add(new Obstacle(44 * Obstacle.getMaxRectangleThickness(), 600 - 10 * Obstacle.getMaxRectangleThickness(), "vertical", 6));
        // cageBottom
        this.obstacles.add(new Obstacle(16 * Obstacle.getMaxRectangleThickness(), 600 - 8 * Obstacle.getMaxRectangleThickness(), "horizontal", 17));
        // cageRightV
        this.obstacles.add(new Obstacle(32 * Obstacle.getMaxRectangleThickness(), 600 - 16 * Obstacle.getMaxRectangleThickness(), "vertical", 8));
        // cageLeftV
        this.obstacles.add(new Obstacle(16 * Obstacle.getMaxRectangleThickness(), 600 - 16 * Obstacle.getMaxRectangleThickness(), "vertical", 8));
        // cateRightH
        this.obstacles.add(new Obstacle(17 * Obstacle.getMaxRectangleThickness(), 8 * Obstacle.getMaxRectangleThickness(), "horizontal", 5));
        // cateLeftH
        this.obstacles.add(new Obstacle(27 * Obstacle.getMaxRectangleThickness(), 8 * Obstacle.getMaxRectangleThickness(), "horizontal", 5));

        root.getChildren().addAll(obstacles);
    }

    // Function that adds an obstacle in the specified location
    // To reduce the long line length of function CreateMaze
    private void addObstacle(double x, double y, String orientation, double size){
        // Add the obstacle to the hashset
        this.obstacles.add(new Obstacle(x, y, orientation, size));
    }
}
