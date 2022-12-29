package fr.univtours.examplanner.repositories;

import fr.univtours.examplanner.entities.dtos.DepartmentDTO;
import fr.univtours.examplanner.entities.dtos.UserDTO;
import fr.univtours.examplanner.enums.UserRole;
import fr.univtours.examplanner.exceptions.DatabaseConnectionException;
import fr.univtours.examplanner.exceptions.MappingException;
import fr.univtours.examplanner.exceptions.RepoException;
import fr.univtours.examplanner.mappers.UserMapper;
import fr.univtours.examplanner.utils.Database;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class UserRepo implements BaseRepo<UserDTO, String> {

    /**
     * Récupère tous utilisateurs en fonction du rôle passé en paramètre
     *
     * @param role filtre les utilisateurs en fonction de ce rôle
     * @return la liste des utilisateurs correspondants
     */
    public @NotNull List< UserDTO > getAllForRole( UserRole role ) throws RepoException {
        return getAll().stream().filter(user -> user.getRole().equals(role)).toList();
    }

    /**
     * Permet d'obtenir tous les utilisateurs
     *
     * @return une liste d'utilisateurs
     *
     * @throws RepoException si une erreur survient lors de la récupération
     */
    @Override
    public @NotNull List< UserDTO > getAll() throws RepoException {
        return getAllFrom(null, null);
    }

    /**
     * Récupère une liste d'utilisateurs selon les paramètres fournit
     *
     * @param key   nom de la colonne à filtrer
     * @param value valeur de la colonne à filtrer
     * @return la liste des utilisateurs correspondants
     */
    private @NotNull List< UserDTO > getAllFrom( @Nullable String key, @Nullable String value ) throws RepoException {
        String sql = "SELECT * FROM User";
        boolean withOptions = !Objects.isNull(key) && !Objects.isNull(value);
        if ( withOptions ) {
            sql += " WHERE " + key + " = ?";
        }
        try ( PreparedStatement stm = Database.getConnection().prepareStatement(sql) ) {
            if ( withOptions ) {
                stm.setString(1, value);
            }
            return UserMapper.entityToDTO(stm.executeQuery());
        } catch ( SQLException | DatabaseConnectionException | MappingException e ) {
            throw new RepoException("Getting users failed, no rows affected.", e);
        }
    }

    /**
     * Récupère tous les utilisateurs qui font partis du département en paramètre
     *
     * @param dep un département
     * @return la liste des utilisateurs correspondants
     */
    public @NotNull List< UserDTO > getAllFromDepartment( @NotNull DepartmentDTO dep ) throws RepoException {
        return getAllFrom("department", dep.getId());
    }

    /**
     * Permet de sauvegarder un utilisateur
     *
     * @param entity l'utilisateur à sauvegarder
     * @return l'utilisateur sauvegardé
     *
     * @throws RepoException si une erreur survient lors de la sauvegarde
     */
    @Override
    public @NotNull UserDTO save( @NotNull UserDTO entity ) throws RepoException {
        UserDTO alreadyExisting = getByMail(entity.getMail());
        if ( !Objects.isNull(alreadyExisting) &&
             !Objects.isNull(alreadyExisting.getId()) &&
             !alreadyExisting.getId().equals(entity.getId()) ) {
            throw new RepoException("An user with the same mail already exists.", null);
        }
        boolean hasId = !Objects.isNull(entity.getId());
        String id = hasId ? entity.getId() : Database.getNewUUID();
        String sql;
        if ( hasId ) {
            sql = "UPDATE User SET mail = ?, password = ?, department = ?, manager = ? WHERE id = '" + id + "'";
        } else {
            sql = "INSERT INTO User (id, mail, password, department, manager) VALUES ('" + id + "', ?, ?, ?, ?)";
        }
        try ( PreparedStatement stm = Database.getConnection().prepareStatement(sql) ) {
            stm.setString(1, entity.getMail());
            stm.setString(2, entity.getPassword());
            stm.setString(3, entity.getDepartmentID());
            stm.setString(4, entity.getManagerID());
            int rows = stm.executeUpdate();
            if ( 0 == rows ) {
                throw new RepoException("Saving user failed, no rows affected.", null);
            }
            if ( !hasId ) {
                entity.setId(id);
            }
            return entity;
        } catch ( SQLException | DatabaseConnectionException e ) {
            throw new RepoException("Saving user failed, no rows affected.", e);
        }
    }

    /**
     * Permet d'obtenir un utilisateur grâce à son adresse mail
     *
     * @param mail l'adresse mail de l'utilisateur recherché
     * @return l'utilisateur qui correspond à l'adresse mail
     */
    public @Nullable UserDTO getByMail( @NotNull String mail ) throws RepoException {
        List< UserDTO > res = getAllFrom("mail", mail);
        if ( res.isEmpty() ) {
            return null;
        }
        return res.get(0);
    }

    /**
     * Permet d'obtenir un utilisateur grâce à son identifiant
     *
     * @param id l'identifiant
     * @return l'utilisateur qui correspond à l'identifiant
     */
    @Override
    public @Nullable UserDTO getById( @NotNull String id ) throws RepoException {
        List< UserDTO > res = getAllFrom("id", id);
        if ( res.isEmpty() ) {
            return null;
        }
        return res.get(0);
    }

    /**
     * Permet de supprimer un utilisateur
     *
     * @param entity l'utilisateur à supprimer
     * @return vraie si l'utilisateur a bien été supprimé
     */
    @Override
    public boolean delete( @NotNull UserDTO entity ) throws RepoException {
        String sql = "DELETE FROM User WHERE id = ?";
        try ( PreparedStatement stm = Database.getConnection().prepareStatement(sql) ) {
            stm.setString(1, entity.getId());
            int rows = stm.executeUpdate();
            return 0 != rows;
        } catch ( SQLException | DatabaseConnectionException e ) {
            throw new RepoException("Deleting user failed, no rows affected.", e);
        }
    }
}