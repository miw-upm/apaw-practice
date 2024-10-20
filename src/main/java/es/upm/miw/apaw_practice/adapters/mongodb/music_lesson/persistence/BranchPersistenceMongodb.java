package es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.persistence;

import java.util.stream.Stream;

import es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.daos.BranchRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.daos.LearnerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.entities.BranchEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.entities.MusicalInstrumentEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.music_lesson.Branch;
import es.upm.miw.apaw_practice.domain.persistence_ports.music_lesson.BranchPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("branchPersistence")
public class BranchPersistenceMongodb implements BranchPersistence {

  private final BranchRepository branchRepository;

  private final LearnerRepository learnerRepository;

  @Autowired
  public BranchPersistenceMongodb(BranchRepository branchRepository, LearnerRepository learnerRepository) {
    this.branchRepository = branchRepository;
    this.learnerRepository = learnerRepository;
  }

  @Override
  public Branch readByCode(String code) {
    return this.branchRepository.findByCode(code)
        .orElseThrow(() -> new NotFoundException(" Branch code: " + code))
        .toBranch();
  }

  @Override
  public Stream<Branch> readAll() {
    return this.branchRepository.findAll()
        .stream()
        .map(BranchEntity::toBranch);
  }

  @Override
  public Stream<Branch> findByAddress(String address) {
    return this.branchRepository.findAll().stream()
        .filter(branchEntity -> branchEntity.getAddress().contains(address))
        .map(BranchEntity::toBranch);
  }

  @Override
  public Stream<String> findUniqueMusicalInstrumentModelsByAddress(String address) {
    return this.branchRepository.findAll().stream()
        .filter(branchEntity -> branchEntity.getAddress().contains(address))
        .flatMap(branchEntity -> this.learnerRepository.findByBranchId(branchEntity.getId()))
        .flatMap(learnerEntity -> learnerEntity.getLessons().stream())
        .flatMap(lessonEntity -> lessonEntity.getMusicalInstruments().stream())
        .map(MusicalInstrumentEntity::getModel)
        .distinct();
  }

}
