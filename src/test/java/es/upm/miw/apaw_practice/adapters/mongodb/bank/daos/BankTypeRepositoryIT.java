package es.upm.miw.apaw_practice.adapters.mongodb.bank.daos;

import es.upm.miw.apaw_practice.TestConfig;
import static org.junit.jupiter.api.Assertions.*;

import es.upm.miw.apaw_practice.adapters.mongodb.bank.BankSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.bank.entities.BankTypeEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@TestConfig
public class BankTypeRepositoryIT {
    @Autowired
    private BankTypeRepository bankTypeRepository;
    @Autowired
    private BankSeederService bankSeederService;




    private static final String TYPENAME="Banco Comercial";

    private static final String DESCRIPTION="Banco que ofrece una amplia gama de servicios financieros a empresas y consumidores.";

    private static final BigDecimal MINIMUNCAPITAL=new BigDecimal("19000000.00");
    @AfterEach
    void resetDataBase() {
        this.bankSeederService.deleteAll();
        this.bankSeederService.seedDatabase();
    }
    @Test
    void testCreateAndRead() {
        assertTrue(this.bankTypeRepository.findAll().stream()
                .anyMatch(bankType ->
                        bankType.getTypeName().equals(TYPENAME) &&
                                bankType.getDescription().equals(DESCRIPTION) &&
                                bankType.getMinimunCapital().equals(MINIMUNCAPITAL)
                ));
    }

    @Test
    void testfindByTypeName(){
        assertTrue(this.bankTypeRepository.findByTypeName("Banco de Inversión").isPresent());
        BankTypeEntity bankTypeEntity= this.bankTypeRepository.findByTypeName("Banco de Inversión").get();
        assertEquals(new BigDecimal("10000000.00"),bankTypeEntity.getMinimunCapital());
    }

    @Test
    void testNotFoundByTypeName(){

        assertFalse(this.bankTypeRepository.findByTypeName("BBBA").isPresent());

    }
}
