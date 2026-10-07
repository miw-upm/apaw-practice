package es.upm.miw.apaw.domain.ports.out.deadlinecalculator;

import es.upm.miw.apaw.domain.model.deadlinecalculator.Deadline;

public interface DeadlineGateway {
    Deadline create(Deadline deadline);

    boolean existsByTitle(String title);
}
