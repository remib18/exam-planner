package fr.univtours.examplanner.mappers;

import fr.univtours.examplanner.exceptions.MappingException;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface BaseMapper < DTO >{

    /**
     * Permet de transformer le résultat/données obtenu(es) de la requête (entity) en une classe (DTO), dans le but de
     * pouvoir l'exploiter facilement dans le code et éviter de faire des requêtes continuellement.
     *
     * @param entities Correspond au retour de la requête SQL
     * @return On retourne une liste de DTO.
     */
    @NotNull
    static < DTO > List< DTO > entityToDTO( @NotNull ResultSet entities ) throws MappingException, SQLException {
        //FIXME @gab comment ne pas avoir de body
        throw new  UnsupportedOperationException();
    }
}
