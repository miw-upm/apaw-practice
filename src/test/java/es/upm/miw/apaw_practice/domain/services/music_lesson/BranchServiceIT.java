package es.upm.miw.apaw_practice.domain.services.music_lesson;

import java.util.List;
import java.util.stream.Stream;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.music_lesson.Branch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@TestConfig
public class BranchServiceIT {

  private final List<String> madridBranchCodes = List.of("MAD-PRDO", "MAD-SBCT");

  private final List<String> madridBranchAddress = List.of("Prado Avenue, 28; Madrid", "Gran Via Street, 12; Madrid");

  @Autowired
  private BranchService branchService;

  @Test
  void testFindByAddress() {
    Stream<Branch> branchStream = this.branchService.findByAddress("Madrid");
    List<Branch> branches = branchStream.toList();
    Assertions.assertFalse(branches.isEmpty());
    branches.forEach(branch -> {
      Assertions.assertTrue(madridBranchCodes.contains(branch.getCode()));
      Assertions.assertTrue(madridBranchAddress.contains(branch.getAddress()));
    });
  }

}
