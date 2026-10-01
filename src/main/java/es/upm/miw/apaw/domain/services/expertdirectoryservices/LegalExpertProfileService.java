package es.upm.miw.apaw.domain.services.expertdirectoryservices;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.model.expertdirectoryservices.LegalExpertProfile;
import es.upm.miw.apaw.domain.ports.out.expertdirectoryservices.LegalExpertProfileGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LegalExpertProfileService {

    private final LegalExpertProfileGateway legalExpertProfileGateway;

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
        return this.legalExpertProfileGateway.read(id);
    }

    public LegalExpertProfile update(String id, LegalExpertProfile legalExpertProfile) {
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
}