package utilities;

import java.applet.Applet;
import java.applet.AudioClip;

import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Effect;

/**
 * 
 * This class contains all game utilities.
 *
 */
public final class GameUtils {

    private static final String SEPARATOR = System.getProperty("file.separator");
    private static final int BLUR_EFFECT_RANGE = 5;
    private static final Effect TRANSPARENT = new BoxBlur(0, 0, 0);
    private static final Effect BLUR = new BoxBlur(BLUR_EFFECT_RANGE, BLUR_EFFECT_RANGE, BLUR_EFFECT_RANGE);
    /**
     * Sound of the Menu.
     */
    public static final AudioClip MAIN_THEME = Applet.newAudioClip(ClassLoader.getSystemResource("sounds" + SEPARATOR + "mainTheme.wav"));
    /**
     * Main sound of the Game.
     */
    public static final AudioClip GAMEPLAY_MUSIC = Applet.newAudioClip(ClassLoader.getSystemResource("sounds" + SEPARATOR + "low-fi.wav"));

    private GameUtils() { };

    /**
     * Method to mute all the sounds in the game.
     */
    public static void muteAllSounds() {
        MAIN_THEME.stop();
        GAMEPLAY_MUSIC.stop();
    }

    /**
     * Get the transparent Effect.
     * @return the effect
     */
    public static Effect getTransparentEffect() {
        return TRANSPARENT;
    }

    /**
     * Get the blur Effect.
     * @return the effect
     */
    public static Effect getBlurEffect() {
        return BLUR;
    }
}
