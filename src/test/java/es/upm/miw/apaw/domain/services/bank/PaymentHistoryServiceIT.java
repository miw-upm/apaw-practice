package es.upm.miw.apaw.domain.services.bank;

import es.upm.miw.apaw.adapters.mongodb.bank.entities.PaymentHistoryEntity;
import es.upm.miw.apaw.domain.models.bank.PaymentHistoryUpdating;
import es.upm.miw.apaw.domain.persistenceports.bank.PaymentHistoryPersistence;
import es.upm.miw.apaw.domain.persistenceports.shop.ArticlePersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class PaymentHistoryServiceIT {
    @Autowired
    private PaymentHistoryService paymentHistoryService;

    @Autowired
    private PaymentHistoryPersistence paymentHistoryPersistence;

    @Test
    void tetsUpdatePaymentHistoryPaid(){
        PaymentHistoryUpdating[] paymentHistoryUpdatings = {
                PaymentHistoryUpdating.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff2000"))
                        .paid(true)
                        .build(),
                PaymentHistoryUpdating.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff3000"))
                        .paid(false)
                        .build()};
        this.paymentHistoryService.updatePaymentHistoryPaid(Arrays.stream(paymentHistoryUpdatings));
        assertThat(this.paymentHistoryPersistence.read(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff2000")).getPaid()).isTrue();
        assertThat(this.paymentHistoryPersistence.read(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff3000")).getPaid()).isFalse();
        PaymentHistoryUpdating[] paymentBack = {
                PaymentHistoryUpdating.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff2000"))
                        .paid(false)
                        .build(),
                PaymentHistoryUpdating.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff3000"))
                        .paid(true)
                        .build()};
        this.paymentHistoryService.updatePaymentHistoryPaid(Arrays.stream(paymentBack));
    }
}
