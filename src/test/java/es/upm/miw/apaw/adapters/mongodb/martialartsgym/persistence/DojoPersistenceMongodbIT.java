package es.upm.miw.apaw.adapters.mongodb.martialartsgym.persistence;

import es.upm.miw.apaw.adapters.mongodb.martialartsgym.daos.DojoRepository;
import es.upm.miw.apaw.adapters.mongodb.martialartsgym.entities.DojoEntity;
import es.upm.miw.apaw.domain.models.martialartsgym.Dojo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class DojoPersistenceMongodbIT {

    @Autowired
    private DojoPersistenceMongodb dojoPersistenceMongodb;

    @Autowired
    private DojoRepository dojoRepository;

    @BeforeEach
    void setUp() {
        dojoRepository.deleteAll();
    }

    @Test
    void testCreateDojo() {
        Dojo dojo = Dojo.builder()
                .cadastralReference("D-2001")
                .city("Granada")
                .foundationDate(LocalDate.of(2021, 10, 10))
                .build();

        Dojo saved = this.dojoPersistenceMongodb.create(dojo);

        assertThat(saved).isNotNull();
        assertThat(saved.getCadastralReference()).isEqualTo("D-2001");
        assertThat(saved.getCity()).isEqualTo("Granada");

        DojoEntity persisted = dojoRepository.findById("D-2001").orElseThrow();
        assertThat(persisted.getCity()).isEqualTo("Granada");
        assertThat(persisted.getFoundationDate()).isEqualTo(LocalDate.of(2021, 10, 10));
    }
}
