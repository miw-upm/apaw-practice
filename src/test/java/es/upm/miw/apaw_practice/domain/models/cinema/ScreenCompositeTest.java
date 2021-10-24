package es.upm.miw.apaw_practice.domain.models.cinema;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class ScreenCompositeTest {

    private Screen screen1, screen2;
    private ScreenComposite screenComposite, screenComposite2;

    @BeforeEach
    void before() {
        List<Spectator> spectatorList1 = List.of(new Spectator[]{
                new Spectator("544578J", "Maria", "Vernia", LocalDate.of(2021, 04, 10)),
                new Spectator("546788P", "Marta", "Sanchis", LocalDate.of(2021, 04, 10)),
                new Spectator("994578T", "Javier", "Vernia", LocalDate.of(2021, 04, 10))
        });
        List<Spectator> spectatorList2 = List.of(new Spectator[]{
                new Spectator("544572e", "Rosa", "Garcia", LocalDate.of(2021, 04, 10)),
                new Spectator("544588h", "Elena", "Fernandez", LocalDate.of(2021, 04, 10)),
                new Spectator("993457t", "Carlos", "Martinez", LocalDate.of(2021, 04, 10))
        });
        screen1 = new Screen(1, 1, 90, false, spectatorList1);
        screen2 = new Screen(1, 1, 90, false, spectatorList2);

        screenComposite = new ScreenComposite("ScreenComposite1");
        screenComposite.add(new ScreenLeaf(screen1));
        screenComposite2 = new ScreenComposite("ScreenComposite2");
        screenComposite.add(screenComposite2);
    }

    @Test
    void screenCompositeTest() {
        assertFalse(new ScreenLeaf(screen1).isComposite());
        assertFalse(new ScreenLeaf(screen2).isComposite());
    }

    @Test
    void testCreateScreenComposite() {
        assertEquals("ScreenComposite1", screenComposite.getName());
        assertTrue(screenComposite.isComposite());
    }

    @Test
    void testAddScreenComposite() {
        screenComposite2.add(new ScreenLeaf(screen2));
        assertEquals(1, screenComposite2.numberOfNodes());
    }

    @Test
    void testRemoveScreenComposite() {
        screenComposite2.remove(new ScreenLeaf(screen2));
        assertEquals(0, screenComposite2.numberOfNodes());
    }
}
