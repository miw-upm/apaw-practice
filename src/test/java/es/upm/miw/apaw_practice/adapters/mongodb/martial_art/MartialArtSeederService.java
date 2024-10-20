package es.upm.miw.apaw_practice.adapters.mongodb.martial_art;

import es.upm.miw.apaw_practice.adapters.mongodb.martial_art.daos.MartialArtsClassRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.martial_art.daos.TechniqueRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.martial_art.daos.StyleRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.martial_art.daos.InstructorRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.martial_art.entities.MartialArtsClassEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.martial_art.entities.StyleEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.martial_art.entities.InstructorEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.martial_art.entities.TechniqueEntity;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
@Service
public class MartialArtSeederService {
    @Autowired
    private MartialArtsClassRepository martialArtsClassRepository;
    @Autowired
    private TechniqueRepository techniqueRepository;
    @Autowired
    private StyleRepository styleRepository;
    @Autowired
    private InstructorRepository instructorRepository;
    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("------- Martial Art Initial Load -----------");

        InstructorEntity[] instructorEntities = new InstructorEntity[]{
                new InstructorEntity("Z1521143C", "Bastian red", 978957449, LocalDateTime.of(1990, 10, 27, 23, 2, 2)),
                new InstructorEntity("84134147K", "Felipe flip", 978957449, LocalDateTime.of(1990, 10, 27, 23, 2, 2)), // Cambiado
                new InstructorEntity("Z1721643C", "Robert wat", 987539464, LocalDateTime.of(1990, 10, 27, 23, 2, 2)),
                new InstructorEntity("03948142R", "Bruce lee", 997790286, LocalDateTime.of(1990, 10, 27, 23, 2, 2)),
        };

        this.instructorRepository.saveAll(Arrays.asList(instructorEntities));

        StyleEntity[] styleEntities ={
                new StyleEntity("Estilo de combate","Tecnica pensada para el deporte de marcial","america",4),
                new StyleEntity("Estilo de marcial","Tecnica pensada para el deporte de contacto","Corea",4),
                new StyleEntity("Estilo de popularidad 2", "Técnica con popularidad 2", "Asia", 2)
        };
        this.styleRepository.saveAll(Arrays.asList(styleEntities));



        TechniqueEntity[] techniqueEntities ={
                new TechniqueEntity("Dollyo",3,true,1.0,List.of(instructorEntities), Arrays.asList(styleEntities).get(0)),
                new TechniqueEntity("Bandal",5,true,1.0,List.of(instructorEntities),Arrays.asList(styleEntities).get(1)),
                new TechniqueEntity("Punch",7,false,1.0,List.of(instructorEntities),Arrays.asList(styleEntities).get(0)),
                new TechniqueEntity("Tuitchagui",5,true,1.0,List.of(instructorEntities),Arrays.asList(styleEntities).get(1)),
                new TechniqueEntity("Neryo", 1, true, 1.0, List.of(instructorEntities), Arrays.asList(styleEntities).get(2))

        };



        this.techniqueRepository.saveAll(Arrays.asList(techniqueEntities));

        MartialArtsClassEntity[] martialArtsClassEntities = {
                new MartialArtsClassEntity("Taekwondo",  LocalDate.of(2024, 10, 7),"forestal", List.of(techniqueEntities)),
                new MartialArtsClassEntity("Karate",  LocalDate.of(2024, 10, 7),"Karate Doo", List.of(techniqueEntities)),
                new MartialArtsClassEntity("Muay thai",  LocalDate.of(2024, 10, 7),"Han moo", List.of(techniqueEntities)),
                new MartialArtsClassEntity("Kick boxing",  LocalDate.of(2024, 10, 7),"Brutal stricker", List.of(techniqueEntities)),
        };

        this.martialArtsClassRepository.saveAll(Arrays.asList(martialArtsClassEntities));

       }

    public void deleteAll() {
        this.martialArtsClassRepository.deleteAll();
        this.techniqueRepository.deleteAll();
        this.styleRepository.deleteAll();
        this.instructorRepository.deleteAll();
    }
}
