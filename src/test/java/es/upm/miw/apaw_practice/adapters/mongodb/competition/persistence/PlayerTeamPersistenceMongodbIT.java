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
    void testReadAll() {
        assertEquals(6, this.playerTeamPersistenceMongodb.readAll().toList().size());
    }

    @Test
    void testDelete() {
        List<PlayerTeam> playerTeamList = this.playerTeamPersistenceMongodb.readAll().toList();
        int playerTeamListSize = playerTeamList.size();
        assertEquals(playerTeamListSize, this.playerTeamPersistenceMongodb.readAll().toList().size());
        this.playerTeamPersistenceMongodb.delete(playerTeamList.get(0).getId());
        assertEquals(playerTeamListSize - 1, this.playerTeamPersistenceMongodb.readAll().toList().size());
    }
}
