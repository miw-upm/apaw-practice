package es.upm.miw.apaw_practice.adapters.mongodb.cinema.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities.DirectorEntity;
import es.upm.miw.apaw_practice.domain.models.cinema.Director;

public class DirectorPersistenceMapper {

    public static Director toDirector(DirectorEntity entity) {
        return new Director(
                entity.getDni(),
                entity.getBirthdate(),
                entity.getStyle()
        );
    }

    public static DirectorEntity toEntity(Director director) {
        return new DirectorEntity(
                director.getDni(),
                director.getBirthdate(),
                director.getStyle()
        );
    }
}