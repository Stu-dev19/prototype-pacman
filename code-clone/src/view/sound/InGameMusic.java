/**
 * InGameMusic.java <br>
 * Date created: 21 Aug 2023 <br> <br>
 * This class provide methods to create custom cursors for the game.
 *
 * @author Muhammad Sohail
 * @version 1.0
 */
package view.sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Objects;

/**
 * InGameMusic class manages in-game music and sound effects.
 * Methods to play Pacman's background music and pellet collection sound effect.
 */
public class InGameMusic {

    private final double VOLUME = 0.5;
    private MediaPlayer m_MusicMediaPlayer;

    /**
     * Plays the Pacman background music.
     *
     * @throws URISyntaxException if a URI syntax error occurs.
     */
    public void PacmanMusic() throws URISyntaxException {

        String path = getClass().getResource("pacman_music.mp3").toURI().toString();
        Media mediaPlayer = new Media(path);
        m_MusicMediaPlayer = new MediaPlayer(mediaPlayer);

        //Control of volume
        m_MusicMediaPlayer.play();
        m_MusicMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        m_MusicMediaPlayer.setVolume(VOLUME);
    }

    /**
     * Plays the pellet collection sound effect.
     *
     * @throws URISyntaxException if a URI syntax error occurs.
     */
    public void PelletSoundEffect() throws URISyntaxException {
        String fileName = getClass().getResource("pacman_move.mp3").toURI().toString();
        String path = Objects.requireNonNull(getClass().getResource(fileName)).getPath();
        Media media = new Media(new File(path).toURI().toString());
        m_MusicMediaPlayer = new MediaPlayer(media);
        m_MusicMediaPlayer.play();
    }
}
