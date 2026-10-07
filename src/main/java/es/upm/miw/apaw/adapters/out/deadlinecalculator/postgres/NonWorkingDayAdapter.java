package es.upm.miw.apaw.adapters.out.deadlinecalculator.postgres;

import es.upm.miw.apaw.domain.model.deadlinecalculator.NonWorkingDay;
import es.upm.miw.apaw.domain.ports.out.deadlinecalculator.NonWorkingDayGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class NonWorkingDayAdapter implements NonWorkingDayGateway {
    private final NonWorkingDayRepository nonWorkingDayRepository;
    private final DeadlineRepository deadlineRepository;

    @Override
    public NonWorkingDay create(NonWorkingDay nonWorkingDay) {
        return this.nonWorkingDayRepository
                .save(new NonWorkingDayEntity(nonWorkingDay))
                .toDomain();
    }

    @Override
    public boolean exists(NonWorkingDay nonWorkingDay) {
        NonWorkingDayEntity entity = new NonWorkingDayEntity(nonWorkingDay);
        return this.nonWorkingDayRepository.existsByDateAndScopeLevelAndRegionAndCity(
                entity.getDate(), entity.getScopeLevel(), entity.getRegion(), entity.getCity());
    }

    @Override
    public Optional<NonWorkingDay> read(UUID id) {
        return this.nonWorkingDayRepository.findById(id)
                .map(NonWorkingDayEntity::toDomain);
    }

    @Override
    public List<NonWorkingDay> findAll() {
        return this.nonWorkingDayRepository.findAllByOrderByDateAscDescriptionAscIdAsc().stream()
                .map(NonWorkingDayEntity::toDomain)
                .toList();
    }

    @Override
    public boolean existsOther(UUID id, NonWorkingDay nonWorkingDay) {
        NonWorkingDayEntity entity = new NonWorkingDayEntity(nonWorkingDay);
        return this.nonWorkingDayRepository.existsByDateAndScopeLevelAndRegionAndCityAndIdNot(
                entity.getDate(), entity.getScopeLevel(), entity.getRegion(), entity.getCity(), id);
    }

    @Override
    public NonWorkingDay update(NonWorkingDay nonWorkingDay) {
        return this.nonWorkingDayRepository
                .save(new NonWorkingDayEntity(nonWorkingDay))
                .toDomain();
    }

    @Override
    public boolean isReferenced(UUID id) {
        return this.deadlineRepository.existsByNonWorkingDaysId(id);
    }

    @Override
    public void delete(UUID id) {
        this.nonWorkingDayRepository.deleteById(id);
    }
}
