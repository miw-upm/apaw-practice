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
        boolean existsByName = this.judicialCourtTypeGateway.existsByName(judicialCourtType.getName());
        boolean existsByCode = this.judicialCourtTypeGateway.existsByCode(judicialCourtType.getCode());

        if (existsByName && existsByCode) {
            throw new ConflictException("Judicial Court type's name and code already exist: "
                    + judicialCourtType.getName() + " / " + judicialCourtType.getCode());
        }
        if (existsByName) {
            throw new ConflictException("Judicial Court type's name already exists: " + judicialCourtType.getName());
        }
        if (existsByCode) {
            throw new ConflictException("Judicial Court type's code already exists: " + judicialCourtType.getCode());
        }
        judicialCourtType.doDefault();
        return this.judicialCourtTypeGateway.create(judicialCourtType);
    }
}
