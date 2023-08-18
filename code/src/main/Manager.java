package main;



import boarder.Pellet;
import boarder.Maze;
import boarder.Obstacle;
import controller.Factory;
import enemies.Ghost;
import boarder.Score;
import player.Pacman;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Manager {

    // Member variables
    private Pacman pacman;
    private Group root;
    private Set<Pellet> cookieSet;
    private Set<Ghost> ghosts;
    private AnimationTimer leftPacmanAnimation;
    private AnimationTimer rightPacmanAnimation;
    private AnimationTimer upPacmanAnimation;
    private AnimationTimer downPacmanAnimation;
    private Maze maze;
    private int lifes;
    private int score;
    private Score scoreBoard;
    private boolean gameEnded;
    private int cookiesEaten;
    private static Manager instance;
    private Factory factory;

    private Manager(Group root) { // changed constructor to private
        this.root = root;
        this.maze = new Maze();
        this.pacman = new Pacman(2.5 * Obstacle.getMaxRectangleThickness(), 2.5 * Obstacle.getMaxRectangleThickness());
        this.cookieSet = new HashSet<>();
        this.ghosts = new HashSet<>();
        this.leftPacmanAnimation = this.createAnimation("left");
        this.rightPacmanAnimation = this.createAnimation("right");
        this.upPacmanAnimation = this.createAnimation("up");
        this.downPacmanAnimation = this.createAnimation("down");
        this.lifes = 3;
        this.score = 0;
        this.cookiesEaten = 0;
        this.factory = new Factory(this.maze, root); // infer new factory
    }

    // Singleton for Manager object
    public static Manager getInstance(Group root){
        if(instance == null){
            instance = new Manager(root);
        }
        return instance;
    }

    private void lifeGone() {
        this.leftPacmanAnimation.stop();
        this.rightPacmanAnimation.stop();
        this.upPacmanAnimation.stop();
        this.downPacmanAnimation.stop();
        for (Ghost ghost : ghosts) {
            ghost.GetAnimation().stop();
        }
        this.pacman.setCenterX(2.5 * Obstacle.getMaxRectangleThickness());
        this.pacman.setCenterY(2.5 * Obstacle.getMaxRectangleThickness());
        lifes--;
        score -= 10;
        this.scoreBoard.setM_lives(new Text(Obstacle.getMaxRectangleThickness() * 20,
                Obstacle.getMaxRectangleThickness() * 28,
                "Life's: " + this.lifes)); // changed from string object; added quotation
        this.scoreBoard.setM_score(new Text(Obstacle.getMaxRectangleThickness() * 4,
                Obstacle.getMaxRectangleThickness() * 28,
                "Score: " + this.score)); // changed from string object
        if (lifes == 0) {
            this.gameOver();
        }
    }

    private void gameOver() {
        this.gameEnded = true;
        root.getChildren().remove(pacman);
        for (Ghost ghost : ghosts) {
            root.getChildren().remove(ghost);
        }
        javafx.scene.text.Text endGame = new javafx.scene.text.Text("Game Over, press ESC to restart");
        endGame.setX(Obstacle.getMaxRectangleThickness() * 3);
        endGame.setY(Obstacle.getMaxRectangleThickness() * 28);
        endGame.setFont(Font.font("Arial", 40));
        endGame.setFill(Color.ROYALBLUE);
        root.getChildren().remove(this.scoreBoard.getM_score());
        root.getChildren().remove(this.scoreBoard.getM_lives());
        root.getChildren().add(endGame);
    }

    public void restartGame(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE && gameEnded) {
            root.getChildren().clear();
            this.cookieSet.clear();
            this.ghosts.clear();
            this.drawMaze();
            this.pacman.setCenterX(2.5 * Obstacle.getMaxRectangleThickness());
            this.pacman.setCenterY(2.5 * Obstacle.getMaxRectangleThickness());
            this.lifes = 3;
            this.score = 0;
            this.cookiesEaten = 0;
            gameEnded = false;
        }
    }

    public void drawMaze() {
        this.maze.CreateMaze(root);
        // 1st line
        Integer skip[] = {5, 17};
        for (int i = 0; i < 23; i++) {
            if (!Arrays.asList(skip).contains(i)) {
                Pellet cookie = this.factory.createCookie(((2*i) + 2.5) * Obstacle.getMaxRectangleThickness(),
                        2.5 * Obstacle.getMaxRectangleThickness());
                this.cookieSet.add(cookie);
                root.getChildren().add(cookie);
            }
        }
        // line 1
        skip = new Integer[]{1, 2, 3, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 19, 20, 21};
        for (int i = 0; i < 23; i++) {
            if (!Arrays.asList(skip).contains(i)) {
                Pellet cookie = this.factory.createCookie(((2*i) + 2.5) * Obstacle.getMaxRectangleThickness(),
                        4.5 * Obstacle.getMaxRectangleThickness());
                this.cookieSet.add(cookie);
                root.getChildren().add(cookie);
            }
        }
        // line 2
        skip = new Integer[]{1, 21};
        for (int i = 0; i < 23; i++) {
            if (!Arrays.asList(skip).contains(i)) {
                Pellet cookie = this.factory.createCookie(((2*i) + 2.5) * Obstacle.getMaxRectangleThickness(),
                        6.5 * Obstacle.getMaxRectangleThickness());
                this.cookieSet.add(cookie);
                root.getChildren().add(cookie);
            }
        }
        // line 3
        skip = new Integer[]{1, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 18, 19, 21};
        for (int i = 0; i < 23; i++) {
            if (!Arrays.asList(skip).contains(i)) {
                Pellet cookie = this.factory.createCookie(((2 * i) + 2.5) * Obstacle.getMaxRectangleThickness(),
                        8.5 * Obstacle.getMaxRectangleThickness());
                this.cookieSet.add(cookie);
                root.getChildren().add(cookie);
            }
        }
        // line 4
        skip = new Integer[]{1, 7, 8, 9, 10, 11, 12, 13, 14, 15, 21};
        for (int i = 0; i < 23; i++) {
            if (!Arrays.asList(skip).contains(i)) {
                Pellet cookie = this.factory.createCookie(((2*i) + 2.5) * Obstacle.getMaxRectangleThickness(),
                        10.5 * Obstacle.getMaxRectangleThickness());
                this.cookieSet.add(cookie);
                root.getChildren().add(cookie);
            }
        }
        // line 5
        skip = new Integer[]{3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 18, 19};
        for (int i = 0; i < 23; i++) {
            if (!Arrays.asList(skip).contains(i)) {
                Pellet cookie = this.factory.createCookie(((2*i) + 2.5) * Obstacle.getMaxRectangleThickness(),
                        12.5 * Obstacle.getMaxRectangleThickness());
                this.cookieSet.add(cookie);
                root.getChildren().add(cookie);
            }
        }
        // line 6
        skip = new Integer[]{1, 7, 8, 9, 10, 11, 12, 13, 14, 15, 21};
        for (int i = 0; i < 23; i++) {
            if (!Arrays.asList(skip).contains(i)) {
                Pellet cookie = this.factory.createCookie(((2 * i) + 2.5) * Obstacle.getMaxRectangleThickness(),
                        14.5 * Obstacle.getMaxRectangleThickness());
                this.cookieSet.add(cookie);
                root.getChildren().add(cookie);
            }
        }
        // line 7
        skip = new Integer[]{1, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 18, 19, 21};
        for (int i = 0; i < 23; i++) {
            if (!Arrays.asList(skip).contains(i)) {
                Pellet cookie = this.factory.createCookie(((2 * i) + 2.5) * Obstacle.getMaxRectangleThickness(),
                        16.5 * Obstacle.getMaxRectangleThickness());
                this.cookieSet.add(cookie);
                root.getChildren().add(cookie);
            }
        }
        // line 8
        skip = new Integer[]{1, 21};
        for (int i = 0; i < 23; i++) {
            if (!Arrays.asList(skip).contains(i)) {
                Pellet cookie = this.factory.createCookie(((2 * i) + 2.5) * Obstacle.getMaxRectangleThickness(),
                        18.5 * Obstacle.getMaxRectangleThickness());
                this.cookieSet.add(cookie);
                root.getChildren().add(cookie);
            }
        }
        // line 9
        skip = new Integer[]{1, 2, 3, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 19, 20, 21};
        for (int i = 0; i < 23; i++) {
            if (!Arrays.asList(skip).contains(i)) {
                Pellet cookie = this.factory.createCookie(((2*i) + 2.5) * Obstacle.getMaxRectangleThickness(),
                        20.5 * Obstacle.getMaxRectangleThickness());
                this.cookieSet.add(cookie);
                root.getChildren().add(cookie);
            }
        }
        // line 10
        skip = new Integer[]{5, 17};
        for (int i = 0; i < 23; i++) {
            if (!Arrays.asList(skip).contains(i)) {
                Pellet cookie = this.factory.createCookie(((2 * i) + 2.5) * Obstacle.getMaxRectangleThickness(),
                        22.5 * Obstacle.getMaxRectangleThickness());
                this.cookieSet.add(cookie);
                root.getChildren().add(cookie);
            }
        }
        root.getChildren().add(this.pacman);
        this.generateGhosts();
        root.getChildren().addAll(this.ghosts);
        this.scoreBoard = new Score(root);
    }

    // Create for loop for ghost and cookies with an array for the colors and x variables
    public void generateGhosts() { // Changed colors for the ghosts and added factory methods
        this.ghosts.add(this.factory.createGhost(18.5 * Obstacle.getMaxRectangleThickness(),
                12.5 * Obstacle.getMaxRectangleThickness(),
                Color.PINK)); // From singleton method added root to parameters
        this.ghosts.add(this.factory.createGhost(22.5 * Obstacle.getMaxRectangleThickness(),
                12.5 * Obstacle.getMaxRectangleThickness(),
                Color.YELLOW));
        this.ghosts.add(this.factory.createGhost(28.5 * Obstacle.getMaxRectangleThickness(),
                12.5 * Obstacle.getMaxRectangleThickness(),
                Color.BLACK));
        this.ghosts.add(this.factory.createGhost(28.5 * Obstacle.getMaxRectangleThickness(),
                9.5 * Obstacle.getMaxRectangleThickness(),
                Color.GREEN));
    }

    public void movePacman(KeyEvent event) {
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

    public void stopPacman(KeyEvent event) {
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
                        checkCokieCoalition(pacman, "x");
                        checkGhostCoalition();
                    }
                    break;
                case "right":
                    if (!maze.isTouching(pacman.getCenterX() + pacman.getRadius(), pacman.getCenterY(), 15)) {
                        pacman.setCenterX(pacman.getCenterX() + step);
                        checkCokieCoalition(pacman, "x");
                        checkGhostCoalition();
                    }
                    break;
                case "up":
                    if (!maze.isTouching(pacman.getCenterX(), pacman.getCenterY() - pacman.getRadius(), 15)) {
                        pacman.setCenterY(pacman.getCenterY() - step);
                        checkCokieCoalition(pacman, "y");
                        checkGhostCoalition();
                    }
                    break;
                case "down":
                   if (!maze.isTouching(pacman.getCenterX(), pacman.getCenterY() + pacman.getRadius(), 15)) {
                       pacman.setCenterY(pacman.getCenterY() + step);
                       checkCokieCoalition(pacman, "y");
                       checkGhostCoalition();
                   }
                   break;
            }
            }
        };
    }

    private void checkCokieCoalition(Pacman pacman, String axis) {
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
                if ((cookieCenterY >= pacmanTopEdge && cookieCenterY <= pacmanBottomEdge) && (pacmanRightEdge >= cookieLeftEdge && pacmanRightEdge <= cookieRightEdge)) {
                    if (cookie.isVisible()) {
                        this.score += cookie.getValue();
                        this.cookiesEaten++;
                    }
                    cookie.hide();
                }
                // pacman goes left
                if ((cookieCenterY >= pacmanTopEdge && cookieCenterY <= pacmanBottomEdge) && (pacmanLeftEdge >= cookieLeftEdge && pacmanLeftEdge <= cookieRightEdge)) {
                    if (cookie.isVisible()) {
                        this.score += cookie.getValue();
                        this.cookiesEaten++;
                    }
                    cookie.hide();
                }
            } else {
                // pacman goes up
                if ((cookieCenterX >= pacmanLeftEdge && cookieCenterX <= pacmanRightEdge) && (pacmanBottomEdge >= cookieTopEdge && pacmanBottomEdge <= cookieBottomEdge)) {
                    if (cookie.isVisible()) {
                        this.score += cookie.getValue();
                        this.cookiesEaten++;
                    }
                    cookie.hide();
                }
                // pacman goes down
                if ((cookieCenterX >= pacmanLeftEdge && cookieCenterX <= pacmanRightEdge) && (pacmanTopEdge <= cookieBottomEdge && pacmanTopEdge >= cookieTopEdge)) {
                    if (cookie.isVisible()) {
                        this.score += cookie.getValue();
                        this.cookiesEaten++;
                    }
                    cookie.hide();
                }
            }
            this.scoreBoard.setM_score(new Text(Obstacle.getMaxRectangleThickness() * 4,
                    Obstacle.getMaxRectangleThickness() * 28,
                    "Score: " + this.score));
            if (this.cookiesEaten == this.cookieSet.size()) {
                this.gameOver();
            }
        }
    }

    public void checkGhostCoalition() {
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
            if ((pacmanLeftEdge <= ghostRightEdge && pacmanLeftEdge >= ghostLeftEdge) || (pacmanRightEdge >= ghostLeftEdge && pacmanRightEdge <= ghostRightEdge)) {
                if ((pacmanTopEdge <= ghostBottomEdge && pacmanTopEdge >= ghostTopEdge) || (pacmanBottomEdge >= ghostTopEdge && pacmanBottomEdge <= ghostBottomEdge)) {
                    lifeGone();
                }
            }
        }
    }

}
