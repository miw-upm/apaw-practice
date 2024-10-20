package es.upm.miw.apaw_practice.domain.persistence_ports.music_lesson;

import java.util.stream.Stream;

import es.upm.miw.apaw_practice.domain.models.music_lesson.Branch;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchPersistence {

  Branch readByCode(String code);

  Stream<Branch> readAll();

  Stream<Branch> findByAddress(String address);

  Stream<String> findUniqueMusicalInstrumentModelsByAddress(String address);
}
