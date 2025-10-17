package es.upm.miw.apaw.adapters.mongodb.bank.daos;

import es.upm.miw.apaw.adapters.mongodb.bank.entities.PaymentHistoryEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class PaymentHistoryRepositoryIT {

    @Autowired
    private PaymentHistoryRepository paymentHistoryRepository;

    @Test
    void testFindById(){
        assertTrue(this.paymentHistoryRepository.findById(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff1000")).isPresent());
        PaymentHistoryEntity paymentHistory = this.paymentHistoryRepository.findById(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff1000")).get();
        assertThat(paymentHistory.getId()).isEqualTo(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff1000"));
        assertThat(paymentHistory.getPaid()).isTrue();
        assertThat(paymentHistory.getAmount()).isEqualTo(new BigDecimal("9.99"));
    }
}
