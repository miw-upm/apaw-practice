package es.upm.miw.apaw.domain.services.clothingstore;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.clothingstore.Garment;
import es.upm.miw.apaw.domain.persistenceports.clothingstore.GarmentPersistence;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class GarmentServiceTest {

    @Mock
    private GarmentPersistence garmentPersistence;

    @Mock
    private UserRestClient userRestClient;

    @InjectMocks
    private GarmentService garmentService;

    @Test
    void testCreate() {
        Garment garment = Garment.builder()
                .size("S")
                .price(new BigDecimal("29.99"))
                .onSale(true)
                .build();

        given(garmentPersistence.create(any(Garment.class))).willReturn(
                Garment.builder()
                        .size("S")
                        .price(new BigDecimal("29.99"))
                        .onSale(true)
                        .build()
        );

        Garment created = garmentService.create(garment);
        assertThat(created).isNotNull();
        assertThat(created.getSize()).isEqualTo("S");
        assertThat(created.getPrice()).isEqualByComparingTo("29.99");
        assertThat(created.getOnSale()).isTrue();
    }

    @Test
    void testFindByPriceBetween() {
        given(garmentPersistence.findByPriceBetween(any(BigDecimal.class), any(BigDecimal.class)))
                .willReturn(Stream.of(
                        Garment.builder().price(new BigDecimal("59.99")).build(),
                        Garment.builder().price(new BigDecimal("89.99")).build()
                ));

        var garments = garmentService.findByPriceBetween(
                new BigDecimal("50"), new BigDecimal("100")).toList();

        assertThat(garments).hasSize(2);
        assertThat(garments.get(0).getPrice())
                .isBetween(new BigDecimal("50"), new BigDecimal("100"));
    }

    @Test
    void testSumDistinctPriceByMobile() {
        String mobile = "666000660";
        UUID userId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000");
        UUID firstGarmentId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff7001");
        UUID secondGarmentId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff7002");

        UserDto user = UserDto.builder()
                .id(userId)
                .mobile(mobile)
                .firstName("user0")
                .build();
        given(userRestClient.readByMobile(mobile)).willReturn(user);

        given(garmentPersistence.findByUserId(userId))
                .willReturn(Stream.of(
                        Garment.builder().id(firstGarmentId).price(new BigDecimal("59.99")).build(),
                        Garment.builder().id(firstGarmentId).price(new BigDecimal("59.99")).build(),
                        Garment.builder().id(secondGarmentId).price(new BigDecimal("89.99")).build()
                ));

        BigDecimal total = garmentService.sumDistinctPriceByMobile(mobile);

        assertThat(total).isEqualByComparingTo("149.98");
    }

}

