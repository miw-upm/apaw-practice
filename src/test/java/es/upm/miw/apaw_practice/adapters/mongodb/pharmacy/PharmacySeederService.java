package es.upm.miw.apaw_practice.adapters.mongodb.pharmacy;

import es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.daos.ActiveIngredientRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.daos.DispensingRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.daos.DrugRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.daos.PharmacyRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.entities.ActiveIngredientEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.entities.DispensingEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.entities.DrugEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.entities.PharmacyEntity;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class PharmacySeederService {

    @Autowired
    private DrugRepository drugRepository;
    @Autowired
    private DispensingRepository dispensingRepository;
    @Autowired
    private PharmacyRepository pharmacyRepository;
    @Autowired
    private ActiveIngredientRepository activeIngredientRepository;

    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("------- Pharmacy Initial Load -----------");
        DrugEntity[] drugs = {
                new DrugEntity("A9001", "Frenadol Complex", true, new BigDecimal("5.39")),
                new DrugEntity("A9002", "Stopcold", true, new BigDecimal("2.28")),
                new DrugEntity("A9003", "Espidifen", true, new BigDecimal("8.12")),
                new DrugEntity("A9004", "Neobrufen", true, new BigDecimal("10.05")),
                new DrugEntity("A9005", "Pirexin", false, new BigDecimal("5.15")),
                new DrugEntity("A9006", "Naxopreno Normon", true, new BigDecimal("3.15")),
        };
        this.drugRepository.saveAll(Arrays.asList(drugs));
        PharmacyEntity[] pharmacy = {
                new PharmacyEntity("123456", "Travesía Cueva de la Mora nº7", 27680, List.of(drugs[0], drugs[1], drugs[2], drugs[3], drugs[4])),
                new PharmacyEntity("789456", "Calle Poeta Vila y Blanco nº10", 29002, List.of(drugs[1], drugs[2], drugs[3], drugs[5])),
                new PharmacyEntity("789111", "Calle Rafael Ramos Cea nº28", 29002, List.of(drugs[0], drugs[1])),
                new PharmacyEntity("654321", "Calle Alan Turing nº20", 27680, List.of(drugs[2], drugs[5])),
                new PharmacyEntity("789455", "Calle Estrella nº35", 27009, List.of(drugs[5]))
        };
        this.pharmacyRepository.saveAll(Arrays.asList(pharmacy));
        ActiveIngredientEntity[] activeIngredients = {
                new ActiveIngredientEntity("B9001", List.of("75% Paracetamol", "15% Cafeína"), 1000, drugs[0]),
                new ActiveIngredientEntity("B9002", List.of("75% Paracetamol", "15% Cafeína"), 700, drugs[1]),
                new ActiveIngredientEntity("B9003", List.of("100% Paracetamol"), 1000, drugs[2]),
                new ActiveIngredientEntity("B9004", List.of("100% Ibuprofeno"), 600, drugs[3]),
                new ActiveIngredientEntity("B9005", List.of("100% Ibuprofeno"), 600, drugs[4]),
                new ActiveIngredientEntity("B9006", List.of("100% Ibuprofeno"), 700, drugs[4]),
                new ActiveIngredientEntity("B9007", List.of("100% Naxopreno"), 400, drugs[5]),
        };

        this.activeIngredientRepository.saveAll(Arrays.asList(activeIngredients));
        DispensingEntity[] dispensings = {
                new DispensingEntity("1", LocalDateTime.of(2021, 7, 2, 21, 34), List.of(activeIngredients[0])),
                new DispensingEntity("2", LocalDateTime.of(2021, 8, 12, 9, 0), List.of(activeIngredients[1], activeIngredients[2])),
                new DispensingEntity("4", LocalDateTime.of(2021, 2, 1, 20, 5), List.of(activeIngredients[2], activeIngredients[3])),
                new DispensingEntity("5", LocalDateTime.of(2021, 9, 27, 8, 15), List.of(activeIngredients[5])),
                new DispensingEntity("6", LocalDateTime.of(2021, 10, 28, 23, 15), List.of(activeIngredients[3], activeIngredients[5])),
                new DispensingEntity("7", LocalDateTime.of(2021, 1, 5, 13, 20), List.of(activeIngredients[3])),
        };
        this.dispensingRepository.saveAll(Arrays.asList(dispensings));
    }

    public void deleteAll() {
        this.drugRepository.deleteAll();
        this.dispensingRepository.deleteAll();
        this.pharmacyRepository.deleteAll();
    }
}
