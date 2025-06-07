package es.upm.miw.apaw_practice.domain.models.music_festival;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;

class MusicFestivalTest {

    @Test
    void testEqualsSameObject() {
        MusicFestival festival = new MusicFestival();
        assertEquals(festival, festival);
    }

    @ParameterizedTest
    @CsvSource("Fest") @NullSource
    void testEquals(String data) {
        MusicFestival festival = new MusicFestival();
        festival.setName("Fest");
        boolean validate = festival.equals(data);
        assertFalse(validate);
    }

    @Test
    void testEqualsSameName() {
        MusicFestival festival1 = new MusicFestival();
        festival1.setName("FestivalName");
        MusicFestival festival2 = new MusicFestival();
        festival2.setName("FestivalName");
        assertEquals(festival1, festival2);
    }

    @Test
    void testEqualsDifferentName() {
        MusicFestival festival1 = new MusicFestival();
        festival1.setName("FestivalA");
        MusicFestival festival2 = new MusicFestival();
        festival2.setName("FestivalB");
        assertNotEquals(festival1, festival2);
    }

    @Test
    void testEqualsWithSubclass() {
        MusicFestival festival = new MusicFestival();
        festival.setName("Fest");
        class LocalMusicFestival extends MusicFestival { }
        MusicFestival subFestival = new LocalMusicFestival();
        subFestival.setName("Fest");
        assertNotEquals(festival, subFestival);
    }

    @Test
    void testEqualsWithNullNameInOther() {
        MusicFestival festival = new MusicFestival();
        festival.setName("Fest");
        MusicFestival otherFestival = new MusicFestival();
        otherFestival.setName(null);
        assertNotEquals(festival, otherFestival);
    }
}