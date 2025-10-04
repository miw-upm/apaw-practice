package es.upm.miw.apaw.domain.persistenceports.sports.academy;

import es.upm.miw.apaw.domain.models.sports.academy.SportModality;

import java.util.UUID;
import java.util.stream.Stream;

public interface ISportModalityPersistence {
    Stream<SportModality> getAll(int page, int pageSize);

    SportModality create(SportModality sportModality);

    SportModality update(UUID id, SportModality sportModality);

    SportModality getById(UUID id);
}
