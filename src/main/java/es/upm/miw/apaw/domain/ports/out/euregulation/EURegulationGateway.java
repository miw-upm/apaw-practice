package es.upm.miw.apaw.domain.ports.out.euregulation;

import es.upm.miw.apaw.domain.model.euregulation.EURegulation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EURegulationGateway {
    Integer findMaxSequentialId();

    EURegulation create(EURegulation euRegulation);

    Optional<EURegulation> read(UUID id);

    List<EURegulation> findAll();
}
