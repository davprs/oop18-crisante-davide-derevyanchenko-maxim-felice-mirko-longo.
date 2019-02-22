package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * 
 *
 */
public final class FieldImpl implements Field {

    private static final int X_DIMENSION = 160;
    private static final int Y_DIMENSION = 90;
    private static final Field SINGLETON = new FieldImpl();
    private final List<List<Optional<Entity>>> battleField;

    /**
     * private constructor prevents the creation of other battle fields.
     * 
     */
    private FieldImpl() { 
        this.battleField = new LinkedList<>();
        for (int y = 0; y < Y_DIMENSION; y++) {
            this.battleField.add(new LinkedList<>());
            for (int x = 0; x < X_DIMENSION; x++) {
                this.battleField.get(y).add(Optional.empty());
            }
        }
    }

    /**
     * 
     * @return the battle field
     */
    public static Field getBattleField() {
        return SINGLETON;
    }

    @Override
    public Optional<Entity> entityAt(final int x, final int y) {
        return this.battleField.get(y).get(x);
    }

    @Override
    public boolean addEntity(final Entity entity, final int x, final int y) {
        
        return false;
    }

    @Override
    public boolean destrotyEntity(final int x, final int y) {
        if (this.entityAt(x, y).isPresent()) {
            this.battleField.get(y).add(x, Optional.empty());
            return true;
        }
        return false;
    }

}
