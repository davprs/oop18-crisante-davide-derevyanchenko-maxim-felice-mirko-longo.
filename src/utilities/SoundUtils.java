package utilities;

import java.applet.Applet;
import java.applet.AudioClip;

/**
 * 
 * this class contains all sounds of the game.
 *
 */
public final class SoundUtils {

    /**
     * 
     */
    public static final AudioClip MAIN_THEME = Applet.newAudioClip(ClassLoader.getSystemResource("mainTheme.wav"));
    /**
     * 
     */
    public static final AudioClip BUTTON_CLICKED = Applet.newAudioClip(ClassLoader.getSystemResource("buttonCliked.wav"));
    /**
     * 
     */
    public static final AudioClip GAMEPLAY_MUSIC = Applet.newAudioClip(ClassLoader.getSystemResource("low-fi.wav"));

   private SoundUtils() { };

    /**
     * Method to mute all the sounds in the game.
     */
    public static void muteAllSounds() {
        BUTTON_CLICKED.stop();
        MAIN_THEME.stop();
        GAMEPLAY_MUSIC.stop();
    }
}
