package es.upm.miw.apaw.domain.ports.out.expertdirectoryservices;

import es.upm.miw.apaw.domain.model.expertdirectoryservices.ExpertServiceSchedule;

import java.util.Collection;
import java.util.UUID;

public interface ExpertServiceScheduleGateway {
    ExpertServiceSchedule create(ExpertServiceSchedule expertServiceSchedule);

    boolean existsByTariffCode(String tariffCode);

    boolean existsByLegalExpertProfileIds(Collection<UUID> legalExpertProfileIds);
}