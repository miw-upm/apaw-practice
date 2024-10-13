package es.upm.miw.apaw_practice.adapters.mongodb.bank.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.bank.entities.InvestmentFundEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class InvestmentFundRepositoryIT {

    @Autowired
    private InvestmentFundRepository investmentFundRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Test
    void testFindByName() {
        assertTrue(this.investmentFundRepository.findByName("FundA").isPresent());
        InvestmentFundEntity investmentFund = this.investmentFundRepository.findByName("FundA").get();
        assertEquals("FundA", investmentFund.getName());
        assertEquals(32, investmentFund.getAssets());
        assertEquals(new BigDecimal("1000.0"), investmentFund.getTotalCapital());
    }

    @Test
    void testDeleteByName() {
        assertTrue(this.investmentFundRepository.deleteByName("FundD").isPresent());
        assertFalse(this.investmentFundRepository.findByName("FundD").isPresent());
    }

}
