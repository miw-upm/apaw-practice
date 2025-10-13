package es.upm.miw.apaw.domain.services.bank;

import es.upm.miw.apaw.adapters.mongodb.bank.daos.BankAccountRepository;
import es.upm.miw.apaw.adapters.mongodb.bank.entities.BankAccountEntity;
import es.upm.miw.apaw.adapters.mongodb.bank.entities.CreditCardEntity;
import es.upm.miw.apaw.adapters.mongodb.bank.entities.LoanEntity;
import es.upm.miw.apaw.adapters.mongodb.bank.entities.PaymentHistoryEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.bank.Loan;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
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
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ActiveProfiles("test")
class BankAccountServiceTest {

    @Autowired
    private BankAccountService bankAccountService;

    @MockitoBean
    private UserRestClient userRestClient;

    @Test
    void testObtainTotalQuantityByMobile(){
        BDDMockito.given(this.userRestClient.readByMobile(any(String.class)))
                .willAnswer(invocation ->
                        UserDto.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"))
                                .mobile(invocation.getArgument(0))
                                .firstName("mock").build());
        assertEquals(new BigDecimal("100000"),this.bankAccountService.obtainTotalQuantityByMobile("123123123"));
    }

}
