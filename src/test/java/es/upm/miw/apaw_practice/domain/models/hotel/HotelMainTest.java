package es.upm.miw.apaw_practice.domain.models.hotel;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class HotelMainTest {

    private HotelMainComponent root;
    private HotelMainComposite composite;
    private HotelMainLeaf leaf1;
    private HotelMainLeaf leaf2;
    private HotelMainLeaf leaf3;

    @BeforeEach
    void init() {
        this.root = new HotelMainComposite();

        composite = new HotelMainComposite();
        this.root.add(composite);

        leaf1 = new HotelMainLeaf(
                new HotelMain("xiangHotel", "calle hola", "911111111",
                        List.of(new HotelRoom("101", "dual", new BigDecimal("15.00"), false)),
                        List.of(new HotelClient("x1231232j","david","611111111","david@gmail.com")))
        );
        leaf2 = new HotelMainLeaf(
                new HotelMain("mengfeiHotel", "calle mengtxu", "911234567",
                        List.of(new HotelRoom("202", "dual", new BigDecimal("55.00"), false)),
                        List.of(new HotelClient("y9991232z","lily","622222222","lily@gmail.com")))
        );
        leaf3 = new HotelMainLeaf(
                new HotelMain("mengfeixiangHotel", "calle god", "922222222",
                        List.of(new HotelRoom("303", "dual", new BigDecimal("666.00"), false)),
                        List.of(new HotelClient("x1233215j","feixiang","666666666","god@gmail.com")))
        );
        composite.add(leaf1);
        composite.add(leaf2);
        this.root.add(leaf3);
    }

    @Test
    void testAddLeaf() {
        HotelMainLeaf leaf4 = new HotelMainLeaf(
                new HotelMain("testHotel", "calle god", "922222222",
                        List.of(new HotelRoom("303", "dual", new BigDecimal("666.00"), false)),
                        List.of(new HotelClient("x1233215j","feixiang","666666666","god@gmail.com")))
        );
        composite.add(leaf4);
        assertTrue(this.root.getName().toList().contains("testHotel"));
    }

    @Test
    void testRemoveLeaf() {
        composite.remove(leaf2);
        assertFalse(this.root.getName().toList().contains("mengfeiHotel"));
    }

    @Test
    void testAddUnsupported() {
        HotelMainLeaf leaf5 = new HotelMainLeaf(
                new HotelMain("testErrorHotel", "calle god", "922222222",
                        List.of(new HotelRoom("303", "dual", new BigDecimal("666.00"), false)),
                        List.of(new HotelClient("x1233215j","feixiang","666666666","god@gmail.com")))
        );
        assertThrows(UnsupportedOperationException.class, () -> leaf1.add(leaf5));
    }

    @Test
    void testRemoveUnsupported() {
        assertThrows(UnsupportedOperationException.class, () -> leaf1.remove(leaf2));
    }

    @Test
    void testIsComposite() {
        assertTrue(this.root.isComposite());
        assertTrue(this.composite.isComposite());
        assertFalse(this.leaf1.isComposite());
        assertFalse(this.leaf2.isComposite());
        assertFalse(this.leaf3.isComposite());
    }

    @Test
    void testGetName() {
        List<String> names = this.root.getName().toList();
        assertEquals(3, names.size());
        assertTrue(names.contains("xiangHotel"));
        assertTrue(names.contains("mengfeiHotel"));
        assertTrue(names.contains("mengfeixiangHotel"));
    }

    @Test
    void testGetAddress() {
        List<String> address = this.root.getAddress().toList();

        assertTrue(address.contains("calle god"));
        assertTrue(address.contains("calle hola"));
        assertTrue(address.contains("calle mengtxu"));
    }

    @Test
    void testGetPhone() {
        List<String> phones = this.root.getPhone().toList();
        assertEquals(3, phones.size());
        assertTrue(phones.contains("922222222"));
        assertTrue(phones.contains("911111111"));
        assertTrue(phones.contains("911234567"));
    }

    @Test
    void testGetRooms() {
        List<HotelRoom> rooms = this.root.getRooms().toList();
        assertEquals(3, rooms.size());
        assertTrue(rooms.stream().anyMatch(room -> room.getNumber().equals("303")));
        assertTrue(rooms.stream().anyMatch(room -> room.getNumber().equals("202")));
        assertTrue(rooms.stream().anyMatch(room -> room.getNumber().equals("101")));
    }

    @Test
    void testGetClients() {
        List<HotelClient> clients = this.root.getClients().toList();
        assertEquals(3, clients.size());
        assertTrue(clients.stream().anyMatch(client -> client.getIdentityDocument().equals("x1231232j")));
        assertTrue(clients.stream().anyMatch(client -> client.getIdentityDocument().equals("x1233215j")));
        assertTrue(clients.stream().anyMatch(client -> client.getIdentityDocument().equals("y9991232z")));
    }
}

