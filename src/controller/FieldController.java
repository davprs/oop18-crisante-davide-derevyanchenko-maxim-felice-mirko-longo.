package controller;

import java.awt.MouseInfo;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import model.CharacterShip;
import model.CharacterShipImpl;
import model.Entity;
import view.FieldView;

public class FieldController {
    
    private static final Image SHIP_IMAGE = new Image("spaceship.png");
    private static final Image SPACE_IMAGE = new Image( "space.png" );
    private final CharacterShip ship;
    private final FieldView view;
    private final AnimationTimer loop;
    private final List<Entity> entities = new ArrayList<>();
    
    public FieldController(FieldView view) {
        this.ship = new CharacterShipImpl(SHIP_IMAGE);
        this.view = view;
        this.view.drawEntity(this.ship.getImage(), this.ship.getBoundary());
        final EventHandler<Event> eh = new EventHandler<Event>() {

            @Override
            public void handle(Event event) {   
                ship.changeMoving();
            }
            
        };
        this.view.getCanvas().setOnMouseEntered(eh);
        this.view.getCanvas().setOnMouseExited(eh);
        this.loop =  new AnimationTimer() {
            private final long startNanoTime = System.nanoTime();
            
            public void handle(long currentNanoTime) {
                
                view.drawBackground(SPACE_IMAGE);
                Point2D mousePosition = new Point2D(MouseInfo.getPointerInfo().getLocation().getX(), MouseInfo.getPointerInfo().getLocation().getY());
                Point2D mouseWindowPosition = new Point2D(mousePosition.getX() - view.getStage().getX() - view.getStage().getScene().getX(), mousePosition.getY() - view.getStage().getY() - view.getStage().getScene().getY());
                Point2D shipMaxPosition = new Point2D(view.getStage().getX() + view.getStage().getWidth() - ship.getImage().getWidth(), view.getStage().getY() + view.getStage().getHeight() - ship.getImage().getHeight());
                if (mouseWindowPosition.getX() > shipMaxPosition.getX() && mouseWindowPosition.getY() > shipMaxPosition.getY()) {
                    ship.update(shipMaxPosition.getX(), shipMaxPosition.getY());
                } else if (mouseWindowPosition.getX() > shipMaxPosition.getX() && mouseWindowPosition.getY() < shipMaxPosition.getY()) {
                    ship.update(shipMaxPosition.getX(), mouseWindowPosition.getY());
                } else if (mouseWindowPosition.getX() < shipMaxPosition.getX() && mouseWindowPosition.getY() > shipMaxPosition.getY()) {
                    ship.update(mouseWindowPosition.getX(),shipMaxPosition.getY());
                } else {
                    ship.update(mouseWindowPosition.getX(), mouseWindowPosition.getY());
                }
                
                view.drawEntity(ship.getImage(), ship.getBoundary());
                
                for (final Entity entity : entities) {
                    entity.update(currentNanoTime - startNanoTime / 1000000000);
                    view.drawEntity(entity.getImage(), entity.getBoundary());
                }
            }
        };
        this.loop.start();
    }
    
}
