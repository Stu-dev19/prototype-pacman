package boarder;

// Javafx and java imports
import javafx.scene.Group;
import java.util.HashSet;
import java.util.Set;

// Class maze is a collection of m_Obstacles
// This class uses a lot of unnecessary indentation so simplifying this class is needed
public class Maze {

    // Set public member variables to private: Encapsulation
    // Class variables to m_
    private Set<Obstacle> m_Obstacles;

    // Getters and Setters
    public Set<Obstacle> getM_Obstacles() {
        return m_Obstacles;
    }

    public void setM_Obstacles(Set<Obstacle> m_Obstacles) {
        this.m_Obstacles = m_Obstacles;
    }

    // Constructor changed to be public for Manager class
    public Maze() {
        setM_Obstacles(new HashSet<>());
    }

    // Function that checks if the current object with coordinates x and y
    // are touching the hashset of m_Obstacles
    // Changed 'Boolean' to 'boolean' for primitive boolean variables
    public boolean isTouching(double x, double y, double padding) {
        // For loop checking conditions for collisions across entire rectangle
        for (Obstacle obstacle : getM_Obstacles()) {
            if (obstacle.isTouching(x, y, padding, obstacle)){
                return true;
            }
        }
        return false;
    }

    // Function that checks if m_Obstacles are touching each other
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

