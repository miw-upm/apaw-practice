package es.upm.miw.apaw_practice.adapters.mongodb.competition.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.competition.PlayerTeam;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
class PlayerTeamPersistenceMongodbIT {
    @Autowired
    private PlayerTeamPersistenceMongodb playerTeamPersistenceMongodb;

    @Test
    void testDelete() {
        List<PlayerTeam> playerTeamList = this.playerTeamPersistenceMongodb.readAll().toList();
        this.playerTeamPersistenceMongodb.delete(playerTeamList.get(0).getId());
        assertEquals((playerTeamList.size() - 1), this.playerTeamPersistenceMongodb.readAll().toList().size());
    }
}
