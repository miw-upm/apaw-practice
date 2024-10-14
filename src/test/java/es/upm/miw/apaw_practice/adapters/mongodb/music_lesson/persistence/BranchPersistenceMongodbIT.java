package es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.music_lesson.Branch;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@TestConfig
class BranchPersistenceMongodbIT {

  @Autowired
  private BranchPersistenceMongodb branchPersistenceMongodb;

  @Test
  void testReadByCode() {
    Branch actualBranch = this.branchPersistenceMongodb.readByCode("MAD-SBCT");
    assertEquals("Gran Via Street, 12; Madrid", actualBranch.getAddress());
    assertEquals("912345778", actualBranch.getPhoneNumber());
  }

}
