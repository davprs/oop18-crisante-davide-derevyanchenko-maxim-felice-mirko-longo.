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
    public static final AudioClip MAIN_THEME = Applet.newAudioClip(SoundUtils.class.getResource("mainTheme.wav"));
    /**
     * 
     */
    public static final AudioClip BUTTON_CLIKED = Applet.newAudioClip(SoundUtils.class.getResource("buttonCliked.wav"));
    /**
     * 
     */
    public static final AudioClip GAMEPLAY_MUSIC = Applet.newAudioClip(SoundUtils.class.getResource("low-fi.wav"));

  // private final SoundUtils() { };

    /**
     * Method to mute all the sounds in the game.
     */
    public void muteAllSounds() {
        SoundUtils.BUTTON_CLIKED.stop();
        SoundUtils.GAMEPLAY_MUSIC.stop();
        SoundUtils.MAIN_THEME.stop();
    }
}
