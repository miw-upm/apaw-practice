package es.upm.miw.apaw.domain.services.expertdirectoryservices;

import es.upm.miw.apaw.domain.exceptions.BadRequestException;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.model.expertdirectoryservices.LegalExpertProfile;
import es.upm.miw.apaw.domain.ports.out.expertdirectoryservices.ExpertServiceScheduleGateway;
import es.upm.miw.apaw.domain.ports.out.expertdirectoryservices.LegalExpertProfileGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.stream.Stream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LegalExpertProfileService {

    private final LegalExpertProfileGateway legalExpertProfileGateway;
    private final ExpertServiceScheduleGateway expertServiceScheduleGateway;

    public LegalExpertProfile create(LegalExpertProfile legalExpertProfile) {

        if (this.legalExpertProfileGateway.existsByTaxIdCode(legalExpertProfile.getTaxIdCode())) {
            throw new ConflictException("Ya existe un perfil con este taxIdCode: " + legalExpertProfile.getTaxIdCode());
        }

        if (legalExpertProfile.getProfessionalLicense() != null
                && !legalExpertProfile.getProfessionalLicense().isBlank()) {
            if (this.legalExpertProfileGateway
                    .existsByProfessionalLicense(legalExpertProfile.getProfessionalLicense())) {
                throw new ConflictException("Ya existe un perfil con esta professionalLicense: "
                        + legalExpertProfile.getProfessionalLicense());
            }
        }

        legalExpertProfile.doDefault();
        return this.legalExpertProfileGateway.create(legalExpertProfile);
    }

    public LegalExpertProfile read(String id) {
        this.assertValidId(id);
        return this.legalExpertProfileGateway.read(id).ofSummary();
    }

    public LegalExpertProfile update(String id, LegalExpertProfile legalExpertProfile) {
        this.assertValidId(id);
        LegalExpertProfile existingProfile = this.legalExpertProfileGateway.read(id);

        if (!existingProfile.getTaxIdCode().equals(legalExpertProfile.getTaxIdCode())
                && this.legalExpertProfileGateway.existsByTaxIdCode(legalExpertProfile.getTaxIdCode())) {
            throw new ConflictException(
                    "Ya existe otro perfil con este taxIdCode: " + legalExpertProfile.getTaxIdCode());
        }

        if (legalExpertProfile.getProfessionalLicense() != null
                && !legalExpertProfile.getProfessionalLicense().isBlank()
                && !legalExpertProfile.getProfessionalLicense().equals(existingProfile.getProfessionalLicense())
                && this.legalExpertProfileGateway
                        .existsByProfessionalLicense(legalExpertProfile.getProfessionalLicense())) {
            throw new ConflictException("Ya existe otro perfil con esta professionalLicense: "
                    + legalExpertProfile.getProfessionalLicense());
        }

        legalExpertProfile.setId(UUID.fromString(id));
        if (legalExpertProfile.getRequiresPrepayment() == null) {
            legalExpertProfile.setRequiresPrepayment(false);
        }

        return this.legalExpertProfileGateway.update(legalExpertProfile);
    }

    public void delete(String id) {
        UUID profileId = this.assertValidId(id);
        if (this.expertServiceScheduleGateway.existsByLegalExpertProfileIds(List.of(profileId))) {
            throw new ConflictException("No se puede eliminar el perfil " + id
                    + " porque está asociado a una tarifa (ExpertServiceSchedule)");
        }
        this.legalExpertProfileGateway.delete(id);
    }

    public Stream<LegalExpertProfile> findAll() {
        return this.legalExpertProfileGateway.findAll()
                .map(LegalExpertProfile::ofSummary);
    }

    public void updatePartial(List<LegalExpertProfile> updates) {
        this.assertUniqueIds(updates);

        List<LegalExpertProfile> profilesToUpdate = updates.stream()
                .map(patchProfile -> {
                    LegalExpertProfile existingProfile = this.legalExpertProfileGateway
                            .read(patchProfile.getId().toString());

                    if (patchProfile.getTaxIdCode() != null) {
                        if (!existingProfile.getTaxIdCode().equals(patchProfile.getTaxIdCode())
                                && this.legalExpertProfileGateway.existsByTaxIdCode(patchProfile.getTaxIdCode())) {
                            throw new ConflictException(
                                    "Ya existe otro perfil con este taxIdCode: " + patchProfile.getTaxIdCode());
                        }
                        existingProfile.setTaxIdCode(patchProfile.getTaxIdCode());
                    }

                    if (patchProfile.getProfessionalLicense() != null
                            && !patchProfile.getProfessionalLicense().isBlank()) {
                        if (!patchProfile.getProfessionalLicense().equals(existingProfile.getProfessionalLicense())
                                && this.legalExpertProfileGateway
                                        .existsByProfessionalLicense(patchProfile.getProfessionalLicense())) {
                            throw new ConflictException("Ya existe otro perfil con esta professionalLicense: "
                                    + patchProfile.getProfessionalLicense());
                        }
                        existingProfile.setProfessionalLicense(patchProfile.getProfessionalLicense());
                    }

                    if (patchProfile.getSpecialtyArea() != null) {
                        existingProfile.setSpecialtyArea(patchProfile.getSpecialtyArea());
                    }

                    if (patchProfile.getYearsOfExperience() != null) {
                        existingProfile.setYearsOfExperience(patchProfile.getYearsOfExperience());
                    }

                    if (patchProfile.getRequiresPrepayment() != null) {
                        existingProfile.setRequiresPrepayment(patchProfile.getRequiresPrepayment());
                    }

                    if (patchProfile.getUserSnapshot() != null) {
                        existingProfile.setUserSnapshot(patchProfile.getUserSnapshot());
                    }

                    return existingProfile;
                })
                .toList();

        profilesToUpdate.forEach(this.legalExpertProfileGateway::update);
    }

    private UUID assertValidId(String id) {
        try {
            return UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Identificador no válido: " + id, e);
        }
    }

    private void assertUniqueIds(List<LegalExpertProfile> updates) {
        long uniqueIdsCount = updates.stream()
                .map(LegalExpertProfile::getId)
                .distinct()
                .count();
        if (uniqueIdsCount != updates.size()) {
            throw new ConflictException("IDs duplicados en la petición de actualización parcial");
        }
    }
}