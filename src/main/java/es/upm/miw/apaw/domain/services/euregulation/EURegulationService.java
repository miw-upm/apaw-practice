package es.upm.miw.apaw.domain.services.euregulation;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.euregulation.EURegulation;
import es.upm.miw.apaw.domain.model.euregulation.EURegulationPatch;
import es.upm.miw.apaw.domain.ports.out.euregulation.EURegulationGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    public EURegulation read(UUID id) {
        return this.euRegulationGateway.read(id)
                .orElseThrow(() -> new NotFoundException("EU regulation id not found: " + id));
    }

    public List<EURegulation> findAll() {
        return this.euRegulationGateway.findAll();
    }

    public EURegulation update(UUID id, EURegulation update) {
        EURegulation storedEURegulation = this.read(id);
        if (!storedEURegulation.getOfficialReferenceNumber().equals(update.getOfficialReferenceNumber())
                && this.euRegulationGateway.existsByOfficialReferenceNumber(update.getOfficialReferenceNumber())) {
            throw new ConflictException(
                    "EU regulation official reference number already exists: "
                            + update.getOfficialReferenceNumber());
        }

        storedEURegulation.setRegulationName(update.getRegulationName());
        storedEURegulation.setOfficialReferenceNumber(update.getOfficialReferenceNumber());
        storedEURegulation.setInstrumentType(update.getInstrumentType());
        storedEURegulation.setApplicationArea(update.getApplicationArea());
        storedEURegulation.setLegalStatus(update.getLegalStatus());
        storedEURegulation.setIssuingBody(update.getIssuingBody());
        storedEURegulation.setTranspositionDeadline(update.getTranspositionDeadline());
        storedEURegulation.setOfficialJournalLink(update.getOfficialJournalLink());
        storedEURegulation.setSummary(update.getSummary());

        return this.euRegulationGateway.update(storedEURegulation);
    }

    public EURegulation patch(UUID id, EURegulationPatch patch) {
        EURegulation storedEURegulation = this.read(id);
        String officialReferenceNumber = patch.officialReferenceNumber();
        if (officialReferenceNumber != null
                && !storedEURegulation.getOfficialReferenceNumber().equals(officialReferenceNumber)
                && this.euRegulationGateway.existsByOfficialReferenceNumber(officialReferenceNumber)) {
            throw new ConflictException(
                    "EU regulation official reference number already exists: " + officialReferenceNumber);
        }

        if (patch.regulationName() != null) {
            storedEURegulation.setRegulationName(patch.regulationName());
        }
        if (officialReferenceNumber != null) {
            storedEURegulation.setOfficialReferenceNumber(officialReferenceNumber);
        }
        if (patch.instrumentType() != null) {
            storedEURegulation.setInstrumentType(patch.instrumentType());
        }
        if (patch.applicationArea() != null) {
            storedEURegulation.setApplicationArea(patch.applicationArea());
        }
        if (patch.legalStatus() != null) {
            storedEURegulation.setLegalStatus(patch.legalStatus());
        }
        if (patch.issuingBody() != null) {
            storedEURegulation.setIssuingBody(patch.issuingBody());
        }
        if (patch.transpositionDeadline() != null) {
            storedEURegulation.setTranspositionDeadline(patch.transpositionDeadline());
        }
        if (patch.officialJournalLink() != null) {
            storedEURegulation.setOfficialJournalLink(patch.officialJournalLink());
        }
        if (patch.summary() != null) {
            storedEURegulation.setSummary(patch.summary());
        }

        return this.euRegulationGateway.update(storedEURegulation);
    }

    public void delete(UUID id) {
        this.read(id);
        if (this.euRegulationGateway.isReferenced(id)) {
            throw new ConflictException("EU regulation is referenced by a compliance assessment: " + id);
        }
        this.euRegulationGateway.delete(id);
    }
}
