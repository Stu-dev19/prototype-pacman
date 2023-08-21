package view.enemies;
// Import useful Java imports
import model.Maze;
import model.Manager;
import java.util.Random;

// To be installed with JavaFx
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

// Class Ghost handles the movement and direction of the enemies on the map
public class Ghost extends Rectangle implements Runnable { // Rectangle class not imported

    // Member class variables
    private String m_Direction;
    private Manager m_Manager;
    private Maze m_Maze;
    private AnimationTimer m_Animation;
    private int m_TimesWalked;
    private Color originalColor;

    // Getter and Setter accessor methods
    public String getM_Direction() {
        return m_Direction;
    }

    public void setM_Direction(String m_Direction) {
        this.m_Direction = m_Direction;
    }

    public Manager getM_Manager() {
        return m_Manager;
    }

    public void setM_Manager(Manager m_Manager) {
        this.m_Manager = m_Manager;
    }

    public Maze getM_Maze() {
        return m_Maze;
    }

    public void setM_Maze(Maze m_Maze) {
        this.m_Maze = m_Maze;
    }

    public AnimationTimer getM_Animation() {
        return m_Animation;
    }

    public void setM_Animation(AnimationTimer m_Animation) {
        this.m_Animation = m_Animation;
    }

    public int getM_TimesWalked() {
        return m_TimesWalked;
    }

    public Color GetOriginalColor(){return originalColor;}

    public void setM_TimesWalked(int m_TimesWalked) {
        this.m_TimesWalked = m_TimesWalked;
    }

    public Ghost(double x, double y, Color color, Maze maze, Group root) { // added root to constructor of Ghost
        this.setX(x);
        this.setY(y);
        this.setM_Maze(maze);
        this.setM_Manager(Manager.GetInstance(root)); // Singleton pass
        this.setHeight(50);
        this.setWidth(50);
        this.setFill(color);
        this.setM_TimesWalked(0);
        this.setM_Direction("down");
        this.CreateAnimation();
        this.originalColor = color;
    }

    private String getRandomDirection(String exclude1, String exclude2) {
        String[] directions = {"left", "right", "up", "down"};
        int rnd = new Random().nextInt(directions.length);
        while (directions[rnd].equals(exclude1) || directions[rnd].equals(exclude2)) {
            rnd = new Random().nextInt(directions.length);
        }
        return directions[rnd];
    }

    public void ChangeGhostColor(Color color){
        this.setFill(color);
    }

    public AnimationTimer GetAnimation() {
        return this.getM_Animation();
    }

    private void determinePath(String direction) {
        double rightEdge, leftEdge, topEdge, bottomEdge;
        switch (direction) {
            case "down" -> {
                leftEdge = getX() - 10;
                bottomEdge = getY() + getHeight() + 15;
                rightEdge = getX() + getWidth() + 10;
                if (!this.getM_Maze().hasObstacle(leftEdge, rightEdge, bottomEdge - 1, bottomEdge)){
                    this.setM_Direction(direction);
                }
            }
            case "up" -> {
                leftEdge = getX() - 10;
                rightEdge = getX() + getWidth() + 10;
                topEdge = getY() - 15;
                if (!this.getM_Maze().hasObstacle(leftEdge, rightEdge, topEdge - 1, topEdge)){
                    this.setM_Direction(direction);
                }
            }
            case "left" -> {
                leftEdge = getX() - 15;
                bottomEdge = getY() + getHeight() + 10;
                topEdge = getY() - 10;
                if (!this.getM_Maze().hasObstacle(leftEdge - 1, leftEdge, topEdge, bottomEdge)){
                    this.setM_Direction(direction);
                }
            }
            case "right" -> {
                bottomEdge = getY() + getHeight() + 10;
                rightEdge = getX() + getWidth() + 15;
                topEdge = getY() - 10;
                if (!this.getM_Maze().hasObstacle(rightEdge - 1, rightEdge, topEdge, bottomEdge)){
                    this.setM_Direction(direction);
                }
            }
        }
    }

    private void determineMovement(String whereToGo, String whereToChangeTo,
                                   double leftEdge, double topEdge,
                                   double rightEdge, double bottomEdge,
                                   double padding) {
        double step = 5;
        switch (whereToGo) {
            case "left":
                if (!this.getM_Maze().isTouching(leftEdge, topEdge, padding)) {
                    setX(leftEdge - step);
                } else {
                    while (this.getM_Maze().isTouching(getX(), getY(), padding)) {
                        setX(getX() + 1);
                    }
                    this.setM_Direction(whereToChangeTo);
                }
                break;
            case "right":
                if (!this.getM_Maze().isTouching(rightEdge, topEdge, padding)) {
                    setX(leftEdge + step);
                } else {
                    while (this.getM_Maze().isTouching(getX() + getWidth(), getY(), padding)) {
                        setX(getX() - 1);
                    }
                    this.setM_Direction(whereToChangeTo);
                }
                break;
            case "up":
                if (!this.getM_Maze().isTouching(leftEdge, topEdge, padding)) {
                    setY(topEdge - step);
                } else {
                    while (this.getM_Maze().isTouching(getX(), getY(), padding)) {
                        setY(getY() + 1);
                    }
                    this.setM_Direction("left");
                }
                break;
            case "down":
                if (!this.getM_Maze().isTouching(leftEdge, bottomEdge, padding)) {
                    setY(topEdge + step);
                } else {
                    while (this.getM_Maze().isTouching(getX(), getY() + getHeight(), padding)) {
                        setY(getY() - 1);
                    }
                    this.setM_Direction("right");
                }
                break;
        }
    }

    public void CreateAnimation() {
        this.setM_Animation(new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                getM_Manager().checkGhostCollision();
                double leftEdge = getX();
                double topEdge = getY();
                double rightEdge = getX() + getWidth();
                double bottomEdge = getY() + getHeight();
                double padding = 12;
                setM_TimesWalked(getM_TimesWalked() + 1);
                int walkAtLeast = 4;
                switch (getM_Direction()) {
                    case "left":
                        determineMovement("left", "down",
                                leftEdge, topEdge, rightEdge, bottomEdge, padding);
                        if (getM_TimesWalked() > walkAtLeast) {
                            determinePath(getRandomDirection("left", "right"));
                            setM_TimesWalked(0);
                        }
                        break;
                    case "right":
                        determineMovement("right", "up",
                                leftEdge, topEdge, rightEdge, bottomEdge, padding);
                        if (getM_TimesWalked() > walkAtLeast) {
                            determinePath(getRandomDirection("left", "right"));
                             setM_TimesWalked(0);
                        }
                        break;
                    case "up":
                        determineMovement("up", "left",
                                leftEdge, topEdge, rightEdge, bottomEdge, padding);
                        if (getM_TimesWalked() > walkAtLeast) {
                            determinePath(getRandomDirection("up", "down"));
                            setM_TimesWalked(0);
                        }
                        break;
                    case "down":
                        determineMovement("down", "right",
                                leftEdge, topEdge, rightEdge, bottomEdge, padding);
                        if (getM_TimesWalked() > walkAtLeast) {
                            determinePath(getRandomDirection("up", "down"));
                            setM_TimesWalked(0);
                        }
                        break;
                }
            }
        });
    }

    @Override
    public void run() {
        this.getM_Animation().start();
    }
}
