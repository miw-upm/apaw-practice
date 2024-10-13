package es.upm.miw.apaw_practice.adapters.mongodb.music_lesson;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;

import es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.daos.BranchRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.daos.LearnerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.daos.MusicalInstrumentRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.entities.BranchEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.entities.LearnerEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.entities.LessonEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.entities.MusicalInstrumentEntity;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;

public class MusicLessonSeederService {

  @Autowired
  private BranchRepository branchRepository;

  @Autowired
  private MusicalInstrumentRepository musicalInstrumentRepository;

  @Autowired
  private LearnerRepository learnerRepository;

  public void seedDatabase() {
    LogManager.getLogger(this.getClass()).warn("------- Music-Lesson Initial Load -----------");

    MusicalInstrumentEntity[] musicalInstrumentEntities = {
        new MusicalInstrumentEntity("FND-SC-001", "Intermediate", "Electric Guitar"),
        new MusicalInstrumentEntity("YMH-FL-222", "Beginner", "Flute"),
        new MusicalInstrumentEntity("STW-PN-998", "Advanced", "Grand Piano"),
        new MusicalInstrumentEntity("GBS-LP-234", "Intermediate", "Electric Guitar"),
        new MusicalInstrumentEntity("LWG-SN-400", "Advanced", "Snare Drum")
    };
    this.musicalInstrumentRepository.saveAll(Arrays.asList(musicalInstrumentEntities));

    BranchEntity[] branchEntities = {
        new BranchEntity("MAD-CTRH", "Serrano Street, 45", "913245678"),
        new BranchEntity("MAD-PRDO", "Prado Avenue, 28", "914567890"),
        new BranchEntity("MAD-SBCT", "Gran Via Street, 12", "912345778")
    };
    this.branchRepository.saveAll(Arrays.asList(branchEntities));

    LessonEntity[] lessonEntities = {
        new LessonEntity(LocalDate.of(2024, Month.JANUARY, 1), 1, BigDecimal.valueOf(20.55),
            Collections.singletonList(musicalInstrumentEntities[0])),
        new LessonEntity(LocalDate.of(2024, Month.JANUARY, 2), 1, BigDecimal.valueOf(20.55),
            Collections.singletonList(musicalInstrumentEntities[0])),
        new LessonEntity(LocalDate.of(2024, Month.FEBRUARY, 1), 1, BigDecimal.valueOf(18.75),
            Collections.singletonList(musicalInstrumentEntities[1])),
        new LessonEntity(LocalDate.of(2024, Month.FEBRUARY, 10), 2, BigDecimal.valueOf(18.75),
            Arrays.asList(musicalInstrumentEntities[1], musicalInstrumentEntities[2]))
    };
    LearnerEntity[] learnerEntities = {
        new LearnerEntity("Z1254521", "Juan Perez", Boolean.FALSE,
            Arrays.asList(lessonEntities[0], lessonEntities[1]), branchEntities[0]),
        new LearnerEntity("N1254552", "Maria Perez", Boolean.TRUE,
            Arrays.asList(lessonEntities[2], lessonEntities[3]), branchEntities[1]),
    };
    this.learnerRepository.saveAll(Arrays.asList(learnerEntities));
  }

  public void deleteAll() {
    this.learnerRepository.deleteAll();
    this.branchRepository.deleteAll();
    this.musicalInstrumentRepository.deleteAll();
  }

}
