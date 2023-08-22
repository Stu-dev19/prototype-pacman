package model;

/**
 * Manager.java <br>
 * Date created: 21 Aug 2023 <br> <br>
 *
 * The model package contains classes responsible for managing game data and logic.
 *
 * @author Muhammad Sohail
 * @version 1.0
 */

// import java classes
import controller.Factory;
import javafx.scene.paint.Paint;
import view.enemies.Ghost;
import view.player.Pacman;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.*;

// Class manager manages the whole system
// Classes need to be delegated to other classes
// MVC pattern needs to be added for organisation
/**
 * Manager class handles the entire game system which includes player movement, collisions, scoring,
 * and game state. Handles Pacman, ghosts, pellets, and game mechanics.
 * Manager follows a singleton pattern to ensure only one instance is created.
 */
public class Manager {

    // Member variables
    private final Pacman pacman;
    private final Group root;
    private final Set<Pellet> cookieSet;
    private final Set<Ghost> ghosts;
    private final AnimationTimer leftPacmanAnimation;
    private final AnimationTimer rightPacmanAnimation;
    private final AnimationTimer upPacmanAnimation;
    private final AnimationTimer downPacmanAnimation;
    private final Maze maze;
    private int lives;
    private int score;
    private Score scoreBoard;
    private boolean gameEnded;
    private int cookiesEaten;
    private static Manager instance;
    private final Factory factory;
    private HighScore highScore;
    private String playerName;
    boolean invincibleMode = true; // set the invincibility to false
    boolean keyChange = false; // set the i key change to false
    private Timer timer; // time for 5 second invincibility mode

    private Manager(Group root) { // changed constructor to private
        this.root = root;
        this.maze = new Maze();
        this.pacman = new Pacman(2.5 * Tiles.getMaxRectangleThickness(),
                2.5 * Tiles.getMaxRectangleThickness());
        this.cookieSet = new HashSet<>();
        this.ghosts = new HashSet<>();
        this.leftPacmanAnimation = this.createAnimation("left");
        this.rightPacmanAnimation = this.createAnimation("right");
        this.upPacmanAnimation = this.createAnimation("up");
        this.downPacmanAnimation = this.createAnimation("down");
        this.lives = 3;
        this.score = 0;
        this.cookiesEaten = 0;
        this.factory = new Factory(this.maze, root); // infer new factory
    }

    // Singleton for Manager object
    /**
     * Creates a single instance of the Manager class
     *
     * @param root root Group for displaying game elements.
     * @return Manager instance.
     */
    public static Manager GetInstance(Group root){
        if(instance == null){
            instance = new Manager(root);
        }
        return instance;
    }

    private void removeLife() {
        this.leftPacmanAnimation.stop();
        this.rightPacmanAnimation.stop();
        this.upPacmanAnimation.stop();
        this.downPacmanAnimation.stop();
        for (Ghost ghost : ghosts) {
            ghost.GetAnimation().stop();
        }
        this.pacman.setCenterX(2.5 * Tiles.getMaxRectangleThickness()); // return pacman to home
        this.pacman.setCenterY(2.5 * Tiles.getMaxRectangleThickness());
        lives -= 1; // reduce lives
        score -= 10; // reduce score
        this.scoreBoard.setM_lives(new Text(Tiles.getMaxRectangleThickness() * 20,
                Tiles.getMaxRectangleThickness() * 28,
                "Lives: " + this.lives), root); // changed from string object; added quotation
        this.scoreBoard.setM_score(new Text(Tiles.getMaxRectangleThickness() * 4,
                Tiles.getMaxRectangleThickness() * 28,
                "Score: " + this.score), root); // changed from string object
        if (lives == 0) {
            this.gameOver();
        }
    }

    // Used only once per game
    // During game play when you press the 'I' key
    // Pacman will change color for 10s
    // The ghosts will change color for 10s
    // When Pacman collides with any ghost they will return to their start position

