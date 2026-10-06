package es.upm.miw.apaw.domain.services.deadlinecalculator;

import es.upm.miw.apaw.domain.exceptions.BadRequestException;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.deadlinecalculator.NonWorkingDay;
import es.upm.miw.apaw.domain.ports.out.deadlinecalculator.NonWorkingDayGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NonWorkingDayService {
    private final NonWorkingDayGateway nonWorkingDayGateway;

    public NonWorkingDay create(NonWorkingDay nonWorkingDay) {
        if (!nonWorkingDay.hasConsistentScope()) {
            throw new BadRequestException("Invalid scope for non working day: "
                    + nonWorkingDay.getScopeLevel()
                    + ", region: " + nonWorkingDay.getRegion()
                    + ", city: " + nonWorkingDay.getCity());
        }
        if (this.nonWorkingDayGateway.exists(nonWorkingDay)) {
            throw new ConflictException("Non working day already exists: " + nonWorkingDay.getDate()
                    + ", " + nonWorkingDay.getScopeLevel()
                    + ", " + nonWorkingDay.getRegion()
                    + ", " + nonWorkingDay.getCity());
        }
        nonWorkingDay.doDefault();
        return this.nonWorkingDayGateway.create(nonWorkingDay);
    }

    public NonWorkingDay read(UUID id) {
        return this.nonWorkingDayGateway.read(id)
                .orElseThrow(() -> new NotFoundException("Non working day id not found: " + id));
    }

    public List<NonWorkingDay> findAll() {
        return this.nonWorkingDayGateway.findAll();
    }

    public NonWorkingDay update(UUID id, NonWorkingDay nonWorkingDay) {
        NonWorkingDay storedNonWorkingDay = this.read(id);
        if (!nonWorkingDay.hasConsistentScope()) {
            throw new BadRequestException("Invalid scope for non working day: "
                    + nonWorkingDay.getScopeLevel()
                    + ", region: " + nonWorkingDay.getRegion()
                    + ", city: " + nonWorkingDay.getCity());
        }
        if (this.nonWorkingDayGateway.existsOther(id, nonWorkingDay)) {
            throw new ConflictException("Non working day already exists: " + nonWorkingDay.getDate()
                    + ", " + nonWorkingDay.getScopeLevel()
                    + ", " + nonWorkingDay.getRegion()
                    + ", " + nonWorkingDay.getCity());
        }
        if (!storedNonWorkingDay.getDate().equals(nonWorkingDay.getDate())
                && this.nonWorkingDayGateway.isReferenced(id)) {
            throw new ConflictException("Non working day date cannot change, it is used by a deadline: " + id);
        }
        storedNonWorkingDay.replaceWith(nonWorkingDay);
        return this.nonWorkingDayGateway.update(storedNonWorkingDay);
    }
}
