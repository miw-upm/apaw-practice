package es.upm.miw.apaw.adapters.out.deadlinecalculator.postgres;

import es.upm.miw.apaw.domain.ports.out.deadlinecalculator.NonWorkingDayGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class NonWorkingDayAdapter implements NonWorkingDayGateway {
    private final NonWorkingDayRepository nonWorkingDayRepository;
}
