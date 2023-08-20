package model;

// Javafx and java imports
import javafx.scene.Group;
import java.util.HashSet;
import java.util.Set;

// Class maze is a collection of m_Obstacles
// This class uses a lot of unnecessary indentation so simplifying this class is needed
public class Maze {

    // Set public member variables to private: Encapsulation
    // Class variables to m_
    private Set<Tiles> m_Tiles;

    // Getters and Setters
    public Set<Tiles> getM_Obstacles() {
        return m_Tiles;
    }

    public void setM_Obstacles(Set<Tiles> m_Tiles) {
        this.m_Tiles = m_Tiles;
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
        for (Tiles tiles : getM_Obstacles()) {
            if (tiles.isTouching(x, y, padding, tiles)){
                return true;
                // for every obstacle in the hashset, if the x and y coordinates are touching a specific obstacle
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
        this.getM_Obstacles().add(new Tiles(0, 0, "horizontal", 48)); //top
        this.getM_Obstacles().add(new Tiles(0, 600, "horizontal", 48)); //bottom
        this.getM_Obstacles().add(new Tiles(0, 0, "vertical", 25)); // left
        this.getM_Obstacles().add(new Tiles(1225 - Tiles.getMaxRectangleThickness(), // right
                0, "vertical", 25));
        //~~~~~~~~~~~~~~~~~~~~~~~~~ Islands ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        this.getM_Obstacles().add(new Tiles(12 * Tiles.getMaxRectangleThickness(),         // obsTopLeft
                Tiles.getMaxRectangleThickness(), "vertical", 4));
        this.getM_Obstacles().add(new Tiles(36 * Tiles.getMaxRectangleThickness(),        // obsTopRight
                Tiles.getMaxRectangleThickness(), "vertical", 4));
        this.getM_Obstacles().add(new Tiles(12 * Tiles.getMaxRectangleThickness(),        // obsBottomLeft
                600 - 4 * Tiles.getMaxRectangleThickness(), "vertical", 4));
        this.getM_Obstacles().add(new Tiles(36 * Tiles.getMaxRectangleThickness(),        // obsBottomRight
                600 - 4 * Tiles.getMaxRectangleThickness(), "vertical", 4));
        this.getM_Obstacles().add(new Tiles(16 * Tiles.getMaxRectangleThickness(),        // obsTopMiddle
                4 * Tiles.getMaxRectangleThickness(), "horizontal", 17));
        this.getM_Obstacles().add(new Tiles(16 * Tiles.getMaxRectangleThickness(),        // obsBottomMiddle
                600 - 4 * Tiles.getMaxRectangleThickness(), "horizontal", 17));
        this.getM_Obstacles().add(new Tiles(8 * Tiles.getMaxRectangleThickness(),    // obsLMTop
                8 * Tiles.getMaxRectangleThickness(), "horizontal", 5));
        this.getM_Obstacles().add(new Tiles(8 * Tiles.getMaxRectangleThickness(),        // obsLMTop4
                12 * Tiles.getMaxRectangleThickness(), "horizontal", 5));
        this.getM_Obstacles().add(new Tiles(8 * Tiles.getMaxRectangleThickness(),        // obsLMBottom
                16 * Tiles.getMaxRectangleThickness(), "horizontal", 5));
        this.getM_Obstacles().add(new Tiles(36 * Tiles.getMaxRectangleThickness(),        // obsRMTop
                8 * Tiles.getMaxRectangleThickness(), "horizontal", 5));
        this.getM_Obstacles().add(new Tiles(36 * Tiles.getMaxRectangleThickness(),      // obsRMTop2
                12 * Tiles.getMaxRectangleThickness(), "horizontal", 5));
        this.getM_Obstacles().add(new Tiles(36 * Tiles.getMaxRectangleThickness(),      // obsRMBottom
                16 * Tiles.getMaxRectangleThickness(), "horizontal", 5));
        this.getM_Obstacles().add(new Tiles(4 * Tiles.getMaxRectangleThickness(),  // LobsLeftTop1
                4 * Tiles.getMaxRectangleThickness(), "horizontal", 5));
        this.getM_Obstacles().add(new Tiles(4 * Tiles.getMaxRectangleThickness(),// LobsLeftTop2
                5 * Tiles.getMaxRectangleThickness(), "vertical", 6));
        this.getM_Obstacles().add(new Tiles(4 * Tiles.getMaxRectangleThickness(),    // LobsLeftBottom1
                600 - 4 * Tiles.getMaxRectangleThickness(), "horizontal", 5));
        this.getM_Obstacles().add(new Tiles(4 * Tiles.getMaxRectangleThickness(),     // LobsLeftBottom2
                600 - 10 * Tiles.getMaxRectangleThickness(), "vertical", 6));
        this.getM_Obstacles().add(new Tiles(40 * Tiles.getMaxRectangleThickness(),        // LobsRightTop1
                4 * Tiles.getMaxRectangleThickness(), "horizontal", 5));
        this.getM_Obstacles().add(new Tiles(44 * Tiles.getMaxRectangleThickness(),       // LobsRightTop2
                5 * Tiles.getMaxRectangleThickness(), "vertical", 6));
        this.getM_Obstacles().add(new Tiles(40 * Tiles.getMaxRectangleThickness(),    // LobsRightBottom1
                600 - 4 * Tiles.getMaxRectangleThickness(), "horizontal", 5));
        this.getM_Obstacles().add(new Tiles(44 * Tiles.getMaxRectangleThickness(),        // LobsRightBottom2
                600 - 10 * Tiles.getMaxRectangleThickness(), "vertical", 6));
        this.getM_Obstacles().add(new Tiles(16 * Tiles.getMaxRectangleThickness(),     // cageBottom
                600 - 8 * Tiles.getMaxRectangleThickness(), "horizontal", 17));
        this.getM_Obstacles().add(new Tiles(32 * Tiles.getMaxRectangleThickness(),        // cageRightV
                600 - 16 * Tiles.getMaxRectangleThickness(), "vertical", 8));
        this.getM_Obstacles().add(new Tiles(16 * Tiles.getMaxRectangleThickness(),      // cageLeftV
                600 - 16 * Tiles.getMaxRectangleThickness(), "vertical", 8));
        this.getM_Obstacles().add(new Tiles(17 * Tiles.getMaxRectangleThickness(),        // cateRightH
                8 * Tiles.getMaxRectangleThickness(), "horizontal", 5));
        this.getM_Obstacles().add(new Tiles(27 * Tiles.getMaxRectangleThickness(),        // cateLeftH
                8 * Tiles.getMaxRectangleThickness(), "horizontal", 5));

        root.getChildren().addAll(getM_Obstacles());
    }
}
