package es.upm.miw.apaw_practice.domain.models.bank;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class TreeClientTest {

    private TreeClientLeaf treeClientLeaf1;
    private TreeClientLeaf treeClientLeaf2;
    private TreeClientComposite treeClientComposite;
    private Client client1;
    private InvestmentFund investmentFund1;
    private InvestmentFund investmentFund2;

    @BeforeEach
    void setUp() {
        investmentFund1 = new InvestmentFund("FundG", new BigDecimal("1000.0"), 32);
        investmentFund2 = new InvestmentFund("FundH", new BigDecimal("1000.0"), 32);
        client1 = new Client("88888888H", "Client8", "Client8", 888888888, "email8@example.com", List.of(investmentFund1));
        Client client2 = new Client("99999999I", "Client9", "Client9", 999999999, "email9@example.com", List.of(investmentFund2));

        treeClientLeaf1 = new TreeClientLeaf(client1);
        treeClientLeaf2 = new TreeClientLeaf(client2);

        treeClientComposite = new TreeClientComposite(List.of(treeClientLeaf1, treeClientLeaf2), "Group1");

    }

    @Test
    void testLeafs() {
        assertFalse(treeClientLeaf1.isComposite());
        assertEquals(client1, treeClientLeaf1.getClient());
        assertEquals(List.of("88888888H"), treeClientLeaf1.getDni());
        assertEquals(List.of("Client8"), treeClientLeaf1.getName());
        assertEquals(List.of("Client8"), treeClientLeaf1.getSurname());
        assertEquals(List.of(888888888), treeClientLeaf1.getPhoneNumber());
        assertEquals(List.of("email8@example.com"), treeClientLeaf1.getEmail());
        assertEquals(List.of(investmentFund1), treeClientLeaf1.getInvestmentFunds());
    }

    @Test
    void testComposite() {
        assertTrue(treeClientComposite.isComposite());
        assertEquals(List.of(treeClientLeaf1,treeClientLeaf2), treeClientComposite.getTreeClients());
        assertEquals("Group1", treeClientComposite.getClientGroupName());
        assertEquals(List.of("88888888H","99999999I"), treeClientComposite.getDni());
        assertEquals(List.of("Client8","Client9"), treeClientComposite.getName());
        assertEquals(List.of("Client8","Client9"), treeClientComposite.getSurname());
        assertEquals(List.of(888888888,999999999), treeClientComposite.getPhoneNumber());
        assertEquals(List.of("email8@example.com","email9@example.com"), treeClientComposite.getEmail());
        assertEquals(List.of(investmentFund1,investmentFund2), treeClientComposite.getInvestmentFunds());
    }

    @Test
    void testExceptions() {
        assertThrows(UnsupportedOperationException.class, () -> treeClientLeaf1.add(treeClientLeaf2));
        assertThrows(UnsupportedOperationException.class, () -> treeClientLeaf1.remove(treeClientLeaf2));
    }
}
