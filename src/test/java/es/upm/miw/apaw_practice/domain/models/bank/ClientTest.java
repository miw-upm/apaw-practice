package es.upm.miw.apaw_practice.domain.models.bank;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class ClientTest {

    @Test
    void testBuilder() {
        Client client = Client.builder()
                .dni("11111111A")
                .name("Client")
                .surname("Client")
                .phoneNumber(111111111)
                .email("email1@example.com")
                .build();
        assertNotNull(client);
        assertEquals("11111111A", client.getDni());
        assertEquals("Client", client.getName());
        assertEquals("Client", client.getSurname());
        assertEquals(111111111, client.getPhoneNumber());
        assertEquals("email1@example.com", client.getEmail());
        assertNull(client.getInvestmentFunds());
    }

    @Test
    void testBuilderWithOptionals() {
        Client client = Client.builder()
                .dni("11111111A")
                .name("Client")
                .surname("Client")
                .phoneNumber(111111111)
                .email("email1@example.com")
                .investmentFunds(List.of(new InvestmentFund("FundI", new BigDecimal("1000.0"), 32)))
                .build();
        assertNotNull(client);
        assertEquals("11111111A", client.getDni());
        assertEquals("Client", client.getName());
        assertEquals("Client", client.getSurname());
        assertEquals(111111111, client.getPhoneNumber());
        assertEquals("email1@example.com", client.getEmail());
        assertNotNull(client.getInvestmentFunds());
    }
}
