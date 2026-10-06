package es.upm.miw.apaw.domain.services.probate;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.probate.Heir;
import es.upm.miw.apaw.domain.model.probate.HeirUpdate;
import es.upm.miw.apaw.domain.ports.out.probate.HeirGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HeirService {
    private final HeirGateway heirGateway;

    public Heir create(Heir heir) {
        if (this.heirGateway.existsByNationalId(heir.getNationalId())) {
            throw new ConflictException("Heir nationalId already exists: " + heir.getNationalId());
        }
        heir.doDefault();
        return this.heirGateway.create(heir);
    }

    public List<Heir> findAll() {
        return this.heirGateway.findAll();
    }

    public Heir read(UUID id) {
        return this.heirGateway.read(id)
                .orElseThrow(() -> new NotFoundException("Heir id not found: " + id));
    }

    public Heir update(UUID id, Heir heir) {
        Heir storedHeir = this.read(id);
        if (!storedHeir.getNationalId().equals(heir.getNationalId())
                && this.heirGateway.existsByNationalId(heir.getNationalId())) {
            throw new ConflictException("Heir nationalId already exists: " + heir.getNationalId());
        }
        storedHeir.setFullName(heir.getFullName());
        storedHeir.setNationalId(heir.getNationalId());
        storedHeir.setBirthDate(heir.getBirthDate());
        storedHeir.setSharePercentage(heir.getSharePercentage());
        storedHeir.setHeirStatus(heir.getHeirStatus());
        storedHeir.setContactEmail(heir.getContactEmail());
        return this.heirGateway.update(storedHeir);
    }

    public Heir patch(UUID id, HeirUpdate update) {
        Heir storedHeir = this.read(id);
        if (update.nationalId() != null
                && !storedHeir.getNationalId().equals(update.nationalId())
                && this.heirGateway.existsByNationalId(update.nationalId())) {
            throw new ConflictException("Heir nationalId already exists: " + update.nationalId());
        }
        Optional.ofNullable(update.fullName()).ifPresent(storedHeir::setFullName);
        Optional.ofNullable(update.nationalId()).ifPresent(storedHeir::setNationalId);
        Optional.ofNullable(update.birthDate()).ifPresent(storedHeir::setBirthDate);
        Optional.ofNullable(update.sharePercentage()).ifPresent(storedHeir::setSharePercentage);
        Optional.ofNullable(update.heirStatus()).ifPresent(storedHeir::setHeirStatus);
        Optional.ofNullable(update.contactEmail()).ifPresent(storedHeir::setContactEmail);
        return this.heirGateway.update(storedHeir);
    }

    public void delete(UUID id) {
        if (this.heirGateway.isReferenced(id)) {
            throw new ConflictException("Heir is referenced by a estate: " + id);
        }
        this.heirGateway.delete(id);
    }
}