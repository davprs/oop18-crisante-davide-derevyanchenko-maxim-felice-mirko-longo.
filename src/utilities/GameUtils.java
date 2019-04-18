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

    private static final int BLUR_EFFECT_RANGE = 5;
    private static final Effect TRANSPARENT = new BoxBlur(0, 0, 0);
    private static final Effect BLUR = new BoxBlur(BLUR_EFFECT_RANGE, BLUR_EFFECT_RANGE, BLUR_EFFECT_RANGE);
    private static final AudioClip MAIN_THEME = Applet.newAudioClip(ClassLoader.getSystemResource("mainTheme.wav"));
    private static final AudioClip GAMEPLAY_MUSIC = Applet.newAudioClip(ClassLoader.getSystemResource("survival.wav"));

    private GameUtils() { };

    /**
     * Method to mute all the sounds in the game.
     */
    public static void muteAllSounds() {
        MAIN_THEME.stop();
        GAMEPLAY_MUSIC.stop();
    }

    /**
     * Get the sound of the Menu.
     * @return the sound of menu
     */
    public static AudioClip getMainTheme() {
        return MAIN_THEME;
    }

    /**
     * Get the main sound of the Game.
     * @return the sound of the game
     */
    public static AudioClip getGameplayMusic() {
        return GAMEPLAY_MUSIC;
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

    /**
     * Method that transforms a value depending on the level of the entity.
     * 
     * @param value the value to be transformed
     * @param level the level to be used in transformation
     * @return the modified value
     */
    public static int transform(final int value, final int level) {
        return level * value;
    }

}
