package es.upm.miw.apaw.domain.services.leases;

import es.upm.miw.apaw.domain.exceptions.BadRequestException;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.leases.Amendment;
import es.upm.miw.apaw.domain.model.leases.CreationLease;
import es.upm.miw.apaw.domain.model.leases.Lease;
import es.upm.miw.apaw.domain.model.leases.LeaseAmendmentReport;
import es.upm.miw.apaw.domain.model.leases.LeaseFindCriteria;
import es.upm.miw.apaw.domain.ports.out.leases.AmendmentGateway;
import es.upm.miw.apaw.domain.ports.out.leases.LeaseGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeaseService {
    private final LeaseGateway leaseGateway;
    private final AmendmentGateway amendmentGateway;
    private final UserFinder userFinder;

    public Lease create(CreationLease creation) {
        if (!creation.hasValidPeriod()) {
            throw new BadRequestException("End date is before start date: " + creation.getEndDate());
        }
        this.assertUniqueAttributes(creation);
        List<UUID> amendmentIds = creation.getAmendmentIds() == null ? List.of() : creation.getAmendmentIds();
        this.assertUniqueIds(amendmentIds);
        Lease lease = new Lease();
        BeanUtils.copyProperties(creation, lease);
        lease.setAmendments(amendmentIds.stream()
                .map(this::readFreeAmendment)
                .toList());
        lease.setUserSnapshot(this.userFinder.read(creation.getUserId()));
        lease.doDefault();
        return this.leaseGateway.create(lease);
    }

    public List<Lease> find(LeaseFindCriteria criteria) {
        List<Lease> leases = this.leaseGateway.find(criteria);
        if (leases.isEmpty()) {
            return List.of();
        }
        Set<UUID> userIds = leases.stream()
                .map(lease -> lease.getUserSnapshot().getId())
                .collect(Collectors.toSet());
        Map<UUID, UserSnapshot> usersById = this.userFinder.findByIds(userIds).stream()
                .collect(Collectors.toMap(UserSnapshot::getId, Function.identity()));
        return leases.stream()
                .map(lease -> this.enrichUserSnapshot(lease, usersById))
                .filter(lease -> this.matchesUserMobile(criteria, lease))
                .map(Lease::ofSummary)
                .toList();
    }

    public List<LeaseAmendmentReport> findAmendmentReport() {
        return this.leaseGateway.findAmendmentReport();
    }

    private Lease enrichUserSnapshot(Lease lease, Map<UUID, UserSnapshot> usersById) {
        UUID userId = lease.getUserSnapshot().getId();
        UserSnapshot user = usersById.get(userId);
        if (user == null) {
            throw new NotFoundException("User id not found: " + userId);
        }
        lease.setUserSnapshot(user);
        return lease;
    }

    private boolean matchesUserMobile(LeaseFindCriteria criteria, Lease lease) {
        return !criteria.hasUserMobile() || criteria.getUserMobile().equals(lease.getUserSnapshot().getMobile());
    }

    private void assertUniqueAttributes(CreationLease creation) {
        if (this.leaseGateway.existsByLeaseNumber(creation.getLeaseNumber())) {
            throw new ConflictException("Lease number already exists: " + creation.getLeaseNumber());
        }
        if (creation.getCadastralReference() != null
                && this.leaseGateway.existsByCadastralReference(creation.getCadastralReference())) {
            throw new ConflictException("Cadastral reference already exists: " + creation.getCadastralReference());
        }
    }

    private void assertUniqueIds(List<UUID> amendmentIds) {
        Set<UUID> ids = new HashSet<>();
        for (UUID id : amendmentIds) {
            if (!ids.add(id)) {
                throw new BadRequestException("Repeated amendment id: " + id);
            }
        }
    }

    private Amendment readFreeAmendment(UUID id) {
        Amendment amendment = this.amendmentGateway.read(id)
                .orElseThrow(() -> new NotFoundException("Amendment id not found: " + id));
        if (this.amendmentGateway.isReferenced(id)) {
            throw new ConflictException("Amendment already belongs to a lease: " + id);
        }
        return amendment;
    }
}
