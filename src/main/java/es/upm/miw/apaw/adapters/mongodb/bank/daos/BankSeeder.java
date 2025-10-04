package es.upm.miw.apaw.adapters.mongodb.bank.daos;

import es.upm.miw.apaw.adapters.mongodb.bank.entities.BankAccountEntity;
import es.upm.miw.apaw.adapters.mongodb.bank.entities.CreditCardEntity;
import es.upm.miw.apaw.adapters.mongodb.bank.entities.LoanEntity;
import es.upm.miw.apaw.adapters.mongodb.bank.entities.PaymentHistoryEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Repository
@Profile({"dev", "test"})
@Log4j2
public class BankSeeder {

    private final BankAccountRepository bankAccountRepository;
    private final PaymentHistoryRepository paymentHistoryRepository;
    private static final String ACTIVE = "active";
    private static final String BLOCKED = "blocked";

    @Autowired
    public BankSeeder(BankAccountRepository bankAccountRepository, PaymentHistoryRepository paymentHistoryRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.paymentHistoryRepository = paymentHistoryRepository;
    }

    public void seedDatabase(){
        log.warn("------- Bank Initial Load -----------");

        PaymentHistoryEntity[] paymentHistories = {
                PaymentHistoryEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff1000"))
                        .amount(new BigDecimal("9.99"))
                        .paymentDate(LocalDateTime.now())
                        .paid(true)
                        .build(),
                PaymentHistoryEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff2000"))
                        .amount(new BigDecimal("19.99"))
                        .paymentDate(LocalDateTime.now())
                        .paid(false)
                        .build(),
                PaymentHistoryEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff3000"))
                        .amount(new BigDecimal("29.99"))
                        .paymentDate(LocalDateTime.now())
                        .paid(true)
                        .build(),
                PaymentHistoryEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff4000"))
                        .amount(new BigDecimal("39.99"))
                        .paymentDate(LocalDateTime.now())
                        .paid(false)
                        .build(),
                PaymentHistoryEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff5000"))
                        .amount(new BigDecimal("49.99"))
                        .paymentDate(LocalDateTime.now())
                        .paid(true)
                        .build()
        };
        this.paymentHistoryRepository.saveAll(Arrays.asList(paymentHistories));

        BankAccountEntity[] bankAccountEntities = {
                BankAccountEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff6000"))
                        .accountNumber("ES2800000000000000000000")
                        .balance(new BigDecimal("8888.88"))
                        .status(ACTIVE)
                        .accountHolders(List.of(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"),UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001")))
                        .creditCardAssociated(CreditCardEntity.builder().cardNumber("1111222233334444").expirationDate(LocalDate.of(2030,12,31)).cardLimit(new BigDecimal("500")).paymentHistoryList(Arrays.asList(paymentHistories[0], paymentHistories[1])).cvv(123).build())
                        .loansApplied(Collections.singletonList(LoanEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0100")).quantity(new BigDecimal("100000")).interestRate(0.07).condition("Pending").build()))
                        .build(),
                BankAccountEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff7000"))
                        .accountNumber("ES2800000000000000000001")
                        .balance(new BigDecimal("5065.65"))
                        .status(BLOCKED)
                        .accountHolders(List.of(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002")))
                        .creditCardAssociated(CreditCardEntity.builder().cardNumber("1111222233334445").expirationDate(LocalDate.of(2028,1,1)).cardLimit(new BigDecimal("1000")).paymentHistoryList(Arrays.asList(paymentHistories[2], paymentHistories[3],paymentHistories[4])).cvv(456).build())
                        .loansApplied(Arrays.asList(LoanEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0200")).quantity(new BigDecimal("20000")).interestRate(0.07).condition(ACTIVE).build(),LoanEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0300")).quantity(new BigDecimal("50000")).interestRate(0.07).condition(ACTIVE).build()))
                        .build()
        };
        this.bankAccountRepository.saveAll(Arrays.asList(bankAccountEntities));
        log.warn("        ------- bank");
    }

    public void deleteAll() {
        this.bankAccountRepository.deleteAll();
        this.paymentHistoryRepository.deleteAll();
    }

}
