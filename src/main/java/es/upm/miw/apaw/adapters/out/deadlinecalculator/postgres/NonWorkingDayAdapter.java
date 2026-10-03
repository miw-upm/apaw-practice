package es.upm.miw.apaw.adapters.out.deadlinecalculator.postgres;

import es.upm.miw.apaw.domain.model.deadlinecalculator.NonWorkingDay;
import es.upm.miw.apaw.domain.ports.out.deadlinecalculator.NonWorkingDayGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class NonWorkingDayAdapter implements NonWorkingDayGateway {
    private final NonWorkingDayRepository nonWorkingDayRepository;

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
}
