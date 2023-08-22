package tests;

import javafx.scene.paint.Color;
import model.Tiles;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TilesTest {

    @Test
    void testTileColor(){
        Tiles newTile = new Tiles(0, 0, "horizontal", 0);
        assertEquals(Color.CADETBLUE, newTile.getFill());
    }

    @Test
    void testTileXCor(){
        Tiles newTile = new Tiles(52, 0, "horizontal", 0);
        assertEquals(52, newTile.getM_XCor());
    }

    @Test
    void testTileYCor(){
        Tiles newTile = new Tiles(0, 52, "horizontal", 0);
        assertEquals(52, newTile.getM_YCor());
    }

    @Test
    void testTileZeroCollision(){
        Tiles newTile = new Tiles(0, 52, "horizontal", 0);
        boolean test = newTile.isTouching(0, 0, 0, newTile);
        assertFalse(test);
    }
}