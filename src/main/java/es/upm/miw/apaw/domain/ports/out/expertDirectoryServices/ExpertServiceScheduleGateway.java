package es.upm.miw.apaw.domain.ports.out.expertdirectoryservices;

import es.upm.miw.apaw.domain.model.expertdirectoryservices.ExpertServiceSchedule;

public interface ExpertServiceScheduleGateway {
    ExpertServiceSchedule create(ExpertServiceSchedule expertServiceSchedule);

    boolean existsByTariffCode(String tariffCode);
}