    // Function CreateMaze creates multiple objects of m_Obstacles
    // Using the hashset it creates instances of objects and places them in a specified order
    // using magic numbers
    // This function is not appropriate and must be reduced: violates BCC Rule 3
    // It appears to be created a frame around the board and the islands in between
    public void CreateMaze(Group root) {
        //~~~~~~~~~~~~~~~~~~~~~~~~~ frame ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        this.getM_Obstacles().add(new Obstacle(0, 0, "horizontal", 48)); //top
        this.getM_Obstacles().add(new Obstacle(0, 600, "horizontal", 48)); //bottom
        this.getM_Obstacles().add(new Obstacle(0, 0, "vertical", 25)); // left
        this.getM_Obstacles().add(new Obstacle(1225 - Obstacle.getMaxRectangleThickness(), // right
                0, "vertical", 25));
        //~~~~~~~~~~~~~~~~~~~~~~~~~ Islands ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        this.getM_Obstacles().add(new Obstacle(12 * Obstacle.getMaxRectangleThickness(),         // obsTopLeft
                Obstacle.getMaxRectangleThickness(), "vertical", 4));
        this.getM_Obstacles().add(new Obstacle(36 * Obstacle.getMaxRectangleThickness(),        // obsTopRight
                Obstacle.getMaxRectangleThickness(), "vertical", 4));
        this.getM_Obstacles().add(new Obstacle(12 * Obstacle.getMaxRectangleThickness(),        // obsBottomLeft
                600 - 4 * Obstacle.getMaxRectangleThickness(), "vertical", 4));
        this.getM_Obstacles().add(new Obstacle(36 * Obstacle.getMaxRectangleThickness(),        // obsBottomRight
                600 - 4 * Obstacle.getMaxRectangleThickness(), "vertical", 4));
        this.getM_Obstacles().add(new Obstacle(16 * Obstacle.getMaxRectangleThickness(),        // obsTopMiddle
                4 * Obstacle.getMaxRectangleThickness(), "horizontal", 17));
        this.getM_Obstacles().add(new Obstacle(16 * Obstacle.getMaxRectangleThickness(),        // obsBottomMiddle
                600 - 4 * Obstacle.getMaxRectangleThickness(), "horizontal", 17));
        this.getM_Obstacles().add(new Obstacle(8 * Obstacle.getMaxRectangleThickness(),    // obsLMTop
                8 * Obstacle.getMaxRectangleThickness(), "horizontal", 5));
        this.getM_Obstacles().add(new Obstacle(8 * Obstacle.getMaxRectangleThickness(),        // obsLMTop4
                12 * Obstacle.getMaxRectangleThickness(), "horizontal", 5));
        this.getM_Obstacles().add(new Obstacle(8 * Obstacle.getMaxRectangleThickness(),        // obsLMBottom
                16 * Obstacle.getMaxRectangleThickness(), "horizontal", 5));
        this.getM_Obstacles().add(new Obstacle(36 * Obstacle.getMaxRectangleThickness(),        // obsRMTop
                8 * Obstacle.getMaxRectangleThickness(), "horizontal", 5));
        this.getM_Obstacles().add(new Obstacle(36 * Obstacle.getMaxRectangleThickness(),      // obsRMTop2
                12 * Obstacle.getMaxRectangleThickness(), "horizontal", 5));
        this.getM_Obstacles().add(new Obstacle(36 * Obstacle.getMaxRectangleThickness(),      // obsRMBottom
                16 * Obstacle.getMaxRectangleThickness(), "horizontal", 5));
        this.getM_Obstacles().add(new Obstacle(4 * Obstacle.getMaxRectangleThickness(),  // LobsLeftTop1
                4 * Obstacle.getMaxRectangleThickness(), "horizontal", 5));
        this.getM_Obstacles().add(new Obstacle(4 * Obstacle.getMaxRectangleThickness(),// LobsLeftTop2
                5 * Obstacle.getMaxRectangleThickness(), "vertical", 6));
        this.getM_Obstacles().add(new Obstacle(4 * Obstacle.getMaxRectangleThickness(),    // LobsLeftBottom1
                600 - 4 * Obstacle.getMaxRectangleThickness(), "horizontal", 5));
        this.getM_Obstacles().add(new Obstacle(4 * Obstacle.getMaxRectangleThickness(),     // LobsLeftBottom2
                600 - 10 * Obstacle.getMaxRectangleThickness(), "vertical", 6));
        this.getM_Obstacles().add(new Obstacle(40 * Obstacle.getMaxRectangleThickness(),        // LobsRightTop1
                4 * Obstacle.getMaxRectangleThickness(), "horizontal", 5));
        this.getM_Obstacles().add(new Obstacle(44 * Obstacle.getMaxRectangleThickness(),       // LobsRightTop2
                5 * Obstacle.getMaxRectangleThickness(), "vertical", 6));
        this.getM_Obstacles().add(new Obstacle(40 * Obstacle.getMaxRectangleThickness(),    // LobsRightBottom1
                600 - 4 * Obstacle.getMaxRectangleThickness(), "horizontal", 5));
        this.getM_Obstacles().add(new Obstacle(44 * Obstacle.getMaxRectangleThickness(),        // LobsRightBottom2
                600 - 10 * Obstacle.getMaxRectangleThickness(), "vertical", 6));
        this.getM_Obstacles().add(new Obstacle(16 * Obstacle.getMaxRectangleThickness(),     // cageBottom
                600 - 8 * Obstacle.getMaxRectangleThickness(), "horizontal", 17));
        this.getM_Obstacles().add(new Obstacle(32 * Obstacle.getMaxRectangleThickness(),        // cageRightV
                600 - 16 * Obstacle.getMaxRectangleThickness(), "vertical", 8));
        this.getM_Obstacles().add(new Obstacle(16 * Obstacle.getMaxRectangleThickness(),      // cageLeftV
                600 - 16 * Obstacle.getMaxRectangleThickness(), "vertical", 8));
        this.getM_Obstacles().add(new Obstacle(17 * Obstacle.getMaxRectangleThickness(),        // cateRightH
                8 * Obstacle.getMaxRectangleThickness(), "horizontal", 5));
        this.getM_Obstacles().add(new Obstacle(27 * Obstacle.getMaxRectangleThickness(),        // cateLeftH
                8 * Obstacle.getMaxRectangleThickness(), "horizontal", 5));

        root.getChildren().addAll(getM_Obstacles());
    }
}
