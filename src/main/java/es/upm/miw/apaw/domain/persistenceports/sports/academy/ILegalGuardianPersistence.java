package es.upm.miw.apaw.domain.persistenceports.sports.academy;

import es.upm.miw.apaw.domain.models.sports.academy.LegalGuardian;

import java.util.UUID;
import java.util.stream.Stream;

public interface ILegalGuardianPersistence {
    Stream<LegalGuardian> getAll(int page, int pageSize);

    LegalGuardian create(LegalGuardian legalGuardian);

    LegalGuardian update(UUID id, LegalGuardian legalGuardian);

    LegalGuardian getById(UUID id);
}
