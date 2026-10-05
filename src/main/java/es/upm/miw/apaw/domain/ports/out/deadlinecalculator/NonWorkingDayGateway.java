package es.upm.miw.apaw.domain.ports.out.deadlinecalculator;

import es.upm.miw.apaw.domain.model.deadlinecalculator.NonWorkingDay;

import java.util.Optional;
import java.util.UUID;

public interface NonWorkingDayGateway {
    NonWorkingDay create(NonWorkingDay nonWorkingDay);

    boolean exists(NonWorkingDay nonWorkingDay);

    Optional<NonWorkingDay> read(UUID id);
}
