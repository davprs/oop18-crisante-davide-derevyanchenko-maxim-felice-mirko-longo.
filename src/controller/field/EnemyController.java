package controller.field;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import model.Entity;
import model.ship.enemyship.EnemyShip;
import model.ship.enemyship.EnemyShipImpl;

/**
 * Controller class of EnemyShip.
 */
public class EnemyController implements EntityController {

    private final Image image;
    private final CharacterController characterController;      //HAS TO BE CHANGED IN CharacterController.
    private final GameController gameController;
    private final EnemyShip enemy;
    private boolean freeze;
    private Dimension2D fieldSize;

    /**
     * Build a new EnemyController and his EnemyShip.
     * @param gameController the fieldView
     * @param level the level of the new created Enemy.
     * @param characterController the entity representing the CharacterShip.
     * @param fieldSize the field width and height.
     */
    public EnemyController(final GameController gameController, final int level, final CharacterController characterController, final Dimension2D fieldSize) {
        System.out.println("lol");
        this.fieldSize = fieldSize;
        this.image = utilities.EntitiesImageUtils.getEnemyShipImage(level);
        System.out.println("then");
        this.enemy = new EnemyShipImpl(level, fieldSize);
        this.gameController = gameController;
        this.characterController = characterController;
        this.freeze = false;
    }

    /**
     * Build a new EnemyController and his easy-level EnemyShip.
     * @param gameController the gameController.
     * @param characterController the entity representing the CharacterShip.
     * @param fieldSize the field width and height.
     */
    public EnemyController(final GameController gameController, final CharacterController characterController, final Dimension2D fieldSize) {
        this(gameController, 1, characterController, fieldSize);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw() {
        final double angle = -Math.toDegrees(Math.atan2(enemy.getBoundary().getMinX() - characterController.getEntity().getBoundary().getMinX(), enemy.getBoundary().getMinY() - characterController.getEntity().getBoundary().getMinY()));
        this.gameController.getFieldView().drawEntity(image, angle, this.enemy.getBoundary());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        Point2D position = new Point2D(enemy.getBoundary().getMinX(), enemy.getBoundary().getMinY());
        if (Math.random() * 10 < 9) {    //li fa muovere in maniera meno imbecille, non si accorpano come deficienti
            if (enemy.getBoundary().getMinX() < characterController.getEntity().getBoundary().getMinX() + characterController.getEntity().getBoundary().getWidth() / 2) {
                position = new Point2D(position.getX() + enemy.getSpeed(), position.getY());
            } else if (enemy.getBoundary().getMinX() > characterController.getEntity().getBoundary().getMinX() + characterController.getEntity().getBoundary().getWidth() / 2) {
                position = new Point2D(position.getX() - enemy.getSpeed(), position.getY());
            }
            if (position.getY() < characterController.getEntity().getBoundary().getMinY() + characterController.getEntity().getBoundary().getHeight() / 2) {
                position = new Point2D(position.getX(), position.getY() + enemy.getSpeed());
            } else if (position.getY() > characterController.getEntity().getBoundary().getMinY() + characterController.getEntity().getBoundary().getHeight() / 2) {
                position = new Point2D(position.getX(), position.getY() - enemy.getSpeed());
            }
            } else {
                if (position.getY() > characterController.getEntity().getBoundary().getMinY() + characterController.getEntity().getBoundary().getHeight() / 2) {
                    position = new Point2D(position.getX(), position.getY() + enemy.getSpeed());
                } else if (position.getY() < characterController.getEntity().getBoundary().getMinY() + characterController.getEntity().getBoundary().getHeight() / 2) {
                    position = new Point2D(position.getX(), position.getY() - enemy.getSpeed());
                }
                if (enemy.getBoundary().getMinX() > characterController.getEntity().getBoundary().getMinX() + characterController.getEntity().getBoundary().getWidth() / 2) {
                    position = new Point2D(position.getX() + enemy.getSpeed(), position.getY());
                } else if (enemy.getBoundary().getMinX() < characterController.getEntity().getBoundary().getMinX() + characterController.getEntity().getBoundary().getWidth() / 2) {
                    position = new Point2D(position.getX() - enemy.getSpeed(), position.getY());
                }
            }
        enemy.update(position);
//        double rad;
//        double movY, movX;
////        rad=Math.atan2(Math.max(position.getX(), character.getBoundary().getMinX()) - Math.min(position.getX(), character.getBoundary().getMinX()) , Math.max(position.getY(), character.getBoundary().getMinY()) - Math.min(position.getY(), character.getBoundary().getMinY()));
//        rad = Math.atan2(characterController.getEntity().getBoundary().getMinY() - position.getY(), characterController.getEntity().getBoundary().getMinX() - position.getX());
////        movY=Math.abs(- Math.pow(Math.sin(rad), 2) + 1);
////        movX=Math.abs(- Math.pow(Math.cos(rad), 2) + 1);
//        movY = enemy.getSpeed() * Math.sin(rad);
//        movX = enemy.getSpeed() * Math.cos(rad);
//        enemy.update(new Point2D(movX, movY));

    }

    /**
     * Method that changes the value of the freeze enemies. 
     */
    public void changeFreeze() {
        this.freeze = !this.freeze;
    }

    /**
     * 
     * @return the new Bullet.
     */
    public BulletController shoot() {
        return new BulletController(this.gameController, this.enemy.getLevel(), this.enemy.shoot(), 
                new Point2D(this.characterController.getEntity().getBoundary().getMinX(),
                    this.characterController.getEntity().getBoundary().getMinY()), this.fieldSize);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity getEntity() {
        return enemy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy() {
        this.gameController.getFieldView().drawExplosion(this.enemy.getBoundary());
        this.gameController.getFieldController().removeEnemy(this);
    }

}
