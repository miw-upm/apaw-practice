package es.upm.miw.apaw.domain.ports.out.courthearing;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

import es.upm.miw.apaw.domain.model.courthearing.Court;
import es.upm.miw.apaw.domain.model.courthearing.CourtHearingByCourtReport;

public interface CourtGateway {
    Court create(Court court);

    boolean existsByName(String name);

    boolean existsByPhone(String phone);

    Optional<Court> read(UUID id);

    Court update(Court court);

    void delete(UUID id);

    boolean isReferenced(UUID id);

    List<Court> findAll();

    List<CourtHearingByCourtReport> findHearingByCourtReport();
}