package es.upm.miw.apaw_practice.adapters.mongodb.pharmacy;

import es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.daos.DispensingRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.daos.DrugRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.daos.PharmacyRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.entities.ActiveIngredientEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.entities.DispensingEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.entities.DrugEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.entities.PharmacyEntity;
import es.upm.miw.apaw_practice.domain.models.pharmacy.Drug;
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

    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("------- Pharmacy Initial Load -----------");
        DrugEntity[] drugs = {
                new DrugEntity(new Drug("A9001", "Frenadol Complex", true, new BigDecimal(5.39))),
                new DrugEntity(new Drug("A9002", "Stopcold", true, new BigDecimal(2.28))),
                new DrugEntity(new Drug("A9003", "Espidifen", true, new BigDecimal(8.12))),
                new DrugEntity(new Drug("A9004", "Neobrufen", true, new BigDecimal(10.05))),
                new DrugEntity(new Drug("A9005", "Pirexin", false, new BigDecimal(5.15))),
                new DrugEntity(new Drug("A9006", "Naxopreno Normon", true, new BigDecimal(3.15))),
        };
        this.drugRepository.saveAll(Arrays.asList(drugs));
        PharmacyEntity[] pharmacy = {
                new PharmacyEntity("Travesía Cueva de la Mora nº7", 27680, List.of(drugs[0], drugs[1], drugs[2], drugs[3], drugs[4])),
                new PharmacyEntity("Calle Poeta Vila y Blanco nº10", 29002, List.of(drugs[1], drugs[2], drugs[3], drugs[5])),
                new PharmacyEntity("Calle Rafael Ramos Cea nº28", 29002, List.of(drugs[0], drugs[1])),
                new PharmacyEntity("Calle Alan Turing nº20", 27680, List.of(drugs[2], drugs[5])),
                new PharmacyEntity("Calle Estrella nº35", 27009, List.of(drugs[5]))
        };
        this.pharmacyRepository.saveAll(Arrays.asList(pharmacy));
        ActiveIngredientEntity[] activeIngredients = {
                new ActiveIngredientEntity(drugs[0], List.of("75% Paracetamol", "15% Cafeína"), 1000),
                new ActiveIngredientEntity(drugs[0], List.of("75% Paracetamol", "15% Cafeína"), 500),
                new ActiveIngredientEntity(drugs[1], List.of("75% Paracetamol", "15% Cafeína"), 1000),
                new ActiveIngredientEntity(drugs[2], List.of("100% Ibuprofeno"), 600),
                new ActiveIngredientEntity(drugs[3], List.of("100% Ibuprofeno"), 600),
                new ActiveIngredientEntity(drugs[4], List.of("100% Ibuprofeno"), 600),
                new ActiveIngredientEntity(drugs[5], List.of("100% Naxopreno"), 400),
        };
        DispensingEntity[] dispensings = {
                new DispensingEntity(LocalDateTime.of(2021, 7, 2, 21, 34), Arrays.asList(activeIngredients[0])),
                new DispensingEntity(LocalDateTime.of(2021, 8, 12, 9, 00), Arrays.asList(activeIngredients[1], activeIngredients[2])),
                new DispensingEntity(LocalDateTime.of(2021, 2, 1, 20, 05), Arrays.asList(activeIngredients[2], activeIngredients[3])),
                new DispensingEntity(LocalDateTime.of(2021, 9, 27, 8, 15), Arrays.asList(activeIngredients[5])),
                new DispensingEntity(LocalDateTime.of(2021, 10, 28, 23, 15), Arrays.asList(activeIngredients[3], activeIngredients[5])),
                new DispensingEntity(LocalDateTime.of(2021, 1, 5, 13, 20), Arrays.asList(activeIngredients[3])),
        };
        this.dispensingRepository.saveAll(Arrays.asList(dispensings));

    }

    public void deleteAll() {
        this.drugRepository.deleteAll();
        this.dispensingRepository.deleteAll();
        this.pharmacyRepository.deleteAll();
    }
}
