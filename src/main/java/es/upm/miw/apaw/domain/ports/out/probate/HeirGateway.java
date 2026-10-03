package es.upm.miw.apaw.domain.ports.out.probate;

import es.upm.miw.apaw.domain.model.probate.Heir;

public interface HeirGateway {
    Heir create(Heir heir);

    boolean existsByNationalId(String nationalId);
}