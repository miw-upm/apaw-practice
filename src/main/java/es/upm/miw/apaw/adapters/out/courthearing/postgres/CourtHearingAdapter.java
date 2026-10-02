package es.upm.miw.apaw.adapters.out.courthearing.postgres;

import es.upm.miw.apaw.domain.model.courthearing.CourtHearing;
import es.upm.miw.apaw.domain.ports.out.courthearing.CourtHearingGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CourtHearingAdapter implements CourtHearingGateway {
    private final CourtHearingRepository courtHearingRepository;
    private final CourtRepository courtRepository;

    @Override
    public CourtHearing create(UUID courtId, CourtHearing courtHearing) {
        CourtHearingEntity entity = new CourtHearingEntity(courtHearing);
        entity.setCourt(this.courtRepository.getReferenceById(courtId));
        this.courtHearingRepository.save(entity);
        return courtHearing;
    }
}