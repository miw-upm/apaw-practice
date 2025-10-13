package es.upm.miw.apaw.adapters.mongodb.studentcouncil.daos;

import es.upm.miw.apaw.adapters.mongodb.studentcouncil.entitites.RepresentativeEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class RepresentativeRepositoryIT {

    @Autowired
    private RepresentativeRepository representativeRepository;

    @Test
    void testCreateAndRead() {
        RepresentativeEntity entity = RepresentativeEntity.builder()
                .id(UUID.randomUUID())
                .joinDate(LocalDateTime.now())
                .responsibility("Treasurer")
                .representativeId(UUID.randomUUID())
                .topics(List.of())
                .build();
        this.representativeRepository.save(entity);

        RepresentativeEntity found = this.representativeRepository.findById(entity.getId()).orElse(null);
        assertThat(found).isNotNull();
        assertThat(found.getResponsibility()).isEqualTo("Treasurer");
    }
}
