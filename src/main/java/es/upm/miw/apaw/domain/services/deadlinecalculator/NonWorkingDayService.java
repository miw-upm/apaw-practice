package es.upm.miw.apaw.domain.services.deadlinecalculator;

import es.upm.miw.apaw.domain.exceptions.BadRequestException;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.deadlinecalculator.NonWorkingDay;
import es.upm.miw.apaw.domain.model.deadlinecalculator.NonWorkingDayRecurringUpdate;
import es.upm.miw.apaw.domain.ports.out.deadlinecalculator.NonWorkingDayGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    @Transactional
    public void updateRecurrences(List<NonWorkingDayRecurringUpdate> updates) {
        this.assertUniqueIds(updates);
        List<NonWorkingDay> nonWorkingDays = updates.stream()
                .map(update -> {
                    NonWorkingDay nonWorkingDay = this.read(update.id());
                    nonWorkingDay.setRecurring(update.recurring());
                    return nonWorkingDay;
                })
                .toList();
        nonWorkingDays.forEach(this.nonWorkingDayGateway::update);
    }

    private void assertUniqueIds(List<NonWorkingDayRecurringUpdate> updates) {
        Set<UUID> ids = new HashSet<>();
        for (NonWorkingDayRecurringUpdate update : updates) {
            if (!ids.add(update.id())) {
                throw new BadRequestException("Repeated non working day id: " + update.id());
            }
        }
    }

    public void delete(UUID id) {
        if (this.nonWorkingDayGateway.isReferenced(id)) {
            throw new ConflictException("Non working day is referenced by a deadline: " + id);
        }
        this.nonWorkingDayGateway.delete(id);
    }
}
