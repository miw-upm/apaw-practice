package es.upm.miw.apaw_practice.domain.services.music_lesson;

import static org.junit.jupiter.api.Assertions.assertTrue;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.persistence_ports.music_lesson.LearnerPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@TestConfig
public class LearnerServiceIT {

  @Autowired
  private LearnerService learnerService;

  @Autowired
  private LearnerPersistence learnerPersistence;

  @Test
  void testDelete() {
    var learnerCountPrev = this.getLearnersCount();
    this.learnerService.delete("Y5879632");
    var learnerCountAfter = this.getLearnersCount();
    assertTrue(learnerCountPrev > learnerCountAfter);
  }

  private Long getLearnersCount() {
    return this.learnerPersistence
        .readAll()
        .count();
  }

}
