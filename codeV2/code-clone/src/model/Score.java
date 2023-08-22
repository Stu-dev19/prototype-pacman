package model;

/**
 * Score.java <br>
 * Date created: 21 Aug 2023 <br> <br>
 *
 * The model package contains classes responsible for managing game data and logic.
 *
 * @author Muhammad Sohail
 * @version 1.0
 */

// JavaFx and java imports
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

// Decide to keep class name the same
// Class score is responsible for keeping the score of Pacman
// when collecting pellets.
// Running a debug, found a problem with the score in the game
// Score is not updated when running the game
// Add some JUnit tests to see if the score is not null and is updating

/**
 * Score class responsible for keeping track of in-game stats like score and lives.
 * Displays the current score and number of lives on the screen.
 * Also includes methods to update and display the score and lives.
 */
public class Score {

    private Text m_score; // class variables start with m_: BCC Rule 4
    private Text m_lives; // member class variables are private BCC Rule 7
    private static final String m_scoreValue = "Score: 0";
    private static final String m_liveValue = "Lives: 3";
    private static final String m_font = "Arial";
    // Symbolic constants converted by BCC Rule 10
    private static final int SCORE_HORIZONTAL_THICKNESS = 4;
    private static final int SCORE_VERTICAL_THICKNESS = 28;
    private static final int LIVES_HORIZONTAL_THICKNESS = 20;
    private static final int LIVES_VERTICAL_THICKNESS = 28;
    private static final int MAX_FONT_SIZE = 30;

    // Constructor is used in manager; make public.
    // Public methods should be named with a capital letter BCC Rule 8
    // Constructor used to set the score of the start of the game to default

    /**
     * Score object to manage the game score and lives display.
     * Sets the score and lives with default values.
     * @param root root Group of JavaFX scene where the displays will be added.
     */
    public Score(Group root) {
        this.setM_score(new Text(Tiles.getMaxRectangleThickness() * SCORE_HORIZONTAL_THICKNESS,
                Tiles.getMaxRectangleThickness() * SCORE_VERTICAL_THICKNESS, m_scoreValue), root);
        this.setM_lives(new Text(Tiles.getMaxRectangleThickness() * LIVES_HORIZONTAL_THICKNESS,
                Tiles.getMaxRectangleThickness() * LIVES_VERTICAL_THICKNESS, m_liveValue), root); // BCC Rule 3; changed spelling of lifes to lives

        // Change the score text's color and font
        getM_score().setFill(Color.MAGENTA);
        getM_score().setFont(Font.font(m_font, MAX_FONT_SIZE));

        // Change the live text's color and font
        getM_lives().setFill(Color.MAROON);
        getM_lives().setFont(Font.font(m_font, MAX_FONT_SIZE));
    }

    // Displayed Accessor methods for m_score and m_life
    // Enforces encapsulation: BCC Rule 5

    /**
     * Gets the Text object for the game score display.
     * @return Text object for the game score display.
     */
    public Text getM_score() {
        return m_score;
    }

    /**
     * Gets the Text object for the lives display.
     * @return Text object for the lives display.
     */
    public Text getM_lives() {
        return m_lives;
    }

    /**
     * Sets the Text object for the game score display.
     * @param newScore New Text object for the game score display.
     * @param root Root Group of JavaFX scene where the display will be added.
     */
    public void setM_score(Text newScore, Group root) {
        // You need to remove the original score first
        root.getChildren().remove(this.m_score);
        this.m_score = newScore;

        // Change the score text's color and font
        getM_score().setFill(Color.MAGENTA);
        getM_score().setFont(Font.font(m_font, MAX_FONT_SIZE));
        root.getChildren().add(this.m_score);
    }

    /**
     * Sets the Text object for the lives display.
     * @param m_lives New Text object for the lives display.
     * @param root Root Group of JavaFX scene where the display will be added.
     */
    public void setM_lives(Text m_lives, Group root) {
        root.getChildren().remove(this.m_lives);
        this.m_lives = m_lives;

        // Change the live text's color and font
        getM_lives().setFill(Color.MAROON);
        getM_lives().setFont(Font.font(m_font, MAX_FONT_SIZE));
        root.getChildren().add(this.m_lives);
    }

    // End of Getter and Setter methods ***
}
