/**
 * Factory.java <br>
 * Date created: 21 Aug 2023 <br> <br>
 *
 * The controller package which contains classes responsible for controlling game elements and interactions.
 *
 * @author Muhammad Sohail
 * @version 1.0
 */

package controller;

import model.Pellet;
import model.Maze;
import view.enemies.Ghost;
import javafx.scene.Group;
import javafx.scene.paint.Color;


/**
 * Factory class which creates game elements such as ghosts and pellets.
 */
public class Factory {

        private Maze m_maze;
        private Group m_root;

        /**
         * Constructs a Factory instance with the maze and root group.
         *
         * @param maze instance representing the game maze.
         * @param root JavaFX group where game entities will be added.
         */

        public Factory(Maze maze, Group root){
            this.m_maze = maze;
            this.m_root = root;
        }

        /**
         * Creation of Ghost entity with specified position and color.
         *
         * @param x     x-coordinate of the Ghost's position.
         * @param y     y-coordinate of the Ghost's position.
         * @param color color of the Ghost.
         * @return A new Ghost instance with the specified properties.
         */

        public Ghost createGhost(double x, double y, Color color){
            return new Ghost(x, y, color, this.m_maze, this.m_root);
        }

        /**
         * Creates a Pellet (cookie) entity
         *
         * @param x x-coordinate of the Pellet's position.
         * @param y y-coordinate of the Pellet's position.
         * @return A new Pellet instance
         */

        public Pellet createCookie(double x, double y){
            return new Pellet(x, y);
        }
}
