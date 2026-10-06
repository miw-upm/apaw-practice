package es.upm.miw.apaw.domain.ports.out.probate;

import es.upm.miw.apaw.domain.model.probate.Estate;

public interface EstateGateway {
    Estate create(Estate estate);
}
