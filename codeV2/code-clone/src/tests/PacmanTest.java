package tests;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;
import view.player.Pacman;

import static org.junit.jupiter.api.Assertions.*;

class PacmanTest {

    @Test
    void getX_cor() {
        Pacman pacman = new Pacman(0, 0);
        int xCor = 0;
        assertEquals(xCor, pacman.getM_XCor());
    }

    @Test
    void setX_cor() {
        Pacman pacman = new Pacman(0, 0);
        pacman.setM_YCor(54);
        int YCor = 54;
        assertEquals(YCor, pacman.getM_YCor());
    }

    @Test
    void testInvincibleColor() {
        Pacman pacman = new Pacman(0, 0);
        pacman.InvinciblePacman();
        assertEquals(Color.ALICEBLUE, pacman.getFill());
    }
}