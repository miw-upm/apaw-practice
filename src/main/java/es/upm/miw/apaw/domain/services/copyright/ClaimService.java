package es.upm.miw.apaw.domain.services.copyright;

import es.upm.miw.apaw.domain.exceptions.BadRequestException;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.copyright.Claim;
import es.upm.miw.apaw.domain.model.copyright.ClaimCreation;
import es.upm.miw.apaw.domain.model.copyright.ClaimTaskStatusUpdate;
import es.upm.miw.apaw.domain.ports.out.copyright.ClaimGateway;
import es.upm.miw.apaw.domain.ports.out.copyright.CreativeWorkGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

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
                .defendant(defendant)
                .build();
        claim.doDefault();

        return this.claimGateway.create(claim, claimCreation.getCreativeWorkId());
    }

    public Claim read(UUID id) {
        return this.claimGateway.read(id)
                .orElseThrow(() -> new NotFoundException("Claim id not found: " + id));
    }

    public Claim update(UUID id, Claim claim) {
        Claim storedClaim = this.read(id);
        
        if (!storedClaim.getNumber().equals(claim.getNumber())
                && this.claimGateway.existsByNumber(claim.getNumber())) {
            throw new ConflictException("Claim number already exists: " + claim.getNumber());
        }

        storedClaim.setNumber(claim.getNumber());
        storedClaim.setRequestedCompensation(claim.getRequestedCompensation());
        storedClaim.setUrgent(claim.getUrgent());
        storedClaim.setResolutionNotes(claim.getResolutionNotes());
        storedClaim.setTaskStatus(claim.getTaskStatus());

        UserSnapshot defendant = this.userFinder.read(claim.getDefendant().getId());
        storedClaim.setDefendant(defendant);

        return this.claimGateway.update(storedClaim);
    }

    public void delete(UUID id) {
        this.claimGateway.delete(id);
    }

    public List<Claim> findAll() {
        return this.claimGateway.findAll();
    }

    @Transactional
    public void updateTaskStatuses(List<ClaimTaskStatusUpdate> updates) {
        this.assertUniqueIds(updates);
        List<Claim> claims = updates.stream()
                .map(update -> {
                    Claim claim = this.read(update.id());
                    claim.setTaskStatus(update.taskStatus());
                    return claim;
                })
                .toList();
        claims.forEach(this.claimGateway::update);
    }

    private void assertUniqueIds(List<ClaimTaskStatusUpdate> updates) {
        Set<UUID> ids = new HashSet<>();
        for (ClaimTaskStatusUpdate update : updates) {
            if (!ids.add(update.id())) {
                throw new BadRequestException("Repeated claim id: " + update.id());
            }
        }
    }
}
