package es.upm.miw.apaw.domain.services.euregulation;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.model.euregulation.EURegulation;
import es.upm.miw.apaw.domain.ports.out.euregulation.EURegulationGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EURegulationService {

    private final EURegulationGateway euRegulationGateway;

    public EURegulation create(EURegulation euRegulation) {
        if (this.euRegulationGateway.existsByOfficialReferenceNumber(euRegulation.getOfficialReferenceNumber())) {
            throw new ConflictException(
                    "EU regulation official reference number already exists: "
                            + euRegulation.getOfficialReferenceNumber());
        }
        euRegulation.doDefault(this.euRegulationGateway.findMaxSequentialId());
        if (this.euRegulationGateway.existsBySequentialId(euRegulation.getSequentialId())) {
            throw new ConflictException(
                    "EU regulation sequential ID already exists: " + euRegulation.getSequentialId());
        }
        return this.euRegulationGateway.create(euRegulation);
    }
}
