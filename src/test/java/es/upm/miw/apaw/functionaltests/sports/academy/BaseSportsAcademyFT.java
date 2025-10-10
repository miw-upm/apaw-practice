package es.upm.miw.apaw.functionaltests.sports.academy;

import es.upm.miw.apaw.adapters.mongodb.sports.academy.daos.SportsAcademySeeder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public abstract class BaseSportsAcademyFT {
    @AfterEach
    void tearDown(@Autowired SportsAcademySeeder sportsAcademySeeder) {
        sportsAcademySeeder.deleteAll();
    }

    @BeforeEach
    void beforeEach(@Autowired SportsAcademySeeder sportsAcademySeeder) {
        sportsAcademySeeder.seedDatabase();
    }
}
