package es.upm.miw.apaw.domain.services.judicialcourt;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.judicialcourt.JudicialCourtType;
import es.upm.miw.apaw.domain.model.judicialcourt.JudicialCourtTypePatch;
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

    public JudicialCourtType patch(UUID id, JudicialCourtTypePatch patch) {
        JudicialCourtType storedJudicialCourtType = this.read(id);
        String newName = patch.name() != null ? patch.name() : storedJudicialCourtType.getName();
        String newCode = patch.code() != null ? patch.code() : storedJudicialCourtType.getCode();
        this.validateNameAndCodeConflict(newName, newCode,
                storedJudicialCourtType.getName(), storedJudicialCourtType.getCode());

        if (patch.name() != null) {
            storedJudicialCourtType.setName(patch.name());
        }
        if (patch.description() != null) {
            storedJudicialCourtType.setDescription(patch.description());
        }
        if (patch.code() != null) {
            storedJudicialCourtType.setCode(patch.code());
        }
        if (patch.jurisdiction() != null) {
            storedJudicialCourtType.setJurisdiction(patch.jurisdiction());
        }
        if (patch.active() != null) {
            storedJudicialCourtType.setActive(patch.active());
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
