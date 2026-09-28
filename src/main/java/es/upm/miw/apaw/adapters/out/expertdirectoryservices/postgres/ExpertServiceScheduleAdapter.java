package es.upm.miw.apaw.adapters.out.expertdirectoryservices.postgres;

import es.upm.miw.apaw.domain.ports.out.expertdirectoryservices.ExpertServiceScheduleGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ExpertServiceScheduleAdapter implements ExpertServiceScheduleGateway {
    private final ExpertServiceScheduleRepository expertServiceScheduleRepository;
}