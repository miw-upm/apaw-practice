package es.upm.miw.apaw.domain.services.clothingstore;

import es.upm.miw.apaw.domain.models.clothingstore.Garment;
import es.upm.miw.apaw.domain.persistenceports.clothingstore.GarmentPersistence;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@org.junit.jupiter.api.extension.ExtendWith(MockitoExtension.class)
class GarmentServiceTest {

    @Mock
    private GarmentPersistence garmentPersistence;

    @InjectMocks
    private GarmentService garmentService;

    @Test
    void testCreate() {
        Garment garment = Garment.builder()
                .size("S")
                .price(new BigDecimal("29.99"))
                .onSale(true)
                .build();

        BDDMockito.given(garmentPersistence.create(any(Garment.class))).willReturn(
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
        BDDMockito.given(garmentPersistence.findByPriceBetween(any(), any()))
                .willReturn(Stream.of(
                        Garment.builder().price(new BigDecimal("59.99")).build(),
                        Garment.builder().price(new BigDecimal("89.99")).build()
                ));

        var garments = garmentService.findByPriceBetween(
                new BigDecimal("50"), new BigDecimal("100")).toList();

        assertThat(garments).hasSize(2);
        assertThat(garments.get(0).getPrice()).isBetween(new BigDecimal("50"), new BigDecimal("100"));
    }

    @Test
    void testSumDistinctPriceByMobile() {
        String mobile = "666000660";
        BigDecimal expectedTotal = new BigDecimal("149.98");

        BDDMockito.given(garmentPersistence.sumDistinctPriceByMobile(eq(mobile)))
                .willReturn(expectedTotal);

        BigDecimal total = garmentService.sumDistinctPriceByMobile(mobile);

        assertThat(total).isNotNull();
        assertThat(total).isEqualByComparingTo(expectedTotal);
        System.out.println(">>> testSumDistinctPriceByMobile(" + mobile + ") = " + total);
    }
}
