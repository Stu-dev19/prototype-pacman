package tests;

import javafx.scene.paint.Color;
import model.Pellet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PelletTest {

    @Test
    void testGetX_cor() {
        Pellet newPellet = new Pellet(0, 0);
        assertEquals(0, newPellet.getM_XCor());
    }

    @Test
    void testSetX_cor() {
        Pellet newPellet = new Pellet(0, 0);
        newPellet.setM_XCor(52);
        assertEquals(52, newPellet.getM_XCor());
    }

    @Test
    void testGetY_cor() {
        Pellet newPellet = new Pellet(0, 0);
        assertEquals(0, newPellet.getM_YCor());
    }

    @Test
    void testSetY_cor() {
        Pellet newPellet = new Pellet(0, 0);
        newPellet.setM_YCor(52);
        assertEquals(52, newPellet.getM_YCor());
    }

    @Test
    void testColorOfPellet(){
        Pellet newPellet = new Pellet(0, 0);
        assertEquals(Color.SADDLEBROWN, newPellet.getFill());
    }

    @Test
    void testRadiusOfPellet(){
        Pellet newPellet = new Pellet(0, 0);
        assertEquals(12.5, newPellet.getRadius());
    }
}