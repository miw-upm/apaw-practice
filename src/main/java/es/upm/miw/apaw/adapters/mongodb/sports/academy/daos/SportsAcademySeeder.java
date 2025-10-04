package es.upm.miw.apaw.adapters.mongodb.sports.academy.daos;

import es.upm.miw.apaw.adapters.mongodb.sports.academy.entities.AthleteEntity;
import es.upm.miw.apaw.adapters.mongodb.sports.academy.entities.LegalGuardianEntity;
import es.upm.miw.apaw.adapters.mongodb.sports.academy.entities.ProfessorEntity;
import es.upm.miw.apaw.adapters.mongodb.sports.academy.entities.SportModalityEntity;
import es.upm.miw.apaw.domain.models.sports.academy.enums.Gender;
import es.upm.miw.apaw.domain.models.sports.academy.enums.Level;
import es.upm.miw.apaw.domain.models.sports.academy.enums.RelationShip;
import es.upm.miw.apaw.domain.models.sports.academy.enums.TargetAudience;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

@Repository
@Profile({"dev", "test"})
@Log4j2
public class SportsAcademySeeder {

    private final IAthleteRepository athleteRepository;
    private final ISportModalityRepository sportModalityRepository;
    private final ILegalGuardianRepository legalGuardianRepository;
    private final IProfessorRepository professorRepository;

    @Autowired
    public SportsAcademySeeder(
            IAthleteRepository athleteRepository,
            ISportModalityRepository sportModalityRepository,
            ILegalGuardianRepository legalGuardianRepository,
            IProfessorRepository professorRepository) {
        this.athleteRepository = athleteRepository;
        this.sportModalityRepository = sportModalityRepository;
        this.legalGuardianRepository = legalGuardianRepository;
        this.professorRepository = professorRepository;
    }

    public void seedDatabase() {
        try {
            log.info("------- Starting Sports Academy Initial Load -----------");

            if (this.athleteRepository.count() > 0 ||
                this.sportModalityRepository.count() > 0 ||
                this.legalGuardianRepository.count() > 0 ||
                this.professorRepository.count() > 0) {
                log.info("------- Sports Academy Initial Load already done -----------");
                return;
            }

            LegalGuardianEntity[] legalGuardians = {
                LegalGuardianEntity.builder()
                        .userDtoId(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
                        .secondMobile("+34711036811")
                        .relationShip(RelationShip.AUNT.getValue())
                        .build(),
                LegalGuardianEntity.builder()
                        .userDtoId(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"))
                        .secondMobile("+34712036811")
                        .relationShip(RelationShip.AUNT.getValue())
                        .build()
            };

            this.legalGuardianRepository.saveAll(java.util.Arrays.asList(legalGuardians));

            AthleteEntity[] athletes = {
                AthleteEntity.builder()
                        .userDtoId(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002"))
                        .gender(Gender.MALE.getValue())
                        .height(1.78)
                        .weight(72)
                        .birthDate(LocalDate.of(2000, 6, 20))
                        .legalGuardians(Collections.singletonList(legalGuardians[0]))
                        .sportModalities(new ArrayList<>())
                        .build(),
                AthleteEntity.builder()
                        .userDtoId(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003"))
                        .gender(Gender.FEMALE.getValue())
                        .height(165)
                        .weight(56)
                        .birthDate(LocalDate.of(2003, 6, 20))
                        .legalGuardians(Collections.singletonList(legalGuardians[1]))
                        .sportModalities(new ArrayList<>())
                        .build()
            };

            this.athleteRepository.saveAll(java.util.Arrays.asList(athletes));

            ProfessorEntity[] professors = {
                    ProfessorEntity.builder()
                        .userDtoId(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0004"))
                        .specialization("Tennis")
                        .licenseNumber("ABC123")
                        .build(),
                    ProfessorEntity.builder()
                        .userDtoId(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0005"))
                        .specialization("Swimming")
                        .licenseNumber("DEF456")
                        .build()
            };

            this.professorRepository.saveAll(java.util.Arrays.asList(professors));

            SportModalityEntity[] sportModalities = {
                    SportModalityEntity.builder()
                        .sportId(UUID.randomUUID())
                        .title("Tennis")
                        .level(Level.BEGINNER.getValue())
                        .targetAudience(TargetAudience.KIDS.getValue())
                        .professor(professors[0])
                        .athletes(java.util.Arrays.asList(athletes))
                        .build(),
                    SportModalityEntity.builder()
                        .sportId(UUID.randomUUID())
                        .title("Swimming")
                        .level(Level.INTERMEDIATE.getValue())
                        .targetAudience(TargetAudience.TEENAGERS.getValue())
                        .professor(professors[1])
                        .athletes(java.util.Arrays.asList(athletes))
                        .build()
            };

            this.sportModalityRepository.saveAll(java.util.Arrays.asList(sportModalities));

            log.info("------- Finished Sports Academy Initial Load -----------");
        }
        catch (Exception e) {
            log.error("------- Error on Sports Academy Initial Load -----------", e);
        }
    }

    public void deleteAll() {
        log.info("------- Deleting All Sports Academy -----------");
        athleteRepository.deleteAll();
        sportModalityRepository.deleteAll();
        legalGuardianRepository.deleteAll();
        professorRepository.deleteAll();
        log.info("------- Deleted All Sports Academy -----------");
    }
}