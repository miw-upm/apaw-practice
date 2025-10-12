package es.upm.miw.apaw.adapters.mongodb.studentcouncil.persistence;

import es.upm.miw.apaw.domain.models.studentcouncil.Representative;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class RepresentativePersistenceMongodbIT {

    @Autowired
    private RepresentativePersistenceMongodb representativePersistence;

    @Test
    void testFindAll() {
        List<Representative> representatives = this.representativePersistence.readAll().toList();
        assertThat(representatives).isNotEmpty();
        assertThat(representatives.getFirst().getResponsibility()).isNotBlank();
        assertThat(representatives.getFirst().getJoinDate()).isNotNull();
    }
}
