package es.upm.miw.apaw;

import es.upm.miw.apaw.adapters.mongodb.sports.academy.daos.*;
import es.upm.miw.apaw.adapters.mongodb.sports.academy.entities.AthleteEntity;
import es.upm.miw.apaw.adapters.mongodb.sports.academy.entities.LegalGuardianEntity;
import es.upm.miw.apaw.adapters.mongodb.sports.academy.entities.ProfessorEntity;
import es.upm.miw.apaw.adapters.mongodb.sports.academy.entities.SportModalityEntity;
import es.upm.miw.apaw.domain.models.sports.academy.enums.Gender;
import es.upm.miw.apaw.domain.models.sports.academy.enums.Level;
import es.upm.miw.apaw.domain.models.sports.academy.enums.RelationShip;
import es.upm.miw.apaw.domain.models.sports.academy.enums.TargetAudience;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

@SpringBootTest
@ActiveProfiles("test")
public abstract class BaseSportsAcademyTests {
    protected final LegalGuardianEntity[] legalGuardians = {
            LegalGuardianEntity.builder()
                    .userDtoId(UUID.fromString(UUID.randomUUID().toString()))
                    .secondMobile("34711036822")
                    .relationShip(RelationShip.AUNT.getValue())
                    .build(),
            LegalGuardianEntity.builder()
                    .userDtoId(UUID.fromString(UUID.randomUUID().toString()))
                    .secondMobile("34712036833")
                    .relationShip(RelationShip.AUNT.getValue())
                    .build(),
            LegalGuardianEntity.builder()
                    .userDtoId(UUID.fromString(UUID.randomUUID().toString()))
                    .secondMobile("34712036844")
                    .relationShip(RelationShip.FATHER.getValue())
                    .build()
    };

    protected final ProfessorEntity[] professors = {
            ProfessorEntity.builder()
                    .userDtoId(UUID.fromString(UUID.randomUUID().toString()))
                    .specialization("Tennis")
                    .licenseNumber("ABC123")
                    .build(),
            ProfessorEntity.builder()
                    .userDtoId(UUID.fromString(UUID.randomUUID().toString()))
                    .specialization("Swimming")
                    .licenseNumber("DEF456")
                    .build()
    };

    protected SportModalityEntity[] sportModalities = {
            SportModalityEntity.builder()
                    .id(UUID.fromString(UUID.randomUUID().toString()))
                    .title("Tennis")
                    .level(Level.BEGINNER.getValue())
                    .targetAudience(TargetAudience.KIDS.getValue())
                    .build(),
            SportModalityEntity.builder()
                    .id(UUID.fromString(UUID.randomUUID().toString()))
                    .title("Swimming")
                    .level(Level.INTERMEDIATE.getValue())
                    .targetAudience(TargetAudience.TEENAGERS.getValue())
                    .build()
    };

    protected AthleteEntity[] athletes = {
            AthleteEntity.builder()
                    .userDtoId(UUID.fromString(UUID.randomUUID().toString()))
                    .gender(Gender.MALE.getValue())
                    .height(1.78)
                    .weight(72)
                    .birthDate(LocalDate.of(2000, 6, 20))
                    .build(),
            AthleteEntity.builder()
                    .userDtoId(UUID.fromString(UUID.randomUUID().toString()))
                    .gender(Gender.FEMALE.getValue())
                    .height(165)
                    .weight(56)
                    .birthDate(LocalDate.of(2003, 6, 20))
                    .build(),
            AthleteEntity.builder()
                    .userDtoId(UUID.fromString(UUID.randomUUID().toString()))
                    .gender(Gender.OTHER.getValue())
                    .height(1.70)
                    .weight(68)
                    .birthDate(LocalDate.of(1995, 6, 20))
                    .build()
    };

    @AfterEach
    void tearDown(@Autowired AthleteRepository athleteRepository,
                  @Autowired LegalGuardianRepository legalGuardianRepository,
                  @Autowired ProfessorRepository professorRepository,
                  @Autowired SportModalityRepository sportModalityRepository)
    {
        athleteRepository.deleteAll(Arrays.stream(athletes).toList());
        legalGuardianRepository.deleteAll(Arrays.stream(legalGuardians).toList());
        professorRepository.deleteAll(Arrays.stream(professors).toList());
        sportModalityRepository.deleteAll(Arrays.stream(sportModalities).toList());
    }

    @BeforeEach
    void beforeEach(@Autowired AthleteRepository athleteRepository,
                    @Autowired LegalGuardianRepository legalGuardianRepository,
                    @Autowired ProfessorRepository professorRepository,
                    @Autowired SportModalityRepository sportModalityRepository)
    {
        sportModalities[0].setProfessor(professors[0]);
        sportModalities[1].setProfessor(professors[1]);
        athletes[0].setLegalGuardians(Collections.singletonList(legalGuardians[0]));
        athletes[0].setSportModalities(java.util.Arrays.asList(sportModalities));
        athletes[1].setLegalGuardians(Collections.singletonList(legalGuardians[1]));
        athletes[1].setSportModalities(java.util.Arrays.asList(sportModalities));
        athletes[2].setLegalGuardians(Collections.singletonList(legalGuardians[2]));
        athletes[2].setSportModalities(java.util.Arrays.asList(sportModalities));

        legalGuardianRepository.saveAll(java.util.Arrays.asList(legalGuardians));
        professorRepository.saveAll(java.util.Arrays.asList(professors));
        sportModalityRepository.saveAll(java.util.Arrays.asList(sportModalities));
        athleteRepository.saveAll(java.util.Arrays.asList(athletes));
    }
}
