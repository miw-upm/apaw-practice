package es.upm.miw.apaw.domain.ports.out.deadlinecalculator;

import es.upm.miw.apaw.domain.model.deadlinecalculator.NonWorkingDay;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NonWorkingDayGateway {
    NonWorkingDay create(NonWorkingDay nonWorkingDay);

    boolean exists(NonWorkingDay nonWorkingDay);

    Optional<NonWorkingDay> read(UUID id);

    List<NonWorkingDay> findAll();

    boolean existsOther(UUID id, NonWorkingDay nonWorkingDay);

    NonWorkingDay update(NonWorkingDay nonWorkingDay);

    boolean isReferenced(UUID id);

    void delete(UUID id);

    List<NonWorkingDay> findApplicable(String region, String city);
}
