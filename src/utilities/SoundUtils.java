package utilities;

import java.applet.Applet;
import java.applet.AudioClip;

/**
 * 
 * This class contains all sounds of the game.
 *
 */
public final class SoundUtils {

    /**
     * Sound of the Menu.
     */
    public static final AudioClip MAIN_THEME = Applet.newAudioClip(ClassLoader.getSystemResource("mainTheme.wav"));
    /**
     * Main sound of the Game.
     */
    public static final AudioClip GAMEPLAY_MUSIC = Applet.newAudioClip(ClassLoader.getSystemResource("low-fi.wav"));

   private SoundUtils() { };

    /**
     * Method to mute all the sounds in the game.
     */
    public static void muteAllSounds() {
        MAIN_THEME.stop();
        GAMEPLAY_MUSIC.stop();
    }
}
