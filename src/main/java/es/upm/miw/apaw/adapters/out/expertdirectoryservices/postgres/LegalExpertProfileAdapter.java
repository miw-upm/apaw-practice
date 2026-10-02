package es.upm.miw.apaw.adapters.out.expertdirectoryservices.postgres;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.expertdirectoryservices.LegalExpertProfile;
import es.upm.miw.apaw.domain.ports.out.expertdirectoryservices.LegalExpertProfileGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import java.util.stream.Stream;
import org.springframework.data.domain.Sort;

@Repository
@RequiredArgsConstructor
public class LegalExpertProfileAdapter implements LegalExpertProfileGateway {

    private final LegalExpertProfileRepository legalExpertProfileRepository;

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
        try {
            this.legalExpertProfileRepository.deleteById(UUID.fromString(id));
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException(
                    "No se puede eliminar el perfil porque está siendo referenciado por una entidad principal.");
        }
    }

    @Override
    public Stream<LegalExpertProfile> findAll() {
        return this.legalExpertProfileRepository.findAll(Sort.by(Sort.Direction.ASC, "taxIdCode"))
                .stream()
                .map(LegalExpertProfileEntity::toDomain);
    }
}