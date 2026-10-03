package es.upm.miw.apaw.adapters.out.courthearing.postgres;

import es.upm.miw.apaw.domain.model.courthearing.Court;
import es.upm.miw.apaw.domain.model.courthearing.CourtHearingByCourtReport;
import es.upm.miw.apaw.domain.ports.out.courthearing.CourtGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CourtAdapter implements CourtGateway {
    private final CourtRepository courtRepository;
    private final CourtHearingRepository courtHearingRepository;
    
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

    @Override
    public Optional<Court> read(UUID id) {
        return this.courtRepository.findById(id)
                .map(CourtEntity::toDomain);
    }

    @Override
    public Court update(Court court) {
        return this.courtRepository
                .save(new CourtEntity(court))
                .toDomain();
    }

    @Override
    public void delete(UUID id) {
        this.courtRepository.deleteById(id);
    }

    @Override
    public boolean isReferenced(UUID id) {
        return this.courtHearingRepository.existsByCourtId(id);
    }

    @Override
    public List<Court> findAll() {
        return this.courtRepository.findAllByOrderByNameAsc().stream()
                .map(CourtEntity::toDomain)
                .toList();
    }

    @Override
    public List<CourtHearingByCourtReport> findHearingByCourtReport() {
        return this.courtHearingRepository.findHearingByCourtReport();
    }
}