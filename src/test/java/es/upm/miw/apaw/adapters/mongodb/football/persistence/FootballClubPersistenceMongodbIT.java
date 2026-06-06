package es.upm.miw.apaw.adapters.mongodb.football.persistence;

import es.upm.miw.apaw.adapters.mongodb.football.daos.FootballSeeder;
import es.upm.miw.apaw.domain.models.football.FootballClub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class FootballClubPersistenceMongodbIT {

    @Autowired
    private FootballClubPersistenceMongodb clubPersistence;

    @Autowired
    private FootballSeeder footballSeeder;

    @BeforeEach
    void resetDb() {
        footballSeeder.deleteAll();
        footballSeeder.seedDatabase();
    }

    @Test
    void testFindByName_ok() {
        Optional<FootballClub> club = this.clubPersistence.findByName("Salamanca FC");
        assertThat(club).isPresent();
        assertThat(club.get().getName()).isEqualTo("Salamanca FC");
        assertThat(club.get().getBudget()).isEqualByComparingTo(new BigDecimal("4500000"));
    }

    @Test
    void testFindByName_notFound() {
        Optional<FootballClub> club = this.clubPersistence.findByName("NonExistent FC");
        assertThat(club).isEmpty();
    }

    @Test
    void testReadAll_ok() {
        List<FootballClub> clubs = this.clubPersistence.readAll();
        assertThat(clubs).hasSize(2);
        assertThat(clubs).extracting(FootballClub::getName)
                .containsExactlyInAnyOrder("Salamanca FC", "Madrid United");
    }

    @Test
    void testFindByClubId_ok() {
        Optional<FootballClub> club = this.clubPersistence.findByClubId(1L);
        assertThat(club).isPresent();
        assertThat(club.get().getName()).isEqualTo("Salamanca FC");
        assertThat(club.get().getBudget()).isEqualByComparingTo(new BigDecimal("4500000"));
    }

    @Test
    void testFindByClubId_notFound() {
        assertThat(this.clubPersistence.findByClubId(999L)).isEmpty();
    }

    @Test
    void testSave_ok() {
        FootballClub newClub = FootballClub.builder()
                .clubId(3L)
                .name("Barcelona FC")
                .budget(new BigDecimal("7000000"))
                .build();

        FootballClub saved = this.clubPersistence.save(newClub);
        assertThat(saved.getName()).isEqualTo("Barcelona FC");
        assertThat(saved.getBudget()).isEqualByComparingTo("7000000");
    }

    @Test
    void testDelete_ok() {
        this.clubPersistence.delete(2L);
        List<FootballClub> clubs = this.clubPersistence.readAll();
        assertThat(clubs).hasSize(1);
    }
}