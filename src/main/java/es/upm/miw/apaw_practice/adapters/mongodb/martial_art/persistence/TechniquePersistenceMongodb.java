package es.upm.miw.apaw_practice.adapters.mongodb.martial_art.persistence;
import es.upm.miw.apaw_practice.adapters.mongodb.martial_art.daos.InstructorRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.martial_art.daos.StyleRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.martial_art.daos.TechniqueRepository;
import es.upm.miw.apaw_practice.domain.models.martial_art.Technique;
import es.upm.miw.apaw_practice.domain.persistence_ports.martial_art.TechniquePersistence;
import es.upm.miw.apaw_practice.adapters.mongodb.martial_art.entities.TechniqueEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.martial_art.entities.StyleEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.martial_art.entities.InstructorEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@Repository("techniquePersistence")
public class TechniquePersistenceMongodb implements TechniquePersistence{

    private final TechniqueRepository techniqueRepository;
    private final StyleRepository styleRepository;
    private final InstructorRepository instructorRepository;

    @Autowired
    public TechniquePersistenceMongodb(
            TechniqueRepository techniqueRepository,
            StyleRepository styleRepository,
            InstructorRepository instructorRepository

            ) {
        this.techniqueRepository = techniqueRepository;
        this.styleRepository = styleRepository;
        this.instructorRepository = instructorRepository;

    }
    @Override
    public Stream<Technique> readAll() {
        return this.techniqueRepository.findAll().stream()
                .map(TechniqueEntity::toTechnique);
    }
    @Override
    public Technique create(Technique technique) {
        StyleEntity styleEntity = this.styleRepository
                .findByName(technique.getStyle().getName())
                .orElseGet(() -> this.styleRepository.save(new StyleEntity(
                        technique.getStyle().getName(),
                        technique.getStyle().getDescription(),
                        technique.getStyle().getOriginCountry(),
                        technique.getStyle().getPopularity()
                )));
        List<InstructorEntity> instructorEntities = technique.getInstructors().stream()
                .map(instructor -> this.instructorRepository.findByDni(instructor.getDni())
                        .orElseGet(() -> this.instructorRepository.save(new InstructorEntity(
                                instructor.getDni(),
                                instructor.getFullName(),
                                instructor.getPhoneNumber(),
                                instructor.getBirthDate()
                        )))
                ).toList();

        TechniqueEntity techniqueEntity = new TechniqueEntity(
                technique.getName(),
                technique.getDuration(),
                technique.getIsAdvanced(),
                technique.getDifficulty(),
                instructorEntities,
                styleEntity
        );

        return this.techniqueRepository
                .save(techniqueEntity)
                .toTechnique();
    }
    @Override
    public Technique read(String name) {
        return this.techniqueRepository
                .findByName(name)
                .orElseThrow(() -> new NotFoundException("Technique name: " + name))
                .toTechnique();
    }

    @Override
    public void delete(String name) {
        TechniqueEntity techniqueEntity = this.techniqueRepository
                .findByName(name)
                .orElseThrow(() -> new NotFoundException("Technique name: " + name));
        this.techniqueRepository.delete(techniqueEntity);
    }
    @Override
    public Stream<Integer> findNonDuplicatedInstructorPhonesByPopularity(int popularity) {
        return this.techniqueRepository.findAll().stream()
                .filter(techniqueEntity -> techniqueEntity.getStyle().getPopularity() == popularity)
                .peek(techniqueEntity -> System.out.println("Filtrando técnica: " + techniqueEntity.getName()))
                .flatMap(techniqueEntity -> {
                    System.out.println("Técnica: " + techniqueEntity.getName() + " tiene instructores: "
                            + techniqueEntity.getInstructors().stream()
                            .map(InstructorEntity::getFullName)
                            .collect(Collectors.joining(", ")));
                    return techniqueEntity.getInstructors().stream();
                })
                .map(InstructorEntity::getPhoneNumber)
                .distinct();
    }

    @Override
    public Integer findTotalDurationNoRepeatByDescription(String description) {
        return this.techniqueRepository.findAll().stream()
                .filter(techniqueEntity -> {
                    boolean matches = techniqueEntity.getStyle().getDescription().equals(description);
                    if (matches) {
                        System.out.println("Matched Technique: " + techniqueEntity.getName());
                    }
                    return matches;
                })
                .map(TechniqueEntity::getDuration)
                .distinct()
                .peek(duration -> System.out.println("Unique Duration: " + duration))
                .reduce(0, Integer::sum);
    }

}
