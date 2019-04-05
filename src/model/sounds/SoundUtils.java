package model.sounds;
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
    public static final AudioClip MAIN_THEME = Applet.newAudioClip(SoundUtils.class.getResource("/mainTheme.wav"));
    /**
     * 
     */
    public static final AudioClip BUTTON_CLICKED = Applet.newAudioClip(SoundUtils.class.getResource("/buttonCliked.wav"));
    /**
     * 
     */
    public static final AudioClip GAMEPLAY_MUSIC = Applet.newAudioClip(SoundUtils.class.getResource("/low-fi.wav"));

  // private final SoundUtils() { };
    //public final AudioClip BUTTON_CLIKED1 = Applet.newAudioClip(SoundUtils.class.getResource("buttonCliked.wav"));
    /**
     * Method to mute all the sounds in the game.
     */
    public static void muteAllSounds() {
        BUTTON_CLICKED.stop();
        MAIN_THEME.stop();
        GAMEPLAY_MUSIC.stop();
    }
}
