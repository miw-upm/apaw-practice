package es.upm.miw.apaw.domain.services.judicialcourt;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.judicialcourt.JudicialCourtType;
import es.upm.miw.apaw.domain.model.judicialcourt.JudicialCourtTypeUpdate;
import es.upm.miw.apaw.domain.ports.out.judicialcourt.JudicialCourtTypeGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JudicialCourtTypeService {
    private final JudicialCourtTypeGateway judicialCourtTypeGateway;

    public JudicialCourtType create(JudicialCourtType judicialCourtType) {
        this.validateNameAndCodeConflict(judicialCourtType.getName(), judicialCourtType.getCode(), null, null);
        judicialCourtType.doDefault();
        return this.judicialCourtTypeGateway.create(judicialCourtType);
    }

    public List<JudicialCourtType> findAll() {
        return this.judicialCourtTypeGateway.findAll();
    }

    public JudicialCourtType read(UUID id) {
        return this.judicialCourtTypeGateway.read(id)
                .orElseThrow(() -> new NotFoundException("Judicial Court type's id not found: " + id));
    }

    public JudicialCourtType update(UUID id, JudicialCourtType judicialCourtType) {
        JudicialCourtType storedJudicialCourtType = this.read(id);
        this.validateNameAndCodeConflict(judicialCourtType.getName(), judicialCourtType.getCode(),
                storedJudicialCourtType.getName(), storedJudicialCourtType.getCode());

        storedJudicialCourtType.setName(judicialCourtType.getName());
        storedJudicialCourtType.setDescription(judicialCourtType.getDescription());
        storedJudicialCourtType.setCode(judicialCourtType.getCode());
        storedJudicialCourtType.setJurisdiction(judicialCourtType.getJurisdiction());
        storedJudicialCourtType.setActive(judicialCourtType.getActive());
        return this.judicialCourtTypeGateway.update(storedJudicialCourtType);
    }

    public JudicialCourtType patch(UUID id, JudicialCourtTypeUpdate update) {
        JudicialCourtType storedJudicialCourtType = this.read(id);
        String newName = update.name() != null ? update.name() : storedJudicialCourtType.getName();
        String newCode = update.code() != null ? update.code() : storedJudicialCourtType.getCode();
        this.validateNameAndCodeConflict(newName, newCode,
                storedJudicialCourtType.getName(), storedJudicialCourtType.getCode());

        if (update.name() != null) {
            storedJudicialCourtType.setName(update.name());
        }
        if (update.description() != null) {
            storedJudicialCourtType.setDescription(update.description());
        }
        if (update.code() != null) {
            storedJudicialCourtType.setCode(update.code());
        }
        if (update.jurisdiction() != null) {
            storedJudicialCourtType.setJurisdiction(update.jurisdiction());
        }
        if (update.active() != null) {
            storedJudicialCourtType.setActive(update.active());
        }
        return this.judicialCourtTypeGateway.update(storedJudicialCourtType);
    }

    public void delete(UUID id) {
        if (this.judicialCourtTypeGateway.isReferenced(id)) {
            throw new ConflictException("Judicial Court type is referenced by a Judicial Court: " + id);
        }
        this.judicialCourtTypeGateway.delete(id);
    }

    private void validateNameAndCodeConflict(String newName, String newCode, String existingName, String existingCode) {
        boolean nameChanged = existingName == null || !existingName.equals(newName);
        boolean codeChanged = existingCode == null || !existingCode.equals(newCode);

        boolean existsByName = nameChanged && this.judicialCourtTypeGateway.existsByName(newName);
        boolean existsByCode = codeChanged && this.judicialCourtTypeGateway.existsByCode(newCode);

        if (existsByName && existsByCode) {
            throw new ConflictException("Judicial Court type's name and code already exist: "
                    + newName + " / " + newCode);
        }
        if (existsByName) {
            throw new ConflictException("Judicial Court type's name already exists: " + newName);
        }
        if (existsByCode) {
            throw new ConflictException("Judicial Court type's code already exists: " + newCode);
        }
    }
}
