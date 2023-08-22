package tests;

import model.Maze;
import model.Tiles;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MazeTest {

    @Test
    void testObstacleDetectionZeroCase(){
        Maze newMaze = new Maze();
        assertFalse(newMaze.isTouching(0, 0, 0));
    }

    @Test
    void testObjectHasObstacle(){
        Maze newMaze = new Maze();
        Boolean bool = newMaze.hasObstacle(0, 0, 0, 0);
        assertEquals(false, bool);
    }

    @Test
    void checkNumberOfObstacles(){
        Maze newMaze = new Maze();

        // Add obstacles to maze
        newMaze.getM_Obstacles().add(new Tiles(0, 0, "horizontal", 48));
        newMaze.getM_Obstacles().add(new Tiles(0, 0, "horizontal", 48));

        //Test
        assertEquals(2, newMaze.getM_Obstacles().size());
    }
}