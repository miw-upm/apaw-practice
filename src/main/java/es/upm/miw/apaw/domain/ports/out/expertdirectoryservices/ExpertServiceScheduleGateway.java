package es.upm.miw.apaw.domain.ports.out.expertdirectoryservices;

import es.upm.miw.apaw.domain.model.expertdirectoryservices.ExpertServiceSchedule;
import es.upm.miw.apaw.domain.model.expertdirectoryservices.criteria.ExpertServiceScheduleFindCriteria;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface ExpertServiceScheduleGateway {
    ExpertServiceSchedule create(ExpertServiceSchedule expertServiceSchedule);

    List<ExpertServiceSchedule> find(ExpertServiceScheduleFindCriteria criteria);

    boolean existsByTariffCode(String tariffCode);

    boolean existsByLegalExpertProfileIds(Collection<UUID> legalExpertProfileIds);
}