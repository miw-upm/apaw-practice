package es.upm.miw.apaw.domain.services.fighters;

import es.upm.miw.apaw.domain.models.fighters.Coach;
import es.upm.miw.apaw.domain.models.fighters.Fighter;
import es.upm.miw.apaw.domain.persistenceports.fighters.CoachPersistence;
import es.upm.miw.apaw.domain.persistenceports.fighters.FighterPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CoachService {
    private final CoachPersistence coachPersistence;
    private final FighterPersistence fighterPersistence;

    @Autowired
    public CoachService(CoachPersistence coachPersistence, FighterPersistence fighterPersistence) {
        this.coachPersistence = coachPersistence;
        this.fighterPersistence = fighterPersistence;
    }

    public Coach readByFullName(String fullName) {
        return coachPersistence.readByFullName(fullName);
    }
    public int findCoachExperienceYearsSumByRatingComment(String comment) {
        return this.fighterPersistence.findByRatingComment(comment)
                .map(Fighter::getCoach)
                .filter(Objects::nonNull)
                .filter(c -> c.getId() != null)
                .distinct()
                .mapToInt(Coach::getExperienceYears)
                .sum();
    }
}
