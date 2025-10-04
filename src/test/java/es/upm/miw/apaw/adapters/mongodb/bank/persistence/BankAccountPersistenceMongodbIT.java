package es.upm.miw.apaw.adapters.mongodb.bank.persistence;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class BankAccountPersistenceMongodbIT {
    @Autowired
    private BankAccountPersistenceMongodb bankAccountPersistenceMongodb;

    @Test
    void testReadStatusByAccountNumberNotFound() {
        assertThrows(NotFoundException.class, () -> this.bankAccountPersistenceMongodb.readStatusByAccountNumber("ES2800000000000000000004"));
    }

    @Test
    void testReadStatusByAccountNumber(){
        assertThat(this.bankAccountPersistenceMongodb.readStatusByAccountNumber("ES2800000000000000000000")).isEqualTo("active");
    }
}
