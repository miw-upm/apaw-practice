package es.upm.miw.apaw_practice.domain.models.music_festival;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TreeConcertTest {

    private TreeConcertLeaf concertLeaf1;
    private TreeConcertLeaf concertLeaf2;
    private TreeConcertComposite concertComposite;

    @BeforeEach
    void setUp() {
        Stage mainStage = new Stage("MainStage", "Central Park", 10000,
                LocalDateTime.of(2025, 5, 15, 14, 0));
        Stage secondStage = new Stage("SecondStage", "Open Air Theater", 5000,
                LocalDateTime.of(2025, 6, 15, 15, 0));

        ConcertArtist artist1 = new ConcertArtist("The Fabulous", "Mexican", 4.5);
        ConcertArtist artist2 = new ConcertArtist("DJ Moon", "Spanish", 4.2);
        ConcertArtist artist3 = new ConcertArtist("ElectroBand", "Argentinian", 4.7);
        ConcertArtist artist4 = new ConcertArtist("FolkSingers", "Colombian", 4.1);

        Concert concert1 = new Concert(LocalDate.of(2025, 5, 15), BigDecimal.valueOf(60.50), false);
        concert1.setStage(mainStage);
        concert1.setArtists(List.of(artist1, artist2, artist3));
        this.concertLeaf1 = new TreeConcertLeaf(concert1);

        Concert concert2 = new Concert(LocalDate.of(2025, 6, 15), BigDecimal.valueOf(55.00), false);
        concert2.setStage(secondStage);
        concert2.setArtists(List.of(artist1, artist4));
        this.concertLeaf2 = new TreeConcertLeaf(concert2);

        this.concertComposite = new TreeConcertComposite(
                LocalDate.of(2025, 6, 16), BigDecimal.valueOf(58.50), false,
                mainStage, List.of(artist1, artist2)
        );
        this.concertComposite.add(this.concertLeaf1);
        this.concertComposite.add(this.concertLeaf2);
    }

    @Test
    void testLeafValues() {
        assertFalse(concertLeaf1.isComposite());
        assertEquals(LocalDate.of(2025, 5, 15), concertLeaf1.date());
        assertEquals(BigDecimal.valueOf(60.50), concertLeaf1.ticketPrice());
        assertFalse(concertLeaf1.isSoldOut());
        assertEquals("MainStage", concertLeaf1.stage().getName());
        assertEquals(3, concertLeaf1.artists().size());
        assertEquals(1, concertLeaf1.countConcerts());
        assertEquals(BigDecimal.valueOf(60.50), concertLeaf1.totalTicketPrice());
    }

    @Test
    void testCompositeValues() {
        assertTrue(concertComposite.isComposite());
        assertEquals(LocalDate.of(2025, 6, 16), concertComposite.date());
        assertEquals(BigDecimal.valueOf(58.50), concertComposite.ticketPrice());
        assertFalse(concertComposite.isSoldOut());
        assertEquals("MainStage", concertComposite.stage().getName());
        assertEquals(2, concertComposite.artists().size());
        assertEquals(2, concertComposite.countConcerts());
        assertEquals(BigDecimal.valueOf(115.50), concertComposite.totalTicketPrice());

        concertComposite.delete(concertLeaf2);
        assertEquals(1, concertComposite.countConcerts());
        assertEquals(BigDecimal.valueOf(60.50), concertComposite.totalTicketPrice());
    }

    @Test
    void testUnsupportedOperationsInLeaf() {
        assertThrows(UnsupportedOperationException.class, () -> concertLeaf1.add(concertLeaf2));
        assertThrows(UnsupportedOperationException.class, () -> concertLeaf1.delete(concertLeaf2));
    }
}