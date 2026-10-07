package es.upm.miw.apaw.domain.ports.out.judicialcourt;

import es.upm.miw.apaw.domain.model.judicialcourt.JudicialCourt;

public interface JudicialCourtGateway {
    JudicialCourt create(JudicialCourt judicialCourt);
}
