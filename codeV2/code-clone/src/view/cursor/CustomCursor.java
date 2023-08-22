/**
 * CustomCursor.java <br>
 * Date created: 21 Aug 2023 <br> <br>
 * This class provide methods to create custom cursors for the game.
 *
 * @author Muhammad Sohail
 * @version 1.0
 */
package view.cursor;

import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import java.net.URISyntaxException;
import java.util.Objects;

/**
 * CustomCursor class provides methods to create a custom cursor for the game.
 * cursor image is loaded from the resources.
 *
 */
public class CustomCursor {

    /**
     * Creates and sets a custom cursor for the scene.
     *
     * @param cursorScene the scene where the custom cursor will be set.
     * @throws URISyntaxException if a URI syntax error occurs.
     */
    public void CreateCursor(Scene cursorScene) throws URISyntaxException {
        Image image = new Image(getClass().getResource("pacman-cursor.png").toURI().toString());
        cursorScene.setCursor(new ImageCursor(image));
    }
}
