package es.upm.miw.apaw.domain.services.copyright;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.model.copyright.Claim;
import es.upm.miw.apaw.domain.ports.out.copyright.ClaimGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClaimService {
    private final ClaimGateway claimGateway;

    public Claim create(Claim claim) {
        if (this.claimGateway.existsByNumber(claim.getNumber())) {
            throw new ConflictException("Claim number already exists: " + claim.getNumber());
        }
        claim.doDefault();
        return this.claimGateway.create(claim);
    }
}
