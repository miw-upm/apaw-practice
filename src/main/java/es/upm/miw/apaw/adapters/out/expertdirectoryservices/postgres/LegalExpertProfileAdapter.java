package es.upm.miw.apaw.adapters.out.expertdirectoryservices.postgres;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.expertdirectoryservices.LegalExpertProfile;
import es.upm.miw.apaw.domain.model.expertdirectoryservices.reports.LegalExpertProfileSpecialtyReport;
import es.upm.miw.apaw.domain.ports.out.expertdirectoryservices.LegalExpertProfileGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Repository
@RequiredArgsConstructor
public class LegalExpertProfileAdapter implements LegalExpertProfileGateway {

    private final LegalExpertProfileRepository legalExpertProfileRepository;
    private final ExpertServiceScheduleRepository expertServiceScheduleRepository;

    @Override
    public LegalExpertProfile create(LegalExpertProfile legalExpertProfile) {
        LegalExpertProfileEntity entity = new LegalExpertProfileEntity(legalExpertProfile);
        return this.legalExpertProfileRepository.save(entity).toDomain();
    }

    @Override
    public boolean existsByTaxIdCode(String taxIdCode) {
        return this.legalExpertProfileRepository.existsByTaxIdCode(taxIdCode);
    }

    @Override
    public boolean existsByProfessionalLicense(String professionalLicense) {
        return this.legalExpertProfileRepository.existsByProfessionalLicense(professionalLicense);
    }

    @Override
    public LegalExpertProfile read(String id) {
        return this.legalExpertProfileRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("Legal expert profile id: " + id))
                .toDomain();
    }

    @Override
    public List<LegalExpertProfile> readAllByIds(List<UUID> ids) {
        return this.legalExpertProfileRepository.findAllById(ids).stream()
                .sorted(Comparator.comparingInt(entity -> ids.indexOf(entity.getId())))
                .map(LegalExpertProfileEntity::toDomain)
                .toList();
    }

    @Override
    public LegalExpertProfile update(LegalExpertProfile legalExpertProfile) {
        LegalExpertProfileEntity entity = this.legalExpertProfileRepository.findById(legalExpertProfile.getId())
                .orElseThrow(() -> new NotFoundException("Legal expert profile id: " + legalExpertProfile.getId()));

        BeanUtils.copyProperties(legalExpertProfile, entity, "partnershipDate", "userSnapshot", "userId");

        if (legalExpertProfile.getUserSnapshot() != null) {
            entity.setUserId(legalExpertProfile.getUserSnapshot().getId());
        }

        return this.legalExpertProfileRepository.save(entity).toDomain();
    }

    @Override
    public void delete(String id) {
        this.legalExpertProfileRepository.deleteById(UUID.fromString(id));
    }

    @Override
    public Stream<LegalExpertProfile> findAll() {
        return this.legalExpertProfileRepository.findAll(Sort.by(Sort.Direction.ASC, "taxIdCode"))
                .stream()
                .map(LegalExpertProfileEntity::toDomain);
    }

    @Override
    public List<LegalExpertProfileSpecialtyReport> findSpecialtyReport() {
        return this.expertServiceScheduleRepository.findSpecialtyReportRows().stream()
                .map(SpecialtyReportRow::toDomain)
                .toList();
    }
}