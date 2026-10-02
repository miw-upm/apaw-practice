package es.upm.miw.apaw.domain.services.immigrationissues;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.immigrationissues.LawBasis;
import es.upm.miw.apaw.domain.ports.out.immigrationissues.LawBasisGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LawBasisService {

    private final LawBasisGateway lawBasisGateway;

    public LawBasis create(LawBasis lawBasis) {
        if (this.lawBasisGateway.existsByLawCode(lawBasis.getLawCode())) {
            throw new ConflictException("Law basis law code already exists: " + lawBasis.getLawCode());
        }
        lawBasis.doDefault();
        return this.lawBasisGateway.create(lawBasis);
    }

    public LawBasis read(UUID id) {
        return this.lawBasisGateway.read(id)
                .orElseThrow(() -> new NotFoundException("Law basis id not found: " + id));
    }
}