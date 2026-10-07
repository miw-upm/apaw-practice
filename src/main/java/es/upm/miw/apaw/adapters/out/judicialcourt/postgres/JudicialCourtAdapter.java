package es.upm.miw.apaw.adapters.out.judicialcourt.postgres;

import es.upm.miw.apaw.domain.model.judicialcourt.JudicialCourt;
import es.upm.miw.apaw.domain.model.judicialcourt.LawyerCourtStat;
import es.upm.miw.apaw.domain.ports.out.judicialcourt.JudicialCourtGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JudicialCourtAdapter implements JudicialCourtGateway {
    private final JudicialCourtRepository judicialCourtRepository;

    @Override
    public JudicialCourt create(JudicialCourt judicialCourt) {
        return this.judicialCourtRepository.save(new JudicialCourtEntity(judicialCourt)).toDomain();
    }

    @Override
    public List<LawyerCourtStat> findLawyerCourtStats() {
        return this.judicialCourtRepository.findLawyerCourtStats();
    }
}
