package es.upm.miw.apaw_practice.adapters.mongodb.emarketer;

import es.upm.miw.apaw_practice.adapters.mongodb.emarketer.daos.CupsRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.emarketer.daos.CustomerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.emarketer.daos.EmarketerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.emarketer.daos.PlanRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.emarketer.entities.CupsEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.emarketer.entities.CustomerEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.emarketer.entities.EmarketerEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.emarketer.entities.PlanEntity;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class EmarketerSeederService {

    @Autowired
    private CupsRepository cupsRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EmarketerRepository emarketerRepository;
    @Autowired
    private PlanRepository planRepository;

    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("------- Emarketer Initial Load -----------");

        CustomerEntity[] customers = {
                new CustomerEntity("Pedro", "Madrid", "particular"),
                new CustomerEntity("Mudanzas S.A", "Barcelona", "empresa"),
                new CustomerEntity("Gestores S.A", "Madrid", "empresa"),
                new CustomerEntity("Paula", "Sevilla", "particular")
        };
        this.customerRepository.saveAll(Arrays.asList(customers));

        CupsEntity[] cups = {
                new CupsEntity("AAPPZZZ6KZ1R149943", new BigDecimal("844.56"), LocalDateTime.of(2021, 10, 12, 19, 00 , 00), customers[0]),
                new CupsEntity("AAPPZZZ6KZ1R149944", new BigDecimal("723.23"), LocalDateTime.of(2021, 10, 12, 19, 00 , 00), customers[1]),
                new CupsEntity("AAPPZZZ6KZ1R149945", new BigDecimal("814.12"),LocalDateTime.of(2021, 10, 12, 19, 00 , 00),  customers[2]),
                new CupsEntity("AAPPZZZ6KZ1R149946", new BigDecimal("912.31"),LocalDateTime.of(2021, 10, 12, 19, 00 , 00),  customers[3])
        };
        this.cupsRepository.saveAll(Arrays.asList(cups));

        PlanEntity[] plans = {
                new PlanEntity("tarifa plana", new BigDecimal(70), 24),
                new PlanEntity("elige tus horas", new BigDecimal(60), 12),
                new PlanEntity("plan dia", new BigDecimal(55), 12),
                new PlanEntity("plan noche", new BigDecimal(40), 12)
        };
        this.planRepository.saveAll(Arrays.asList(plans));

        EmarketerEntity[] emarketers = {
                new EmarketerEntity("Comercializadora A", "Madrid", true, List.of(cups[0]), List.of(plans[0])),
                new EmarketerEntity("Comercializadora B", "Barcelona", false, List.of(cups[1]), List.of(plans[1])),
                new EmarketerEntity("Comercializadora C", "Bilbao", true, List.of(cups[2]), List.of(plans[2])),
                new EmarketerEntity("Comercializadora D", "Vigo", false, List.of(cups[3]), List.of(plans[3]))
        };
        this.emarketerRepository.saveAll(Arrays.asList(emarketers));
    }

    public void deleteAll() {
        this.emarketerRepository.deleteAll();
        this.planRepository.deleteAll();
        this.cupsRepository.deleteAll();
        this.customerRepository.deleteAll();
    }

}
