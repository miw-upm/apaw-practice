package es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

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

  @Test
  void testFindUniqueMusicalInstrumentModelsByAddress() {
    String[] expectedModels = {"YMH-FL-222", "STW-PN-998"};
    String address = "Prado Avenue";
    Stream<String> modelsStream = this.branchPersistenceMongodb.findUniqueMusicalInstrumentModelsByAddress(address);
    List<String> actualModelList = modelsStream.toList();

    assertFalse(actualModelList.isEmpty());
    actualModelList.forEach(actualModel ->
        assertTrue(Arrays.asList(expectedModels).contains(actualModel))
    );
  }

}
