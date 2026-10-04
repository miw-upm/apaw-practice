package es.upm.miw.apaw.adapters.out.evidencemanagement.postgres;

import es.upm.miw.apaw.domain.model.evidencemanagement.CustodyRecord;
import es.upm.miw.apaw.domain.ports.out.evidencemanagement.CustodyRecordGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CustodyRecordAdapter implements CustodyRecordGateway {

    private final CustodyRecordRepository custodyRecordRepository;
    private final EvidenceRepository evidenceRepository;

    @Override
    public CustodyRecord create(CustodyRecord custodyRecord) {
        return this.custodyRecordRepository
                .save(new CustodyRecordEntity(custodyRecord))
                .toDomain();
    }

    @Override
    public Optional<CustodyRecord> read(UUID id) {
        return this.custodyRecordRepository
                .findById(id)
                .map(CustodyRecordEntity::toDomain);
    }

    @Override
    public CustodyRecord update(CustodyRecord custodyRecord) {
        return this.custodyRecordRepository
                .save(new CustodyRecordEntity(custodyRecord))
                .toDomain();
    }

    @Override
    public boolean isReferenced(UUID id) {
        return this.evidenceRepository.existsByCustodyRecordsId(id);
    }

    @Override
    public void deleteById(UUID id) {
        this.custodyRecordRepository.deleteById(id);
    }

}
