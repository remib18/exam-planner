package fr.univtours.examplanner.controllers;

import fr.univtours.examplanner.entities.dtos.MockUpDTO;
import fr.univtours.examplanner.enums.Degree;
import fr.univtours.examplanner.enums.MockupYear;
import fr.univtours.examplanner.exceptions.ControllerException;
import fr.univtours.examplanner.exceptions.RepoException;
import fr.univtours.examplanner.repositories.MockUpRepo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class MockUpController {

    private static MockUpController instance;

    /**
     * Interface avec la base de données permettant d'effectuer des opérations standards sur les MockUp
     */
    @NotNull
    private final MockUpRepo repo;

    private MockUpController() {
        repo = new MockUpRepo();
    }

    /**
     * Liste les MockUp
     *
     * @return La liste des MockUp
     *
     * @throws ControllerException Si une erreur survient
     */

    public static @NotNull List< MockUpDTO > getAll() throws ControllerException {
        try {
            return getInstance().repo.getAll();
        } catch ( RepoException e ) {
            throw new ControllerException("An error occurred during the data fetching", e);
        }
    }

    private static MockUpController getInstance() {
        if ( Objects.isNull(instance) ) {
            instance = new MockUpController();
        }
        return instance;
    }

    public static @Nullable MockUpDTO getById( @NotNull String id ) throws ControllerException {
        try {
            return getInstance().repo.getById(id);
        } catch ( RepoException e ) {
            throw new ControllerException("An error occurred during the data fetching", e);
        }
    }

    /**
     * Permet de créer un MockUp
     *
     * @param name     Le nom du MockUp
     * @param degree   Le niveau du MockUp
     * @param semester Le semestre du MockUp
     * @param subjects La liste des matières du MockUp
     * @return Le MockUp crée
     *
     * @throws ControllerException Si une erreur survient
     */

    public static @NotNull MockUpDTO create(
            @NotNull String name,
            @NotNull Degree degree,
            MockupYear year,
            int semester,
            @NotNull List< String > subjects
    ) throws ControllerException {
        try {
            return getInstance().repo.save(new MockUpDTO(null, name, year, degree, semester, subjects));
        } catch ( RepoException e ) {
            throw new ControllerException("An error occurred during the data saving", e);
        }
    }

    /**
     * Permet de modifier ou créer un MockUp
     *
     * @param entity Le MockUp à modifier
     * @throws ControllerException Si une erreur survient
     */
    public static void save( @NotNull MockUpDTO entity ) throws ControllerException {
        try {
            getInstance().repo.save(entity);
        } catch ( RepoException e ) {
            throw new ControllerException("An error occurred during the data saving.", e);
        }
    }

    /**
     * Permet de supprimer un MockUp
     *
     * @param entity Le MockUp à supprimer
     * @throws ControllerException Si une erreur survient
     */
    public static void delete( @NotNull MockUpDTO entity ) throws ControllerException {
        try {
            getInstance().repo.delete(entity);
        } catch ( RepoException e ) {
            throw new ControllerException("An error occurred during the data deletion.", e);
        }
    }

}
