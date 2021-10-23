package es.upm.miw.apaw_practice.domain.models.vet_clinic;

import es.upm.miw.apaw_practice.TestConfig;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class VetCompositeTest {
    private Vet vet, vet2, vet3;
    private VetComposite vetCompositeRoot, vetComposite1;

    @BeforeEach
    void createTree() {
        vet = Vet.builder()
                .vetNumber(147)
                .name("vet147")
                .surname("surname147")
                .build();
        vet2 = Vet.builder()
                .vetNumber(258)
                .name("vet258")
                .surname("surname258")
                .build();
        vet3 = Vet.builder()
                .vetNumber(369)
                .name("vet369")
                .surname("surname369")
                .build();
        vetCompositeRoot = new VetComposite("Root");
        vetCompositeRoot.add(new VetLeaf(vet));
        vetComposite1 = new VetComposite("VetComposite1");
        vetCompositeRoot.add(vetComposite1);
        vetComposite1.add(new VetLeaf(vet2));

    }
    @Test
    void vetCompositeTest() {
        assertFalse(new VetLeaf(vet).isComposite());
        assertFalse(new VetLeaf(vet2).isComposite());
        assertFalse(new VetLeaf(vet3).isComposite());
        assertTrue(vetCompositeRoot.isComposite());
        assertTrue(vetComposite1.isComposite());
    }

    @Test
    void addVetTest() {
        vetComposite1.add(new VetLeaf(vet3));
        assertEquals(2, vetComposite1.numberOfNodes());
        assertEquals(2, vetCompositeRoot.numberOfNodes());
    }

    @Test
    void removeVetTest() {
        vetComposite1.remove(new VetLeaf(vet2));
        assertEquals(1, vetComposite1.numberOfNodes());
    }
}
