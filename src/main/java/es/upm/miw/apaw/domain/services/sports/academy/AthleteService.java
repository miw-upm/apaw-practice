package es.upm.miw.apaw.domain.services.sports.academy;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.sports.academy.Athlete;
import es.upm.miw.apaw.domain.persistenceports.sports.academy.IAthletePersistence;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AthleteService {
    private final IAthletePersistence athletePersistence;
    private final UserRestClient userRestClient;
    private final LegalGuardianService legalGuardianService;

    @Autowired
    public AthleteService(
            IAthletePersistence athletePersistence,
            UserRestClient userRestClient,
            LegalGuardianService legalGuardianService) {
        this.athletePersistence = athletePersistence;
        this.userRestClient = userRestClient;
        this.legalGuardianService = legalGuardianService;
    }

    public Athlete getById(UUID id) {
        var athlete = this.athletePersistence.getById(id);
        UserDto userDto = this.userRestClient.readById(id);
        athlete.setUser(userDto);
        athlete.getLegalGuardians().forEach(legalGuardian -> {
            UserDto userGuardianDto = this.userRestClient.readById(legalGuardian.getUser().getId());
            legalGuardian.setUser(userGuardianDto);
        });
        athlete.getSportModalities().forEach(sportModality -> {
            UserDto professor = this.userRestClient.readById(sportModality.getProfessor().getUser().getId());
            sportModality.getProfessor().setUser(professor);
        });
        return athlete;
    }

    public List<String> getDistinctProfessorsSpecializationsByLegalGuardianSecondMobile(String secondMobile){
        return athletePersistence
                .getByLegalGuardians(legalGuardianService.getBySecondMobile(secondMobile))
                .flatMap(athlete -> athlete.getSportModalities().stream())
                .map(sportModality -> sportModality.getProfessor().getSpecialization())
                .distinct()
                .toList();
    }
}
