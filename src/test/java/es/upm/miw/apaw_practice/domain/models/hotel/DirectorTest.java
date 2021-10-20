package es.upm.miw.apaw_practice.domain.models.hotel;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DirectorTest {

    private static final List<Director> directors = new ArrayList<>();
    private static final List<TreeDirectorsLeaf> directorsLeaf = new ArrayList<>();
    private static final List<TreeDirectorsComposite> directorsComposite = new ArrayList<>();
    private static final TreeDirectorsComposite treeFather = new TreeDirectorsComposite("TreeFather");

    @BeforeAll
    static void createDirectors() {
        Room[] rooms = {
                new Room(1, new BigDecimal(25), false, new ArrayList<>()),
                new Room(2, new BigDecimal(50), false, new ArrayList<>()),
                new Room(3, new BigDecimal(100), false, new ArrayList<>()),
                new Room(4, new BigDecimal(150), true, new ArrayList<>()),
        };

        Hotel[] hotels = {
                new Hotel("Maru", "Direction Maru", 3, List.of(rooms[0])),
                new Hotel("Odisea", "Direction Odisea", 2, List.of(rooms[1], rooms[2])),
                new Hotel("Sole Wanderer", "Direction Sole Wanderer, Salamanca, 15243", 4, List.of(rooms[3])),
        };

        directors.add(
                new Director("01010101R", "ymustafa@greendike.com", 222222222, List.of(hotels[0])));
        directors.add(
                new Director("31313131P", "6n3n319@johonmasalalu.com", 1234567, List.of(hotels[1])));
        directors.add(
                new Director("99999999E", "onasrin@prct.site", 765432331, List.of(hotels[2])));

        directors.add(
                new Director("45674734S", "tnikosc@rmune.com", 209123345, List.of(hotels[0])));
        directors.add(
                new Director("00000000A", "ymaka@nbobd.com", 902902895, List.of(hotels[1])));
        directors.add(
                new Director("45747567R", "arabalfaidyo@rehtdita.com", 564738293, List.of(hotels[2])));

        directors.add(
                new Director("87543689P", "zyounes-isse@filevino.com", 456713546, List.of(hotels[0])));
        directors.add(
                new Director("66576893J", "twashington@eetieg.com", 123123123, List.of(hotels[1])));

        directors.add(
                new Director("87543689P", "zyounes-isse@filevino.com", 456713546, List.of(hotels[0])));
        directors.add(
                new Director("66576893J", "twashington@eetieg.com", 123123123, List.of(hotels[1])));


        directorsLeaf.add(new TreeDirectorsLeaf(directors.get(0)));
        directorsLeaf.add(new TreeDirectorsLeaf(directors.get(1)));
        directorsLeaf.add(new TreeDirectorsLeaf(directors.get(2)));
        directorsLeaf.add(new TreeDirectorsLeaf(directors.get(3)));
        directorsLeaf.add(new TreeDirectorsLeaf(directors.get(4)));
        directorsLeaf.add(new TreeDirectorsLeaf(directors.get(5)));
        directorsLeaf.add(new TreeDirectorsLeaf(directors.get(6)));
        directorsLeaf.add(new TreeDirectorsLeaf(directors.get(7)));
        directorsLeaf.add(new TreeDirectorsLeaf(directors.get(8)));
        directorsLeaf.add(new TreeDirectorsLeaf(directors.get(9)));

        directorsComposite.add(new TreeDirectorsComposite("Directors"));
        directorsComposite.get(0).add(new TreeDirectorsLeaf(directors.get(0)));
        directorsComposite.get(0).add(new TreeDirectorsLeaf(directors.get(1)));
        directorsComposite.get(0).add(new TreeDirectorsLeaf(directors.get(2)));

        directorsComposite.add(new TreeDirectorsComposite("Subdirectors"));
        directorsComposite.get(1).add(new TreeDirectorsLeaf(directors.get(3)));
        directorsComposite.get(1).add(new TreeDirectorsLeaf(directors.get(4)));
        directorsComposite.get(1).add(new TreeDirectorsLeaf(directors.get(5)));

        directorsComposite.get(1).add(new TreeDirectorsComposite("Hotel Manager"));
        directorsComposite.get(1).getTreeDirector().get(3).add(directorsLeaf.get(6));
        directorsComposite.get(1).getTreeDirector().get(3).add(directorsLeaf.get(7));

        directorsComposite.get(1).add(new TreeDirectorsComposite("Marketing Manager"));
        directorsComposite.get(1).getTreeDirector().get(4).add(directorsLeaf.get(8));
        directorsComposite.get(1).getTreeDirector().get(4).add(directorsLeaf.get(9));

        treeFather.add(directorsComposite.get(0));
        treeFather.add(directorsComposite.get(1));

    }

    @Test
    void testDirectorLeaf() {
        assertFalse(directorsLeaf.get(0).isComposite());
        assertEquals(1, directorsLeaf.get(0).numberOfNodes());
    }

    @Test
    void testCompositePattern() {
        assertTrue(directorsComposite.get(0).isComposite());

        assertEquals("Directors", directorsComposite.get(0).getName());
        assertEquals("Subdirectors", directorsComposite.get(1).getName());

        assertEquals("Hotel Manager",((TreeDirectorsComposite) directorsComposite.get(1).getTreeDirector().get(3)).getName());
        assertEquals("Marketing Manager",((TreeDirectorsComposite) directorsComposite.get(1).getTreeDirector().get(4)).getName());

        assertEquals(2, directorsComposite.size());
        assertEquals(10, directorsLeaf.size());

        assertEquals(3, directorsComposite.get(0).numberOfNodes());

        assertEquals(7, directorsComposite.get(1).numberOfNodes());

        assertEquals(2, directorsComposite.get(1).getTreeDirector().get(3).numberOfNodes());
        assertEquals(2, directorsComposite.get(1).getTreeDirector().get(4).numberOfNodes());

        assertEquals(10, treeFather.numberOfNodes());

    }
}
