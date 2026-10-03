package es.upm.miw.apaw.domain.ports.out.euregulation;

import es.upm.miw.apaw.domain.model.euregulation.EURegulation;

public interface EURegulationGateway {
    Integer findMaxSequentialId();

    EURegulation create(EURegulation euRegulation);
}
