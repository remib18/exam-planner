package fr.univtours.examplanner.mappers;

import org.jetbrains.annotations.NotNull;

public interface BaseMapper {

    /**
     * Permet de transformer le résultat/données obtenu(es) de la requête (entity) en une classe (DTO),
     * dans le but de pouvoir l'exploiter facilement dans le code et éviter de faire des requêtes continuellement.
     *
     * @param entity Correspond au retour de la requête SQL
     * @return c'est une methode d'interface, mais l'IDE ne supporte pas qu'il n'y a pas de "body"
     *
     * @param <DTO> On indique une classe générique pour pouvoir utiliser la même méthode sur différents DTO
     * @param <Entity> On indique une classe générique pour pouvoir utiliser la même méthode sur différentes entités
     */
    static <DTO, Entity> @NotNull DTO entityToDTO(@NotNull Entity entity) {
        throw new UnsupportedOperationException();
    }

    //Todo(@gab): same
    /**
     * Permet de transformer les attributs/données d'une classe (DTO) en données (entity) exploitables dans une requête.
     * @param DTO Correspond à la classe dont certaines données seront utilisées dans une requête
     * @return c'est une methode d'interface, mais l'IDE ne supporte pas qu'il n'y a pas de "body"
     * @param <Entity> On indique une classe générique pour pouvoir utiliser la même méthode sur différentes entités
     * @param <DTO> On indique une classe générique pour pouvoir utiliser la même méthode sur différents DTO
     */
    static <Entity, DTO> @NotNull Entity DTOToEntity(@NotNull DTO DTO) {
        throw new UnsupportedOperationException();
    }

}
