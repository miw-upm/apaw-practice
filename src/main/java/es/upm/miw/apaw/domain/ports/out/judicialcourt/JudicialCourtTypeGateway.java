package es.upm.miw.apaw.domain.ports.out.judicialcourt;

import es.upm.miw.apaw.domain.model.judicialcourt.JudicialCourtType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JudicialCourtTypeGateway {
    JudicialCourtType create(JudicialCourtType judicialCourtType);

    List<JudicialCourtType> findAll();

    Optional<JudicialCourtType> read(UUID id);

    JudicialCourtType update(JudicialCourtType judicialCourtType);

    void delete(UUID id);

    boolean isReferenced(UUID id);

    boolean existsByName(String name);

    boolean existsByCode(String code);
}
