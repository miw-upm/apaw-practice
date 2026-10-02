package es.upm.miw.apaw.domain.services.copyright;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.copyright.Claim;
import es.upm.miw.apaw.domain.model.copyright.ClaimCreation;
import es.upm.miw.apaw.domain.ports.out.copyright.ClaimGateway;
import es.upm.miw.apaw.domain.ports.out.copyright.CreativeWorkGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClaimService {
    private final ClaimGateway claimGateway;
    private final CreativeWorkGateway creativeWorkGateway;
    private final UserFinder userFinder;

    public Claim create(ClaimCreation claimCreation) {
        if (this.claimGateway.existsByNumber(claimCreation.getNumber())) {
            throw new ConflictException("Claim number already exists: " + claimCreation.getNumber());
        }
        if (!this.creativeWorkGateway.existsById(claimCreation.getCreativeWorkId())) {
            throw new NotFoundException("CreativeWork id not found: " + claimCreation.getCreativeWorkId());
        }
        
        UserSnapshot defendant = this.userFinder.read(claimCreation.getDefendantId());

        Claim claim = Claim.builder()
                .number(claimCreation.getNumber())
                .requestedCompensation(claimCreation.getRequestedCompensation())
                .urgent(claimCreation.getUrgent())
                .resolutionNotes(claimCreation.getResolutionNotes())
                .userSnapshot(defendant)
                .build();
        claim.doDefault();

        return this.claimGateway.create(claim, claimCreation.getCreativeWorkId());
    }
}
