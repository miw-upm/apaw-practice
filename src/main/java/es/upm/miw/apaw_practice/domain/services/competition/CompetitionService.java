package es.upm.miw.apaw_practice.domain.services.competition;

import es.upm.miw.apaw_practice.domain.models.competition.Competition;
import es.upm.miw.apaw_practice.domain.persistence_ports.competition.CompetitionPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetitionService {

    private final CompetitionPersistence competitionPersistence;

    @Autowired
    public CompetitionService(CompetitionPersistence competitionPersistence) {
        this.competitionPersistence = competitionPersistence;
    }

    public Competition readById(String id){
        return this.competitionPersistence.readById(id);
    }

    public List<String> competitionNameByPlayerId(String idPlayerTeam){
        return this.competitionPersistence.competitionNameByPlayerId(idPlayerTeam);
    }
}
