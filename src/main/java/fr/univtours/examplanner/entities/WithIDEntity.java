package fr.univtours.examplanner.entities;

import javafx.beans.property.SimpleObjectProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public abstract class WithIDEntity {

    /**
     * Identifiant du groupe dans la base de donnée<br/> Si null, le groupe n'est pas encore enregistré
     */
    @NotNull
    protected SimpleObjectProperty< @Nullable String > id = new SimpleObjectProperty<>();

    protected WithIDEntity() {
        this(null);
    }

    protected WithIDEntity( @Nullable String id ) {
        this.id.set(id);
    }

    public @Nullable String getId() {
        return id.get();
    }

    public void setId( @NotNull String id ) throws IllegalStateException {
        if ( Objects.isNull(this.id.get()) ) {
            this.id.set(id);
            return;
        }
        throw new IllegalStateException("Cannot change the id of a saved entity.");
    }

    public SimpleObjectProperty< @Nullable String > idProperty() {
        return id;
    }
}
