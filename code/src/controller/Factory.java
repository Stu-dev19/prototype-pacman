package controller;

import boarder.Cookie;
import boarder.Maze;
import enemies.Ghost;
import javafx.scene.Group;
import javafx.scene.paint.Color;

public class Factory {

        private Maze m_maze;
        private Group m_root;

        public Factory(Maze maze, Group root){
            this.m_maze = maze;
            this.m_root = root;
        }

        public Ghost createGhost(double x, double y, Color color){
            return new Ghost(x, y, color, this.m_maze, this.m_root);
        }

        public Cookie createCookie(double x, double y){
            return new Cookie(x, y);
        }
}
