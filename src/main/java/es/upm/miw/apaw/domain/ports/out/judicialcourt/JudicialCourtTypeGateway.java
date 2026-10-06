package es.upm.miw.apaw.domain.ports.out.judicialcourt;

import es.upm.miw.apaw.domain.model.judicialcourt.JudicialCourtType;

public interface JudicialCourtTypeGateway {
    JudicialCourtType create(JudicialCourtType judicialCourtType);

    boolean existsByName(String name);

    boolean existsByCode(String code);
}
