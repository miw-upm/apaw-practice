package es.upm.miw.apaw.domain.services.warehouse;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.warehouse.MovementOrder;
import es.upm.miw.apaw.domain.persistenceports.warehouse.MovementOrderPersistence;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class MovementOrderServiceIT {

    @Autowired
    private MovementOrderService movementOrderService;

    @MockitoBean
    private UserRestClient userRestClient;

    @Autowired
    private MovementOrderPersistence movementOrderPersistence;

    @Test
    void testCreateMovementOrder() {
        UUID mockUserId = UUID.randomUUID();
        BDDMockito.given(this.userRestClient.readById(mockUserId))
                .willReturn(UserDto.builder()
                        .id(mockUserId)
                        .firstName("mockUser")
                        .mobile("123456789")
                        .build());

        MovementOrder movementOrder = MovementOrder.builder()
                .typeOrder("INBOUND")
                .partnerName("Supplier A")
                .partnerAddress("Calle Falsa 123")
                .completedOrder(false)
                .user(UserDto.builder().id(mockUserId).build())
                .build();

        MovementOrder created = this.movementOrderService.create(movementOrder);

        assertThat(created.getId()).isNotNull();
        assertThat(created.getRegistrationDate()).isBeforeOrEqualTo(LocalDateTime.now());
        assertThat(created.getUser().getId()).isEqualTo(mockUserId);
    }

}