    /**
     * Manages the "I" key press to activate invincible mode.
     * Invincible mode changes pacman and ghosts color and Pacman becomes invulnerable.
     *
     * @param event KeyEvent representing the key press.
     */
    public void InvincibleMode(KeyEvent event) {
        // When invincible mode is true
        if (event.getCode() == KeyCode.I && this.invincibleMode){ // When I pressed
            // Change the color of pacman
            this.pacman.InvinciblePacman();
            // Change the color of the ghosts
            for (Ghost ghost : ghosts) {
                ghost.ChangeGhostColor(Color.RED);
            }
            // Set invincible mode to true
            this.invincibleMode = true;
            this.keyChange = true;
            // Start the countdown timer
            this.timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    // After the timer revert pacman
                    // and the ghosts to original colors
                    pacman.setFill(Color.YELLOW);
                    for (Ghost ghost : ghosts) {
                        ghost.ChangeGhostColor(ghost.GetOriginalColor());
                    }
                    // set invincibility to false
                    invincibleMode = false;
                }
            }, 5000); // 5 seconds
        }
    }

    /**
     * Displays the player's name on game screen.
     *
     * @param playerName name of the player to be displayed.
     */
    public void DisplayPlayerName(String playerName){
        Text player = new Text("PLAYER: " + playerName); // removed whole javafx. palava
        this.playerName = playerName;
        player.setX(Tiles.getMaxRectangleThickness() * 40);
        player.setY(Tiles.getMaxRectangleThickness() * 30);
        player.setFont(Font.font("Arial", 20));
        player.setFill(Color.MEDIUMPURPLE);
        root.getChildren().add(player);
    }

    /**
     * Manages game termination conditions, displays the game over message, and shows the high score board.
     */
    private void gameOver() {
        this.gameEnded = true;
        root.getChildren().remove(pacman);
        for (Ghost ghost : ghosts) {
            root.getChildren().remove(ghost);
        }
        Text endGame = new Text("Game Over!, press ESC to restart"); // removed whole javafx. palava
        endGame.setX(Tiles.getMaxRectangleThickness() * 3);
        endGame.setY(Tiles.getMaxRectangleThickness() * 28);
        endGame.setFont(Font.font("Arial", 40));
        endGame.setFill(Color.ROYALBLUE);
        root.getChildren().remove(this.scoreBoard.getM_score());
        root.getChildren().remove(this.scoreBoard.getM_lives());
        root.getChildren().add(endGame);

        // Show the high score board
        highScore = new HighScore(this.playerName, this.score);
    }

    /**
     * Restarts the game when the ESC key is pressed.
     *
     * @param event KeyEvent representing the key press.
     */
    public void restartGame(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE && gameEnded) {
            root.getChildren().clear();
            this.cookieSet.clear();
            this.ghosts.clear();
            this.DrawMaze();
            this.pacman.setCenterX(2.5 * Tiles.getMaxRectangleThickness());
            this.pacman.setCenterY(2.5 * Tiles.getMaxRectangleThickness());
            this.lives = 3;
            this.score = 0;
            this.cookiesEaten = 0;
            gameEnded = false;
            this.invincibleMode = true;
        }
    }

    /**
     * Draws the maze, sets up pellets, ghosts and the game environment.
     */
    public void DrawMaze() {
        this.maze.CreateMaze(root);
        Integer skip[] = {5, 17};     // line 1
        for (int i = 0; i < 23; i++) {
            if (!Arrays.asList(skip).contains(i)) {
                Pellet cookie = this.factory.createCookie(((2*i) + 2.5)
                                * Tiles.getMaxRectangleThickness(),
                        2.5 * Tiles.getMaxRectangleThickness());
                this.cookieSet.add(cookie);
                root.getChildren().add(cookie);
            }
        }
        skip = new Integer[]{1, 2, 3, 5, 7, 8, 9, 10, 11,
                12, 13, 14, 15, 17, 19, 20, 21};        // line 2
        for (int i = 0; i < 23; i++) {
            if (!Arrays.asList(skip).contains(i)) {
                Pellet cookie = this.factory.createCookie(((2*i) + 2.5)
                                * Tiles.getMaxRectangleThickness(),
                        4.5 * Tiles.getMaxRectangleThickness());
                this.cookieSet.add(cookie);
                root.getChildren().add(cookie);
            }
        }
        skip = new Integer[]{1, 21};        // line 3
        for (int i = 0; i < 23; i++) {
            if (!Arrays.asList(skip).contains(i)) {
                Pellet cookie = this.factory.createCookie(((2*i) + 2.5)
                                * Tiles.getMaxRectangleThickness(),
                        6.5 * Tiles.getMaxRectangleThickness());
                this.cookieSet.add(cookie);
                root.getChildren().add(cookie);
            }
        }
        skip = new Integer[]{1, 3, 4, 5, 7, 8, 9, 10, 11,
                12, 13, 14, 15, 17, 18, 19, 21};        // line 4
        for (int i = 0; i < 23; i++) {
            if (!Arrays.asList(skip).contains(i)) {
                Pellet cookie = this.factory.createCookie(((2 * i) + 2.5)
                                * Tiles.getMaxRectangleThickness(),
                        8.5 * Tiles.getMaxRectangleThickness());
                this.cookieSet.add(cookie);
                root.getChildren().add(cookie);
            }
        }
        skip = new Integer[]{1, 7, 8, 9, 10, 11, 12, 13, 14, 15, 21};        // line 5
        for (int i = 0; i < 23; i++) {
            if (!Arrays.asList(skip).contains(i)) {
                Pellet cookie = this.factory.createCookie(((2*i) + 2.5)
                                * Tiles.getMaxRectangleThickness(),
                        10.5 * Tiles.getMaxRectangleThickness());
                this.cookieSet.add(cookie);
                root.getChildren().add(cookie);
            }
        }
        skip = new Integer[]{3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 18, 19};  // line 6
        for (int i = 0; i < 23; i++) {
            if (!Arrays.asList(skip).contains(i)) {
                Pellet cookie = this.factory.createCookie(((2*i) + 2.5)
                                * Tiles.getMaxRectangleThickness(),
                        12.5 * Tiles.getMaxRectangleThickness());
                this.cookieSet.add(cookie);
                root.getChildren().add(cookie);
            }
        }
        skip = new Integer[]{1, 7, 8, 9, 10, 11, 12, 13, 14, 15, 21};   // line 7
        for (int i = 0; i < 23; i++) {
            if (!Arrays.asList(skip).contains(i)) {
                Pellet cookie = this.factory.createCookie(((2 * i) + 2.5)
                                * Tiles.getMaxRectangleThickness(),
                        14.5 * Tiles.getMaxRectangleThickness());
                this.cookieSet.add(cookie);
                root.getChildren().add(cookie);
            }
        }
        skip = new Integer[]{1, 3, 4, 5, 7, 8, 9, 10,
                11, 12, 13, 14, 15, 17, 18, 19, 21};        // line 8
        for (int i = 0; i < 23; i++) {
            if (!Arrays.asList(skip).contains(i)) {
                Pellet cookie = this.factory.createCookie(((2 * i) + 2.5)
                                * Tiles.getMaxRectangleThickness(),
                        16.5 * Tiles.getMaxRectangleThickness());
                this.cookieSet.add(cookie);
                root.getChildren().add(cookie);
            }
        }
        skip = new Integer[]{1, 21};        // line 9
        for (int i = 0; i < 23; i++) {
            if (!Arrays.asList(skip).contains(i)) {
                Pellet cookie = this.factory.createCookie(((2 * i) + 2.5)
                                * Tiles.getMaxRectangleThickness(),
                        18.5 * Tiles.getMaxRectangleThickness());
                this.cookieSet.add(cookie);
                root.getChildren().add(cookie);
            }
        }
        skip = new Integer[]{1, 2, 3, 5, 7, 8, 9, 10,
                11, 12, 13, 14, 15, 17, 19, 20, 21};      // line 10
        for (int i = 0; i < 23; i++) {
            if (!Arrays.asList(skip).contains(i)) {
                Pellet cookie = this.factory.createCookie(((2*i) + 2.5)
                                * Tiles.getMaxRectangleThickness(),
                        20.5 * Tiles.getMaxRectangleThickness());
                this.cookieSet.add(cookie);
                root.getChildren().add(cookie);
            }
        }
        skip = new Integer[]{5, 17};        // line 11
        for (int i = 0; i < 23; i++) {
            if (!Arrays.asList(skip).contains(i)) {
                Pellet cookie = this.factory.createCookie(((2 * i) + 2.5)
                                * Tiles.getMaxRectangleThickness(),
                        22.5 * Tiles.getMaxRectangleThickness());
                this.cookieSet.add(cookie);
                root.getChildren().add(cookie);
            }
        }
        root.getChildren().add(this.pacman);
        this.GenerateGhosts();
        root.getChildren().addAll(this.ghosts);
        this.scoreBoard = new Score(root); // call constructor of score
    }

    // Create for loop for ghost and cookies with an array for the colors and x variables

    /**
     * Generates ghosts at predefined positions
     */
    public void GenerateGhosts() { // Changed colors for the ghosts and added factory methods
        this.ghosts.add(this.factory.createGhost(18.5 *
                        Tiles.getMaxRectangleThickness(),
                12.5 * Tiles.getMaxRectangleThickness(),
                Color.PINK)); // From singleton method added root to parameters
        this.ghosts.add(this.factory.createGhost(22.5 *
                        Tiles.getMaxRectangleThickness(),
                12.5 * Tiles.getMaxRectangleThickness(),
                Color.YELLOW));
        this.ghosts.add(this.factory.createGhost(28.5 *
                        Tiles.getMaxRectangleThickness(),
                12.5 * Tiles.getMaxRectangleThickness(),
                Color.BLACK));
        this.ghosts.add(this.factory.createGhost(28.5 *
                        Tiles.getMaxRectangleThickness(),
                9.5 * Tiles.getMaxRectangleThickness(),
                Color.GREEN));
    }

    /**
     * Manages Pacman movement based on the KeyEvent provided.
     *
     * @param event KeyEvent representing the key press.
     */
    public void MovePacman(KeyEvent event) {
        for (Ghost ghost : this.ghosts) {
            ghost.run();
        }
        switch(event.getCode()) {
            case RIGHT:
                this.rightPacmanAnimation.start();
                break;
            case LEFT:
                this.leftPacmanAnimation.start();
                break;
            case UP:
                this.upPacmanAnimation.start();
                break;
            case DOWN:
                this.downPacmanAnimation.start();
                break;
        }
    }

    /**
     * Stops Pacman's movement animation based on KeyEvent provided.
     *
     * @param event KeyEvent representing the key release.
     */
    public void StopPacman(KeyEvent event) {
        switch(event.getCode()) {
            case RIGHT:
                this.rightPacmanAnimation.stop();
                break;
            case LEFT:
                this.leftPacmanAnimation.stop();
                break;
            case UP:
                this.upPacmanAnimation.stop();
                break;
            case DOWN:
                this.downPacmanAnimation.stop();
                break;
        }
    }

    /**
     * Creates an AnimationTimer for Pacman's movement in the specified direction.
     *
     * @param direction direction of movement ("left," "right," "up," or "down").
     * @return AnimationTimer for Pacman's movement.
     */
    private AnimationTimer createAnimation(String direction) {
        double step = 5;
        return new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
            switch (direction) {
                case "left":
                    if (!maze.isTouching(pacman.getCenterX() - pacman.getRadius(), pacman.getCenterY(), 15)) {
                        pacman.setCenterX(pacman.getCenterX() - step);
                        checkPelletCollision(pacman, "x");
                        checkGhostCollision();
                    }
                    break;
                case "right":
                    if (!maze.isTouching(pacman.getCenterX() + pacman.getRadius(), pacman.getCenterY(), 15)) {
                        pacman.setCenterX(pacman.getCenterX() + step);
                        checkPelletCollision(pacman, "x");
                        checkGhostCollision();
                    }
                    break;
                case "up":
                    if (!maze.isTouching(pacman.getCenterX(), pacman.getCenterY() - pacman.getRadius(), 15)) {
                        pacman.setCenterY(pacman.getCenterY() - step);
                        checkPelletCollision(pacman, "y");
                        checkGhostCollision();
                    }
                    break;
                case "down":
                   if (!maze.isTouching(pacman.getCenterX(), pacman.getCenterY() + pacman.getRadius(), 15)) {
                       pacman.setCenterY(pacman.getCenterY() + step);
                       checkPelletCollision(pacman, "y");
                       checkGhostCollision();
                   }
                   break;
            }
            }
        };
    }

    /**
     * Checks for collisions between Pacman and pellets along the axis.
     *
     * @param pacman Pacman instance.
     * @param axis axis along which collisions are checked ("x" or "y").
     */
    private void checkPelletCollision(Pacman pacman, String axis) {
        double pacmanCenterY = pacman.getCenterY();
        double pacmanCenterX = pacman.getCenterX();
        double pacmanLeftEdge = pacmanCenterX - pacman.getRadius();
        double pacmanRightEdge = pacmanCenterX + pacman.getRadius();
        double pacmanTopEdge = pacmanCenterY - pacman.getRadius();
        double pacmanBottomEdge = pacmanCenterY + pacman.getRadius();

        for (Pellet cookie : cookieSet) {
            double cookieCenterX = cookie.getCenterX();
            double cookieCenterY = cookie.getCenterY();
            double cookieLeftEdge = cookieCenterX - cookie.getRadius();
            double cookieRightEdge = cookieCenterX + cookie.getRadius();
            double cookieTopEdge = cookieCenterY - cookie.getRadius();
            double cookieBottomEdge = cookieCenterY + cookie.getRadius();
            if (axis.equals("x")) {
                // pacman goes right
                if ((cookieCenterY >= pacmanTopEdge && cookieCenterY <= pacmanBottomEdge)
                        && (pacmanRightEdge >= cookieLeftEdge && pacmanRightEdge <= cookieRightEdge)) {
                    if (cookie.isVisible()) {
                        this.score += cookie.getValue();
                        this.cookiesEaten++;
                    }
                    cookie.hide();
                }
                // pacman goes left
                if ((cookieCenterY >= pacmanTopEdge && cookieCenterY <= pacmanBottomEdge)
                        && (pacmanLeftEdge >= cookieLeftEdge && pacmanLeftEdge <=cookieRightEdge)){
                    if (cookie.isVisible()) {
                        this.score += cookie.getValue();
                        this.cookiesEaten++;
                    }
                    cookie.hide();
                }
            } else {
                // pacman goes up
                if ((cookieCenterX >= pacmanLeftEdge && cookieCenterX <= pacmanRightEdge)
                        && (pacmanBottomEdge >= cookieTopEdge && pacmanBottomEdge <=cookieBottomEdge)){
                    if (cookie.isVisible()) {
                        this.score += cookie.getValue();
                        this.cookiesEaten++;
                    }
                    cookie.hide();
                }
                // pacman goes down
                if ((cookieCenterX >= pacmanLeftEdge && cookieCenterX <= pacmanRightEdge
                ) && (pacmanTopEdge <= cookieBottomEdge && pacmanTopEdge >=cookieTopEdge)){
                    if (cookie.isVisible()) {
                        this.score += cookie.getValue();
                        this.cookiesEaten++;
                    }
                    cookie.hide();
                }
            }
            this.scoreBoard.setM_score(new Text(Tiles.getMaxRectangleThickness() * 4,
                    Tiles.getMaxRectangleThickness() * 28,
                    "Score: " + this.score), root);
            if (this.cookiesEaten == this.cookieSet.size()) {
                this.gameOver();
            }
        }
    }

    /**
     * Checks and handles collisions between Pacman and ghosts based on game rules
     */
    public void checkGhostCollision() {
        double pacmanCenterY = pacman.getCenterY();
        double pacmanCenterX = pacman.getCenterX();
        double pacmanLeftEdge = pacmanCenterX - pacman.getRadius();
        double pacmanRightEdge = pacmanCenterX + pacman.getRadius();
        double pacmanTopEdge = pacmanCenterY - pacman.getRadius();
        double pacmanBottomEdge = pacmanCenterY + pacman.getRadius();

        for (Ghost ghost : ghosts) {
            double ghostLeftEdge = ghost.getX();
            double ghostRightEdge = ghost.getX() + ghost.getWidth();
            double ghostTopEdge = ghost.getY();
            double ghostBottomEdge = ghost.getY() + ghost.getHeight();
            if ((pacmanLeftEdge <= ghostRightEdge && pacmanLeftEdge >= ghostLeftEdge) ||
                    (pacmanRightEdge >= ghostLeftEdge && pacmanRightEdge <= ghostRightEdge)) {
                if ((pacmanTopEdge <= ghostBottomEdge && pacmanTopEdge >= ghostTopEdge) ||
                        (pacmanBottomEdge >= ghostTopEdge && pacmanBottomEdge <= ghostBottomEdge)) {
                    // When invincibility mode is on
                    // Return the ghost that was touched back to its position
                    if (this.invincibleMode && keyChange){
                        // Save the ghost
                        Ghost newGhost = ghost;
                        // Remove the ghost from its position
                        root.getChildren().remove(ghost);
                        // Add it to the original position
                        newGhost.setX(22.5 * Tiles.getMaxRectangleThickness());
                        newGhost.setY(12.5 * Tiles.getMaxRectangleThickness());
                        root.getChildren().add(newGhost);
                        // Add 20 bonus points
                        this.score += 20;
                    }
                    else{
                        removeLife();
                    }
                }
            }
        }
    }
}
