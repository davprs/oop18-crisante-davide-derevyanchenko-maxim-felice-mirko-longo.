package model.sounds;
import java.applet.Applet;
import java.applet.AudioClip;

/**
 * 
 * this class contains all sounds of the game.
 *
 */

public final class Sound {

    /**
     * 
     */
    public static final AudioClip MAIN_THEME = Applet.newAudioClip(Sound.class.getResource("mainTheme.wav"));
    /**
     * 
     */
    public static final AudioClip BUTTON_CLIKED = Applet.newAudioClip(Sound.class.getResource("buttonCliked.wav"));
    /**
     * 
     */
    public static final AudioClip GAMEPLAY_MUSIC = Applet.newAudioClip(Sound.class.getResource("low-fi.wav"));

    private Sound() { };


}
