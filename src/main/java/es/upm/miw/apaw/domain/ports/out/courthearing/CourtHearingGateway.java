package es.upm.miw.apaw.domain.ports.out.courthearing;

import es.upm.miw.apaw.domain.model.courthearing.CourtHearing;

import java.util.UUID;

public interface CourtHearingGateway {
    CourtHearing create(UUID courtId, CourtHearing courtHearing);
}