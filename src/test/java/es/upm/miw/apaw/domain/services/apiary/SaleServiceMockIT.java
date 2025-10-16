package es.upm.miw.apaw.domain.services.apiary;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.persistenceports.apiary.SalePersistence;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class SaleServiceMockIT {

    @Autowired
    private SaleService saleService;

    @MockitoBean
    private SalePersistence salePersistence;

    private UserDto testUser;

    @Test
    void testAssertIdSaleNotExist_noConflict() {
        BDDMockito.given(this.salePersistence.existIdSale(123)).willReturn(false);

        saleService.assertIdSaleNotExist(123);
    }

    @Test
    void testAssertIdSaleNotExist_conflict() {
        BDDMockito.given(this.salePersistence.existIdSale(456)).willReturn(true);

        assertThatThrownBy(() -> saleService.assertIdSaleNotExist(456))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining("idSale exist: 456");
    }
}