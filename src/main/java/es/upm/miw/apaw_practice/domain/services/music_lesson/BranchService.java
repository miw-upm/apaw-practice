package es.upm.miw.apaw_practice.domain.services.music_lesson;

import java.util.stream.Stream;

import es.upm.miw.apaw_practice.domain.models.music_lesson.Branch;
import es.upm.miw.apaw_practice.domain.persistence_ports.music_lesson.BranchPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BranchService {

  private final BranchPersistence branchPersistence;

  @Autowired
  public BranchService(BranchPersistence branchPersistence) {
    this.branchPersistence = branchPersistence;
  }

  public Stream<Branch> findByAddress(String address) {
    return this.branchPersistence.findByAddress(address);
  }
}
