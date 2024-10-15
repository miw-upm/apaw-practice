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

        TechniqueEntity[] techniqueEntities ={
                new TechniqueEntity("Dollyo",3,true,1.0),
                new TechniqueEntity("Bandal",5,true,1.0),
                new TechniqueEntity("Punch",7,false,1.0),
                new TechniqueEntity("Tuitchagui",1,true,1.0)
        };
        this.techniqueRepository.saveAll(Arrays.asList(techniqueEntities));

        MartialArtsClassEntity[] martialArtsClassEntities = {
                new MartialArtsClassEntity("Taekwondo",  LocalDateTime.of(1990, 10, 27, 23, 2, 2),"forestal", List.of(techniqueEntities)),
                new MartialArtsClassEntity("Karate",  LocalDateTime.of(1990, 10, 27, 23, 2, 2),"Karate Doo", List.of(techniqueEntities)),
                new MartialArtsClassEntity("Muay thai",  LocalDateTime.of(1990, 10, 27, 23, 2, 2),"Han moo", List.of(techniqueEntities)),
                new MartialArtsClassEntity("Kick boxing",  LocalDateTime.of(1990, 10, 27, 23, 2, 2),"Brutal stricker", List.of(techniqueEntities)),
        };

        this.martialArtsClassRepository.saveAll(Arrays.asList(martialArtsClassEntities));
        long countBefore = this.instructorRepository.count();
        InstructorEntity[] instructorEntities = new InstructorEntity[]{
                new InstructorEntity("Z1521143C", "Bastian red", 999999, LocalDateTime.of(1990, 10, 27, 23, 2, 2)),
                new InstructorEntity("84134147K", "Felipe flip", 999999, LocalDateTime.of(1990, 10, 27, 23, 2, 2)),
                new InstructorEntity("Z1721643C", "Robert wat", 999999, LocalDateTime.of(1990, 10, 27, 23, 2, 2)),
                new InstructorEntity("03948142R", "Bruce lee", 999999, LocalDateTime.of(1990, 10, 27, 23, 2, 2)),
        };

        this.instructorRepository.saveAll(Arrays.asList(instructorEntities));
        // Contar después de guardar
        long countAfter = this.instructorRepository.count();

        // Verificar que el tamaño ha aumentado
        if (countAfter != countBefore + instructorEntities.length) {
            throw new RuntimeException("No se han guardado todas las entidades.");
        }
        LogManager.getLogger(this.getClass()).info("Cantidad de instructores antes: " + countBefore);
        LogManager.getLogger(this.getClass()).info("Cantidad de instructores después: " + countAfter);


        StyleEntity[] styleEntities ={
                new StyleEntity("Estilo de combate","Tecnica pensada para el deporte de contacto","america",4),
                new StyleEntity("Estilo de marcial","Tecnica pensada para el deporte de contacto","Corea",4)
        };
        this.styleRepository.saveAll(Arrays.asList(styleEntities));
    }

    public void deleteAll() {
        this.martialArtsClassRepository.deleteAll();
        this.techniqueRepository.deleteAll();
        this.styleRepository.deleteAll();
        this.instructorRepository.deleteAll();
    }
}
