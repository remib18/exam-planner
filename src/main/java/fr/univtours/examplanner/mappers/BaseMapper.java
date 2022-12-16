package fr.univtours.examplanner.mappers;

import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;

public interface BaseMapper {

    /**
     * Permet de transformer le résultat/données obtenu(es) de la requête (entity) en une classe (DTO),
     * dans le but de pouvoir l'exploiter facilement dans le code et éviter de faire des requêtes continuellement.
     *
     * @param entities Correspond au retour de la requête SQL
     * @return On retourne un nouveau DTO.
     * C'est une methode d'interface, mais l'IDE ne supporte pas qu'il n'y a pas de "body".
     *
     * @param <DTO> On indique une classe générique pour pouvoir utiliser la même méthode sur différents DTO
     */
    static <DTO> @NotNull DTO entityToDTO(@NotNull ResultSet entities) {
        throw new UnsupportedOperationException();
    }

    /**
     * Permet de transformer les attributs/données d'une classe (DTO) en données (entity) exploitables dans une requête.
     *
     * @param DTO Correspond à la classe dont certaines données seront utilisées dans une requête
     * @return On retourne un tableau à une entrée de chaines de caractères pour pouvoir les utiliser dans une requête
     * C'est une methode d'interface, mais l'IDE ne supporte pas qu'il n'y a pas de "body".
     *
     * @param <DTO> On indique une classe générique pour pouvoir utiliser la même méthode sur différents DTO
     */
    static <DTO> @NotNull String[] DTOToEntity( @NotNull DTO DTO) {
        throw new UnsupportedOperationException();
    }

}
