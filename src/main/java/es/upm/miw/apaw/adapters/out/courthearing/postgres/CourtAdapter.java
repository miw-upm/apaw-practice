package es.upm.miw.apaw.adapters.out.courthearing.postgres;

import es.upm.miw.apaw.domain.model.courthearing.Court;
import es.upm.miw.apaw.domain.ports.out.courthearing.CourtGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CourtAdapter implements CourtGateway {
    private final CourtRepository courtRepository;

    @Override
    public Court create(Court court) {
        return this.courtRepository
                .save(new CourtEntity(court))
                .toDomain();
    }

    @Override
    public boolean existsByName(String name) {
        return this.courtRepository.existsByName(name);
    }

    @Override
    public boolean existsByPhone(String phone) {
        return this.courtRepository.existsByPhone(phone);
    }
}