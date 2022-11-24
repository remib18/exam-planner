package fr.univtours.examplanner.entities.dtos;

import fr.univtours.examplanner.entities.WithIDEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class SubjectDTO extends WithIDEntity {

    /**
     * Nom de la matière
     */
    @NotNull
    private String name;

    /**
     * Matière d'un cursus
     *
     * @param id   Identifiant de la matière dans la base de donnée, null si la matière n'est pas encore enregistrée
     * @param name Nom de la matière
     */
    public SubjectDTO(@Nullable String id, @NotNull String name) {
        super(id);
        this.name = name;
    }

    public @NotNull String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubjectDTO that = (SubjectDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "SubjectDTO{" +
                "\n\tid: " + id +
                ", \n\tname: " + name +
                "\n}";
    }
}
