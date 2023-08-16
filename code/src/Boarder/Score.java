package Boarder;



import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

// Wrong with javafx imports

public class Score {

    private Text m_score; // class variables start with m_CLASSVARIABLE BCC Rule 4
    private Text m_life; // member class variables are private BCC Rule 7

    // Constructor is used in manager to make public.
    // Public methods should be named with a capital letter BCC Rule 8
    public Score(Group root) {
        this.setM_score(new Text(Obstacle.THICKNESS * 4,
                Obstacle.THICKNESS * 28,
                "Score: 0"));
        this.setM_life(new Text(Obstacle.THICKNESS * 20,
                Obstacle.THICKNESS * 28,
                "Lifes: 3")); // BCC Rule 3

        getM_score().setFill(Color.MAGENTA);
        getM_score().setFont(Font.font("Arial", 30));

        getM_life().setFill(Color.MAROON);
        getM_life().setFont(Font.font("Arial", 30));

        root.getChildren().add(m_score);
        root.getChildren().add(m_life); // problem with manager class
    }

    // Displayed Accessor methods for m_score and m_life
    // Enforces encapsulation: BCC Rule 5
    public Text getM_score() {
        return m_score;
    }

    public Text getM_life() {
        return m_life;
    }

    public void setM_score(Text m_score) {
        this.m_score = m_score;
    }

    public void setM_life(Text m_life) {
        this.m_life = m_life;
    }
}
