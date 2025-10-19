package es.upm.miw.apaw.domain.services.metro;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class TrainStationServiceIT {

    @Autowired
    private TrainStationService trainStationService;

    @MockitoBean
    private UserRestClient userRestClient;

    @Test
    void testReadCapacityByName(){
        assertThat(this.trainStationService
                .readCapacityByName("Central Station"))
                .isEqualTo(500);
    }

    static Stream<Arguments> provideUserMobilesAndExpectedNumCars() {
        return Stream.of(
                Arguments.of("602123456", UUID.fromString("11111111-1111-1111-1111-111111111111"), List.of(5, 8, 10)),
                Arguments.of("608123456", UUID.fromString("22222222-2222-2222-2222-222222222222"), List.of(5, 8, 10)),
                Arguments.of("603654321", UUID.fromString("33333333-3333-3333-3333-333333333333"), List.of(12)),
                Arguments.of("603654321", UUID.fromString("44444444-4444-4444-4444-444444444444"), List.of(10))
        );
    }

    @ParameterizedTest
    @MethodSource("provideUserMobilesAndExpectedNumCars")
    void testFindNumCarsByUserMobile(String mobile, UUID id, List<Integer> expectedNumCars) {
        // given
        BDDMockito.given(this.userRestClient.readByMobile(mobile))
                .willReturn(UserDto.builder()
                        .id(id)
                        .mobile(mobile)
                        .build());

        // when
        Stream<Integer> streamNumCars = this.trainStationService.findNumCarsByUserMobile(mobile);

        // then
        List<Integer> result = streamNumCars.toList();
        assertThat(result).containsAll(expectedNumCars);
    }
}