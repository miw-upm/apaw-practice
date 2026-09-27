package es.upm.miw.apaw.adapters.out.leases.postgres;

import es.upm.miw.apaw.domain.model.leases.Lease;
import es.upm.miw.apaw.domain.ports.out.leases.LeaseGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class LeaseAdapter implements LeaseGateway {
    private final LeaseRepository leaseRepository;
    private final AmendmentRepository amendmentRepository;

    @Override
    @Transactional
    public Lease create(Lease lease) {
        LeaseEntity leaseEntity = new LeaseEntity(lease);
        List<AmendmentEntity> amendmentEntities = lease.getAmendments().stream()
                .map(amendment -> this.amendmentRepository.getReferenceById(amendment.getId()))
                .collect(Collectors.toCollection(ArrayList::new));
        leaseEntity.setAmendments(amendmentEntities);
        this.leaseRepository.save(leaseEntity);
        return lease;
    }

    @Override
    public boolean existsByLeaseNumber(String leaseNumber) {
        return this.leaseRepository.existsByLeaseNumber(leaseNumber);
    }

    @Override
    public boolean existsByCadastralReference(String cadastralReference) {
        return this.leaseRepository.existsByCadastralReference(cadastralReference);
    }
}
