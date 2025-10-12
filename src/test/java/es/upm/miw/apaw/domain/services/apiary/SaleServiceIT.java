package es.upm.miw.apaw.domain.services.apiary;

import es.upm.miw.apaw.adapters.mongodb.apiary.daos.SaleRepository;
import es.upm.miw.apaw.adapters.mongodb.apiary.persistence.SalePersistenceMongodb;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.apiary.Sale;
import es.upm.miw.apaw.domain.services.apiary.SaleService;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class SaleServiceIT {

    @Autowired
    private SaleService saleService;

    @Autowired
    private SalePersistenceMongodb salePersistence;

    @Autowired
    private SaleRepository saleRepository;

    @MockitoBean
    private UserRestClient userRestClient;

    private UserDto testUser;

    @BeforeEach
    void setUp() {
        saleRepository.deleteAll();

        testUser = UserDto.builder()
                .id(UUID.randomUUID())
                .mobile("600123456")
                .firstName("Juan")
                .build();

        // Simular el cliente para UserRestClient
        when(userRestClient.readById(testUser.getId())).thenReturn(testUser);
    }

    @Test
    void testCreateSale() {
        Sale sale = Sale.builder()
                .paymentForm(1)
                .shippingAddress("Calle Test, 123")
                .amount(BigDecimal.valueOf(25.5))
                .client(testUser)
                .build();

        Sale createdSale = saleService.create(sale);

        assertThat(createdSale).isNotNull();
        assertThat(createdSale.getIdSale()).isNotNull();
        assertThat(createdSale.getClient()).isNotNull();
        assertThat(createdSale.getClient().getMobile()).isEqualTo("600123456");

        // Verificar que se creó en la base de datos
        assertThat(salePersistence.existIdSale(createdSale.getIdSale())).isTrue();
    }

    @Test
    void testDeleteSale() {
        Sale sale = Sale.builder()
                .paymentForm(2)
                .shippingAddress("Calle Borrar, 456")
                .amount(BigDecimal.valueOf(50.0))
                .client(testUser)
                .build();

        Sale createdSale = saleService.create(sale);

        assertThat(salePersistence.existIdSale(createdSale.getIdSale())).isTrue();

        saleService.delete(createdSale.getIdSale());

        assertThat(salePersistence.existIdSale(createdSale.getIdSale())).isFalse();
    }
}
