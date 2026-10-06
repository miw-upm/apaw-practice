package es.upm.miw.apaw.domain.ports.out.probate;

import es.upm.miw.apaw.domain.model.probate.Estate;
import es.upm.miw.apaw.domain.model.probate.EstateUsageReport;

import java.util.List;

public interface EstateGateway {
    Estate create(Estate estate);

    boolean existsByFileNumber(String fileNumber);

    List<EstateUsageReport> findUsageReport();
}
