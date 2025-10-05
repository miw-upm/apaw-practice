package es.upm.miw.apaw.domain.services.sports.academy;

import es.upm.miw.apaw.domain.models.sports.academy.LegalGuardian;
import es.upm.miw.apaw.domain.persistenceports.sports.academy.ILegalGuardianPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LegalGuardianService {

    private final ILegalGuardianPersistence legalGuardianPersistence;

    @Autowired
    public LegalGuardianService(ILegalGuardianPersistence legalGuardianPersistence) {
        this.legalGuardianPersistence = legalGuardianPersistence;
    }

    public LegalGuardian update(java.util.UUID id, LegalGuardian legalGuardian) {
        var existingLegalGuardian = this.legalGuardianPersistence.getById(id);
        existingLegalGuardian.setRelationShip(legalGuardian.getRelationShip());
        existingLegalGuardian.setSecondMobile(legalGuardian.getSecondMobile());
        return this.legalGuardianPersistence.update(id, existingLegalGuardian);
    }
}
