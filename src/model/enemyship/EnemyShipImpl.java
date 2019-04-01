package model.enemyship;

import java.awt.Toolkit;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Entity;
import model.Life;
import model.LifeImpl;
import model.bullet.Bullet;
import model.bullet.BulletImpl;

/**
 * Implementation of EnemyShip interface.
 *
 */
public class EnemyShipImpl implements EnemyShip {

    private static final int DELAY = (int) (Math.random() * 800 + 200);
    private static final Image BULLET_IMAGE = new Image("res" + System.getProperty("file.separator") + "images" + System.getProperty("file.separator") + "bullet.png", 30, 30, true, true);
    private final ImageView image;
    private Point2D position;
    private final Dimension2D dimension;
    private final double speed;
    private int framesFromShoot;
    private int level;
    private int framesToShoot;
    private boolean alive;
    private final Life life;
    private boolean shootingAvailable;

    /**
     * Build a new EnemyShip.
     * @param image is the image of the ship
     * @param level is the level (fastness, power of bullets)
     * @param timeToShoot frames passing from one bullet-shot to the next one
     */
    public EnemyShipImpl(final Image image, final int level, final int timeToShoot) {
        this.level = level;
        this.alive = true;
        this.life = LifeImpl.createDefaultLife();
        this.speed = level;
        this.framesToShoot = timeToShoot;
        this.image = new ImageView(image);
        this.dimension = new Dimension2D(image.getWidth(), image.getHeight());
        this.position = new Point2D(Math.random() * Toolkit.getDefaultToolkit().getScreenSize().getWidth(), Math.random() * Toolkit.getDefaultToolkit().getScreenSize().getWidth());
    }

    /**
     * Build a customizable level Bullet.
     * @param image is the image of the ship
     * @param level is the level (fastness, power of bullets)
     */
    public EnemyShipImpl(final Image image, final int level) {
        this(image, level, DELAY);
    }

    /**
     * Build a simple EnemyShip.
     * @param image is the image of the ship
     */
    public EnemyShipImpl(final Image image) {
        this(image, 1);
    }

    /**
     * Get the difficulty level.
     * @return the level.
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * Return true if the EnemyShip can shoot.
     * @return true if the EnemyShip can shoot.
     */
    public boolean canShoot() {
        return this.shootingAvailable;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Rectangle2D getBoundary() {
        return new Rectangle2D(position.getX(), position.getY(), dimension.getWidth(), dimension.getHeight());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean intersects(final Entity entity) {
        return entity.getBoundary().intersects(this.getBoundary());
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public Bullet shoot(final Point2D target) {
        this.framesFromShoot = 0;
        this.shootingAvailable = false;
        return new BulletImpl(BULLET_IMAGE, this.level, this.position, target);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImageView getImageView() {
        return this.image;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final Point2D addPosition) {
        this.position.add(addPosition);
        this.framesFromShoot++;
        if (framesFromShoot >= framesToShoot) {
            this.shootingAvailable = true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getSpeed() {
        return this.speed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAlive() {
        return this.alive;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loseHealth(final int health) {
        this.life.loseHealth(health);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addHealth(final int health) {
        this.life.addHealth(health);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addLife() {
        life.addLife();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loseLife() {
        life.loseLife();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHealth() {
        return life.getHealth();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLives() {
        return life.getLives();
    }

}
