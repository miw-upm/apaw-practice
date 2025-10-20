package es.upm.miw.apaw.adapters.mongodb.music.persistence;

import es.upm.miw.apaw.adapters.mongodb.music.daos.MusicSeeder;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
@ActiveProfiles("test")
class ArtistPersistenceMongodbIT {

    @Autowired private ArtistPersistenceMongodb artistPersistenceMongodb;
    @Autowired private MusicSeeder musicSeeder;

    @MockBean private UserRestClient userRestClient;

    @BeforeEach
    void resetDb() {
        musicSeeder.deleteAll();
        musicSeeder.seedDatabase();

        UserDto mockUser = Mockito.mock(UserDto.class);
        Mockito.when(userRestClient.readById(any(UUID.class))).thenReturn(mockUser);
        Mockito.when(userRestClient.readByMobile(anyString())).thenReturn(mockUser);
    }

    @Test
    void testReadByNameOk() {
        var artists = this.artistPersistenceMongodb.readByName("Tame Impala")
                .collect(Collectors.toList());

        assertThat(artists).isNotEmpty();
        var artist = artists.get(0);
        assertThat(artist.getName()).isEqualTo("Tame Impala");
        assertThat(artist.getUser()).isNotNull();
        assertThat(artist.getMonthlyListeners()).isGreaterThan(0);
    }

    @Test
    void testReadByNameEmpty() {
        assertThat(this.artistPersistenceMongodb.readByName("Nonexistent Artist")).isEmpty();
    }

    @Test
    void testFindMoodsByUserMobile() {
        UserDto mockUser = UserDto.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
                .mobile("666000660")
                .firstName("Thomas")
                .build();

        Mockito.when(userRestClient.readByMobile(anyString())).thenReturn(mockUser);

        List<String> moods = this.artistPersistenceMongodb.findMoodsByUserMobile("666000660").toList();

        assertThat(moods).isNotEmpty();
        assertThat(moods).containsExactly("ENERGETIC");
        assertThat(moods).doesNotHaveDuplicates();
    }
}
