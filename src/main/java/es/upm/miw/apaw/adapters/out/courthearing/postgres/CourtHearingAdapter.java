package es.upm.miw.apaw.adapters.out.courthearing.postgres;

import es.upm.miw.apaw.domain.ports.out.courthearing.CourtHearingGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CourtHearingAdapter implements CourtHearingGateway {
    private final CourtHearingRepository courtHearingRepository;
}