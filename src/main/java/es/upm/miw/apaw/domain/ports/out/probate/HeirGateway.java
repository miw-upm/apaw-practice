package es.upm.miw.apaw.domain.ports.out.probate;

import es.upm.miw.apaw.domain.model.probate.Heir;

import java.util.List;

public interface HeirGateway {
    Heir create(Heir heir);

    List<Heir> findAll();

    boolean existsByNationalId(String nationalId);
}