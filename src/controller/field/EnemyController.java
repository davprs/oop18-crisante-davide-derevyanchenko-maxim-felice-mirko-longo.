package controller.field;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import model.Entity;
import model.enemyship.EnemyShip;
import model.enemyship.EnemyShipImpl;
import view.field.FieldView;

/**
 * Controller class of EnemyShip.
 */
public class EnemyController implements EntityController {

    private final Image image;
    private final CharacterController characterController;      //HAS TO BE CHANGED IN CharacterController.
    private final FieldView view;
    private final EnemyShip enemy;
    private boolean freeze;

    /**
     * Build a new EnemyController and his EnemyShip.
     * @param view the fieldView
     * @param level the level of the new created Enemy.
     * @param characterController the entity representing the CharacterShip.
     */
    public EnemyController(final FieldView view, final int level, final CharacterController characterController) {
        this.image = utilities.EntitiesImageUtils.getEnemyShipImage(level);
        this.enemy = new EnemyShipImpl(level);
        this.view = view;
        this.characterController = characterController;
        this.freeze = false;
    }

    /**
     * Build a new EnemyController and his easy-level EnemyShip.
     * @param view the fieldView
     * @param characterController the entity representing the CharacterShip.
     */
    public EnemyController(final FieldView view, final CharacterController characterController) {
        this(view, 1, characterController);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw() {
        final double angle = -Math.toDegrees(Math.atan2(enemy.getBoundary().getMinX() - characterController.getEntity().getBoundary().getMinX(), enemy.getBoundary().getMinY() - characterController.getEntity().getBoundary().getMinY()));
        this.view.drawEntity(image, angle, this.enemy.getBoundary());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        final Point2D position = new Point2D(enemy.getBoundary().getMinX(), enemy.getBoundary().getMinY());
//        if(Math.random()*10 < 9) {    //li fa muovere in maniera meno imbecille, non si accorpano come deficienti
//            if(enemy.getBoundary().getMinX()< character.getBoundary().getMinX() + character.getBoundary().getWidth()/2) {
//                position = new Point2D(position.getX() + enemyFASTNESS, position.getY());
//            }else if(enemy.getBoundary().getMinX() > character.getBoundary().getMinX() + character.getBoundary().getWidth()/2) {
//                position = new Point2D(position.getX() - FASTNESS, position.getY());
//            }
//            if(position.getY() < character.getBoundary().getMinY() + character.getBoundary().getHeight()/2) {
//                position = new Point2D(position.getX(), position.getY() + FASTNESS);
//            }else if(position.getY() > character.getBoundary().getMinY() + character.getBoundary().getHeight()/2) {
//                position = new Point2D(position.getX(), position.getY() - FASTNESS);
//            }
//            }else {
//                if(position.getY() > character.getBoundary().getMinY() + character.getBoundary().getHeight()/2) {
//                    position = new Point2D(position.getX(), position.getY() + FASTNESS);
//                }else if(position.getY() < character.getBoundary().getMinY() + character.getBoundary().getHeight()/2) {
//                    position = new Point2D(position.getX(), position.getY() - FASTNESS);
//                }
//                if(enemy.getBoundary().getMinX() > character.getBoundary().getMinX() + character.getBoundary().getWidth()/2) {
//                    position = new Point2D(position.getX() + FASTNESS, position.getY());
//                }else if(enemy.getBoundary().getMinX() < character.getBoundary().getMinX() + character.getBoundary().getWidth()/2) {
//                    position = new Point2D(position.getX() - FASTNESS, position.getY());
//                }
//            }
        double rad;
        double movY, movX;
//        rad=Math.atan2(Math.max(position.getX(), character.getBoundary().getMinX()) - Math.min(position.getX(), character.getBoundary().getMinX()) , Math.max(position.getY(), character.getBoundary().getMinY()) - Math.min(position.getY(), character.getBoundary().getMinY()));
        rad = Math.atan2(characterController.getEntity().getBoundary().getMinY() - position.getY(), characterController.getEntity().getBoundary().getMinX() - position.getX());
//        movY=Math.abs(- Math.pow(Math.sin(rad), 2) + 1);
//        movX=Math.abs(- Math.pow(Math.cos(rad), 2) + 1);
        movY = enemy.getSpeed() * Math.sin(rad);
        movX = enemy.getSpeed() * Math.cos(rad);
        enemy.update(new Point2D(movX, movY));

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
        return new BulletController(this.view, this.enemy.getLevel(), this.enemy.shoot(), 
                new Point2D(this.characterController.getEntity().getBoundary().getMinX(),
                    this.characterController.getEntity().getBoundary().getMinY()));
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean isAlive() {
        return enemy.isAlive();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean intersects(final EntityController entityController) {
        return enemy.intersects(entityController.getEntity());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity getEntity() {
        return enemy;
    }

}
