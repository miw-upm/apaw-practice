package es.upm.miw.apaw.domain.services.warehouse;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.warehouse.Location;
import es.upm.miw.apaw.domain.models.warehouse.MovementOrder;
import es.upm.miw.apaw.domain.persistenceports.warehouse.LocationPersistence;
import es.upm.miw.apaw.domain.persistenceports.warehouse.MovementOrderPersistence;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class MovementOrderService {

    private final MovementOrderPersistence movementOrderPersistence;
    private final LocationPersistence locationPersistence;
    private final UserRestClient userRestClient;

    @Autowired
    public MovementOrderService(MovementOrderPersistence movementOrderPersistence,
                                LocationPersistence locationPersistence,
                                UserRestClient userRestClient) {
        this.movementOrderPersistence = movementOrderPersistence;
        this.locationPersistence = locationPersistence;
        this.userRestClient = userRestClient;
    }

    public MovementOrder create(MovementOrder movementOrder) {

        UUID userId = movementOrder.getUser().getId();
        this.userRestClient.readById(userId);

        movementOrder.setId(UUID.randomUUID());
        movementOrder.setRegistrationDate(LocalDateTime.now());

        return this.movementOrderPersistence.create(movementOrder);
    }

    public Stream<String> findPositionsByUserMobile(String mobile) {

        UserDto userDto = this.userRestClient.readByMobile(mobile);
        if (userDto == null) {
            throw new NotFoundException("User not found with mobile: " + mobile);
        }

        return this.movementOrderPersistence.findAll()
                .filter(order -> {
                    if (order.getUser() == null) {
                        return false;
                    }

                    if (order.getUser().getId() != null && userDto.getId() != null) {
                        return order.getUser().getId().equals(userDto.getId());
                    }

                    return order.getUser().getMobile() != null &&
                            order.getUser().getMobile().equals(userDto.getMobile());
                })

                .flatMap(order ->
                        this.locationPersistence.findAll()
                                .filter(location -> location.getProductItems() != null &&
                                        location.getProductItems().stream()
                                                .anyMatch(productItem ->
                                                        order.getOrderDetails() != null &&
                                                                order.getOrderDetails().stream()
                                                                        .anyMatch(detail ->
                                                                                detail.getProductItem() != null &&
                                                                                        detail.getProductItem().getBarcode().equals(productItem.getBarcode()))
                                                )
                                )
                                .map(Location::getPosition)
                )
                .distinct();
    }

}