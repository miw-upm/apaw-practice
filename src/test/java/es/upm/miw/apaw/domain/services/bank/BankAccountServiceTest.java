package es.upm.miw.apaw.domain.services.bank;

import es.upm.miw.apaw.adapters.mongodb.bank.daos.BankAccountRepository;
import es.upm.miw.apaw.adapters.mongodb.bank.entities.BankAccountEntity;
import es.upm.miw.apaw.adapters.mongodb.bank.entities.CreditCardEntity;
import es.upm.miw.apaw.adapters.mongodb.bank.entities.LoanEntity;
import es.upm.miw.apaw.adapters.mongodb.bank.entities.PaymentHistoryEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.bank.Loan;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class BankAccountServiceTest {

    @Autowired
    private BankAccountService bankAccountService;

    @MockitoBean
    private BankAccountRepository bankAccountRepository;


    @Test
    void testApplyANewLoanForABankAccount(){
        Loan loan = Loan.builder().quantity(new BigDecimal("10000")).condition("active").interestRate(0.07).build();
        LoanEntity mockLoan = new LoanEntity(loan);
        BankAccountEntity mock = BankAccountEntity.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff8000"))
                .accountNumber("ES2800000000000000000002")
                .balance(new BigDecimal("6666.66"))
                .status("active")
                .accountHolders(List.of(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002")))
                .creditCardAssociated(CreditCardEntity.builder().cardNumber("1111222233334446").expirationDate(LocalDate.of(2028,8,8)).cardLimit(new BigDecimal("2000")).paymentHistoryList(Collections.singletonList(PaymentHistoryEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff6000"))
                        .amount(new BigDecimal("59.99"))
                        .paymentDate(LocalDateTime.now())
                        .paid(false)
                        .build())).cvv(789).build())
                .build();
        BDDMockito.given(this.bankAccountRepository.findByAccountNumber(Mockito.anyString())).willReturn(Optional.ofNullable(mock));
        mock.setLoansApplied(new ArrayList<>(List.of(mockLoan)));
        BDDMockito.given(this.bankAccountRepository.save(Mockito.any())).willReturn(mock);


        List<Loan> result = this.bankAccountService.applyANewLoanForABankAccount("ES2800000000000000000002",loan);
        assertEquals(2,result.size());
        assertNotNull(result.getFirst().getId());
        assertEquals(new BigDecimal("10000"), result.getFirst().getQuantity());
        assertEquals("active",result.getFirst().getCondition());
        assertEquals(0.07,result.getFirst().getInterestRate());
    }

    @Test
    void testDelete(){
        BDDMockito.given(this.bankAccountRepository.deleteByAccountNumber(Mockito.anyString())).willReturn(1);
        BDDMockito.given(this.bankAccountRepository.findByAccountNumber(Mockito.anyString())).willReturn(Optional.empty());
        this.bankAccountService.delete("ES2800000000000000000001");
        assertThrows(NotFoundException.class, () -> this.bankAccountService.findByAccountNumber("ES2800000000000000000001"));
    }
}
