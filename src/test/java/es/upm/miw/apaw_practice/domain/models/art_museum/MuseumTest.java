package es.upm.miw.apaw_practice.domain.models.art_museum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MuseumTest {
    private MuseumComponent museumRoot;
    private MuseumComponent museumComposite;
    private MuseumComponent museumLeaf;

    @BeforeEach
    void ini() {
        this.museumRoot = new MuseumComposite();

        Museum museum1 = new Museum("El Prado", 600, true,
                List.of(new Artwork("27001", "La Gioconda", 1506,
                        new Artist("Leonardo da Vinci", 67, "Renaissance"))),
                List.of(new Exhibition("Spanish authors", LocalDateTime.of(2024, 10, 10,10, 0), BigDecimal.valueOf(35.00))));
        Museum museum2 = new Museum("Thyssen", 300, false, List.of(), List.of());


        this.museumLeaf = new MuseumLeaf(museum1);
        MuseumLeaf museumLeaf2 = new MuseumLeaf(museum2);

        this.museumRoot.add(museumLeaf);
        this.museumRoot.add(museumLeaf2);

        this.museumComposite = new MuseumComposite();
        Museum museum3 = new Museum("Amsterdam", 1000, true, List.of(), List.of());
        this.museumComposite.add(new MuseumLeaf(museum3));
        this.museumComposite.add(new MuseumLeaf(new Museum("Sorolla", 500, true,
                List.of(new Artwork("27002", "The Starry Night", 1889,
                        new Artist("Vincent van Gogh", 37, "Post-Impressionism"))),
                List.of(new Exhibition("Impressionist authors", LocalDateTime.of(2024, 12, 12, 10, 0), BigDecimal.valueOf(40.00))))));

        this.museumRoot.add(museumComposite);
    }

    @Test
    void testIsComposite() {
        assertTrue(this.museumRoot.isComposite());
        assertTrue(this.museumComposite.isComposite());
    }

    @Test
    void testIsNotComposite() {
        assertFalse(this.museumLeaf.isComposite());
    }

    @Test
    void testAddLeaf() {
        Museum museum = new Museum("Louvre", 700, true, List.of(), List.of());
        assertThrows(UnsupportedOperationException.class, () -> this.museumLeaf.add(new MuseumLeaf(museum)));
    }

    @Test
    void testRemoveLeaf() {
        Museum museum = new Museum("Louvre", 700, true, List.of(), List.of());
        assertThrows(UnsupportedOperationException.class, () -> this.museumLeaf.remove(new MuseumLeaf(museum)));
    }

    @Test
    void testAddRemoveComponents() {
        Museum museum = new Museum("Louvre", 700, true, List.of(), List.of());
        MuseumLeaf leaf = new MuseumLeaf(museum);

        this.museumRoot.add(leaf);
        assertTrue(this.museumRoot.getName().toList().contains("Louvre"));

        this.museumRoot.remove(leaf);
        assertFalse(this.museumRoot.getName().toList().contains("Louvre"));
    }

    @Test
    void testGetName() {
        List<String> museumNames = this.museumRoot.getName().toList();
        assertEquals(4, museumNames.size());
        assertTrue(museumNames.contains("El Prado"));
        assertTrue(museumNames.contains("Thyssen"));
        assertTrue(museumNames.contains("Amsterdam"));
        assertTrue(museumNames.contains("Sorolla"));
    }

    @Test
    void testGetCapacity() {
        List<Integer> capacities = this.museumRoot.getCapacity().toList();
        assertEquals(4, capacities.size());
        assertTrue(capacities.contains(600));
        assertTrue(capacities.contains(300));
        assertTrue(capacities.contains(1000));
        assertTrue(capacities.contains(500));
    }

    @Test
    void testGetOpen() {
        List<Boolean> openStatus = this.museumRoot.getOpen().toList();
        assertEquals(4, openStatus.size());
    }

    @Test
    void testGetArtworks() {
        List<Artwork> artworks = this.museumRoot.getArtworks().toList();
        assertEquals(2, artworks.size());

        assertTrue(artworks.stream().anyMatch(artwork -> artwork.getTitleName().equals("La Gioconda")));
        assertTrue(artworks.stream().anyMatch(artwork -> artwork.getTitleName().equals("The Starry Night")));
    }

    @Test
    void testGetExhibitions() {
        List<Exhibition> exhibitions = this.museumRoot.getExhibitions().toList();
        assertEquals(2, exhibitions.size());

        assertTrue(exhibitions.stream().anyMatch(exhibition -> exhibition.getName().equals("Spanish authors")));
        assertTrue(exhibitions.stream().anyMatch(exhibition -> exhibition.getName().equals("Impressionist authors")));
    }
}
