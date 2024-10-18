package es.upm.miw.apaw_practice.domain.models.theme_park;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestConfig
class UserTest {
    private User user1;
    private User user2;
    private UserComposite userGroup;

    @BeforeEach
    void setup() {
        List<UserComponent> users = new ArrayList<>();
        userGroup = new UserComposite(users);

        user1 = new User("U001", "123 Main St", LocalDateTime.now(), true);
        user2 = new User("U002", "456 Elm St", LocalDateTime.now(), false);

        UserLeaf userLeaf1 = new UserLeaf(user1);
        UserLeaf userLeaf2 = new UserLeaf(user2);

        userGroup.add(userLeaf1);
        userGroup.add(userLeaf2);

    }

    @Test
    void testBuilder() {
        User instance = User.builder()
                        .idMembership("POQWRJ32K")
                        .address("C/ Casa,23")
                        .entranceDate(LocalDateTime.of(1995,7,8,12,5,2))
                        .oneYearMembership(false)
                        .build();
        assertEquals("POQWRJ32K", instance.getIdMembership());
        assertEquals("C/ Casa,23", instance.getAddress());
        assertEquals(LocalDateTime.of(1995,7,8,12,5,2), instance.getEntranceDate());
        assertFalse(instance.getOneYearMembership());
    }

    @Test
    void testGroupInfo() {
        userGroup.setAddress("789 Oak St");

        assertEquals("789 Oak St", user1.getAddress());
        assertEquals("789 Oak St", user2.getAddress());
    }


}
