package es.upm.miw.apaw.domain.ports.out.judicialcourt;

import es.upm.miw.apaw.domain.model.judicialcourt.JudicialCourt;
import es.upm.miw.apaw.domain.model.judicialcourt.JudicialCourtFindCriteria;
import es.upm.miw.apaw.domain.model.judicialcourt.LawyerCourtStat;

import java.util.List;

public interface JudicialCourtGateway {
    JudicialCourt create(JudicialCourt judicialCourt);

    List<JudicialCourt> find(JudicialCourtFindCriteria criteria);

    List<LawyerCourtStat> findLawyerCourtStats();
}
