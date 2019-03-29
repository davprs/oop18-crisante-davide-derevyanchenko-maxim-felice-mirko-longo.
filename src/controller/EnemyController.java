package controller;

import javafx.geometry.Point2D;
import model.ship.CharacterShip;
import model.enemyship.EnemyShip;
import view.field.FieldView;

/**
 * Controller class of EnemyShip.
 *
 */
public class EnemyController {

    private final CharacterShip character;
    private final FieldView view;
    private final EnemyShip enemy;
    private boolean freeze;

    /**
     * Build a new EnemyController.
     * @param view the fieldView
     * @param enemy the entity representing the Enemy.
     * @param character the entity representing the CharacterShip.
     */
    public EnemyController(final FieldView view, final EnemyShip enemy, final CharacterShip character) {
        this.view = view;
        this.enemy = enemy;
        this.character = character;
        this.freeze = false;
    }

    /**
     * 
     */
    public void draw() {
        double angle = -Math.toDegrees(Math.atan2(enemy.getBoundary().getMinX() - character.getBoundary().getMinX(), enemy.getBoundary().getMinY() - character.getBoundary().getMinY()));
        this.view.drawEntity(this.enemy.getImageView(), angle, this.enemy.getBoundary());
    }

    /**
     * 
     */
    public void update() {
        Point2D position = new Point2D(enemy.getBoundary().getMinX(), enemy.getBoundary().getMinY());
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
        rad = Math.atan2(character.getBoundary().getMinY() - position.getY(), character.getBoundary().getMinX() - position.getX());
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

    //fare metodo di shoot.

}
