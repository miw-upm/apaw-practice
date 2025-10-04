package es.upm.miw.apaw.domain.persistenceports.sports.academy;

import es.upm.miw.apaw.domain.models.sports.academy.Athlete;

import java.util.UUID;
import java.util.stream.Stream;

public interface IAthletePersistence {
    Stream<Athlete> getAll(int page, int pageSize);

    Athlete create(Athlete athlete);

    Athlete update(UUID id, Athlete athlete);

    Athlete getById(UUID id);
}
