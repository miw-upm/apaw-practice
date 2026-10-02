package es.upm.miw.apaw.adapters.out.deadlinecalculator.postgres;

import es.upm.miw.apaw.domain.ports.out.deadlinecalculator.DeadlineGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DeadlineAdapter implements DeadlineGateway {
    private final DeadlineRepository deadlineRepository;
}
