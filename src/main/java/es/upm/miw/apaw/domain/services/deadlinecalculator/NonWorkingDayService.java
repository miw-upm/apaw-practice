package es.upm.miw.apaw.domain.services.deadlinecalculator;

import es.upm.miw.apaw.domain.exceptions.BadRequestException;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.model.deadlinecalculator.NonWorkingDay;
import es.upm.miw.apaw.domain.ports.out.deadlinecalculator.NonWorkingDayGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NonWorkingDayService {
    private final NonWorkingDayGateway nonWorkingDayGateway;

    public NonWorkingDay create(NonWorkingDay nonWorkingDay) {
        this.validateScope(nonWorkingDay);
        if (this.nonWorkingDayGateway.exists(nonWorkingDay)) {
            throw new ConflictException("Non working day already exists: " + nonWorkingDay.getDate()
                    + ", " + nonWorkingDay.getScopeLevel()
                    + ", " + nonWorkingDay.getRegion()
                    + ", " + nonWorkingDay.getCity());
        }
        nonWorkingDay.doDefault();
        return this.nonWorkingDayGateway.create(nonWorkingDay);
    }

    private void validateScope(NonWorkingDay nonWorkingDay) {
        boolean hasRegion = nonWorkingDay.getRegion() != null && !nonWorkingDay.getRegion().isBlank();
        boolean hasCity = nonWorkingDay.getCity() != null && !nonWorkingDay.getCity().isBlank();
        switch (nonWorkingDay.getScopeLevel()) {
            case NATIONAL -> this.assertScope(!hasRegion && !hasCity, nonWorkingDay);
            case REGIONAL -> this.assertScope(hasRegion && !hasCity, nonWorkingDay);
            case LOCAL -> this.assertScope(hasRegion && hasCity, nonWorkingDay);
        }
    }

    private void assertScope(boolean valid, NonWorkingDay nonWorkingDay) {
        if (!valid) {
            throw new BadRequestException("Invalid scope for non working day: "
                    + nonWorkingDay.getScopeLevel()
                    + ", region: " + nonWorkingDay.getRegion()
                    + ", city: " + nonWorkingDay.getCity());
        }
    }
}
