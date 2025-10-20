package es.upm.miw.apaw.functionaltests.music;

import es.upm.miw.apaw.adapters.mongodb.music.daos.ArtistRepository;
import es.upm.miw.apaw.adapters.mongodb.music.daos.MusicSeeder;
import es.upm.miw.apaw.adapters.mongodb.music.entities.ArtistEntity;
import es.upm.miw.apaw.adapters.resources.music.ArtistResource;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.music.Artist;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class ArtistResourceFT {

    @Autowired private WebTestClient webTestClient;
    @Autowired private MusicSeeder musicSeeder;
    @Autowired private ArtistRepository artistRepository;

    @MockBean private UserRestClient userRestClient;

    private String existingName;

    @BeforeEach
    void setUp() {
        musicSeeder.deleteAll();
        musicSeeder.seedDatabase();

        this.existingName = artistRepository.findAll().stream()
                .findFirst().map(ArtistEntity::getName).orElse("Tame Impala");

        UserDto mockUser = Mockito.mock(UserDto.class);
        Mockito.when(userRestClient.readById(any(UUID.class))).thenReturn(mockUser);
        Mockito.when(userRestClient.readByMobile(anyString())).thenReturn(mockUser);
    }

    @Test
    void testReadByNameOk() {
        webTestClient.get()
                .uri(ArtistResource.ARTISTS + ArtistResource.ARTIST_ID, this.existingName)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Artist.class)
                .value(a -> {
                    assertThat(a.getName()).isEqualTo(this.existingName);
                    assertThat(a.getUser()).isNotNull();
                });
    }

    @Test
    void testReadByNameNotFound() {
        webTestClient.get()
                .uri(ArtistResource.ARTISTS + ArtistResource.ARTIST_ID, "NO-SUCH-ARTIST")
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void testFindMoodsByUserMobile() throws Exception {
        UserDto mockUser = UserDto.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
                .mobile("666000660")
                .firstName("Thomas")
                .build();
        Mockito.when(userRestClient.readByMobile(anyString())).thenReturn(mockUser);

        // 1) Pedimos el cuerpo como String
        String body = webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(ArtistResource.ARTISTS + "/moods")
                        .queryParam("mobile", "666000660")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();

        // 2) Si body ya es un array JSON, lo parseamos; si es un String con JSON, también
        com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();

        java.util.List<String> moods;
        if (body != null && body.startsWith("[") && body.endsWith("]")) {
            // Es un array JSON “normal”
            moods = mapper.readValue(body, new com.fasterxml.jackson.core.type.TypeReference<java.util.List<String>>() {});
        } else {
            // Por si viniera con comillas exteriores (String JSON que contiene el array)
            String unwrapped = mapper.readValue(body, String.class);
            moods = mapper.readValue(unwrapped, new com.fasterxml.jackson.core.type.TypeReference<java.util.List<String>>() {});
        }

        org.assertj.core.api.Assertions.assertThat(moods).isNotEmpty();
        org.assertj.core.api.Assertions.assertThat(moods).containsExactly("ENERGETIC");
        org.assertj.core.api.Assertions.assertThat(moods).doesNotHaveDuplicates();
    }
}
