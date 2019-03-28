package model.powerup;

import java.util.function.Consumer;

import model.Life;
/**
 *  adds a life powerup.
 *  
 */
public class LifePowerUp implements Consumer<Life> {

    @Override
    public void accept(Life life) {
        life.addLife();
    }

}
