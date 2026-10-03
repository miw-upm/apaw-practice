package es.upm.miw.apaw.adapters.out.evidencemanagement.postgres;

import es.upm.miw.apaw.domain.model.evidencemanagement.CustodyRecord;
import es.upm.miw.apaw.domain.ports.out.evidencemanagement.CustodyRecordGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustodyRecordAdapter implements CustodyRecordGateway {

    private final CustodyRecordRepository custodyRecordRepository;

    @Override
    public CustodyRecord create(CustodyRecord custodyRecord) {
        return this.custodyRecordRepository
                .save(new CustodyRecordEntity(custodyRecord))
                .toDomain();
    }
}
