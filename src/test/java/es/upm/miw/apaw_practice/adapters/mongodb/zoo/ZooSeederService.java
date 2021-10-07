package es.upm.miw.apaw_practice.adapters.mongodb.zoo;

import es.upm.miw.apaw_practice.adapters.mongodb.zoo.daos.AnimalRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.zoo.daos.CageRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.zoo.daos.CaretakerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.zoo.daos.ZooRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.zoo.entities.AnimalEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.zoo.entities.CageEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.zoo.entities.CaretakerEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.zoo.entities.ZooEntity;
import es.upm.miw.apaw_practice.domain.models.zoo.*;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ZooSeederService {

    @Autowired
    private ZooRepository zooRepository;
    @Autowired
    private CageRepository cageRepository;
    @Autowired
    private CaretakerRepository caretakerRepository;
    @Autowired
    private AnimalRepository animalRepository;

    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("------- Shop Initial Load -----------");
        ZooAddress[] addresses = {
                new ZooAddress("Calle Carranza", 22, "28004"),
                new ZooAddress("Portobello Road", 301, "GB 11K"),
                new ZooAddress("Mulholland Drive", 141, "HLS33"),
        };
        ZooEntity[] zoos = {
                new ZooEntity(new Zoo(addresses[0], 914334789)),
                new ZooEntity(new Zoo(addresses[1], 35664718)),
                new ZooEntity(new Zoo(addresses[2], 5551450))
        };
        zoos[0].setId("id1");
        this.zooRepository.saveAll(Arrays.asList(zoos));
        CaretakerEntity[] caretakers = {
                new CaretakerEntity(new Caretaker("71679884Q", "Samuel L", "Jackson")),
                new CaretakerEntity(new Caretaker("53562718L", "Rodrigo", "de Paul")),
                new CaretakerEntity(new Caretaker("13348920W", "Abel", "Piñón")),
                new CaretakerEntity(new Caretaker("93879237O", "Thomas", "Partey"))
        };
        this.caretakerRepository.saveAll(Arrays.asList(caretakers));
        AnimalEntity[] animals = {
                new AnimalEntity(new Animal("Gato", "Felino", "Omnívoro")),
                new AnimalEntity(new Animal("Chimpancé", "Mono", "Omnívoro")),
                new AnimalEntity(new Animal("Tigre dientes de sable", "Felino", "Carnívoro")),
                new AnimalEntity(new Animal("Anjhk", "Bovino", "Herbívoro")),
                new AnimalEntity(new Animal("Oso Grizzlie", "Oso", "Omnívoro")),
                new AnimalEntity(new Animal("Tiburón Martillo", "Escualo", "Carnívoro"))
        };
        this.animalRepository.saveAll(Arrays.asList(animals));
        List<CageEntity> cages = new ArrayList<>();
        cages.add(new CageEntity(new Cage(150.0, "A1", caretakers[0].toCaretaker()), zoos[0], caretakers[0]));
        cages.add(new CageEntity(new Cage(75.5, "A2", caretakers[0].toCaretaker()), zoos[0], caretakers[0]));
        cages.add(new CageEntity(new Cage(81.0, "B7", caretakers[1].toCaretaker()), zoos[0], caretakers[1]));
        cages.add(new CageEntity(new Cage(45.0, "B7", caretakers[1].toCaretaker()), zoos[0], caretakers[1]));
        cages.add(new CageEntity(new Cage(1350.75, "1", caretakers[2].toCaretaker()), zoos[1], caretakers[2]));
        cages.get(0).setAnimals(Arrays.asList(animals).subList(0, 4));
        cages.get(1).setAnimals(Arrays.asList(animals).subList(0, 2));
        cages.get(2).setAnimals(Arrays.asList(animals).subList(1, 4));
        cages.get(3).setAnimals(Arrays.asList(animals).subList(0, 1));
        cages.get(4).setAnimals(Arrays.asList(animals).subList(4, 5));
        this.cageRepository.saveAll(cages);
    }

    public void deleteAll() {
        this.cageRepository.deleteAll();
        this.animalRepository.deleteAll();
        this.caretakerRepository.deleteAll();
        this.zooRepository.deleteAll();
    }
}
