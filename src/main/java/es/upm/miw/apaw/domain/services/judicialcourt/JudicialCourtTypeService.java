package es.upm.miw.apaw.domain.services.judicialcourt;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.model.judicialcourt.JudicialCourtType;
import es.upm.miw.apaw.domain.ports.out.judicialcourt.JudicialCourtTypeGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JudicialCourtTypeService {
    private final JudicialCourtTypeGateway judicialCourtTypeGateway;

    public JudicialCourtType create(JudicialCourtType judicialCourtType) {
        if (this.judicialCourtTypeGateway.existsByName(judicialCourtType.getName())) {
            throw new ConflictException("Judicial court type name already exists: " + judicialCourtType.getName());
        }
        if (this.judicialCourtTypeGateway.existsByCode(judicialCourtType.getCode())) {
            throw new ConflictException("Judicial court type code already exists: " + judicialCourtType.getCode());
        }
        judicialCourtType.doDefault();
        return this.judicialCourtTypeGateway.create(judicialCourtType);
    }
}
