package utilities;

import java.util.Arrays;
import java.util.List;
import javafx.scene.image.Image;
/**
 * 
 * This class has the scope to return the image related to the entity level.
 */
public final class EntitiesImageUtils {

    private static final String SEPARATOR = System.getProperty("file.separator");

    private static final List<Image> BULLET_IMAGES = Arrays.asList(
            new Image("res" + SEPARATOR + "bullet.png"));
    private static final List<Image> METEOR_IMAGES = Arrays.asList(
            new Image("res" + SEPARATOR + "meteor.png"));
    private static final List<Image> ENEMYSHIP_IMAGES = Arrays.asList(
            new Image("res" + SEPARATOR + "spaceship.png"));

    private EntitiesImageUtils() { }

    /**
     * 
     * @param level the level of the calling Bullet.
     * @return the image to print.
     */
    public static Image getBulletImage(final int level) {
        return BULLET_IMAGES.get(level - 1);
    }

    /**
     * 
     * @param level the level of the calling Meteor.
     * @return the image to print.
     */
    public static Image getMeteorImage(final int level) {
        return METEOR_IMAGES.get(level - 1);
    }

    /**
     * 
     * @param level the level of the calling EnemyShip.
     * @return the image to print.
     */
    public static Image getEnemyShipImage(final int level) {
        return ENEMYSHIP_IMAGES.get(level - 1);
    }
}
