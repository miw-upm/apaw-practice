package es.upm.miw.apaw.domain.services.judicialcourt;

import es.upm.miw.apaw.domain.exceptions.BadRequestException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.judicialcourt.CreationJudicialCourt;
import es.upm.miw.apaw.domain.model.judicialcourt.JudicialCourt;
import es.upm.miw.apaw.domain.model.judicialcourt.JudicialCourtType;
import es.upm.miw.apaw.domain.ports.out.judicialcourt.JudicialCourtGateway;
import es.upm.miw.apaw.domain.ports.out.judicialcourt.JudicialCourtTypeGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JudicialCourtService {
    private final JudicialCourtGateway judicialCourtGateway;
    private final JudicialCourtTypeGateway judicialCourtTypeGateway;
    private final UserFinder userFinder;

    public JudicialCourt create(CreationJudicialCourt creation) {
        if (creation == null) {
            throw new BadRequestException("Judicial court creation cannot be null");
        }
        if (creation.getUserId() == null) {
            throw new BadRequestException("Judicial court user id cannot be null");
        }
        if (creation.getTypeId() == null) {
            throw new BadRequestException("Judicial court type id cannot be null");
        }
        if (creation.getLawyerIds() == null || creation.getLawyerIds().isEmpty()) {
            throw new BadRequestException("Judicial court lawyer ids cannot be empty");
        }

        this.userFinder.read(creation.getUserId());

        JudicialCourtType judicialCourtType = this.judicialCourtTypeGateway.read(creation.getTypeId())
                .orElseThrow(() -> new NotFoundException("Judicial court type id not found: " + creation.getTypeId()));

        JudicialCourt judicialCourt = new JudicialCourt();
        BeanUtils.copyProperties(creation, judicialCourt, "typeId", "userId", "lawyerIds");
        judicialCourt.setType(judicialCourtType);
        judicialCourt.setLawyers(creation.getLawyerIds().stream()
                .map(id -> UserSnapshot.builder().id(id).build())
                .toList());
        judicialCourt.doDefault();
        return this.judicialCourtGateway.create(judicialCourt);
    }
}
