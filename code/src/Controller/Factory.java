package Controller;

import Boarder.Cookie;
import Boarder.Maze;
import Enemies.Ghost;
import java.awt.*;

public class Factory {

        private Maze m_maze;
        private Group m_root; // to be later imported in javafx

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
