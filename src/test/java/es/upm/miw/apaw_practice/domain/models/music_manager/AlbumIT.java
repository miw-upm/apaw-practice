package es.upm.miw.apaw_practice.domain.models.music_manager;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class AlbumIT {
    private TreeAlbums albums;
    private TreeAlbums albumsCD;
    private TreeAlbums albumsVinyl;
    private List<Album> albumList;

    @BeforeEach
    void initializeCompositeTest() {
        albums = new TreeAlbumsComposite("Albums");
        albumList = List.of(
                new Album(null, null, "Piano Man", "Family Productions",
                        new BigDecimal("4.95"), LocalDate.of(1973, 11, 9)),
                new Album(null, null, "Appetite for Destruction", "Geffen",
                        new BigDecimal("6.95"), LocalDate.of(1981, 7, 21)),
                new Album(null, null, "Led Zeppelin IV", "Atlantic",
                        new BigDecimal("9.99"), LocalDate.of(1971, 11, 8))
        );
        albumsCD = new TreeAlbumsComposite("CD");
        albumsVinyl = new TreeAlbumsComposite("Vinyl");
        List<TreeAlbums> subcategories = List.of(
                albumsCD,
                albumsVinyl
        );
        subcategories.forEach(albums::add);
        List<Album> albumCDList = List.of(albumList.get(0), albumList.get(1));
        List<Album> albumsVinylList = List.of(albumList.get(2));
        albumCDList.stream()
                .map(TreeAlbumsLeaf::new)
                .forEach(treeAlbumsLeaf -> albumsCD.add(treeAlbumsLeaf));
        albumsVinylList.stream()
                .map(TreeAlbumsLeaf::new)
                .forEach(treeAlbumsLeaf -> albumsVinyl.add(treeAlbumsLeaf));
    }

    @Test
    void testComposite() {
        assertEquals(List.of(albumList.get(0), albumList.get(1)), albumsCD.getAlbums());
        assertEquals(List.of(albumList.get(2)), albumsVinyl.getAlbums());
        assertEquals(albumList, albums.getAlbums());
    }
}
