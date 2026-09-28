package es.upm.miw.apaw.domain.ports.out.courthearing;

import es.upm.miw.apaw.domain.model.courthearing.Court;

public interface CourtGateway {
    Court create(Court court);

    boolean existsByName(String name);

    boolean existsByPhone(String phone);
}