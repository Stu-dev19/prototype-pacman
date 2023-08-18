package boarder;

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
    public Score(Group root) {
        this.setM_score(new Text(Obstacle.getMaxRectangleThickness() * SCORE_HORIZONTAL_THICKNESS,
                Obstacle.getMaxRectangleThickness() * SCORE_VERTICAL_THICKNESS,
                m_scoreValue));
        this.setM_lives(new Text(Obstacle.getMaxRectangleThickness() * LIVES_HORIZONTAL_THICKNESS,
                Obstacle.getMaxRectangleThickness() * LIVES_VERTICAL_THICKNESS,
                m_liveValue)); // BCC Rule 3; changed spelling of lifes to lives

        // Change the score text's color and font
        getM_score().setFill(Color.MAGENTA);
        getM_score().setFont(Font.font(m_font, MAX_FONT_SIZE));

        // Change the live text's color and font
        getM_lives().setFill(Color.MAROON);
        getM_lives().setFont(Font.font(m_font, MAX_FONT_SIZE));

        // Add the following texts to the main screen
        root.getChildren().add(m_score);
        root.getChildren().add(m_lives);
    }

    // Displayed Accessor methods for m_score and m_life
    // Enforces encapsulation: BCC Rule 5
    public Text getM_score() {
        return m_score;
    }

    public Text getM_lives() {
        return m_lives;
    }

    public void setM_score(Text m_score) {
        this.m_score = m_score;
    }

    public void setM_lives(Text m_lives) {
        this.m_lives = m_lives;
    }

    // End of Getter and Setter methods ***
}
