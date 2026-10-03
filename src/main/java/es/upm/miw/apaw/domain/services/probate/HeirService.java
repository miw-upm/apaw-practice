package es.upm.miw.apaw.domain.services.probate;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.model.probate.Heir;
import es.upm.miw.apaw.domain.ports.out.probate.HeirGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HeirService {
    private final HeirGateway heirGateway;

    public Heir create(Heir heir) {
        if (this.heirGateway.existsByNationalId(heir.getNationalId())) {
            throw new ConflictException("Heir nationalId already exists: " + heir.getNationalId());
        }
        return this.heirGateway.create(heir);
    }
}