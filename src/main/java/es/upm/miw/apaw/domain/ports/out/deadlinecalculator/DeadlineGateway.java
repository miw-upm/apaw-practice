package es.upm.miw.apaw.domain.ports.out.deadlinecalculator;

import es.upm.miw.apaw.domain.model.deadlinecalculator.Deadline;
import es.upm.miw.apaw.domain.model.deadlinecalculator.DeadlineFindCriteria;
import es.upm.miw.apaw.domain.model.deadlinecalculator.DeadlineWorkloadReport;

import java.time.LocalDate;
import java.util.List;

public interface DeadlineGateway {
    Deadline create(Deadline deadline);

    boolean existsByTitle(String title);

    List<DeadlineWorkloadReport> findWorkloadReport(LocalDate today);

    List<Deadline> find(DeadlineFindCriteria criteria, LocalDate today);
}
