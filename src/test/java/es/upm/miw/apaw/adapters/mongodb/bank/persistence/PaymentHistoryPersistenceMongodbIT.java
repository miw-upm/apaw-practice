package es.upm.miw.apaw.adapters.mongodb.bank.persistence;

import es.upm.miw.apaw.adapters.mongodb.bank.daos.BankSeeder;
import es.upm.miw.apaw.adapters.mongodb.bank.entities.PaymentHistoryEntity;
import es.upm.miw.apaw.domain.models.bank.PaymentHistory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
class PaymentHistoryPersistenceMongodbIT {

    @Autowired
    private BankSeeder bankSeeder;

    @Autowired
    private PaymentHistoryPersistenceMongodb paymentHistoryPersistenceMongodb;

    @Test
    void testRead(){
        assertNotNull(this.paymentHistoryPersistenceMongodb.read(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff1000")));
        PaymentHistory paymentHistory = this.paymentHistoryPersistenceMongodb.read(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff1000"));
        assertThat(paymentHistory.getId()).isEqualTo(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff1000"));
        assertThat(paymentHistory.getPaid()).isTrue();
        assertThat(paymentHistory.getAmount()).isEqualTo(new BigDecimal("9.99"));
    }

    @Test
    void testUpdate(){
        PaymentHistory paymentHistory = PaymentHistory
                .builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff1000"))
                .paid(false)
                .paymentDate(LocalDateTime.now())
                .amount(new BigDecimal("19.99"))
                .build();

        PaymentHistory result = this.paymentHistoryPersistenceMongodb.update(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff1000"),paymentHistory);

        assertFalse(result.getPaid());
        assertThat(result.getId()).isEqualTo(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff1000"));
        assertThat(paymentHistory.getAmount()).isEqualTo(new BigDecimal("19.99"));
        bankSeeder.deleteAll();
        bankSeeder.seedDatabase();
    }
}
